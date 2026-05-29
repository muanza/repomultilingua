package com.agt.crm.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CrmDashboardBean {
    public String getTitulo() {
        return "CRM Licenciamento Online";
    }
}
