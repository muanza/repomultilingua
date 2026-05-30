package ao.agt.users.api.controller;
import ao.agt.users.core.service.AutenticacaoService;
public class AutenticacaoController { private final AutenticacaoService auth = new AutenticacaoService(); public boolean validar(String senha){ return auth.validarSenhaForte(senha);} }
