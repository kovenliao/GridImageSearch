package handler;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import activities.SearchActivity;
import adapters.ImageResultsAdapter;
import data.ImageResult;
import data.QueryReq;

/**
 * Created by kovenliao on 8/6/15.
 */
public class ImageSearchClient {
    public static final String TAG = "ImageSearchClient";

    private static String CLINET_URL = "https://ajax.googleapis.com/ajax/services/search/images?";

    private static ImageSearchClient instance = null;

    private AsyncHttpClient client;

    private QueryReq queryReq;

    public static ImageSearchClient getInstance() {
        if (instance == null) {
            synchronized (ImageSearchClient.class) {
                if (instance == null) {
                    instance = new ImageSearchClient();
                }
            }
        }
        return instance;
    }

    private ImageSearchClient() {
        this.client = new AsyncHttpClient();
    }

    public void query(QueryReq req, final ImageResultsAdapter imageResultsAdpt) {
        this.queryReq = req;

        Log.i(TAG, "Query= " + req.buildQuery());

        client.get(CLINET_URL + req.buildQuery(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i(TAG, response.toString());

                try {
                    JSONArray imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
                    imageResultsAdpt.addAll(ImageResult.fromJSONArray(imageResultsJson));
                    imageResultsAdpt.notifyDataSetChanged();
                    Log.i(TAG, "changed");
                } catch(JSONException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });
    }

}
