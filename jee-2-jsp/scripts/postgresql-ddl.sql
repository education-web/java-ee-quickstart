DROP TABLE IF EXISTS comics;

CREATE TABLE comics (
  id        BIGSERIAL,
  title     VARCHAR(255),
  publisher VARCHAR(255),
  author    VARCHAR(255),
  num       INTEGER,
  price     NUMERIC
);
