package com.muanza.repomultilingua.crm.api.service;

import java.time.LocalDate;

public final class CrmModels {
    private CrmModels() {
    }

    public static class ParceiroDTO {
        private Long id;
        private String nome;
        private String email;
        private String telefone;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }
    }

    public static class TenantDTO {
        private Long id;
        private Long parceiroId;
        private Long empresaId;
        private String nomeEmpresa;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getParceiroId() {
            return parceiroId;
        }

        public void setParceiroId(Long parceiroId) {
            this.parceiroId = parceiroId;
        }

        public Long getEmpresaId() {
            return empresaId;
        }

        public void setEmpresaId(Long empresaId) {
            this.empresaId = empresaId;
        }

        public String getNomeEmpresa() {
            return nomeEmpresa;
        }

        public void setNomeEmpresa(String nomeEmpresa) {
            this.nomeEmpresa = nomeEmpresa;
        }
    }

    public static class LicencaDTO {
        private Long id;
        private Long tenantId;
        private LocalDate dataInicio;
        private LocalDate dataExpiracao;
        private Integer numeroUsuarios;
        private String statusLicenca;
        private Integer limiteFacturas;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getTenantId() {
            return tenantId;
        }

        public void setTenantId(Long tenantId) {
            this.tenantId = tenantId;
        }

        public LocalDate getDataInicio() {
            return dataInicio;
        }

        public void setDataInicio(LocalDate dataInicio) {
            this.dataInicio = dataInicio;
        }

        public LocalDate getDataExpiracao() {
            return dataExpiracao;
        }

        public void setDataExpiracao(LocalDate dataExpiracao) {
            this.dataExpiracao = dataExpiracao;
        }

        public Integer getNumeroUsuarios() {
            return numeroUsuarios;
        }

        public void setNumeroUsuarios(Integer numeroUsuarios) {
            this.numeroUsuarios = numeroUsuarios;
        }

        public String getStatusLicenca() {
            return statusLicenca;
        }

        public void setStatusLicenca(String statusLicenca) {
            this.statusLicenca = statusLicenca;
        }

        public Integer getLimiteFacturas() {
            return limiteFacturas;
        }

        public void setLimiteFacturas(Integer limiteFacturas) {
            this.limiteFacturas = limiteFacturas;
        }
    }

    public static class RelatorioUtilizacaoDTO {
        private long totalTenants;
        private long licencasActivas;

        public long getTotalTenants() {
            return totalTenants;
        }

        public void setTotalTenants(long totalTenants) {
            this.totalTenants = totalTenants;
        }

        public long getLicencasActivas() {
            return licencasActivas;
        }

        public void setLicencasActivas(long licencasActivas) {
            this.licencasActivas = licencasActivas;
        }
    }
}

