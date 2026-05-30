package ao.agt.common.i18n;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class I18nMessagesTest {

    @Test
    void shouldResolveInvoiceTitleInDifferentLanguages() {
        I18nMessages i18n = new I18nMessages();

        assertEquals("Fatura", i18n.get("invoice.title", Locale.forLanguageTag("pt")));
        assertEquals("Invoice", i18n.get("invoice.title", Locale.ENGLISH));
        assertEquals("Facture", i18n.get("invoice.title", Locale.FRENCH));
        assertEquals("发票", i18n.get("invoice.title", Locale.SIMPLIFIED_CHINESE));
    }
}
