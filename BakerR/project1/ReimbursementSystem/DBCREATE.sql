-- Note: Postgres auto-commit is enabled by default.

------------------------
-- Miscellaneous Queries
------------------------

-- List all constraints
--select conrelid::regclass AS table_from, conname, pg_get_constraintdef(c.oid)
--from   pg_constraint c
--join   pg_namespace n ON n.oid = c.connamespace
--where  contype in ('f', 'p','c','u') order by contype;


-- Drop Everything & recreate public schema (if public schema is owned by user, it may get dropped with this command.)
-- [replace wamuu with db username].
--drop owned by wamuu cascade;
--CREATE SCHEMA public;

-- command-line tool to connect to postgresql database.
-- psql -h [host] -p [port] -U [username] -d [database name/SID]
-- Example: psql -h localhost -p 5432 -U wamuu -d pgsql
-- to run an sql script using psql:
-- \i [script filename]


---------------------------
-- Database extension setup
---------------------------
-- Only need to execute once for database; may need to reinstall if using the drop
create extension pgcrypto; -- Crypto functions
create extension plpgsql; -- PL/pgsql language for functions
-- select * from pg_available_extensions; -- Shows all available (and installed) extensions.


------------------------------------
-- Database Table & Constraint Setup
------------------------------------

-- ERS: Employee Reimbursement System
-- U_*: user
-- UR_*: user role
-- R_*: reimbursement
-- RS_*: reimbursement status
-- RT_*: reimbursement type

-- P: Primary Key
-- F: Foreign Key
-- U: Unique
-- *: NOT NULL


-- ERS_USER_ROLES
-- [P *] UR_ID      NUMBER(*,0) | SERIAL
-- [   ] UR_ROLE    VARCHAR2(40)| VARCHAR(40)
CREATE TABLE ERS_USER_ROLES(
    UR_ID   SERIAL      PRIMARY KEY     NOT NULL,
    UR_ROLE VARCHAR(40)                         
);

INSERT INTO ERS_USER_ROLES (UR_ROLE) VALUES ('Employee');
INSERT INTO ERS_USER_ROLES (UR_ROLE) VALUES ('Manager');

-- ERS_USERS
/**
 * [P *] U_ID           NUMBER(*,0)     | SERIAL
 * [U *] U_USERNAME     VARCHAR2(40)    | VARCHAR(40)
 * [  *] U_PASSWORD     VARCHAR2(40)    | TEXT
 * [   ] U_FIRSTNAME    VARCHAR2(30)    | VARCHAR(30)
 * [   ] U_LASTNAME     VARCHAR2(30)    | VARCHAR(30)
 * [U  ] U_EMAIL        VARCHAR2(100)   | TEXT
 * [F *] UR_ID          NUMBER(*,0)     | INTEGER
 */
CREATE TABLE ERS_USERS(
    U_ID        SERIAL      PRIMARY KEY     NOT NULL,
    U_USERNAME  VARCHAR(40) UNIQUE          NOT NULL,
    U_PASSWORD  TEXT                        NOT NULL,
    U_FIRSTNAME VARCHAR(30)                         ,
    U_LASTNAME  VARCHAR(30)                         ,
    U_EMAIL     TEXT        UNIQUE                  ,
    UR_ID       INTEGER                     NOT NULL,
    FOREIGN KEY (UR_ID) REFERENCES ERS_USER_ROLES(UR_ID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);


-- ERS_REIMBURSEMENT_STATUS
/**
 * [P *] RS_ID      NUMBER(*,0) | SERIAL
 * [  *] RS_STATUS  VARCHAR2(30)| VARCHAR(30)
 */
CREATE TABLE ERS_REIMBURSEMENT_STATUS(
    RS_ID       SERIAL      PRIMARY KEY     NOT NULL,
    RS_STATUS   VARCHAR(30)                 NOT NULL
);

INSERT INTO ERS_REIMBURSEMENT_STATUS (RS_STATUS) VALUES('PENDING');
INSERT INTO ERS_REIMBURSEMENT_STATUS (RS_STATUS) VALUES('ACCEPTED');
INSERT INTO ERS_REIMBURSEMENT_STATUS (RS_STATUS) VALUES('DENIED');


-- ERS_REIMBURSEMENT_TYPE
/**
 * [P *] RT_ID      NUMBER(*,0) | SERIAL
 * [  *] RT_TYPE    VARCHAR2(30)| VARCHAR(30)
 */
CREATE TABLE ERS_REIMBURSEMENT_TYPE(
    RT_ID   SERIAL      PRIMARY KEY     NOT NULL,
    RT_TYPE VARCHAR(30)                 NOT NULL
);

INSERT INTO ers_reimbursement_type(rt_type) VALUES ('Business');
INSERT INTO ers_reimbursement_type(rt_type) VALUES ('Travel');
INSERT INTO ers_reimbursement_type(rt_type) VALUES ('Medical');
INSERT INTO ers_reimbursement_type(rt_type) VALUES ('Other');


-- ERS_REIMBURSEMENTS
/**
 * [P *] R_ID           NUMBER(*,0)     | SERIAL
 * [  *] R_AMOUNT       NUMBER(22,2)    | MONEY
 * [   ] R_DESCRIPTION  VARCHAR2(100)   | VARCHAR(100)
 * [   ] R_RECEIPT      BLOB            | BYTEA
 * [  *] R_SUBMITTED    TIMESTAMP       | TIMESTAMP (0) WITH TIME ZONE
 * [   ] R_RESOLVED     TIMESTAMP       | TIMESTAMP (0) WITH TIME ZONE
 * [F *] U_ID_AUTHOR    NUMBER(*,0)     | INTEGER
 * [F  ] U_ID_RESOLVER  NUMBER(*,0)     | INTEGER
 * [F *] RT_TYPE        NUMBER(*,0)     | INTEGER
 * [F *] RT_STATUS      NUMBER(*,0)     | INTEGER
 */
CREATE TABLE ERS_REIMBURSEMENTS(
    R_ID            SERIAL          PRIMARY KEY     NOT NULL,
    R_AMOUNT        MONEY                           NOT NULL,
    R_DESCRIPTION   VARCHAR(100)                            ,
    R_RECEIPT       BYTEA                                   ,
    R_RECEIPT_NAME  TEXT                                    ,
    R_SUBMITTED     TIMESTAMP (0) WITH TIME ZONE    NOT NULL,
    R_RESOLVED      TIMESTAMP (0) WITH TIME ZONE            ,
    U_ID_AUTHOR     INTEGER                         NOT NULL,
    U_ID_RESOLVER   INTEGER                                 ,
    RT_TYPE         INTEGER                         NOT NULL,
    RT_STATUS       INTEGER                         NOT NULL,
    FOREIGN KEY (U_ID_AUTHOR) REFERENCES ERS_USERS(U_ID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE                                   ,
    FOREIGN KEY (U_ID_RESOLVER) REFERENCES ERS_USERS(U_ID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE                                   ,
    FOREIGN KEY (RT_TYPE) REFERENCES ERS_REIMBURSEMENT_TYPE(RT_ID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE                                   ,
    FOREIGN KEY (RT_STATUS) REFERENCES ERS_REIMBURSEMENT_STATUS(RS_ID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE                                   
);




---------------------------------------------------------------------------------------------------------------
-- STORED PROCEDURES/FUNCTIONS

-- USER ROLES
-- Get Role by Id
CREATE OR REPLACE FUNCTION get_role_by_id(id INTEGER) RETURNS VARCHAR AS $$
    DECLARE name VARCHAR(40);
BEGIN
    SELECT ur_role INTO name FROM ers_user_roles WHERE ur_id = id;
    RETURN name;
END;
$$ LANGUAGE plpgsql;


-- Get Role by Name
CREATE OR REPLACE FUNCTION get_role_id_by_name(name VARCHAR(40)) RETURNS INTEGER AS $$
    DECLARE id INTEGER;
BEGIN
    SELECT ur_id INTO id FROM ers_user_roles WHERE ur_role ILIKE name;
    RETURN id;
END;
$$ LANGUAGE plpgsql;

-- Get all roles
CREATE OR REPLACE FUNCTION get_all_user_roles() RETURNS TABLE(id INTEGER, name VARCHAR(40)) AS $$
BEGIN
    RETURN QUERY SELECT ur_id, ur_role FROM ers_user_roles;
END;
$$ LANGUAGE plpgsql;


-- REIMBURSEMENT TYPES
-- Get Reimbursement Type by Id
CREATE OR REPLACE FUNCTION get_reimbursement_type_by_id(id INTEGER) RETURNS VARCHAR AS $$
    DECLARE name VARCHAR(30);
BEGIN
    SELECT rt_type INTO name FROM ers_reimbursement_type WHERE rt_id = id;
    RETURN name;
END;
$$ LANGUAGE plpgsql;


-- Get Reimbursement Type by Name
CREATE OR REPLACE FUNCTION get_reimbursement_type_id_by_name(name VARCHAR(30)) RETURNS INTEGER AS $$
    DECLARE id INTEGER;
BEGIN
    SELECT rt_id INTO id FROM ers_reimbursement_type WHERE rt_type ILIKE name;
    RETURN id;
END;
$$ LANGUAGE plpgsql;

-- Get all Reimbursement Types
CREATE OR REPLACE FUNCTION get_all_reimbursement_types() RETURNS TABLE(id INTEGER, name VARCHAR(30)) AS $$
BEGIN
    RETURN QUERY SELECT rt_id, rt_type FROM ers_reimbursement_type;
END;
$$ LANGUAGE plpgsql;


-- REIMBURSEMENT STATUS
-- Get Reimbursement Status by Id
CREATE OR REPLACE FUNCTION get_reimbursement_status_by_id(id INTEGER) RETURNS VARCHAR AS $$
    DECLARE name VARCHAR(30);
BEGIN
    SELECT rs_status INTO name FROM ers_reimbursement_status WHERE rs_id = id;
    RETURN name;
END;
$$ LANGUAGE plpgsql;


-- Get Reimbursement Status by Name
CREATE OR REPLACE FUNCTION get_reimbursement_status_id_by_name(name VARCHAR(30)) RETURNS INTEGER AS $$
    DECLARE id INTEGER;
BEGIN
    SELECT rs_id INTO id FROM ers_reimbursement_status WHERE rs_status ILIKE name;
    RETURN id;
END;
$$ LANGUAGE plpgsql;

-- Get all Reimbursement Statuses
CREATE OR REPLACE FUNCTION get_all_reimbursement_statuses() RETURNS TABLE(id INTEGER, name VARCHAR(30)) AS $$
BEGIN
    RETURN QUERY SELECT rs_id, rs_status FROM ers_reimbursement_status;
END;
$$ LANGUAGE plpgsql;


---------------------------
-- USERS
---------------------------

------LOOKUP------
-- Get All Users
CREATE OR REPLACE FUNCTION get_all_users() RETURNS TABLE(id INTEGER, username VARCHAR(40), firstname VARCHAR(30), lastname VARCHAR(30), email TEXT, roleid INTEGER) AS $$
BEGIN
    RETURN QUERY SELECT u_id, u_username, u_firstname, u_lastname, u_email, ur_id FROM ers_users;
END;
$$ LANGUAGE plpgsql;

-- Get User by Id
CREATE OR REPLACE FUNCTION get_user_by_id(userid INTEGER) RETURNS TABLE(id INTEGER, username VARCHAR(40), firstname VARCHAR(30), lastname VARCHAR(30), email TEXT, roleid INTEGER) AS $$
BEGIN
    RETURN QUERY SELECT u_id, u_username, u_firstname, u_lastname, u_email, ur_id FROM ers_users WHERE u_id = userid;
END;
$$ LANGUAGE plpgsql;

-- Get User by Name
CREATE OR REPLACE FUNCTION get_user_by_username(uname VARCHAR(40)) RETURNS TABLE(id INTEGER, username VARCHAR(40), firstname VARCHAR(30), lastname VARCHAR(30), email TEXT, roleid INTEGER) AS $$
BEGIN
    RETURN QUERY SELECT u_id, u_username, u_firstname, u_lastname, u_email, ur_id FROM ers_users WHERE u_username = uname;
END;
$$ LANGUAGE plpgsql;


-- Verify password.
CREATE OR REPLACE FUNCTION verify_password(uname VARCHAR(40), pass TEXT) RETURNS BOOLEAN AS $$
    DECLARE valid_pass BOOLEAN;
    DECLARE pwhash TEXT;
BEGIN
    SELECT u_password FROM ers_users INTO pwhash WHERE u_username=uname;
    SELECT pwhash=crypt(pass, pwhash) INTO valid_pass;
    RETURN valid_pass;
END;
$$ LANGUAGE plpgsql;


------UPDATE------
-- Update User (by Id)
CREATE OR REPLACE FUNCTION update_user(id INTEGER, username VARCHAR(40), firstname VARCHAR(30), lastname VARCHAR(30), email TEXT, roleid INTEGER) RETURNS VOID AS $$
BEGIN
    UPDATE ers_users
        SET u_username  = username, 
            u_firstname = firstname, 
            u_lastname  = lastname, 
            u_email     = email, 
            ur_id       = roleid
        WHERE u_id = id;
END;
$$ LANGUAGE plpgsql;

-- Change Password
CREATE OR REPLACE FUNCTION update_password(id INTEGER, password TEXT) RETURNS VOID AS $$
    DECLARE pwhash TEXT;
BEGIN
    SELECT crypt(password, gen_salt('bf', 11)) INTO pwhash;
    UPDATE ers_users 
        SET u_password = pwhash
        WHERE u_id = id;
END;
$$ LANGUAGE plpgsql;



------INSERT------
-- Insert New User
CREATE OR REPLACE FUNCTION add_user(username VARCHAR(40), password TEXT, firstname VARCHAR(30), lastname VARCHAR(30), email TEXT, roleid INTEGER) RETURNS VOID AS $$
    DECLARE pwhash TEXT;
BEGIN
    SELECT crypt(password, gen_salt('bf', 11)) INTO pwhash;
    INSERT INTO ers_users(u_username, u_password, u_firstname, u_lastname, u_email, ur_id)
    VALUES(username, pwhash, firstname, lastname, email, roleid);
END;
$$ LANGUAGE plpgsql;



------DELETE-------
-- Delete User by Id
CREATE OR REPLACE FUNCTION delete_user_by_id(id INTEGER) RETURNS VOID AS $$
BEGIN
    DELETE FROM ers_users
        WHERE u_id = id;
END;
$$ LANGUAGE plpgsql;

-- Delete User by Username
CREATE OR REPLACE FUNCTION delete_user_by_username(username VARCHAR(40)) RETURNS VOID AS $$
BEGIN
    DELETE FROM ers_users
        WHERE u_username = username;
END;
$$ LANGUAGE plpgsql;



---------------------------
-- REIMBURSEMENTS
---------------------------

------LOOKUP-------
-- Get All Reimbursements
CREATE OR REPLACE FUNCTION get_all_reimbursements() 
RETURNS TABLE(id INTEGER, amount NUMERIC, description VARCHAR(100), 
submitted TIMESTAMP(0) WITH TIME ZONE, resolved TIMESTAMP(0) WITH TIME ZONE,
author_id INTEGER, resolver_id INTEGER, 
r_type INTEGER, r_status INTEGER) AS $$
BEGIN
    RETURN QUERY 
        SELECT r_id, r_amount::NUMERIC, r_description, 
               r_submitted, r_resolved,
               u_id_author, u_id_resolver, rt_type, rt_status
            FROM ers_reimbursements;
END;
$$ LANGUAGE plpgsql;


-- Get All of a User's Reimbursements
CREATE OR REPLACE FUNCTION get_all_user_reimbursements(username VARCHAR(40)) 
RETURNS TABLE(id INTEGER, amount NUMERIC, description VARCHAR(100), 
submitted TIMESTAMP(0) WITH TIME ZONE, resolved TIMESTAMP(0) WITH TIME ZONE,
author_id INTEGER, resolver_id INTEGER, 
r_type INTEGER, r_status INTEGER) AS $$
    DECLARE id INTEGER;
BEGIN
    SELECT u_id INTO id FROM ers_users WHERE u_username = username;
    
    RETURN QUERY 
        SELECT r_id, r_amount::NUMERIC, r_description, 
               r_submitted, r_resolved,
               u_id_author, u_id_resolver, rt_type, rt_status
        FROM ers_reimbursements 
        WHERE u_id_author = id;
END;
$$ LANGUAGE plpgsql;


-- Get a Reimbursement by its ID.
CREATE OR REPLACE FUNCTION get_reimbursement_by_id(reimbursement_id INTEGER)
RETURNS TABLE(id INTEGER, amount NUMERIC, description VARCHAR(100),
submitted TIMESTAMP(0) WITH TIME ZONE, resolved TIMESTAMP(0) WITH TIME ZONE,
author_id INTEGER, resolver_id INTEGER,
r_type INTEGER, r_status INTEGER) AS $$
BEGIN
    RETURN QUERY 
        SELECT r_id, r_amount::NUMERIC, r_description,
               r_submitted, r_resolved,
               u_id_author, u_id_resolver, rt_type, rt_status
        FROM ers_reimbursements 
        WHERE r_id = reimbursement_id;
END;
$$ LANGUAGE plpgsql;


-- Gets all reimbursements of a specified type.
CREATE OR REPLACE FUNCTION get_reimbursements_by_type(typeid INTEGER) 
RETURNS TABLE(id INTEGER, amount NUMERIC, description VARCHAR(100), 
submitted TIMESTAMP(0) WITH TIME ZONE, resolved TIMESTAMP(0) WITH TIME ZONE,
author_id INTEGER, resolver_id INTEGER, 
r_type INTEGER, r_status INTEGER) AS $$
BEGIN
    RETURN QUERY 
        SELECT r_id, r_amount::NUMERIC, r_description, 
               r_submitted, r_resolved,
               u_id_author, u_id_resolver, rt_type, rt_status
        FROM ers_reimbursements
        WHERE typeid = rt_type;
END;
$$ LANGUAGE plpgsql;


-- Get reimbursements resolved by the given username.
CREATE OR REPLACE FUNCTION get_reimbursements_resolved_by(username VARCHAR(40))
RETURNS TABLE(id INTEGER, amount NUMERIC, description VARCHAR(100),
submitted TIMESTAMP(0) WITH TIME ZONE, resolved TIMESTAMP(0) WITH TIME ZONE,
author_id INTEGER, resolver_id INTEGER,
r_type INTEGER, r_status INTEGER) AS $$
    DECLARE id INTEGER;
BEGIN
    SELECT u_id INTO id FROM ers_users WHERE u_username = username;
    
    RETURN QUERY 
        SELECT r_id, r_amount::NUMERIC, r_description, 
               r_submitted, r_resolved,
               u_id_author, u_id_resolver, rt_type, rt_status
        FROM ers_reimbursements 
        WHERE u_id_resolver = id;
END;
$$ LANGUAGE plpgsql;


-- Get a given reimbursement request's receipt
CREATE OR REPLACE FUNCTION get_receipt(id INTEGER) RETURNS TABLE(fname TEXT, data BYTEA) AS $$
BEGIN
    RETURN QUERY
        SELECT r_receipt_name, r_receipt FROM ers_reimbursements
        WHERE r_id = id;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_receipt_metadata(id INTEGER) RETURNS TABLE(filesize INTEGER, filename TEXT) AS $$
BEGIN
    RETURN QUERY
        SELECT length(r_receipt), r_receipt_name 
            FROM ers_reimbursements
            WHERE r_id = id;
END;
$$ LANGUAGE plpgsql;


------INSERT-------
-- Insert a new reimbursement request.
CREATE OR REPLACE FUNCTION add_new_reimbursement(amount NUMERIC, 
description VARCHAR(100), submitted TIMESTAMP(0) WITH TIME ZONE,
author_id INTEGER, r_type_id INTEGER, r_status_id INTEGER, receipt_data BYTEA, receipt_name TEXT) RETURNS VOID AS $$
BEGIN
    INSERT INTO ers_reimbursements(
            r_amount, r_description, r_submitted, u_id_author, 
            rt_type, rt_status, r_receipt, r_receipt_name)
    VALUES(amount::MONEY, description, submitted, author_id, r_type_id, r_status_id, receipt_data, receipt_name);
END;
$$ LANGUAGE plpgsql;


------UPDATE-------
-- Updates a reimbursement request.
CREATE OR REPLACE FUNCTION update_reimbursement(id INTEGER,
amount NUMERIC, description VARCHAR(100), resolved TIMESTAMP(0) WITH TIME ZONE,
resolver_id INTEGER, r_status_id INTEGER) RETURNS VOID AS $$
BEGIN
    UPDATE ers_reimbursements
        SET r_amount = amount::MONEY,
            r_description = description,
            r_resolved = resolved,
            u_id_resolver = resolver_id,
            rt_status = r_status_id
        WHERE r_id = id;
END ;
$$ LANGUAGE plpgsql;



-- Updates a receipt.
CREATE OR REPLACE FUNCTION update_receipt(id INTEGER, receipt BYTEA, receipt_name TEXT)
RETURNS VOID AS $$
BEGIN
    UPDATE ers_reimbursements
        SET r_receipt = receipt,
            r_receipt_name = receipt_name
        WHERE r_id = id;
END;
$$ LANGUAGE plpgsql;


------DELETE-------
CREATE OR REPLACE FUNCTION delete_reimbursement(id INTEGER) RETURNS VOID AS $$
BEGIN
    DELETE FROM ers_reimbursements
        WHERE r_id = id;
END;
$$ LANGUAGE plpgsql;


-- COMMIT: auto-commit enabled
