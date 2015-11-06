INSERT INTO users(username,password,enabled)
VALUES ('inka','$2a$10$YzdijZUJhEIVpbTG9qijB.SSQ478fJpgbTbYxBDeQ9o4JMm6.ZdHy', true);
INSERT INTO users(username,password,enabled)
VALUES ('jukka','$2a$10$fjOVHXiM.pRLtPNjGtYuqeIItKthayCxFZLmCG57szka.QuPvRvBe', true);

INSERT INTO user_roles (username, role)
VALUES ('inka', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('inka', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('jukka', 'ROLE_USER');