package com.muanza.repomultilingua.crm.web.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TenantBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getTitulo() {
        return "CRM AGT";
    }

    public List<String> getMenus() {
        return Arrays.asList("dashboard", "parceiros", "tenants", "licencas", "relatorios");
    }
}

