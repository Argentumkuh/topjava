DELETE FROM meal;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meal (user_id, description, calories, datetime) VALUES
  (100000, 'Завтрак админа', '500', '2017-11-22 09:11:17'),
  (100000, 'Обед админа', '1000', '2017-11-22 12:18:17');
