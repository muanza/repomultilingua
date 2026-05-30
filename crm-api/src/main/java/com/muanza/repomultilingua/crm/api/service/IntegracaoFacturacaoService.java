package com.muanza.repomultilingua.crm.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IntegracaoFacturacaoService {
    @Value("${facturacao.api.base-url:http://localhost:8080}")
    private String facturacaoBaseUrl;

    public String endpointLicenciamento() {
        return facturacaoBaseUrl + "/api/v1/facturas";
    }
}

