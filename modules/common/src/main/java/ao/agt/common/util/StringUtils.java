package ao.agt.common.util;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }

    public static String requireNonBlank(String value, String message) {
        if (isBlank(value)) {
            throw new IllegalArgumentException(message);
        }
        return value.trim();
    }
}
