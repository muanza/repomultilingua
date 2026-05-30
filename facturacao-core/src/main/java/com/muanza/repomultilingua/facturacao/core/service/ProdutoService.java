package com.muanza.repomultilingua.facturacao.core.service;

import java.math.BigDecimal;

import com.muanza.repomultilingua.facturacao.core.domain.Produto;

public class ProdutoService {
    public Produto criarPadrao(Long empresaId, Long id, String codigo, String descricao, BigDecimal preco, BigDecimal imposto) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setEmpresaId(empresaId);
        produto.setCodigo(codigo);
        produto.setDescricao(descricao);
        produto.setPrecoVenda(preco);
        produto.setPercentualImposto(imposto);
        return produto;
    }
}

