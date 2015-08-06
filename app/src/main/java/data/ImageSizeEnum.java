package data;

/**
 * Created by kovenliao on 8/6/15.
 */
public enum ImageSizeEnum {
    //    imgsz=small|medium|large|xlarge
    small,
    medium,
    large,
    xlarge;

    public static String[] getValues() {
        return new String[] {
                "small", "medium", "large", "xlarge"
        };
    }

    public static ImageSizeEnum getByOrdinal(int ord) {
        for (ImageSizeEnum m : ImageSizeEnum.values()) {
            if (m.ordinal() == ord) {
                return m;
            }
        }
        return null;
    }
}