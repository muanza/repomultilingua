package com.muanza.repomultilingua.facturacao.api.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muanza.repomultilingua.common.api.ApiResponse;
import com.muanza.repomultilingua.facturacao.core.domain.Cliente;
import com.muanza.repomultilingua.facturacao.core.dto.ClienteDTO;
import com.muanza.repomultilingua.facturacao.core.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final Map<Long, Cliente> clienteStore;
    private final ClienteService clienteService;
    private final AtomicLong sequenceGenerator;

    public ClienteController(Map<Long, Cliente> clienteStore, ClienteService clienteService, AtomicLong sequenceGenerator) {
        this.clienteStore = clienteStore;
        this.clienteService = clienteService;
        this.sequenceGenerator = sequenceGenerator;
    }

    @PostMapping
    public ApiResponse<ClienteDTO> criar(@RequestBody ClienteDTO dto) {
        dto.setId(sequenceGenerator.getAndIncrement());
        Cliente cliente = clienteService.fromDto(dto);
        clienteStore.put(cliente.getId(), cliente);
        return ApiResponse.sucesso("Cliente criado.", clienteService.toDto(cliente));
    }

    @GetMapping
    public ApiResponse<List<ClienteDTO>> listar(@RequestParam Long empresaId) {
        List<ClienteDTO> clientes = clienteStore.values().stream()
                .filter(cliente -> empresaId.equals(cliente.getEmpresaId()))
                .map(clienteService::toDto)
                .collect(Collectors.toList());
        return ApiResponse.sucesso("Clientes encontrados.", clientes);
    }
}

