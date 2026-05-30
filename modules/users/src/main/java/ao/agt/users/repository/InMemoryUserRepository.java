package ao.agt.users.repository;

import ao.agt.users.model.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
