package com.agt.crm.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class TenantGestaoBean {
    public List<TenantResumo> getTenants() {
        return List.of(
                new TenantResumo("TENANT-001", "Tenant Demonstração", "pt", "Activo"),
                new TenantResumo("TENANT-002", "Tenant Expiração", "en", "Activo"),
                new TenantResumo("TENANT-003", "Tenant Suspenso", "fr", "A rever")
        );
    }

    public static class TenantResumo {
        private final String codigo;
        private final String nome;
        private final String idiomaPadrao;
        private final String estado;

        public TenantResumo(String codigo, String nome, String idiomaPadrao, String estado) {
            this.codigo = codigo;
            this.nome = nome;
            this.idiomaPadrao = idiomaPadrao;
            this.estado = estado;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getNome() {
            return nome;
        }

        public String getIdiomaPadrao() {
            return idiomaPadrao;
        }

        public String getEstado() {
            return estado;
        }
    }
}
