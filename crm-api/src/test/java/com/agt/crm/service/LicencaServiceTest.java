package com.agt.crm.service;

import com.agt.crm.entity.LicencaEntity;
import com.agt.crm.repository.LicencaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LicencaServiceTest {

    private LicencaService service;

    @BeforeEach
    void setUp() {
        service = new LicencaService();
        LicencaRepository repository = new LicencaRepository();
        LicencaEntity entity = new LicencaEntity();
        entity.setTenantCodigo("TENANT-TESTE");
        entity.setTenantId(99L);
        entity.setPlano("AGT-PRO");
        entity.setEstado("ATIVA");
        entity.setValidade(LocalDate.now().plusDays(10));
        entity.setLimiteFacturasDia(500);
        entity.setChave("LIC-TESTE");
        repository.save(entity);
        service.setLicencaRepository(repository);
    }

    @Test
    void deveValidarLicencaAtiva() {
        assertTrue(service.licencaValida("TENANT-TESTE"));
    }

    @Test
    void deveRetornarResumoDaLicenca() {
        var dto = service.consultar("TENANT-TESTE");

        assertEquals("AGT-PRO", dto.getPlano());
        assertEquals(500, dto.getLimiteFacturasDia());
        assertTrue(dto.isValida());
    }
}
