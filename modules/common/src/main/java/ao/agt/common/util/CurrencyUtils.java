package ao.agt.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public final class CurrencyUtils {

    private CurrencyUtils() {
    }

    public static BigDecimal normalize(BigDecimal amount) {
        if (amount == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static String format(BigDecimal amount, Locale locale) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale == null ? Locale.ENGLISH : locale);
        return numberFormat.format(normalize(amount));
    }
}
