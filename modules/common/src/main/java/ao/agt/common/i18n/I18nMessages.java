package ao.agt.common.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18nMessages {

    private static final String BUNDLE_BASE_NAME = "messages";

    public String get(String key, Locale locale, Object... params) {
        Locale effectiveLocale = locale == null ? Locale.ENGLISH : locale;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, effectiveLocale);
            String pattern = bundle.getString(key);
            return MessageFormat.format(pattern, params);
        } catch (MissingResourceException ex) {
            return key;
        }
    }
}
