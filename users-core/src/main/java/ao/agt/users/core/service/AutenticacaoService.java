package ao.agt.users.core.service;

public class AutenticacaoService {
    public boolean validarSenhaForte(String senha) {
        return senha != null && senha.length() >= 8
                && senha.chars().anyMatch(Character::isUpperCase)
                && senha.chars().anyMatch(Character::isLowerCase)
                && senha.chars().anyMatch(Character::isDigit);
    }
}
