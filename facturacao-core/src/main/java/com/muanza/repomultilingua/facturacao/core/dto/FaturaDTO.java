package com.muanza.repomultilingua.facturacao.core.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FaturaDTO {
    private Long id;
    private Long empresaId;
    private Long clienteId;
    private String numeroDocumento;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private String idiomaDocumento;
    private String status;
    private BigDecimal totalLiquido;
    private BigDecimal totalImposto;
    private BigDecimal valorTotal;
    private List<ItemFaturaDTO> itens = new ArrayList<>();
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

    public String getIdiomaDocumento() {
        return idiomaDocumento;
    }

    public void setIdiomaDocumento(String idiomaDocumento) {
        this.idiomaDocumento = idiomaDocumento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<ItemFaturaDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemFaturaDTO> itens) {
        this.itens = itens;
    }

    public Map<String, String> getLegendas() {
        return legendas;
    }

    public void setLegendas(Map<String, String> legendas) {
        this.legendas = legendas;
    }
}

