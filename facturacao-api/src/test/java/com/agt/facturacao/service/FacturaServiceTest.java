package com.agt.facturacao.service;

import com.agt.common.exception.BusinessException;
import com.agt.facturacao.dto.FacturaDTO;
import com.agt.facturacao.repository.FacturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FacturaServiceTest {

    private FacturaService service;

    @BeforeEach
    void setUp() {
        service = new FacturaService();
        service.setRepository(new FacturaRepository());
        service.setCrmLicenseClient(new CrmLicenseClient());
    }

    @Test
    void deveBloquearQuandoAtingeLimiteDiario() {
        FacturaDTO dto = new FacturaDTO();
        dto.setNumero("FT-1");
        dto.setClienteNome("Cliente Teste");
        dto.setIdioma("pt");
        dto.setTotal(BigDecimal.TEN);

        assertThrows(BusinessException.class, () -> service.criar(dto, 1L, 1000));
    }

    @Test
    void deveCriarFacturaComDadosDoTenant() {
        FacturaDTO dto = new FacturaDTO();
        dto.setClienteNome("Cliente Teste");
        dto.setTotal(new BigDecimal("150.00"));

        FacturaDTO criada = service.criar("TENANT-001", dto);

        assertEquals("TENANT-001", criada.getTenantCodigo());
        assertEquals("pt", criada.getIdioma());
        assertEquals("EMITIDA", criada.getEstado());
        assertEquals(new BigDecimal("150.00"), criada.getTotal());
    }
}
