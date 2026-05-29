package com.agt.facturacao.web.bean;

import com.agt.common.backup.IncrementalBackupService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class FacturacaoAdminBean {
    private final IncrementalBackupService backupService = new IncrementalBackupService();

    public FacturacaoAdminBean() {
        backupService.schedule("TENANT-001", "s3://agt-backups");
        backupService.schedule("TENANT-002", "s3://agt-backups");
    }

    public List<ConfiguracaoItem> getConfiguracoes() {
        return List.of(
                new ConfiguracaoItem("Facturas por dia", "1000"),
                new ConfiguracaoItem("Backup incremental", "Activo"),
                new ConfiguracaoItem("Destino cloud", "s3://agt-backups"),
                new ConfiguracaoItem("Monitorização", "Painel em tempo real")
        );
    }

    public List<String> getBackupsAgendados() {
        return backupService.listScheduledExecutions();
    }

    public List<String> getAlertas() {
        return List.of(
                "Validar renovação do tenant TENANT-002 nos próximos 15 dias.",
                "Rever políticas de retenção para backups incrementais semanais."
        );
    }

    public static class ConfiguracaoItem {
        private final String parametro;
        private final String valor;

        public ConfiguracaoItem(String parametro, String valor) {
            this.parametro = parametro;
            this.valor = valor;
        }

        public String getParametro() {
            return parametro;
        }

        public String getValor() {
            return valor;
        }
    }
}
