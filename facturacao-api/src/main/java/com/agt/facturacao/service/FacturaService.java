package com.agt.facturacao.service;

import com.agt.common.exception.BusinessException;
import com.agt.facturacao.dto.FacturaDTO;
import com.agt.facturacao.entity.FacturaEntity;
import com.agt.facturacao.repository.FacturaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class FacturaService {
    private static final int LIMITE_DIARIO = 1000;
    private static final String ESTADO_EMITIDA = "EMITIDA";

    @Inject
    FacturaRepository repository;

    @Inject
    CrmLicenseClient crmLicenseClient;

    public FacturaDTO criar(FacturaDTO dto, long tenantId, int facturasJaProcessadasHoje) {
        validarPedido(dto);
        if (facturasJaProcessadasHoje >= LIMITE_DIARIO) {
            throw new BusinessException("Limite diário de facturas atingido");
        }
        return persistir(dto, tenantId, dto.getTenantCodigo(), dto.getIdioma(), facturasJaProcessadasHoje + 1, LIMITE_DIARIO);
    }

    public FacturaDTO criar(String tenantCodigo, FacturaDTO dto) {
        validarPedido(dto);
        CrmLicenseClient.TenantLicenseProfile profile = crmLicenseClient.requireTenantProfile(tenantCodigo);
        int facturasHoje = repository.countByTenantAndDate(profile.getTenantId(), LocalDate.now());
        if (facturasHoje >= profile.getLimiteFacturasDia()) {
            throw new BusinessException("Limite diário de facturas do plano " + profile.getPlano() + " atingido");
        }
        String idioma = dto.getIdioma() == null || dto.getIdioma().isBlank() ? profile.getIdiomaPadrao() : dto.getIdioma().trim();
        return persistir(dto, profile.getTenantId(), profile.getTenantCodigo(), idioma, facturasHoje + 1, profile.getLimiteFacturasDia());
    }

    public List<FacturaDTO> listar(String tenantCodigo) {
        CrmLicenseClient.TenantLicenseProfile profile = crmLicenseClient.requireTenantProfile(tenantCodigo);
        return repository.findByTenantId(profile.getTenantId()).stream()
                .map(entity -> toDto(entity, profile.getTenantCodigo()))
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<String, Object> resumo(String tenantCodigo) {
        CrmLicenseClient.TenantLicenseProfile profile = crmLicenseClient.requireTenantProfile(tenantCodigo);
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("tenantCodigo", profile.getTenantCodigo());
        resumo.put("tenantNome", profile.getTenantNome());
        resumo.put("plano", profile.getPlano());
        resumo.put("limiteFacturasDia", profile.getLimiteFacturasDia());
        resumo.put("facturasEmitidasHoje", repository.countByTenantAndDate(profile.getTenantId(), LocalDate.now()));
        resumo.put("totalFacturado", repository.totalFacturado(profile.getTenantId()));
        return resumo;
    }

    void setRepository(FacturaRepository repository) {
        this.repository = repository;
    }

    void setCrmLicenseClient(CrmLicenseClient crmLicenseClient) {
        this.crmLicenseClient = crmLicenseClient;
    }

    private FacturaDTO persistir(FacturaDTO dto, long tenantId, String tenantCodigo, String idioma,
                                 int ordemDoDia, int limiteDiario) {
        FacturaEntity entity = new FacturaEntity();
        entity.setTenantId(tenantId);
        entity.setClienteId(dto.getClienteId() == null ? 1L : dto.getClienteId());
        entity.setClienteNome(dto.getClienteNome().trim());
        entity.setNumero(resolveNumero(dto.getNumero(), tenantCodigo, ordemDoDia, limiteDiario));
        entity.setIdioma(idioma);
        entity.setEstado(resolveValue(dto.getEstado(), ESTADO_EMITIDA));
        entity.setSubtotal(resolveSubtotal(dto));
        entity.setTotalImposto(resolveTotalImposto(dto, entity.getSubtotal()));
        entity.setTotal(resolveTotal(dto, entity.getSubtotal(), entity.getTotalImposto()));
        entity.setDataEmissao(dto.getDataEmissao() == null ? LocalDate.now() : dto.getDataEmissao());
        repository.save(entity);
        return toDto(entity, tenantCodigo);
    }

    private void validarPedido(FacturaDTO dto) {
        if (dto == null) {
            throw new BusinessException("Factura inválida");
        }
        if (dto.getClienteNome() == null || dto.getClienteNome().isBlank()) {
            throw new BusinessException("Cliente é obrigatório");
        }
        BigDecimal total = dto.getTotal();
        BigDecimal subtotal = dto.getSubtotal();
        if ((total == null || total.signum() <= 0) && (subtotal == null || subtotal.signum() <= 0)) {
            throw new BusinessException("Total da factura deve ser positivo");
        }
    }

    private String resolveNumero(String numero, String tenantCodigo, int ordemDoDia, int limiteDiario) {
        if (numero != null && !numero.isBlank()) {
            return numero.trim();
        }
        return tenantCodigo + "-" + LocalDate.now() + "-" + ordemDoDia + "-" + limiteDiario;
    }

    private String resolveValue(String value, String defaultValue) {
        return value == null || value.isBlank() ? defaultValue : value.trim();
    }

    private BigDecimal resolveSubtotal(FacturaDTO dto) {
        if (dto.getSubtotal() != null && dto.getSubtotal().signum() > 0) {
            return dto.getSubtotal();
        }
        if (dto.getTotal() != null && dto.getTotal().signum() > 0) {
            return dto.getTotal();
        }
        throw new BusinessException("Subtotal inválido");
    }

    private BigDecimal resolveTotalImposto(FacturaDTO dto, BigDecimal subtotal) {
        if (dto.getTotalImposto() != null && dto.getTotalImposto().signum() >= 0) {
            return dto.getTotalImposto();
        }
        if (dto.getTotal() != null) {
            BigDecimal imposto = dto.getTotal().subtract(subtotal);
            return imposto.signum() >= 0 ? imposto : BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal resolveTotal(FacturaDTO dto, BigDecimal subtotal, BigDecimal totalImposto) {
        if (dto.getTotal() != null && dto.getTotal().signum() > 0) {
            return dto.getTotal();
        }
        return subtotal.add(totalImposto);
    }

    private FacturaDTO toDto(FacturaEntity entity, String tenantCodigo) {
        FacturaDTO dto = new FacturaDTO();
        dto.setTenantCodigo(tenantCodigo);
        dto.setClienteId(entity.getClienteId());
        dto.setClienteNome(entity.getClienteNome());
        dto.setNumero(entity.getNumero());
        dto.setDataEmissao(entity.getDataEmissao());
        dto.setIdioma(entity.getIdioma());
        dto.setEstado(entity.getEstado());
        dto.setSubtotal(entity.getSubtotal());
        dto.setTotalImposto(entity.getTotalImposto());
        dto.setTotal(entity.getTotal());
        return dto;
    }
}
