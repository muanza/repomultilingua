package com.muanza.repomultilingua.crm.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muanza.repomultilingua.common.api.ApiResponse;
import com.muanza.repomultilingua.crm.api.service.CrmModels.RelatorioUtilizacaoDTO;
import com.muanza.repomultilingua.crm.api.service.CrmStore;
import com.muanza.repomultilingua.crm.api.service.IntegracaoFacturacaoService;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioUtilizacaoController {
    private final CrmStore store;
    private final IntegracaoFacturacaoService integracaoFacturacaoService;

    public RelatorioUtilizacaoController(CrmStore store, IntegracaoFacturacaoService integracaoFacturacaoService) {
        this.store = store;
        this.integracaoFacturacaoService = integracaoFacturacaoService;
    }

    @GetMapping("/utilizacao")
    public ApiResponse<RelatorioUtilizacaoDTO> utilizacao() {
        RelatorioUtilizacaoDTO dto = new RelatorioUtilizacaoDTO();
        dto.setTotalTenants(store.getTenants().size());
        dto.setLicencasActivas(store.getLicencas().values().stream()
                .filter(licenca -> "ACTIVA".equalsIgnoreCase(licenca.getStatusLicenca()))
                .count());
        return ApiResponse.sucesso("Relatório e integração disponíveis em " + integracaoFacturacaoService.endpointLicenciamento(), dto);
    }
}

