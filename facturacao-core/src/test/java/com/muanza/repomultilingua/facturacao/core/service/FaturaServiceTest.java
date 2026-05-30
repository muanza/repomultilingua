package com.muanza.repomultilingua.facturacao.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.muanza.repomultilingua.common.i18n.InvoiceLabelService;
import com.muanza.repomultilingua.facturacao.core.domain.Cliente;
import com.muanza.repomultilingua.facturacao.core.domain.Empresa;
import com.muanza.repomultilingua.facturacao.core.domain.Fatura;
import com.muanza.repomultilingua.facturacao.core.domain.ItemFatura;

class FaturaServiceTest {
    private final FaturaService service = new FaturaService(new InvoiceLabelService());

    @Test
    void shouldCalculateInvoiceTotalsAndLegends() {
        Empresa empresa = new Empresa();
        empresa.setId(10L);
        Cliente cliente = new Cliente();
        cliente.setEmpresaId(10L);

        ItemFatura item = new ItemFatura();
        item.setDescricao("Licença");
        item.setQuantidade(BigDecimal.valueOf(2));
        item.setPrecoUnitario(BigDecimal.valueOf(100));
        item.setPercentualDesconto(BigDecimal.TEN);
        item.setPercentualImposto(BigDecimal.valueOf(14));

        Fatura fatura = new Fatura();
        fatura.setEmpresaId(10L);
        fatura.setClienteId(100L);
        fatura.setNumeroDocumento("FT 2026/1");
        fatura.setDataEmissao(LocalDate.now());
        fatura.setIdiomaDocumento("en");
        fatura.setItens(Collections.singletonList(item));

        Fatura emitida = service.emitir(empresa, cliente, fatura);

        assertEquals(BigDecimal.valueOf(180.00).setScale(2), emitida.getTotalLiquido());
        assertEquals(BigDecimal.valueOf(25.20).setScale(2), emitida.getTotalImposto());
        assertEquals("Fatura / Invoice", emitida.getLegendas().get("Fatura"));
    }

    @Test
    void shouldRejectTenantMismatch() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        Cliente cliente = new Cliente();
        cliente.setEmpresaId(2L);
        Fatura fatura = new Fatura();
        fatura.setEmpresaId(1L);

        assertThrows(IllegalArgumentException.class, () -> service.emitir(empresa, cliente, fatura));
    }
}
