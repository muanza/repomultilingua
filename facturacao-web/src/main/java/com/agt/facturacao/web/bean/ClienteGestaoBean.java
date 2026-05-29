package com.agt.facturacao.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ClienteGestaoBean {
    public List<ClienteResumo> getClientes() {
        return List.of(
                new ClienteResumo("Cliente Atlântico", "500100200", "atlantic@cliente.ao", "Luanda"),
                new ClienteResumo("Cliente Kwanza", "500100201", "kwanza@cliente.ao", "Benguela"),
                new ClienteResumo("Cliente Horizonte", "500100202", "horizonte@cliente.ao", "Huíla")
        );
    }

    public int getTotalClientes() {
        return getClientes().size();
    }

    public static class ClienteResumo {
        private final String nome;
        private final String nif;
        private final String email;
        private final String localidade;

        public ClienteResumo(String nome, String nif, String email, String localidade) {
            this.nome = nome;
            this.nif = nif;
            this.email = email;
            this.localidade = localidade;
        }

        public String getNome() {
            return nome;
        }

        public String getNif() {
            return nif;
        }

        public String getEmail() {
            return email;
        }

        public String getLocalidade() {
            return localidade;
        }
    }
}
