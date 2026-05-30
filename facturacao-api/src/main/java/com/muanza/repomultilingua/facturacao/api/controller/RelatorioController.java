package com.muanza.repomultilingua.facturacao.api.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muanza.repomultilingua.common.api.ApiResponse;
import com.muanza.repomultilingua.facturacao.core.domain.Fatura;
import com.muanza.repomultilingua.facturacao.core.dto.RelatorioVendasDTO;
import com.muanza.repomultilingua.facturacao.core.service.RelatorioService;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioController {
    private final Map<Long, Fatura> facturaStore;
    private final RelatorioService relatorioService;

    public RelatorioController(Map<Long, Fatura> facturaStore, RelatorioService relatorioService) {
        this.facturaStore = facturaStore;
        this.relatorioService = relatorioService;
    }

    @GetMapping("/vendas")
    public ApiResponse<RelatorioVendasDTO> vendas(@RequestParam Long empresaId) {
        return ApiResponse.sucesso("Relatório gerado.",
                relatorioService.gerar(empresaId, facturaStore.values().stream()
                        .filter(fatura -> empresaId.equals(fatura.getEmpresaId()))
                        .collect(Collectors.toList())));
    }
}

