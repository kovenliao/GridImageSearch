package activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import data.ColorEnum;
import data.ImageSizeEnum;
import data.ImageTypeEnum;
import data.SearchFilter;
import kl.gridimagesearch.R;


public class FilterConfigActivity extends ActionBarActivity {
    public static final String TAG = "FilterConfigActivity";
    private Spinner spImageSize;
    private Spinner spColor;
    private Spinner spImageType;
    private EditText etSite;

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_config);

        setupViews();
        spImageSize.setAdapter(new ArrayAdapter<String>(
                FilterConfigActivity.this,
                android.R.layout.simple_spinner_item,
                ImageSizeEnum.getValues()
        ));
        spColor.setAdapter(new ArrayAdapter<String>(
                FilterConfigActivity.this,
                android.R.layout.simple_spinner_item,
                ColorEnum.getValues()
        ));
        spImageType.setAdapter(new ArrayAdapter<String>(
                FilterConfigActivity.this,
                android.R.layout.simple_spinner_item,
                ImageTypeEnum.getValues()
        ));
    }

    private void setupViews() {
        spImageSize = (Spinner) findViewById(R.id.spImageSize);
        spColor = (Spinner) findViewById(R.id.spColor);
        spImageType = (Spinner) findViewById(R.id.spImageType);
        etSite = (EditText) findViewById(R.id.etSite);

        btnSave = (Button) findViewById(R.id.btnSave);
    }

    public void onSaveFilter(View v) {
        SearchFilter data = new SearchFilter();
//        Log.i(TAG, "onSaveFilter: " + spImageSize.getSelectedItemPosition());
//        Log.i(TAG, "onSaveFilter: " + ImageSizeEnum.getByOrdinal(spImageSize.getSelectedItemPosition()));

        data.setImageSize(ImageSizeEnum.getByOrdinal(spImageSize.getSelectedItemPosition()));
        data.setColor(ColorEnum.getByOrdinal(spColor.getSelectedItemPosition()));
        data.setImageType(ImageTypeEnum.getByOrdinal(spImageType.getSelectedItemPosition()));
        data.setSite(etSite.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("searchFilter", data);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter_config, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
