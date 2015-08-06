package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kovenliao on 8/6/15.
 */
public class ImageResult {
    private String fullUrl;
    private String thumbUrl;
    private String title;

    public static List<ImageResult> fromJSONArray(JSONArray array) throws JSONException {
        ArrayList<ImageResult> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            results.add(new ImageResult(array.getJSONObject(i)));
        }
        return results;
    }

    public ImageResult(JSONObject json) throws JSONException {
        this.fullUrl = json.getString("url");
        this.thumbUrl = json.getString("tbUrl");
        this.title = json.getString("title");
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
