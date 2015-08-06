package activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import adapters.ImageResultsAdapter;
import data.ImageResult;
import data.QueryReq;
import data.SearchFilter;
import handler.ImageSearchClient;
import kl.gridimagesearch.R;


public class SearchActivity extends ActionBarActivity {
    public static final String TAG = "SearchActivity";
    public static final Integer REQUEST_CODE = 200;

    private EditText etQuery;
//    private Button btnSearch;
    private GridView gvResults;

    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter imageResultsAdpt;
    private QueryReq queryReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // for nav bar icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_action_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setupViews();

        queryReq = new QueryReq();
        imageResults = new ArrayList();
        imageResultsAdpt = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(imageResultsAdpt);
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int pageLabel, int totalItemsCount) {
                Log.i(TAG, "onLoadMore, page" + pageLabel);
                if (pageLabel < 9) {
                    queryReq.setPageIndex(pageLabel - 1);
                    doQuery(false);
                } else {
                    Toast.makeText(SearchActivity.this, "reach 8 pages", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                ImageResult result = imageResults.get(position);
                intent.putExtra("url", result.getFullUrl());
                startActivity(intent);
            }
        });
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);
    }

    public void onImageSearch(View v) {
        String query = etQuery.getText().toString();
        Toast.makeText(this, "Search for: " + query, Toast.LENGTH_SHORT).show();

        queryReq.setQuery(query);
        doQuery();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onFilterClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onFilterClick() {
        Intent intent = new Intent(SearchActivity.this, FilterConfigActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    protected void onActivityResult(int requectCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requectCode == REQUEST_CODE) {
            SearchFilter searchFilter = (SearchFilter) data.getSerializableExtra("searchFilter");

            queryReq.updateFromFilter(searchFilter);
            doQuery();
//            Log.d(TAG, "onActivityResult..." + searchFilter);
//            Log.d(TAG, "onActivityResult..." + searchFilter.getImageSize());
        }
    }

    private void doQuery() {
        this.doQuery(true);
    }

    private void doQuery(boolean clear) {
        if (queryReq.getQuery().equals("")) {
            Toast.makeText(SearchActivity.this, "please enter query", Toast.LENGTH_SHORT).show();
            return;
        }
        if (clear) {
            imageResultsAdpt.clear();
        }

        ImageSearchClient.getInstance().query(queryReq, imageResultsAdpt);
    }
}
