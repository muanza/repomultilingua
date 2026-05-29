package com.agt.facturacao.rest;

import com.agt.facturacao.dto.FacturaDTO;
import com.agt.facturacao.service.CrmLicenseClient;
import com.agt.facturacao.service.FacturaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/facturas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaResource {

    @Inject
    FacturaService facturaService;

    @Inject
    CrmLicenseClient crmLicenseClient;

    @POST
    public FacturaDTO criar(@HeaderParam("X-Tenant") String tenantCodigo, FacturaDTO dto) {
        if (!crmLicenseClient.licencaActiva(tenantCodigo)) {
            throw new WebApplicationException("Licença inválida", 403);
        }
        return facturaService.criar(dto, 1L, 0);
    }
}
