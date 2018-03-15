--User roles
CREATE TABLE ers_user_roles
(
    ur_id NUMBER(*,0) GENERATED ALWAYS AS IDENTITY,
    ur_roles VARCHAR(40),
    CONSTRAINT ers_user_roles_pk PRIMARY KEY (ur_id)
);

--Users
CREATE TABLE ers_users
(
    u_id NUMBER(*,0) GENERATED ALWAYS AS IDENTITY,
    u_username VARCHAR2(40) NOT NULL,
    u_password VARCHAR2(40) NOT NULL, 
    u_firstname VARCHAR2(30), 
    u_lastname VARCHAR2(30),
    u_email VARCHAR2(100),
    ur_id NUMBER(*,0) NOT NULL,
    CONSTRAINT ers_users_pk PRIMARY KEY (u_id),
    CONSTRAINT ers_user_role_id_fk FOREIGN KEY (ur_id) 
            REFERENCES ers_user_roles (ur_id),
    CONSTRAINT ers_users_u_username_un UNIQUE (u_username),
    CONSTRAINT ers_users_u_email_un UNIQUE (u_email)
);

--Reimbursement Status
CREATE TABLE  ers_reimbursement_status
(
    rs_id NUMBER(*,0) GENERATED ALWAYS AS IDENTITY,
    rs_status VARCHAR2(30) NOT NULL,
    CONSTRAINT ers_reimbursment_status_pk PRIMARY KEY (rs_id)
);

--Reimbursement Type
CREATE TABLE  ers_reimbursement_type
(
    rt_id NUMBER(*,0) GENERATED ALWAYS AS IDENTITY,
    rt_type VARCHAR2(30) NOT NULL,
    CONSTRAINT ers_reimbursment_type_pk PRIMARY KEY (rt_id)
);

--Reimbursements
CREATE TABLE ers_reimbursements
(
    r_id NUMBER(*,0) GENERATED ALWAYS AS IDENTITY,
    r_amount NUMBER(22,2) NOT NULL,
    r_description VARCHAR2(100),
    r_receipt BLOB,
    r_submitted TIMESTAMP NOT NULL,
    r_resolved TIMESTAMP,
    u_id_author NUMBER(*,0) NOT NULL,
    u_id_resolver NUMBER(*,0),
    rt_type NUMBER(*,0) NOT NULL,
    rt_status NUMBER(*,0) NOT NULL,
    CONSTRAINT ers_reimbursements_pk PRIMARY KEY (r_id),
    CONSTRAINT ers_u_id_author FOREIGN KEY (u_id_author) 
            REFERENCES ers_users (u_id),
    CONSTRAINT ers_u_id_resolver FOREIGN KEY (u_id_resolver) 
        REFERENCES ers_users (u_id),
    CONSTRAINT ers_rt_type FOREIGN KEY (rt_type) 
            REFERENCES ers_reimbursement_type (rt_id),
    CONSTRAINT ers_rt_status FOREIGN KEY (rt_status) 
        REFERENCES ers_reimbursement_status (rs_id)
);


--Stored Procedures for insert, updates, and deletes for users
CREATE OR REPLACE PROCEDURE insert_user
(user_username IN ers_users.u_username%TYPE,
 user_password IN ers_users.u_password%TYPE,
 user_firstname IN ers_users.u_firstname%TYPE,
 user_lastname IN ers_users.u_lastname%TYPE,
 user_email IN ers_users.u_email%TYPE,
 user_role IN ers_users.ur_id%TYPE
)
AS
BEGIN
    INSERT INTO ers_users (u_username,u_password,u_firstname,u_lastname,u_email,ur_id) 
    VALUES (user_username, user_password,user_firstname,user_lastname,user_email,user_role);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE update_user
(user_id IN ers_users.u_id%TYPE,
 user_username IN ers_users.u_username%TYPE,
 user_password IN ers_users.u_password%TYPE,
 user_firstname IN ers_users.u_firstname%TYPE,
 user_lastname IN ers_users.u_lastname%TYPE,
 user_email IN ers_users.u_email%TYPE,
 user_role IN ers_users.ur_id%TYPE
)
AS
BEGIN
    UPDATE ers_users
    SET u_username = user_username,
        u_password = user_password,
        u_firstname = user_firstname,
        u_lastname = user_lastname,
        u_email = user_email,
        ur_id = user_role
    WHERE ers_users.u_id = user_id;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE delete_user
(user_id IN ers_users.u_id%TYPE)
AS
BEGIN
    DELETE FROM ers_reimbursements WHERE u_id_author IN user_id;
    DELETE FROM ers_users WHERE u_id=user_id;
    COMMIT;
END;
/

--stored procedures of insert, update, and delete for reimbursements
CREATE OR REPLACE PROCEDURE insert_ticket
(ticket_amount IN ers_reimbursements.r_amount%TYPE,
 ticket_description IN ers_reimbursements.r_description%TYPE,
 ticket_receipt IN ers_reimbursements.r_receipt%TYPE,
 ticket_submitted IN ers_reimbursements.r_submitted%TYPE,
 ticket_resolved IN ers_reimbursements.r_resolved%TYPE,
 ticket_author IN ers_reimbursements.u_id_author%TYPE,
 ticket_resolver IN ers_reimbursements.u_id_resolver%TYPE,
 ticket_rtype IN ers_reimbursements.rt_type%TYPE,
 ticket_rstatus IN ers_reimbursements.rt_status%TYPE
)
AS
BEGIN
    INSERT INTO ers_reimbursements 
    (r_amount,r_description,r_receipt,r_submitted,r_resolved,
     u_id_author,u_id_resolver,rt_type,rt_status)
    VALUES 
    (ticket_amount,ticket_description,ticket_receipt,ticket_submitted,ticket_resolved,
     ticket_author,ticket_resolver,ticket_rtype,ticket_rstatus);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE update_ticket
(ticket_id IN ers_reimbursements.r_id%TYPE,
 ticket_amount IN ers_reimbursements.r_amount%TYPE,
 ticket_description IN ers_reimbursements.r_description%TYPE,
 ticket_receipt IN ers_reimbursements.r_receipt%TYPE,
 ticket_submitted IN ers_reimbursements.r_submitted%TYPE,
 ticket_resolved IN ers_reimbursements.r_resolved%TYPE,
 ticket_author IN ers_reimbursements.u_id_author%TYPE,
 ticket_resolver IN ers_reimbursements.u_id_resolver%TYPE,
 ticket_rtype IN ers_reimbursements.rt_type%TYPE,
 ticket_rstatus IN ers_reimbursements.rt_status%TYPE
)
AS
BEGIN
    UPDATE ers_reimbursements
    SET
    r_amount = ticket_amount,
    r_description = ticket_description,
    r_receipt = ticket_receipt,
    r_submitted = ticket_submitted,
    r_resolved = ticket_resolved,
    u_id_author = ticket_author,
    u_id_resolver = ticket_resolver,
    rt_type = ticket_rtype,
    rt_status = ticket_rstatus
    WHERE ers_reimbursements.r_id = ticket_id;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE delete_ticket
(ticket_id IN ers_reimbursements.r_id%TYPE)
AS
BEGIN
    DELETE FROM ers_reimbursements WHERE r_id IN ticket_id;
    COMMIT;
END;
/

--INSERT DUMMY DATA
INSERT INTO ERS_REIMBURSEMENT_TYPE (RT_TYPE) VALUES ('travel');
INSERT INTO ERS_REIMBURSEMENT_TYPE (RT_TYPE) VALUES ('medical');
INSERT INTO ERS_REIMBURSEMENT_TYPE (RT_TYPE) VALUES ('business');
INSERT INTO ERS_REIMBURSEMENT_STATUS (RS_STATUS) VALUES ('pending');
INSERT INTO ERS_REIMBURSEMENT_STATUS (RS_STATUS) VALUES ('approved');
INSERT INTO ERS_REIMBURSEMENT_STATUS (RS_STATUS) VALUES ('denied');
INSERT INTO ers_user_roles (UR_ROLES) VALUES ('employee');
INSERT INTO ers_user_roles (UR_ROLES) VALUES ('manager');

CALL insert_user('khsieh','pass','kevin','hsieh','kevin@gmail.com',1);
CALL insert_user('hello','world','jimm','wong','jimbo@gmail.com',1);
CALL insert_user('admin','password','admin','admin','admin@gmail.com',2);

CALL insert_ticket(35.0,
'Hello World', 
null,
TO_DATE('2018-2-21 00:00:00','yyyy-mm-dd hh24:mi:ss'),
TO_DATE('2018-2-21 00:00:00','yyyy-mm-dd hh24:mi:ss'),
1,3,1,1);

CALL insert_ticket(200.0,
'ticket 2', 
null,
TO_DATE('2018-2-21 00:00:00','yyyy-mm-dd hh24:mi:ss'),
TO_DATE('2018-2-21 00:00:00','yyyy-mm-dd hh24:mi:ss'),
2,null,2,1);

SELECT * FROM ers_users WHERE u_username='khsieh' AND u_password='pass';


--drop tables
DROP TABLE ERS_REIMBURSEMENTS;
DROP TABLE ERS_USERS;
DROP TABLE ERS_USER_ROLES;
DROP TABLE ERS_REIMBURSEMENT_STATUS;
DROP TABLE ERS_REIMBURSEMENT_TYPE;
