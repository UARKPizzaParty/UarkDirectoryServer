USE UARK;

CREATE TABLE APP (
  ID          INT         NOT NULL AUTO_INCREMENT,
  NAME        VARCHAR(20) NOT NULL,
  DEVELOPER   VARCHAR(20) NOT NULL,
  VERSION     VARCHAR(20) NOT NULL,
  DESCRIPTION VARCHAR(200),
  CREATE_DATE TIMESTAMP   NOT NULL,
  NUM_IMAGES  INTEGER     NOT NULL,
  APK_NAME    VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
);