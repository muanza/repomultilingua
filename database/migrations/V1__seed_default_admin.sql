INSERT INTO users (username, email, active)
VALUES ('admin', 'admin@localhost', TRUE)
ON CONFLICT (username) DO NOTHING;
