package data;

/**
 * Created by kovenliao on 8/6/15.
 */
public enum ColorEnum {
    black,
    blue,
    brown,
    gray,
    green;

    public static String[] getValues() {
        return new String[] {
            "black", "blue", "brown", "gray", "green"
        };
    }

    public static ColorEnum getByOrdinal(int ord) {
        for (ColorEnum m : ColorEnum.values()) {
            if (m.ordinal() == ord) {
                return m;
            }
        }
        return null;
    }
}
