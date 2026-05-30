package com.muanza.repomultilingua.crm.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muanza.repomultilingua.common.api.ApiResponse;
import com.muanza.repomultilingua.crm.api.service.CrmModels.TenantDTO;
import com.muanza.repomultilingua.crm.api.service.CrmStore;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {
    private final CrmStore store;

    public TenantController(CrmStore store) {
        this.store = store;
    }

    @PostMapping
    public ApiResponse<TenantDTO> criar(@RequestBody TenantDTO dto) {
        dto.setId(store.getSequence().getAndIncrement());
        store.getTenants().put(dto.getId(), dto);
        return ApiResponse.sucesso("Tenant criado.", dto);
    }

    @GetMapping("/{id}")
    public ApiResponse<TenantDTO> obter(@PathVariable Long id) {
        return ApiResponse.sucesso("Tenant encontrado.", store.getTenants().get(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<TenantDTO> actualizar(@PathVariable Long id, @RequestBody TenantDTO dto) {
        dto.setId(id);
        store.getTenants().put(id, dto);
        return ApiResponse.sucesso("Tenant actualizado.", dto);
    }

    @GetMapping
    public ApiResponse<List<TenantDTO>> listar() {
        return ApiResponse.sucesso("Tenants encontrados.", new ArrayList<>(store.getTenants().values()));
    }
}

