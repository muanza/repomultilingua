package com.muanza.repomultilingua.facturacao.api.controller;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muanza.repomultilingua.common.api.ApiResponse;

@RestController
@RequestMapping("/api/v1/backup")
public class BackupController {
    private final Map<Long, String> backupStore;

    public BackupController(Map<Long, String> backupStore) {
        this.backupStore = backupStore;
    }

    @PostMapping("/trigger")
    public ApiResponse<Map<String, String>> trigger(@RequestParam Long empresaId) {
        backupStore.put(empresaId, "COMPLETO:" + OffsetDateTime.now());
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "COMPLETO");
        response.put("cloud", "aws-s3://agt-backup/tenant-" + empresaId);
        response.put("timestamp", OffsetDateTime.now().toString());
        return ApiResponse.sucesso("Backup incremental agendado.", response);
    }

    @GetMapping("/status")
    public ApiResponse<Map<String, String>> status(@RequestParam Long empresaId) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("empresaId", String.valueOf(empresaId));
        response.put("ultimoBackup", backupStore.getOrDefault(empresaId, "PENDENTE"));
        response.put("sincronizacaoCloud", "ATIVA");
        return ApiResponse.sucesso("Estado do backup obtido.", response);
    }
}

