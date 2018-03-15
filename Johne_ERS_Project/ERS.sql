/*******************************************************************************
   Expense Reimbursement System (ERS) Database
   Script: ERS.sql
   Description: Creates and populates the Expense Reimbursement System database.
   DB Server: Oracle
   Author: Johne Vang
********************************************************************************/
DROP TABLE ERS_REIMBURSEMENTS_STATUS;
DROP TABLE ERS_REIMBURSEMENT_TYPE;
DROP TABLE ERS_REIMBURSEMENTS;
DROP TABLE ERS_USERS;
DROP TABLE ERS_USER_ROLES;

DROP VIEW viewUsers;

DROP SEQUENCE users_id_seq;
DROP SEQUENCE reimburse_id_seq;
DROP SEQUENCE r_type_seq;
DROP SEQUENCE r_status_seq;


-- create user roles table
-- 1 for employee and 2 for manager
CREATE TABLE ERS_USER_ROLES
(
    UR_ID NUMBER(*,0) NOT NULL,
    UR_ROLE VARCHAR2 (40 BYTE),
    CONSTRAINT ERS_USER_ROLES_PK PRIMARY KEY (UR_ID)
);
INSERT INTO ERS_USER_ROLES (UR_ID, UR_ROLE) VALUES
(1, 'Employee');

INSERT INTO ERS_USER_ROLES (UR_ID, UR_ROLE) VALUES
(2, 'Manager');

-- create the users table
CREATE TABLE ERS_USERS
(
    U_ID NUMBER(*, 0) NOT NULL,
    U_USERNAME VARCHAR2(40 BYTE) NOT NULL,
    U_PASSWORD VARCHAR2(40 BYTE) NOT NULL,
    U_FIRSTNAME VARCHAR2(30 BYTE),
    U_LASTNAME VARCHAR2(30 BYTE),
    U_EMAIL VARCHAR2(100 BYTE),
    UR_ID NUMBER(*,0) NOT NULL,
    CONSTRAINT ERS_USERS_PK PRIMARY KEY (U_ID),
    CONSTRAINT ERS_USERS_U_USERNAME_UN UNIQUE (U_USERNAME),
    CONSTRAINT ERS_USERS_U_EMAIL_UN UNIQUE (U_EMAIL),
    CONSTRAINT ERS_USER_ROLES_ERS_USERS_FK FOREIGN KEY (UR_ID)
    REFERENCES ERS_USER_ROLES (UR_ID)
);

-- create the sequence for incrementing user id
CREATE SEQUENCE users_id_seq
    START WITH 1000
    INCREMENT BY 1;

-- procedure for inserting users
CREATE OR REPLACE PROCEDURE insert_user_procedure(UNAME VARCHAR2, PW VARCHAR2, 
FNAME VARCHAR2, LNAME VARCHAR2, EMAIL VARCHAR2, ROLEID NUMBER) 
AS
    BEGIN
    INSERT INTO ERS_USERS (U_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID) VALUES
    (users_id_seq.NEXTVAL, UNAME, PW, FNAME, LNAME, EMAIL, ROLEID);
    COMMIT;
    END;
    /
EXECUTE insert_user_procedure('prisonmike', 'scott', 'Michael', 'Scott', 'mscott@email.com', 2);
EXECUTE insert_user_procedure('dwigt', 'beets', 'Dwight', 'Schrute', 'schrute@email.com', 1);
EXECUTE insert_user_procedure('pam', 'cece', 'Pam', 'Halpert ', 'phalpert@email.com', 1);
EXECUTE insert_user_procedure('jim', 'halpert', 'Jim', 'Halpert', 'jhalpert@email.com', 1);
EXECUTE insert_user_procedure('kevin', 'brownies', 'Kevin', 'Malone', 'kmalone@email.com', 1);

SELECT UR_ID, UR_ROLE FROM ERS_USER_ROLES;

SELECT U_ID, UR_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME,
U_EMAIL FROM ERS_USERS;

-- view all users with user role and without password shown
CREATE VIEW viewUsers AS
    SELECT ERS_USERS.U_ID AS USER_ID, ERS_USERS.U_USERNAME AS USERNAME, 
    ERS_USERS.U_FIRSTNAME AS FIRSTNAME, ERS_USERS.U_LASTNAME AS LASTNAME, ERS_USERS.U_EMAIL AS EMAIL_ADDRESS, 
    ERS_USER_ROLES.UR_ROLE 
    FROM ERS_USERS, ERS_USER_ROLES
    WHERE ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID
    ORDER BY ERS_USERS.U_ID ASC;
    
SELECT * FROM  viewUsers;

--reimbursements status
CREATE TABLE ERS_REIMBURSEMENTS_STATUS
(
    RS_ID NUMBER(*,0) NOT NULL,
    RS_STATUS VARCHAR2(30 BYTE) NOT NULL,
    CONSTRAINT ERS_REIMBURSEMENTS_STATUS_PK PRIMARY KEY (RS_ID)
);

CREATE SEQUENCE r_status_seq
    START WITH 1
    INCREMENT BY 1;
    
-- status type: 1 Pending, 2 Approved, 3 Denied
CREATE OR REPLACE PROCEDURE r_status_procedure(R_STATUS VARCHAR2) 
AS
    BEGIN
    INSERT INTO ERS_REIMBURSEMENTS_STATUS (RS_ID, RS_STATUS) VALUES
    (r_status_seq.NEXTVAL, R_STATUS);
    COMMIT;
    END;
    /

EXECUTE r_status_procedure('Pending');
EXECUTE r_status_procedure('Approved');
EXECUTE r_status_procedure('Denied');

SELECT RS_ID, RS_STATUS FROM ERS_REIMBURSEMENTS_STATUS;

-- reimbursement type table
CREATE TABLE ERS_REIMBURSEMENT_TYPE
(
    RT_ID NUMBER(*,0) NOT NULL,
    RT_TYPE VARCHAR2(30 BYTE) NOT NULL,
    CONSTRAINT ERS_REIMBURSEMENT_TYPE_PK PRIMARY KEY (RT_ID)
);

CREATE SEQUENCE r_type_seq
    START WITH 1
    INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE r_type_procedure(R_TYPE VARCHAR2) 
AS
    BEGIN
    INSERT INTO ERS_REIMBURSEMENT_TYPE (RT_ID, RT_TYPE) VALUES
    (r_type_seq.NEXTVAL, R_TYPE);
    COMMIT;
    END;
    /
    
-- 1 Party planning committe
-- 2 Travel
-- 3 Lodging
-- 4 Food
-- 5 Other
EXECUTE r_type_procedure('Party Planning Committe');
EXECUTE r_type_procedure('Travel');
EXECUTE r_type_procedure('Lodging');
EXECUTE r_type_procedure('Food');
EXECUTE r_type_procedure('Other');

SELECT RT_ID, RT_TYPE FROM ERS_REIMBURSEMENT_TYPE;

CREATE TABLE ERS_REIMBURSEMENTS
(
    R_ID NUMBER(*,0) NOT NULL,
    R_AMOUNT NUMBER(22,2) NOT NULL,
    R_DESCRIPTION VARCHAR2(100 BYTE),
    R_RECEIPT BLOB,
    R_SUBMITTED TIMESTAMP NOT NULL,
    R_RESOLVED TIMESTAMP,
    U_ID_AUTHOR NUMBER(*,0) NOT NULL,
    U_ID_RESOLVER NUMBER(*,0),
    RT_TYPE NUMBER(*,0) NOT NULL,
    RS_STATUS NUMBER(*,0) NOT NULL,
    CONSTRAINT ERS_REIMBURSEMENTS_pk PRIMARY KEY (R_ID),
    CONSTRAINT ERS_USERSREIMBURSEMENTS_fk FOREIGN KEY (U_ID_AUTHOR)
    REFERENCES ERS_USERS (U_ID),
    CONSTRAINT ERS_USERS_REIMBURSEMENTS_fk FOREIGN KEY (U_ID_RESOLVER)
    REFERENCES ERS_USERS (U_ID)
);

CREATE SEQUENCE reimburse_id_seq
    START WITH 100
    INCREMENT BY 1;

CREATE OR REPLACE FUNCTION get_current_time 
RETURN TIMESTAMP WITH TIME ZONE
IS 
currentTime TIMESTAMP WITH TIME ZONE;
BEGIN
   SELECT CURRENT_TIMESTAMP INTO currentTime FROM DUAL;
   RETURN currentTime;
END get_current_time;
/

-- procedure for inserting users
-- 1 Party planning committe
-- 2 Travel/Lodging
-- 3 Food
-- 4 Other
CREATE OR REPLACE PROCEDURE insert_reimburse_procedure(AMOUNT NUMBER, DESCRIPTION VARCHAR2, 
RECEIPT BLOB, SUBMITTED TIMESTAMP, RESOLVED TIMESTAMP, ID_AUTHOR NUMBER, ID_RESOLVER NUMBER, R_TYPE NUMBER, R_STATUS NUMBER) 
AS
    BEGIN
    INSERT INTO ERS_REIMBURSEMENTS (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, 
    U_ID_RESOLVER, RT_TYPE, RS_STATUS) VALUES
    (reimburse_id_seq.NEXTVAL, AMOUNT, DESCRIPTION, RECEIPT, SUBMITTED, RESOLVED, ID_AUTHOR, ID_RESOLVER, R_TYPE, R_STATUS);
    COMMIT;
    END;
    /

EXECUTE insert_reimburse_procedure(15.55, 'For Decorations', NULL, get_current_time, NULL, 1002, NULL, 1, 1);
EXECUTE insert_reimburse_procedure(20.23, 'Traveled to Beets Farm with Mose', NULL, get_current_time, NULL, 1001, NULL, 2, 1);
EXECUTE insert_reimburse_procedure(11.99, 'Spilled my Famous Chili', NULL, get_current_time, NULL, 1004, NULL, 3, 1);
EXECUTE insert_reimburse_procedure(8.99, 'Brownies', NULL, get_current_time, NULL, 1004, NULL, 3, 1);

SELECT R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, TO_CHAR(get_current_time, 'HH:MM:SS' ) AS TIME_SUBMITTED, 
R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RS_STATUS FROM ERS_REIMBURSEMENTS;

CREATE OR REPLACE VIEW view_reimbursements AS 
    (select * from 
    (SELECT U_ID AS M_ID, U_FIRSTNAME AS APPROVER_FIRSTNAME, U_LASTNAME AS APPROVER_LASTNAME FROM ERS_USERS) MAN
    right outer join
    (select * from ERS_REIMBURSEMENTS REIM inner join
    (SELECT U_ID AS E_ID, U_FIRSTNAME AS AUTHOR_FIRSTNAME, U_LASTNAME AS AUTHOR_LASTNAME FROM ERS_USERS) EMP
    on u_id_author = e_id)
    on u_id_resolver=m_id);

SELECT R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, RT_TYPE, RS_STATUS, E_ID, AUTHOR_FIRSTNAME, AUTHOR_LASTNAME,
M_ID, APPROVER_FIRSTNAME, APPROVER_LASTNAME
FROM view_reimbursements;




UPDATE ERS_REIMBURSEMENTS SET U_ID_RESOLVER = '1000', RS_STATUS = '1' WHERE R_ID = '100' ;

