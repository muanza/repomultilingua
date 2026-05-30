package com.muanza.repomultilingua.facturacao.api.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.muanza.repomultilingua.common.api.ApiResponse;
import com.muanza.repomultilingua.facturacao.core.domain.Cliente;
import com.muanza.repomultilingua.facturacao.core.domain.Empresa;
import com.muanza.repomultilingua.facturacao.core.domain.Fatura;
import com.muanza.repomultilingua.facturacao.core.dto.FaturaDTO;
import com.muanza.repomultilingua.facturacao.core.service.FaturaService;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {
    private final Map<Long, Fatura> facturaStore;
    private final Map<Long, Cliente> clienteStore;
    private final FaturaService faturaService;
    private final AtomicLong sequenceGenerator;

    public FacturaController(Map<Long, Fatura> facturaStore, Map<Long, Cliente> clienteStore, FaturaService faturaService,
            AtomicLong sequenceGenerator) {
        this.facturaStore = facturaStore;
        this.clienteStore = clienteStore;
        this.faturaService = faturaService;
        this.sequenceGenerator = sequenceGenerator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<FaturaDTO> criar(@RequestBody FaturaDTO dto) {
        dto.setId(sequenceGenerator.getAndIncrement());
        Fatura fatura = faturaService.fromDto(dto);
        facturaStore.put(fatura.getId(), processar(fatura));
        return ApiResponse.sucesso("Factura criada.", faturaService.toDto(facturaStore.get(fatura.getId())));
    }

    @GetMapping("/{id}")
    public ApiResponse<FaturaDTO> obter(@PathVariable Long id) {
        return ApiResponse.sucesso("Factura encontrada.", faturaService.toDto(facturaStore.get(id)));
    }

    @PutMapping("/{id}")
    public ApiResponse<FaturaDTO> actualizar(@PathVariable Long id, @RequestBody FaturaDTO dto) {
        dto.setId(id);
        Fatura actualizada = processar(faturaService.fromDto(dto));
        facturaStore.put(id, actualizada);
        return ApiResponse.sucesso("Factura actualizada.", faturaService.toDto(actualizada));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<FaturaDTO> cancelar(@PathVariable Long id) {
        Fatura fatura = facturaStore.get(id);
        fatura.setStatus("CANCELADA");
        return ApiResponse.sucesso("Factura cancelada.", faturaService.toDto(fatura));
    }

    @GetMapping
    public ApiResponse<List<FaturaDTO>> listar(@RequestParam Long empresaId) {
        List<FaturaDTO> facturas = facturaStore.values().stream()
                .filter(fatura -> empresaId.equals(fatura.getEmpresaId()))
                .map(faturaService::toDto)
                .collect(Collectors.toList());
        return ApiResponse.sucesso("Facturas encontradas.", facturas);
    }

    private Fatura processar(Fatura fatura) {
        Cliente cliente = clienteStore.get(fatura.getClienteId());
        Empresa empresa = new Empresa();
        empresa.setId(fatura.getEmpresaId());
        return faturaService.emitir(empresa, cliente, fatura);
    }
}

