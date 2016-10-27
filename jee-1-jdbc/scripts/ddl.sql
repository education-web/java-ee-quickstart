DROP TABLE IF EXISTS super_hero;
DROP TABLE IF EXISTS comics;
DROP TABLE IF EXISTS online_comics;

CREATE TABLE online_comics (
  online_comics_id BIGSERIAL PRIMARY KEY,
  url              VARCHAR(2000),
  size             BIGINT
);

CREATE TABLE comics (
  comics_id        BIGSERIAL PRIMARY KEY,
  title            VARCHAR(255),
  publisher        VARCHAR(255),
  num              INTEGER,
  price            NUMERIC,
  online_comics_id BIGINT REFERENCES online_comics
);

CREATE TABLE super_hero (
  super_hero_id SERIAL PRIMARY KEY,
  comics_id     BIGINT REFERENCES comics,
  name          VARCHAR(255),
  alter_ego     VARCHAR(255)
);
