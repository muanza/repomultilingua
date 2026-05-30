package com.muanza.repomultilingua.facturacao.core.service;

import com.muanza.repomultilingua.facturacao.core.domain.Cliente;
import com.muanza.repomultilingua.facturacao.core.dto.ClienteDTO;

public class ClienteService {

    public Cliente fromDto(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setEmpresaId(dto.getEmpresaId());
        cliente.setNome(dto.getNome());
        cliente.setNif(dto.getNif());
        cliente.setEmail(dto.getEmail());
        return cliente;
    }

    public ClienteDTO toDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setEmpresaId(cliente.getEmpresaId());
        dto.setNome(cliente.getNome());
        dto.setNif(cliente.getNif());
        dto.setEmail(cliente.getEmail());
        return dto;
    }
}

