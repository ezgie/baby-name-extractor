CREATE SEQUENCE SEQU;

CREATE TABLE firstnames
(
  id BIGINT GENERATED BY DEFAULT AS SEQUENCE SEQU PRIMARY KEY,
  firstname VARCHAR(255) DEFAULT NULL,
  origin VARCHAR(255) DEFAULT NULL,
  meaning VARCHAR(1000000) DEFAULT NULL,
  gender CHAR(1) DEFAULT NULL
)