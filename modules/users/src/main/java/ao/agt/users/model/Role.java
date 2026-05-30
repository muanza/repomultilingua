package ao.agt.users.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Role {

    private final String name;
    private final Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }

    public Set<Permission> getPermissions() {
        return Collections.unmodifiableSet(permissions);
    }
}
