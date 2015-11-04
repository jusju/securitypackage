INSERT INTO users(username,password,enabled)
VALUES ('inka','123456', true);
INSERT INTO users(username,password,enabled)
VALUES ('jukka','123457', true);

INSERT INTO user_roles (username, role)
VALUES ('inka', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('inka', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('jukka', 'ROLE_USER');