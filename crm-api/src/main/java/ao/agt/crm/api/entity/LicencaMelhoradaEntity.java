package ao.agt.crm.api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LicencaMelhoradaEntity {
    public Long id;
    public String codigoLicenca;
    public Long tenantId;
    public Long parceiroId;
    public String tipoLicenca;
    public LocalDate dataInicio;
    public LocalDate dataExpiracao;
    public String statusLicenca;
    public BigDecimal precoMensal;
}
