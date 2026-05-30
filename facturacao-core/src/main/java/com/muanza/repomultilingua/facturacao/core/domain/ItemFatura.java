package com.muanza.repomultilingua.facturacao.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ItemFatura {
    private Long produtoId;
    private String descricao;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal percentualDesconto = BigDecimal.ZERO;
    private BigDecimal percentualImposto = BigDecimal.ZERO;

    public BigDecimal subtotal() {
        return precoUnitario.multiply(quantidade);
    }

    public BigDecimal desconto() {
        return subtotal().multiply(percentualDesconto).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal liquido() {
        return subtotal().subtract(desconto()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal imposto() {
        return liquido().multiply(percentualImposto).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

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

