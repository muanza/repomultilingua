package com.agt.common.backup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IncrementalBackupService {
    private final Map<String, BackupExecution> scheduledExecutions = new LinkedHashMap<>();

    public synchronized String schedule(String tenantCode, String cloudDestination) {
        String normalizedTenantCode = requireValue(tenantCode, "tenantCode");
        String normalizedDestination = normalizeDestination(cloudDestination, normalizedTenantCode);
        String executionId = "BKP-" + normalizedTenantCode + "-" + (scheduledExecutions.size() + 1);

        BackupExecution execution = new BackupExecution(
                executionId,
                normalizedTenantCode,
                normalizedDestination,
                LocalDateTime.now(),
                "AGENDADO"
        );
        scheduledExecutions.put(executionId, execution);
        return execution.summary();
    }

    public synchronized List<String> listScheduledExecutions() {
        List<String> executions = new ArrayList<>();
        for (BackupExecution execution : scheduledExecutions.values()) {
            executions.add(execution.summary());
        }
        return executions;
    }

    private String requireValue(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " é obrigatório");
        }
        return value.trim();
    }

    private String normalizeDestination(String cloudDestination, String tenantCode) {
        String normalized = requireValue(cloudDestination, "cloudDestination");
        String suffix = normalized.endsWith("/") ? "" : "/";
        return normalized + suffix + tenantCode.toLowerCase();
    }

    private static final class BackupExecution {
        private final String id;
        private final String tenantCode;
        private final String destination;
        private final LocalDateTime scheduledAt;
        private final String status;

        private BackupExecution(String id, String tenantCode, String destination, LocalDateTime scheduledAt, String status) {
            this.id = id;
            this.tenantCode = tenantCode;
            this.destination = destination;
            this.scheduledAt = scheduledAt;
            this.status = status;
        }

        private String summary() {
            return id + " | " + tenantCode + " | " + status + " | " + destination + " | " + scheduledAt;
        }
    }
}
