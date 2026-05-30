package ao.agt.common.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class LegendaFaturaService {
    public String traduzir(String chave, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        return bundle.containsKey(chave) ? bundle.getString(chave) : chave;
    }
}
