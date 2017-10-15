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
  ('user');

INSERT INTO user (first_name, last_name, email, birthday, role_id) VALUES
  ('Siarhei', 'Blashuk', 'Siarhei_Blashuk@epam.com', '1996-07-18', 1),
  ('Bob', 'Bob', 'Bob_Bob@epam.com', '1997-02-21', 2);

INSERT INTO event_auditoriums (event_id, auditorium_id, start_time) VALUES
  (1, 2, '2017-10-01 18:30:00');