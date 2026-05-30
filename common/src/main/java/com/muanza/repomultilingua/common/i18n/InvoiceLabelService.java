package com.muanza.repomultilingua.common.i18n;

import java.util.LinkedHashMap;
import java.util.Map;

public class InvoiceLabelService {

    public Map<String, String> invoiceLabels(Language language) {
        Map<String, String> labels = new LinkedHashMap<>();
        labels.put("Fatura", translate(language, "Fatura", "Invoice", "Facture", "发票"));
        labels.put("Cliente", translate(language, "Cliente", "Customer", "Client", "客户"));
        labels.put("Produto", translate(language, "Produto", "Product", "Produit", "产品"));
        labels.put("Quantidade", translate(language, "Quantidade", "Quantity", "Quantité", "数量"));
        labels.put("Preço Unitário", translate(language, "Preço Unitário", "Unit Price", "Prix Unitaire", "单价"));
        labels.put("Imposto", translate(language, "Imposto", "Tax", "Taxe", "税费"));
        labels.put("Total", translate(language, "Total", "Total", "Total", "合计"));
        return labels;
    }

    private String translate(Language language, String pt, String en, String fr, String zh) {
        switch (language) {
            case EN:
                return pt + " / " + en;
            case FR:
                return pt + " / " + fr;
            case ZH:
                return pt + " / " + zh;
            case PT:
            default:
                return pt + " / " + pt;
        }
    }
}

