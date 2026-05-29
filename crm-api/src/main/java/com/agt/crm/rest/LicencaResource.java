package com.agt.crm.rest;

import com.agt.crm.dto.LicencaDTO;
import com.agt.crm.service.LicencaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/licencas")
@Produces(MediaType.APPLICATION_JSON)
public class LicencaResource {

    @Inject
    LicencaService licencaService;

    @GET
    public List<LicencaDTO> listar() {
        return licencaService.listar();
    }

    @GET
    @Path("/{tenantCodigo}/valida")
    public LicencaDTO valida(@PathParam("tenantCodigo") String tenantCodigo) {
        return licencaService.consultar(tenantCodigo);
    }

    @GET
    @Path("/resumo")
    public Map<String, Object> resumo() {
        return licencaService.resumo();
    }
}
