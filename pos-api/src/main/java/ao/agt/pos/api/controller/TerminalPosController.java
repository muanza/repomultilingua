package ao.agt.pos.api.controller;
import ao.agt.pos.core.service.TerminalPosService;
public class TerminalPosController { private final TerminalPosService service = new TerminalPosService(); public boolean ativo(String status){ return service.ativo(status);} }
