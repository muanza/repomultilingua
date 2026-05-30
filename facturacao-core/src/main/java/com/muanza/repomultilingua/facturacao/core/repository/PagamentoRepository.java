package com.muanza.repomultilingua.facturacao.core.repository;

import java.util.Collection;

import com.muanza.repomultilingua.facturacao.core.domain.Pagamento;

public interface PagamentoRepository {
    Pagamento save(Pagamento pagamento);

    Collection<Pagamento> findByFaturaId(Long faturaId);
}

