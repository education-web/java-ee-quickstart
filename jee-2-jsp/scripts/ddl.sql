DROP TABLE IF EXISTS super_hero;
DROP TABLE IF EXISTS comics;
DROP TABLE IF EXISTS online_comics;

CREATE TABLE comics (
  id        BIGSERIAL,
  title     VARCHAR(255),
  publisher VARCHAR(255),
  author    VARCHAR(255),
  num       INTEGER,
  price     NUMERIC
);
