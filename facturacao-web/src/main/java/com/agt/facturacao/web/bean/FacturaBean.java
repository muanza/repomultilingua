package com.agt.facturacao.web.bean;

import com.agt.common.i18n.InvoiceLegendService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class FacturaBean {
    private String idioma = "pt";

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Map<String, String> getLegendas() {
        return new InvoiceLegendService().forLanguage(idioma);
    }
}
