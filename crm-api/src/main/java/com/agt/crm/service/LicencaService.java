package com.agt.crm.service;

import com.agt.crm.entity.LicencaEntity;
import com.agt.crm.repository.LicencaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;

@ApplicationScoped
public class LicencaService {
    @Inject
    LicencaRepository licencaRepository;

    public boolean licencaValida(String tenantCodigo) {
        LicencaEntity entity = licencaRepository.findByTenantCodigo(tenantCodigo);
        return entity != null && "ATIVA".equalsIgnoreCase(entity.getEstado()) && !entity.getValidade().isBefore(LocalDate.now());
    }
}
