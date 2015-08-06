package data;

import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by kovenliao on 8/6/15.
 */
public class SearchFilter implements Serializable {
    private ImageSizeEnum imageSize;

    private ColorEnum color;

    private ImageTypeEnum imageType;

    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public ImageSizeEnum getImageSize() {
        return imageSize;
    }

    public void setImageSize(ImageSizeEnum imageSize) {
        this.imageSize = imageSize;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public ImageTypeEnum getImageType() {
        return imageType;
    }

    public void setImageType(ImageTypeEnum imageType) {
        this.imageType = imageType;
    }
}
