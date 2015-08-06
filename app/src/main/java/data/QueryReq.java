package data;

/**
 * Created by kovenliao on 8/6/15.
 */
public class QueryReq {

    private static final String VERSION = "1.0";

    private static final Integer PAGE_SIZE = 8;

    private static final String AND = "&";

    private String query;

    private Integer pageIndex;

    private ImageSizeEnum imageSize;

    private ColorEnum color;

    private ImageTypeEnum imageType;

    private String site;

    public QueryReq() {
        this.query = "";
        this.pageIndex = 0;

    }

    public String buildQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("v=" + VERSION);
        sb.append(AND + "rsz=" + String.valueOf(PAGE_SIZE));
        sb.append(AND + "q=" + query);
        sb.append(AND + "start=" + String.valueOf(pageIndex * PAGE_SIZE));

        if (imageSize != null) {
            sb.append(AND + "imgsz=" + imageSize.name());
        }
        if (color != null) {
            sb.append(AND + "imgc=" + color.name());
        }
        if (imageType != null) {
            sb.append(AND + "imgtype=" + imageType.name());
        }
        if (site != null && !site.equals("")) {
            sb.append(AND + "as_sitesearch=" + site);
        }

        return sb.toString();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void updateFromFilter(SearchFilter filter) {
        this.imageSize = filter.getImageSize();
        this.color = filter.getColor();
        this.imageType = filter.getImageType();
        this.site = filter.getSite();
    }
}
