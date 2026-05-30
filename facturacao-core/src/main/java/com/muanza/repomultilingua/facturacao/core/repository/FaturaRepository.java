package com.muanza.repomultilingua.facturacao.core.repository;

import java.util.Collection;

import com.muanza.repomultilingua.facturacao.core.domain.Fatura;

public interface FaturaRepository {
    Fatura save(Fatura fatura);

    Fatura findById(Long id);

    Collection<Fatura> findByEmpresaId(Long empresaId);
}

