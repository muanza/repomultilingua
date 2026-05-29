package com.agt.common.backup;

import java.time.LocalDateTime;

public class IncrementalBackupService {
    public String schedule(String tenantCode, String cloudDestination) {
        return "Backup incremental agendado para " + tenantCode + " em " + cloudDestination + " @ " + LocalDateTime.now();
    }
}
