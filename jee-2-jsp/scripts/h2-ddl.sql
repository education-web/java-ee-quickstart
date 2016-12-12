DROP TABLE IF EXISTS comics;

CREATE TABLE comics (
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  title     VARCHAR(255),
  publisher VARCHAR(255),
  author    VARCHAR(255),
  num       INTEGER,
  price     NUMERIC
);
