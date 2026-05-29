package com.agt.facturacao.service;

import com.agt.common.exception.BusinessException;
import com.agt.facturacao.dto.FacturaDTO;
import com.agt.facturacao.repository.FacturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FacturaServiceTest {

    private FacturaService service;

    @BeforeEach
    void setUp() throws Exception {
        service = new FacturaService();
        FacturaRepository repo = new FacturaRepository();
        var field = FacturaService.class.getDeclaredField("repository");
        field.setAccessible(true);
        field.set(service, repo);
    }

    @Test
    void deveBloquearQuandoAtingeLimiteDiario() {
        FacturaDTO dto = new FacturaDTO();
        dto.setNumero("FT-1");
        dto.setIdioma("pt");
        dto.setTotal(BigDecimal.TEN);

        assertThrows(BusinessException.class, () -> service.criar(dto, 1L, 1000));
    }
}
