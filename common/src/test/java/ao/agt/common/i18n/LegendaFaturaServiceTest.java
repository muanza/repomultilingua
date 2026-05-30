package ao.agt.common.i18n;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Locale;
import org.junit.jupiter.api.Test;

class LegendaFaturaServiceTest {

    private final LegendaFaturaService service = new LegendaFaturaService();

    @Test
    void deveTraduzirLegendaEmMultiplosIdiomas() {
        assertNotEquals("fatura.numero", service.traduzir("fatura.numero", new Locale("pt")));
        assertNotEquals("fatura.numero", service.traduzir("fatura.numero", Locale.ENGLISH));
        assertNotEquals("fatura.numero", service.traduzir("fatura.numero", Locale.FRENCH));
        assertNotEquals("fatura.numero", service.traduzir("fatura.numero", Locale.CHINESE));
    }
}
