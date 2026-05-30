package com.muanza.repomultilingua.facturacao.core.repository;

import java.util.Collection;

import com.muanza.repomultilingua.facturacao.core.domain.Cliente;

public interface ClienteRepository {
    Cliente save(Cliente cliente);

    Collection<Cliente> findByEmpresaId(Long empresaId);
}

