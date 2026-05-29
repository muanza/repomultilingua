package com.agt.facturacao.repository;

import com.agt.facturacao.entity.FacturaEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class FacturaRepository {
    private final List<FacturaEntity> store = new ArrayList<>();

    public FacturaEntity save(FacturaEntity facturaEntity) {
        store.add(facturaEntity);
        return facturaEntity;
    }

    public List<FacturaEntity> findAll() {
        return Collections.unmodifiableList(store);
    }
}
