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
import com.muanza.repomultilingua.crm.api.service.CrmModels.ParceiroDTO;
import com.muanza.repomultilingua.crm.api.service.CrmStore;

@RestController
@RequestMapping("/api/v1/parceiros")
public class ParceiroController {
    private final CrmStore store;

    public ParceiroController(CrmStore store) {
        this.store = store;
    }

    @PostMapping
    public ApiResponse<ParceiroDTO> criar(@RequestBody ParceiroDTO dto) {
        dto.setId(store.getSequence().getAndIncrement());
        store.getParceiros().put(dto.getId(), dto);
        return ApiResponse.sucesso("Parceiro criado.", dto);
    }

    @GetMapping("/{id}")
    public ApiResponse<ParceiroDTO> obter(@PathVariable Long id) {
        return ApiResponse.sucesso("Parceiro encontrado.", store.getParceiros().get(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<ParceiroDTO> actualizar(@PathVariable Long id, @RequestBody ParceiroDTO dto) {
        dto.setId(id);
        store.getParceiros().put(id, dto);
        return ApiResponse.sucesso("Parceiro actualizado.", dto);
    }

    @GetMapping
    public ApiResponse<List<ParceiroDTO>> listar() {
        return ApiResponse.sucesso("Parceiros encontrados.", new ArrayList<>(store.getParceiros().values()));
    }
}

