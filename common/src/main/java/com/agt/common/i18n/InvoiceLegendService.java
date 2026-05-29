package com.agt.common.i18n;

import java.util.Map;

public class InvoiceLegendService {
    private static final Map<String, Map<String, String>> LEGENDS = Map.of(
            "pt", Map.of("cliente", "Cliente", "numero", "Número", "data", "Data", "total", "Total"),
            "en", Map.of("cliente", "Customer", "numero", "Number", "data", "Date", "total", "Total"),
            "fr", Map.of("cliente", "Client", "numero", "Numéro", "data", "Date", "total", "Total"),
            "zh", Map.of("cliente", "客户", "numero", "号码", "data", "日期", "total", "总计")
    );

    public Map<String, String> forLanguage(String language) {
        return LEGENDS.getOrDefault(language, LEGENDS.get("pt"));
    }
}
