package spec45as.bitskins.objects;

/**
 * Created by spec45as on 9/28/2016.
 */
public class SearchEnums {

    public enum SEARCH_SORTING_CHOICE {
        SEARCH_SORTING_BY_CREATION_TIME,
        SEARCH_SORTING_BY_PRICE,
        SEARCH_SORTING_BY_WEAR_VALUE
    }

    public enum SEARCH_SORTING_DIRECTION {
        SEARCH_SORTING_ASC,
        SEARCH_SORTING_DESC
    }

    public enum SEARCH_OPTIONAL_CHOICE {
        ONLY_WITHOUT,
        ANY,
        ONLY_HAVING
    }

    public static String getEnumValue(SEARCH_SORTING_CHOICE direction) {
        switch (direction) {
            case SEARCH_SORTING_BY_PRICE:
                return "price";
            case SEARCH_SORTING_BY_CREATION_TIME:
                return "created_at";
            case SEARCH_SORTING_BY_WEAR_VALUE:
                return "wear_value";
            default:
                return "price";
        }
    }
    public static String getEnumValue(SEARCH_SORTING_DIRECTION direction) {
        switch (direction) {
            case SEARCH_SORTING_ASC:
                return "asc";
            case SEARCH_SORTING_DESC:
                return "desc";
            default:
                return "asc";
        }
    }

    public static String getEnumValue(SEARCH_OPTIONAL_CHOICE choice) {
        switch (choice) {
            case ONLY_WITHOUT:
                return "-1";
            case ANY:
                return "0";
            case ONLY_HAVING:
                return "1";
            default:
                return "0";
        }
    }
}
