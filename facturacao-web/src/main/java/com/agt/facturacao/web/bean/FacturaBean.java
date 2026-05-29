package com.agt.facturacao.web.bean;

import com.agt.common.i18n.InvoiceLegendService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

    public List<String> getIdiomasDisponiveis() {
        return List.of("pt", "en", "fr", "zh");
    }

    public List<LinhaFacturaResumo> getFacturasRecentes() {
        return List.of(
                new LinhaFacturaResumo("FT-2026-001", "Cliente Atlântico", LocalDate.now(), new BigDecimal("120000.00"), "EMITIDA"),
                new LinhaFacturaResumo("FT-2026-002", "Cliente Kwanza", LocalDate.now().minusDays(1), new BigDecimal("84500.00"), "PAGA"),
                new LinhaFacturaResumo("FT-2026-003", "Cliente Horizonte", LocalDate.now().minusDays(2), new BigDecimal("63000.00"), "EM COBRANÇA")
        );
    }

    public BigDecimal getTotalDoPeriodo() {
        return getFacturasRecentes().stream()
                .map(LinhaFacturaResumo::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static class LinhaFacturaResumo {
        private final String numero;
        private final String cliente;
        private final LocalDate data;
        private final BigDecimal total;
        private final String estado;

        public LinhaFacturaResumo(String numero, String cliente, LocalDate data, BigDecimal total, String estado) {
            this.numero = numero;
            this.cliente = cliente;
            this.data = data;
            this.total = total;
            this.estado = estado;
        }

        public String getNumero() {
            return numero;
        }

        public String getCliente() {
            return cliente;
        }

        public LocalDate getData() {
            return data;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public String getEstado() {
            return estado;
        }
    }
}
