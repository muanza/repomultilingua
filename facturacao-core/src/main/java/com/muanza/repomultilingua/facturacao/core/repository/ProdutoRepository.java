package com.muanza.repomultilingua.facturacao.core.repository;

import java.util.Collection;

import com.muanza.repomultilingua.facturacao.core.domain.Produto;

public interface ProdutoRepository {
    Produto save(Produto produto);

    Collection<Produto> findByEmpresaId(Long empresaId);
}

