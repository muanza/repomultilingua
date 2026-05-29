package com.agt.facturacao.rest;

import com.agt.facturacao.dto.FacturaDTO;
import com.agt.facturacao.service.FacturaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/facturas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaResource {

    @Inject
    FacturaService facturaService;

    @POST
    public FacturaDTO criar(@HeaderParam("X-Tenant") String tenantCodigo, FacturaDTO dto) {
        if (tenantCodigo == null || tenantCodigo.isBlank()) {
            throw new WebApplicationException("Cabeçalho X-Tenant é obrigatório", 400);
        }
        return facturaService.criar(tenantCodigo, dto);
    }

    @GET
    public List<FacturaDTO> listar(@HeaderParam("X-Tenant") String tenantCodigo) {
        if (tenantCodigo == null || tenantCodigo.isBlank()) {
            throw new WebApplicationException("Cabeçalho X-Tenant é obrigatório", 400);
        }
        return facturaService.listar(tenantCodigo);
    }

    @GET
    @Path("/resumo")
    public Map<String, Object> resumo(@HeaderParam("X-Tenant") String tenantCodigo) {
        if (tenantCodigo == null || tenantCodigo.isBlank()) {
            throw new WebApplicationException("Cabeçalho X-Tenant é obrigatório", 400);
        }
        return facturaService.resumo(tenantCodigo);
    }
}
