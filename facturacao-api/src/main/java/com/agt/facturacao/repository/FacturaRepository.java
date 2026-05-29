package com.agt.facturacao.repository;

import com.agt.facturacao.entity.FacturaEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.math.BigDecimal;

@ApplicationScoped
public class FacturaRepository {
    private final AtomicLong sequence = new AtomicLong(1);
    private final List<FacturaEntity> store = new ArrayList<>();

    public synchronized FacturaEntity save(FacturaEntity facturaEntity) {
        if (facturaEntity.getId() == null) {
            facturaEntity.setId(sequence.getAndIncrement());
        }
        store.add(facturaEntity);
        return facturaEntity;
    }

    public synchronized List<FacturaEntity> findAll() {
        return Collections.unmodifiableList(store);
    }

    public synchronized List<FacturaEntity> findByTenantId(Long tenantId) {
        return store.stream()
                .filter(factura -> factura.getTenantId().equals(tenantId))
                .sorted(Comparator.comparing(FacturaEntity::getDataEmissao).reversed()
                        .thenComparing(FacturaEntity::getNumero))
                .collect(Collectors.toUnmodifiableList());
    }

    public synchronized int countByTenantAndDate(Long tenantId, LocalDate dataEmissao) {
        return (int) store.stream()
                .filter(factura -> factura.getTenantId().equals(tenantId))
                .filter(factura -> factura.getDataEmissao().equals(dataEmissao))
                .count();
    }

    public synchronized BigDecimal totalFacturado(Long tenantId) {
        return store.stream()
                .filter(factura -> factura.getTenantId().equals(tenantId))
                .map(FacturaEntity::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
