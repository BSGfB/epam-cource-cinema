DROP TABLE IF EXISTS auditorium;

CREATE TABLE auditorium (
  auditorium_id         INT           NOT NULL AUTO_INCREMENT,
  name                  VARCHAR(100)  NOT NULL,
  number_of_seats       INT           NOT NULL,

  PRIMARY KEY (auditorium_id)
);

DROP TABLE IF EXISTS event_rating;

CREATE TABLE event_rating (
  event_rating_id INT         NOT NULL  AUTO_INCREMENT,
  name            VARCHAR(50) NOT NULL  UNIQUE,

  PRIMARY KEY (event_rating_id)
);

DROP TABLE IF EXISTS event;

CREATE TABLE event (
  event_id        INT          NOT NULL     AUTO_INCREMENT,
  name            VARCHAR(255) NOT NULL,
  base_price      DOUBLE       NOT NULL,
  event_rating_id INT          NOT NULL,

  PRIMARY KEY (event_id),
  FOREIGN KEY (event_rating_id) REFERENCES event_rating(event_rating_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS role;

CREATE TABLE role (
  role_id   INT         NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(50) NOT NULL UNIQUE,

  PRIMARY KEY (role_id)
);

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  user_id     INT           NOT NULL  AUTO_INCREMENT,
  first_name  VARCHAR(50)   NOT NULL,
  last_name   VARCHAR(50)   NOT NULL,
  email       VARCHAR(100)  NOT NULL  UNIQUE,
  birthday    DATE          NOT NULL,
  password    VARCHAR(100)  NOT NULL,

  PRIMARY KEY (user_id)
);

CREATE TABLE user_roles (
  user_id INT   NOT NULL,
  role_id INT   NOT NULL,

  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (role_id) REFERENCES role(role_id)
);

DROP TABLE IF EXISTS message;

CREATE TABLE message (
  message_id    INT           NOT NULL AUTO_INCREMENT,
  user_id       INT           NOT NULL,
  message_text  VARCHAR(248)  NOT NULL,

  PRIMARY KEY (message_id),
  FOREIGN KEY (user_id)   REFERENCES user(user_id)   ON DELETE CASCADE,
);

DROP TABLE IF EXISTS ticket;

CREATE TABLE ticket (
  ticket_id  INT      NOT NULL AUTO_INCREMENT,
  user_id    INT      NOT NULL,
  event_id   INT      NOT NULL,
  date_time  DATETIME NOT NULL,
  seat       INT      NOT NULL,

  PRIMARY KEY (ticket_id),
  FOREIGN KEY (user_id)   REFERENCES user(user_id)   ON DELETE CASCADE,
  FOREIGN KEY (event_id)  REFERENCES event(event_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS event_auditoriums;

CREATE TABLE event_auditoriums (
  event_id      INT       NOT NULL,
  auditorium_id INT       NOT NULL,
  start_time    DATETIME  NOT NULL,

  FOREIGN KEY (auditorium_id) REFERENCES auditorium(auditorium_id)  ON DELETE CASCADE,
  FOREIGN KEY (event_id)      REFERENCES event(event_id)            ON DELETE CASCADE
);

DROP TABLE IF EXISTS discount;

CREATE TABLE discount (
  discount_id   INT           NOT NULL AUTO_INCREMENT,
  user_id       INT           NOT NULL,
  discount_name VARCHAR(100)  NOT NULL,
  price         DOUBLE        NOT NULL,

  PRIMARY KEY (discount_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id)  ON DELETE CASCADE
);

CREATE TABLE persistent_logins (
  username    VARCHAR(64) NOT NULL,
  series      VARCHAR(64) NOT NULL,
  token       VARCHAR(64) NOT NULL,
  last_used   TIMESTAMP   NOT NULL,

  PRIMARY KEY (series)
);

CREATE TABLE user_account (
  user_account_id INT             NOT NULL      AUTO_INCREMENT,
  user_id         INT             NOT NULL      UNIQUE,
  money           DECIMAL(16, 2)  DEFAULT 0.0,

  PRIMARY KEY (user_account_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);