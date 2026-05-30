package ao.agt.users.service;

import ao.agt.users.model.Permission;
import ao.agt.users.model.Role;
import ao.agt.users.model.User;
import ao.agt.users.repository.InMemoryUserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    @Test
    void shouldAuthenticateAndAuthorizeUser() {
        UserService userService = new UserService(new InMemoryUserRepository());
        User user = userService.register("admin", "admin@local", "secret");

        Role role = new Role("ADMIN");
        role.addPermission(new Permission("invoice:approve"));
        user.addRole(role);

        User authenticated = userService.authenticate("admin", "secret");

        assertTrue(userService.authorize(authenticated, "invoice:approve"));
        assertFalse(userService.authorize(authenticated, "invoice:delete"));
    }
}
