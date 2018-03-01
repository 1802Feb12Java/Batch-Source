--Users
CREATE TABLE ers_users
(
    u_id NUMBER(*,0) NOT NULL,
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

--User roles
CREATE TABLE ers_user_roles
(
    ur_id NUMBER(*,0),
    ur_roles VARCHAR(40),
    CONSTRAINT ers_user_roles_pk PRIMARY KEY (ur_id)
);

--Reimbursements
CREATE TABLE ers_reimbursements
(
    r_id NUMBER(*,0) NOT NULL,
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
    CONSTRAINT ers_rt_type FOREIGN KEY (rt_id) 
            REFERENCES ers_reimbursement_type (rt_id),
    CONSTRAINT ers_rt_status FOREIGN KEY (rt_status) 
        REFERENCES ers_reimbursement_status (rs_id)
);

--Reimbursement Status
CREATE TABLE  ers_reimbursement_status
(
    rs_id NUMBER(*,0) NOT NULL,
    rs_status VARCHAR2(30) NOT NULL,
    CONSTRAINT ers_reimbursment_status_pk PRIMARY KEY (rs_id)
);

--Reimbursement Type
CREATE TABLE  ers_reimbursement_type
(
    rt_id NUMBER(*,0) NOT NULL,
    rs_type VARCHAR2(30) NOT NULL,
    CONSTRAINT ers_reimbursment_type_pk PRIMARY KEY (rt_id)
);
