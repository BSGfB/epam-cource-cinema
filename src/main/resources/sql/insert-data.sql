INSERT INTO auditorium (name, number_of_seats) VALUES
  ('Small', 50),
  ('Big', 100);

INSERT INTO event_rating (name) VALUES
  ('LOW'),
  ('MID'),
  ('HIGH');

INSERT INTO event (name, base_price, event_rating_id) VALUES
  ('Kingsman 2', 5.0, 1);

INSERT INTO role (role_name) VALUES
  ('admin'),
  ('user'),
  ('manager');

INSERT INTO user (first_name, last_name, email, birthday, password) VALUES
  ('Siarhei', 'Blashuk', 'Siarhei_Blashuk@epam.com', '1996-07-18', '$2a$10$zh4mEB7Frd31jn1UZQv1AuT2bJI2di6aXmOp.Uv0EhIzKsgQlkKIG'),
  ('Admin', 'Admin', 'admin@mail.com', '1997-02-21', '$2a$10$zh4mEB7Frd31jn1UZQv1AuT2bJI2di6aXmOp.Uv0EhIzKsgQlkKIG'),
  ('User', 'User', 'user@mail.com', '1997-02-21', '$2a$10$zh4mEB7Frd31jn1UZQv1AuT2bJI2di6aXmOp.Uv0EhIzKsgQlkKIG'),
  ('Manager', 'Manager', 'manager@mail.com', '1997-02-21', '$2a$10$zh4mEB7Frd31jn1UZQv1AuT2bJI2di6aXmOp.Uv0EhIzKsgQlkKIG');

INSERT INTO event_auditoriums (event_id, auditorium_id, start_time) VALUES
  (1, 2, '2017-10-01 18:30:00');

INSERT INTO ticket (user_id, event_id, date_time, seat) VALUES
  (1, 1, '2017-10-01 18:30:00', 10),
  (2, 1, '2017-10-01 18:30:00', 11),
  (3, 1, '2017-10-01 18:30:00', 12),
  (4, 1, '2017-10-01 18:30:00', 13),
  (1, 1, '2017-10-01 18:30:00', 14),
  (2, 1, '2017-10-01 18:30:00', 15),
  (3, 1, '2017-10-01 18:30:00', 16),
  (4, 1, '2017-10-01 18:30:00', 17);

INSERT INTO user_roles (user_id, role_id) VALUES
  (1, 1),
  (2, 1),
  (3, 2),
  (4, 3);

INSERT INTO user_account (user_id, money) VALUES
  (1, 1000.0),
  (2, 100.0),
  (3, 100.0),
  (4, 100.0);