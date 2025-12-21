INSERT INTO app_user(id, username, password, email)
VALUES (5, 'admin5@48', '2a$10$5qFttBg6CHZV1.xUyYNbxuX2WcxKEXGLK45zSGhGzFoXYXUtqBa7y', 'expence-tracer@gmail.com');

INSERT INTO roles(id, name)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO permissions(id, name)
VALUES (1, 'ARTICLE_WRITE'),
       (2, 'ARTICLE_READ');

INSERT INTO user_roles(id, user_id, role_id)
VALUES (10, 5, 1);


INSERT INTO role_permissions(id, role_id, permission_id)
VALUES (1, 1, 1),
       (2, 1, 2);


INSERT INTO role_permissions(id, role_id, permission_id)
VALUES (3, 2, 1);
INSERT INTO role_permissions(id, role_id, permission_id)
VALUES (4, 2, 2);