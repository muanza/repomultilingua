package com.agt.facturacao.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class RelatorioVendasBean {
    public List<VendaResumo> getResumoDiario() {
        return List.of(
                new VendaResumo(LocalDate.now(), 12, new BigDecimal("420000.00")),
                new VendaResumo(LocalDate.now().minusDays(1), 9, new BigDecimal("315000.00")),
                new VendaResumo(LocalDate.now().minusDays(2), 14, new BigDecimal("508500.00"))
        );
    }

    public BigDecimal getTotalPeriodo() {
        return getResumoDiario().stream()
                .map(VendaResumo::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static class VendaResumo {
        private final LocalDate data;
        private final int totalFacturas;
        private final BigDecimal valorTotal;

        public VendaResumo(LocalDate data, int totalFacturas, BigDecimal valorTotal) {
            this.data = data;
            this.totalFacturas = totalFacturas;
            this.valorTotal = valorTotal;
        }

        public LocalDate getData() {
            return data;
        }

        public int getTotalFacturas() {
            return totalFacturas;
        }

        public BigDecimal getValorTotal() {
            return valorTotal;
        }
    }
}
