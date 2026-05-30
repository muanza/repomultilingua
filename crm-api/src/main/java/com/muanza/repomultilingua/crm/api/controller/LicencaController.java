package com.muanza.repomultilingua.crm.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muanza.repomultilingua.common.api.ApiResponse;
import com.muanza.repomultilingua.crm.api.service.CrmModels.LicencaDTO;
import com.muanza.repomultilingua.crm.api.service.CrmStore;

@RestController
@RequestMapping("/api/v1/licencas")
public class LicencaController {
    private final CrmStore store;

    public LicencaController(CrmStore store) {
        this.store = store;
    }

    @GetMapping
    public ApiResponse<List<LicencaDTO>> listar() {
        return ApiResponse.sucesso("Licenças encontradas.", new ArrayList<>(store.getLicencas().values()));
    }

    @PostMapping
    public ApiResponse<LicencaDTO> activar(@RequestBody LicencaDTO dto) {
        dto.setId(store.getSequence().getAndIncrement());
        if (dto.getStatusLicenca() == null) {
            dto.setStatusLicenca("ACTIVA");
        }
        store.getLicencas().put(dto.getId(), dto);
        return ApiResponse.sucesso("Licença activada.", dto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<LicencaDTO> desactivar(@PathVariable Long id) {
        LicencaDTO dto = store.getLicencas().get(id);
        dto.setStatusLicenca("INACTIVA");
        return ApiResponse.sucesso("Licença desactivada.", dto);
    }
}

