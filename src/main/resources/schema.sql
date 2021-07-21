DROP TABLE IF EXISTS tblFields;
CREATE TABLE tblFields(
  id VARCHAR PRIMARY KEY,
  name VARCHAR NOT NULL,
  created timestamp NOT NULL,
  updated timestamp NOT NULL,
  countrycode VARCHAR NOT NULL
);

DROP TABLE IF EXISTS tblBoundaries;
CREATE TABLE tblBoundaries(
  id VARCHAR PRIMARY KEY,
  created timestamp NOT NULL,
  updated timestamp NOT NULL,
  geojson JSONB
);


