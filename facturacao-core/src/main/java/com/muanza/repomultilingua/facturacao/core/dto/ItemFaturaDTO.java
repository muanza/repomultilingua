package com.muanza.repomultilingua.facturacao.core.dto;

import java.math.BigDecimal;

public class ItemFaturaDTO {
    private Long produtoId;
    private String descricao;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal percentualDesconto;
    private BigDecimal percentualImposto;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(BigDecimal percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public BigDecimal getPercentualImposto() {
        return percentualImposto;
    }

    public void setPercentualImposto(BigDecimal percentualImposto) {
        this.percentualImposto = percentualImposto;
    }
}

