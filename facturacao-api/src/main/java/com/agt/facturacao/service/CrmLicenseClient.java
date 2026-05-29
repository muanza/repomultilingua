package com.agt.facturacao.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CrmLicenseClient {
    public boolean licencaActiva(String tenantCodigo) {
        return tenantCodigo != null && !tenantCodigo.isBlank();
    }
}
