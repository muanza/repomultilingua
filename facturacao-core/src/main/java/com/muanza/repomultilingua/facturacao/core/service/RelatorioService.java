package com.muanza.repomultilingua.facturacao.core.service;

import java.math.BigDecimal;
import java.util.Collection;

import com.muanza.repomultilingua.facturacao.core.domain.Fatura;
import com.muanza.repomultilingua.facturacao.core.dto.RelatorioVendasDTO;

public class RelatorioService {

    public RelatorioVendasDTO gerar(Long empresaId, Collection<Fatura> faturas) {
        RelatorioVendasDTO dto = new RelatorioVendasDTO();
        dto.setEmpresaId(empresaId);
        dto.setTotalFacturas(faturas.size());
        dto.setValorAcumulado(faturas.stream()
                .map(Fatura::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return dto;
    }
}

