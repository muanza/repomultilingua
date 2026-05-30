package ao.agt.users.model;

import ao.agt.common.model.Audit;
import ao.agt.common.model.Entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User extends Entity {

    private final String username;
    private final String email;
    private String passwordHash;
    private boolean active;
    private final Set<Role> roles = new HashSet<>();
    private final Audit audit;

    public User(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = true;
        this.audit = new Audit(username);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        this.audit.touch(username);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        this.audit.touch(username);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public Audit getAudit() {
        return audit;
    }
}
