package com.muanza.repomultilingua.facturacao.web.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class FaturaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<String> getPaginas() {
        return Arrays.asList("dashboard", "facturas", "clientes", "produtos", "relatorios", "backup");
    }

    public String getTitulo() {
        return "Facturação AGT Multi-Tenant";
    }
}

