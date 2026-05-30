package ao.agt.users.api.controller;

import ao.agt.users.api.dto.UtilizadorDTO;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorController {
    private final List<UtilizadorDTO> utilizadores = new ArrayList<>();

    public UtilizadorDTO criar(UtilizadorDTO dto) {
        utilizadores.add(dto);
        return dto;
    }

    public List<UtilizadorDTO> listar() {
        return List.copyOf(utilizadores);
    }
}
