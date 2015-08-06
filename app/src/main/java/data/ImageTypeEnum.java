package data;

/**
 * Created by kovenliao on 8/6/15.
 */
public enum ImageTypeEnum {
    face,
    photo,
    clipart,
    lineart;

    public static String[] getValues() {
        return new String[] {
                "face", "photo", "clipart", "lineart"
        };
    }

    public static ImageTypeEnum getByOrdinal(int ord) {
        for (ImageTypeEnum m : ImageTypeEnum.values()) {
            if (m.ordinal() == ord) {
                return m;
            }
        }
        return null;
    }
}
