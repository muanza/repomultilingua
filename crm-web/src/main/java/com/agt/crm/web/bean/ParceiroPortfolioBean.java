package com.agt.crm.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ParceiroPortfolioBean {
    public List<OfertaParceiro> getOfertas() {
        return List.of(
                new OfertaParceiro("AGT-PRO", "Suite completa de facturação e CRM", "Activo em 18 tenants"),
                new OfertaParceiro("AGT-START", "Licenciamento base para PME", "Activo em 7 tenants"),
                new OfertaParceiro("AGT-LITE", "Catálogo simplificado de entrada", "Em campanha comercial")
        );
    }

    public static class OfertaParceiro {
        private final String nome;
        private final String descricao;
        private final String cobertura;

        public OfertaParceiro(String nome, String descricao, String cobertura) {
            this.nome = nome;
            this.descricao = descricao;
            this.cobertura = cobertura;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getCobertura() {
            return cobertura;
        }
    }
}
