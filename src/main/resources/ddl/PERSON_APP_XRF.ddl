USE UARK;

CREATE TABLE PERSON_APP_XRF (
  ID       INT     NOT NULL AUTO_INCREMENT,
  PERSON   INT     NOT NULL,
  APP      INT     NOT NULL,
  IS_OWNER BOOLEAN NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (PERSON) REFERENCES PERSON (ID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (APP) REFERENCES APP (ID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);