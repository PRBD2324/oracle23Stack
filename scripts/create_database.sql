ALTER SESSION SET CONTAINER = FREEPDB1;
-- CREATE USER TEST IDENTIFIED BY myPwd QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO myUser;
-- 
CREATE SCHEMA mySchema AUTHORIZATION myUser;
-- 
ALTER SESSION SET CURRENT_SCHEMA = mySchema;

CREATE TABLE FESTIVO (
  FECHA   DATE    NOT NULL
);
