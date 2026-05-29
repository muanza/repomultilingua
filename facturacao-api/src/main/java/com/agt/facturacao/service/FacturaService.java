package com.agt.facturacao.service;

import com.agt.common.exception.BusinessException;
import com.agt.facturacao.dto.FacturaDTO;
import com.agt.facturacao.entity.FacturaEntity;
import com.agt.facturacao.repository.FacturaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;

@ApplicationScoped
public class FacturaService {
    private static final int LIMITE_DIARIO = 1000;

    @Inject
    FacturaRepository repository;

    public FacturaDTO criar(FacturaDTO dto, long tenantId, int facturasJaProcessadasHoje) {
        if (facturasJaProcessadasHoje >= LIMITE_DIARIO) {
            throw new BusinessException("Limite diário de facturas atingido");
        }
        FacturaEntity entity = new FacturaEntity();
        entity.setNumero(dto.getNumero());
        entity.setIdioma(dto.getIdioma());
        entity.setTotal(dto.getTotal());
        entity.setTenantId(tenantId);
        entity.setDataEmissao(LocalDate.now());
        repository.save(entity);
        return dto;
    }
}
