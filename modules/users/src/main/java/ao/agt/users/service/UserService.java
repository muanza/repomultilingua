package ao.agt.users.service;

import ao.agt.common.exception.BusinessException;
import ao.agt.common.exception.NotFoundException;
import ao.agt.common.util.StringUtils;
import ao.agt.users.model.Permission;
import ao.agt.users.model.Role;
import ao.agt.users.model.User;
import ao.agt.users.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String email, String rawPassword) {
        String resolvedUsername = StringUtils.requireNonBlank(username, "Username is required");
        String resolvedEmail = StringUtils.requireNonBlank(email, "Email is required");
        String password = StringUtils.requireNonBlank(rawPassword, "Password is required");
        userRepository.findByUsername(resolvedUsername)
                .ifPresent(u -> {
                    throw new BusinessException("User already exists");
                });
        User user = new User(resolvedUsername, resolvedEmail, hash(password));
        return userRepository.save(user);
    }

    public User authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (!user.isActive()) {
            throw new BusinessException("User is inactive");
        }
        if (!user.getPasswordHash().equals(hash(rawPassword))) {
            throw new BusinessException("Invalid credentials");
        }
        return user;
    }

    public boolean authorize(User user, String permissionCode) {
        return user.getRoles().stream()
                .map(Role::getPermissions)
                .flatMap(java.util.Set::stream)
                .map(Permission::getCode)
                .anyMatch(permissionCode::equals);
    }

    private String hash(String rawValue) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawValue.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 not available", ex);
        }
    }
}
