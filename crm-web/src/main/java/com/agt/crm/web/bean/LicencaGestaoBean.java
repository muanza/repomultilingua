package com.agt.crm.web.bean;

import com.agt.crm.dto.LicencaDTO;
import com.agt.crm.service.LicencaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class LicencaGestaoBean {
    @Inject
    LicencaService licencaService;

    public List<LicencaDTO> getLicencas() {
        return licencaService.listar();
    }
}
