package com.agt.crm.service;

import com.agt.crm.dto.LicencaDTO;
import com.agt.crm.entity.LicencaEntity;
import com.agt.crm.repository.LicencaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class LicencaService {
    @Inject
    LicencaRepository licencaRepository;

    public boolean licencaValida(String tenantCodigo) {
        LicencaEntity entity = licencaRepository.findByTenantCodigo(tenantCodigo);
        return entity != null && "ATIVA".equalsIgnoreCase(entity.getEstado()) && !entity.getValidade().isBefore(LocalDate.now());
    }

    public LicencaDTO consultar(String tenantCodigo) {
        LicencaEntity entity = licencaRepository.findByTenantCodigo(tenantCodigo);
        if (entity == null) {
            LicencaDTO dto = new LicencaDTO();
            dto.setTenantCodigo(tenantCodigo);
            dto.setEstado("INEXISTENTE");
            dto.setValida(false);
            return dto;
        }
        return toDto(entity);
    }

    public List<LicencaDTO> listar() {
        return licencaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalLicencas", licencaRepository.findAll().size());
        resumo.put("licencasValidas", licencaRepository.countAtivas());
        resumo.put("aExpirarEm30Dias", licencaRepository.countExpiringWithinDays(30));
        return resumo;
    }

    void setLicencaRepository(LicencaRepository licencaRepository) {
        this.licencaRepository = licencaRepository;
    }

    private LicencaDTO toDto(LicencaEntity entity) {
        LicencaDTO dto = new LicencaDTO();
        dto.setTenantCodigo(entity.getTenantCodigo());
        dto.setPlano(entity.getPlano());
        dto.setEstado(entity.getEstado());
        dto.setValidade(entity.getValidade());
        dto.setLimiteFacturasDia(entity.getLimiteFacturasDia());
        dto.setValida(licencaValida(entity.getTenantCodigo()));
        dto.setChaveMascarada(mask(entity.getChave()));
        return dto;
    }

    private String mask(String chave) {
        if (chave == null || chave.length() < 4) {
            return "****";
        }
        return chave.substring(0, 4) + "-****";
    }
}
