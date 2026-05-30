package ao.agt.users.repository;

import ao.agt.users.model.User;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);
}
