INSERT INTO users (username, email, password_hash, active)
VALUES (
    'admin',
    'admin@localhost',
    '2bb80d537b1da3e38bd30361aa855686bde0baef0fda57fd3f64b5f5f3b9d768',
    TRUE
)
ON CONFLICT (username) DO NOTHING;

INSERT INTO roles (name) VALUES ('ADMIN') ON CONFLICT (name) DO NOTHING;
INSERT INTO permissions (code) VALUES ('invoice:approve') ON CONFLICT (code) DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name = 'ADMIN'
WHERE u.username = 'admin'
ON CONFLICT DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.code = 'invoice:approve'
WHERE r.name = 'ADMIN'
ON CONFLICT DO NOTHING;
