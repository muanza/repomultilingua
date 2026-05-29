package com.agt.crm.service;

import com.agt.crm.entity.LicencaEntity;
import com.agt.crm.repository.LicencaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LicencaServiceTest {

    private LicencaService service;

    @BeforeEach
    void setUp() throws Exception {
        service = new LicencaService();
        LicencaRepository repository = new LicencaRepository();
        LicencaEntity entity = new LicencaEntity();
        entity.setEstado("ATIVA");
        entity.setValidade(LocalDate.now().plusDays(10));
        repository.save("TENANT-001", entity);

        var field = LicencaService.class.getDeclaredField("licencaRepository");
        field.setAccessible(true);
        field.set(service, repository);
    }

    @Test
    void deveValidarLicencaAtiva() {
        assertTrue(service.licencaValida("TENANT-001"));
    }
}
