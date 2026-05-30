package com.muanza.repomultilingua.facturacao.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Fatura {
    private Long id;
    private Long empresaId;
    private Long clienteId;
    private String numeroDocumento;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private String status = "EMITIDA";
    private String idiomaDocumento = "pt";
    private List<ItemFatura> itens = new ArrayList<>();
    private BigDecimal totalLiquido = BigDecimal.ZERO;
    private BigDecimal totalImposto = BigDecimal.ZERO;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private Map<String, String> legendas = new LinkedHashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdiomaDocumento() {
        return idiomaDocumento;
    }

    public void setIdiomaDocumento(String idiomaDocumento) {
        this.idiomaDocumento = idiomaDocumento;
    }

    public List<ItemFatura> getItens() {
        return itens;
    }

    public void setItens(List<ItemFatura> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotalLiquido() {
        return totalLiquido;
    }

    public void setTotalLiquido(BigDecimal totalLiquido) {
        this.totalLiquido = totalLiquido;
    }

    public BigDecimal getTotalImposto() {
        return totalImposto;
    }

    public void setTotalImposto(BigDecimal totalImposto) {
        this.totalImposto = totalImposto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Map<String, String> getLegendas() {
        return legendas;
    }

    public void setLegendas(Map<String, String> legendas) {
        this.legendas = legendas;
    }
}

