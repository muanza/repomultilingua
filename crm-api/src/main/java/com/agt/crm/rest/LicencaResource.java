package com.agt.crm.rest;

import com.agt.crm.dto.LicencaDTO;
import com.agt.crm.service.LicencaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/licencas")
@Produces(MediaType.APPLICATION_JSON)
public class LicencaResource {

    @Inject
    LicencaService licencaService;

    @GET
    @Path("/{tenantCodigo}/valida")
    public LicencaDTO valida(@PathParam("tenantCodigo") String tenantCodigo) {
        LicencaDTO dto = new LicencaDTO();
        dto.setTenantCodigo(tenantCodigo);
        dto.setEstado(licencaService.licencaValida(tenantCodigo) ? "ATIVA" : "INVALIDA");
        return dto;
    }
}
