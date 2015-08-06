package adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import data.ImageResult;
import kl.gridimagesearch.R;

/**
 * Created by kovenliao on 8/6/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

    private static class ViewHolder {
        TextView tvTitle;
        ImageView ivImage;
    }

    public ImageResultsAdapter(Context context, List<ImageResult> images) {
        super(context, android.R.layout.simple_list_item_1, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageInfo = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.ivImage =  (ImageView) convertView.findViewById(R.id.ivImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(Html.fromHtml(imageInfo.getTitle()));
        Picasso.with(getContext()).load(imageInfo.getThumbUrl()).into(viewHolder.ivImage);
        return convertView;
    }
}
