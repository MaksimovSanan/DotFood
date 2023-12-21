INSERT INTO roles(role_id, role_name)
VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1);