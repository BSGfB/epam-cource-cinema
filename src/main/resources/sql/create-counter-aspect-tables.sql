
DROP TABLE IF EXISTS count_event_name;

CREATE TABLE count_event_name (
  count_event INT           NOT NULL AUTO_INCREMENT,
  name        VARCHAR(248)  NOT NULL UNIQUE,
  counter     INT           DEFAULT 1
);

DROP TABLE IF EXISTS count_event_price_name;

CREATE TABLE count_event_price_name (
  count_event INT           NOT NULL AUTO_INCREMENT,
  name        VARCHAR(248)  NOT NULL UNIQUE,
  counter     INT           DEFAULT 1
);

DROP TABLE IF EXISTS count_booking_ticket;

CREATE TABLE count_booking_ticket (
  count_event INT           NOT NULL AUTO_INCREMENT,
  name        VARCHAR(248)  NOT NULL UNIQUE,
  counter     INT           DEFAULT 1
);