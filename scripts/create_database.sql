ALTER SESSION SET CONTAINER = FREEPDB1;
--------- CREATE USER myUser IDENTIFIED BY myPwd QUOTA UNLIMITED ON USERS;
-- CREATE SCHEMA AUTHORIZATION myUser;

GRANT CONNECT, RESOURCE TO myUser;
GRANT CREATE TABLE TO myUser;
ALTER SESSION SET CURRENT_SCHEMA = myUser;

SELECT * FROM all_tab_privs_recd WHERE grantee = 'myUser';

CREATE TABLE FESTIVO (
  FECHA   DATE    NOT NULL
);

-- SELECT table_name FROM all_tables;
-- SELECT table_name FROM all_tables WHERE OWNER = 'myUser';
 Select owner from dba_tables where table_name = 'FESTIVO'; 
