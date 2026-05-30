package ao.agt.users.core.entity;

import java.time.LocalDateTime;

public class UtilizadorEntity {
    public Long id;
    public Long empresaId;
    public String nomeCompleto;
    public String email;
    public String username;
    public String passwordHash;
    public boolean twoFaEnabled;
    public int tentativasFalhas;
    public LocalDateTime ultimoLogin;
}
