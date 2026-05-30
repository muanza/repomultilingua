package ao.agt.crm.api.controller;

import ao.agt.crm.api.dto.LicencaMelhoradaDTO;
import java.util.ArrayList;
import java.util.List;

public class LicencaMelhoradaController {
    private final List<LicencaMelhoradaDTO> licencas = new ArrayList<>();

    public LicencaMelhoradaDTO criar(LicencaMelhoradaDTO dto) {
        licencas.add(dto);
        return dto;
    }

    public List<LicencaMelhoradaDTO> listar() {
        return List.copyOf(licencas);
    }
}
