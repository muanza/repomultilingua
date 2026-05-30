package com.muanza.repomultilingua.facturacao.core.service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import com.muanza.repomultilingua.common.i18n.InvoiceLabelService;
import com.muanza.repomultilingua.common.i18n.Language;
import com.muanza.repomultilingua.facturacao.core.domain.Cliente;
import com.muanza.repomultilingua.facturacao.core.domain.Empresa;
import com.muanza.repomultilingua.facturacao.core.domain.Fatura;
import com.muanza.repomultilingua.facturacao.core.domain.ItemFatura;
import com.muanza.repomultilingua.facturacao.core.dto.FaturaDTO;
import com.muanza.repomultilingua.facturacao.core.dto.ItemFaturaDTO;

public class FaturaService {
    private final InvoiceLabelService invoiceLabelService;

    public FaturaService(InvoiceLabelService invoiceLabelService) {
        this.invoiceLabelService = invoiceLabelService;
    }

    public Fatura emitir(Empresa empresa, Cliente cliente, Fatura fatura) {
        if (!empresa.getId().equals(fatura.getEmpresaId()) || !empresa.getId().equals(cliente.getEmpresaId())) {
            throw new IllegalArgumentException("Tenant inválido para emissão de factura.");
        }
        BigDecimal totalLiquido = fatura.getItens().stream().map(ItemFatura::liquido).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalImposto = fatura.getItens().stream().map(ItemFatura::imposto).reduce(BigDecimal.ZERO, BigDecimal::add);
        fatura.setTotalLiquido(totalLiquido);
        fatura.setTotalImposto(totalImposto);
        fatura.setValorTotal(totalLiquido.add(totalImposto));
        fatura.setLegendas(invoiceLabelService.invoiceLabels(Language.fromCode(fatura.getIdiomaDocumento())));
        return fatura;
    }

    public FaturaDTO toDto(Fatura fatura) {
        FaturaDTO dto = new FaturaDTO();
        dto.setId(fatura.getId());
        dto.setEmpresaId(fatura.getEmpresaId());
        dto.setClienteId(fatura.getClienteId());
        dto.setNumeroDocumento(fatura.getNumeroDocumento());
        dto.setDataEmissao(fatura.getDataEmissao());
        dto.setDataVencimento(fatura.getDataVencimento());
        dto.setIdiomaDocumento(fatura.getIdiomaDocumento());
        dto.setStatus(fatura.getStatus());
        dto.setTotalLiquido(fatura.getTotalLiquido());
        dto.setTotalImposto(fatura.getTotalImposto());
        dto.setValorTotal(fatura.getValorTotal());
        dto.setLegendas(fatura.getLegendas());
        dto.setItens(fatura.getItens().stream().map(this::toItemDto).collect(Collectors.toList()));
        return dto;
    }

    public Fatura fromDto(FaturaDTO dto) {
        Fatura fatura = new Fatura();
        fatura.setId(dto.getId());
        fatura.setEmpresaId(dto.getEmpresaId());
        fatura.setClienteId(dto.getClienteId());
        fatura.setNumeroDocumento(dto.getNumeroDocumento());
        fatura.setDataEmissao(dto.getDataEmissao());
        fatura.setDataVencimento(dto.getDataVencimento());
        fatura.setIdiomaDocumento(dto.getIdiomaDocumento());
        fatura.setStatus(dto.getStatus());
        fatura.setItens(dto.getItens().stream().map(this::fromItemDto).collect(Collectors.toList()));
        return fatura;
    }

    private ItemFaturaDTO toItemDto(ItemFatura item) {
        ItemFaturaDTO dto = new ItemFaturaDTO();
        dto.setProdutoId(item.getProdutoId());
        dto.setDescricao(item.getDescricao());
        dto.setQuantidade(item.getQuantidade());
        dto.setPrecoUnitario(item.getPrecoUnitario());
        dto.setPercentualDesconto(item.getPercentualDesconto());
        dto.setPercentualImposto(item.getPercentualImposto());
        return dto;
    }

    private ItemFatura fromItemDto(ItemFaturaDTO dto) {
        ItemFatura item = new ItemFatura();
        item.setProdutoId(dto.getProdutoId());
        item.setDescricao(dto.getDescricao());
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(dto.getPrecoUnitario());
        item.setPercentualDesconto(dto.getPercentualDesconto());
        item.setPercentualImposto(dto.getPercentualImposto());
        return item;
    }
}

