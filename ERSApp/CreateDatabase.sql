#create tables w/o fks first
create table ERS_REIMBURSEMENT_STATUS(
	RS_ID NUMBER(*,0) PRIMARY KEY,
    RS_STATUS VARCHAR2(30) NOT NULL
);
create table ERS_REIMBURSEMENT_TYPE(
	RT_ID NUMBER(*,0) PRIMARY KEY,
    RT_TYPE VARCHAR2(30) NOT NULL
);
create table ERS_USER_ROLES(
	UR_ID NUMBER(*,0) PRIMARY KEY,
    UR_ROLE VARCHAR2(40)
);
create table ERS_USERS(
	U_ID NUMBER(*,0) PRIMARY KEY,
    U_USERNAME VARCHAR2(40) UNIQUE,
    U_PASSWORD VARCHAR2(40) NOT NULL,
    U_FIRSTNAME VARCHAR2(30),
    U_LASTNAME VARCHAR2(30),
    U_EMAIL VARCHAR2(100) UNIQUE,
    UR_ID NUMBER(*,0) NOT NULL,
    constraint UR_ID foreign key(UR_ID) 
    references ERS_USER_ROLES(UR_ID) 
);
create table ERS_REIMBURSEMENTS(
	R_ID NUMBER(*,0) PRIMARY KEY,
    R_AMOUNT NUMBER(22,2) NOT NULL,
    R_DESCRIPTION VARCHAR2(100),
    R_RECEIPT BLOB,
    R_SUBMITTED TIMESTAMP NOT NULL,
    R_RESOLVED TIMESTAMP,
    U_ID_AUTHOR NUMBER(*,0) NOT NULL,
    U_ID_RESOLVER NUMBER(*,0),
    RT_TYPE NUMBER(*,0) NOT NULL,
    RT_STATUS NUMBER(*,0) NOT NULL,
    
    constraint U_ID_AUTHOR foreign key(U_ID_AUTHOR) 
    references ERS_USERS(U_ID),
    
     constraint U_ID_RESOLVER foreign key(U_ID_RESOLVER) 
    references ERS_USERS(U_ID),
	
    constraint RT_TYPE foreign key(RT_TYPE) 
    references ERS_REIMBURSEMENT_TYPE(RT_ID),
    
	constraint RT_STATUS foreign key(RT_STATUS) 
    references ERS_REIMBURSEMENT_STATUS(RS_ID)
    
);

--TRIGGERS FOR GENERATING SEQ IDS
CREATE SEQUENCE USER_ID_SEQ;

CREATE OR REPLACE TRIGGER GIVE_U_ID
BEFORE INSERT ON ERS_USERS 
FOR EACH ROW
DECLARE 
    USER_ID NUMBER;
BEGIN
    SELECT USER_ID_SEQ.NEXTVAL INTO USER_ID
     FROM DUAL;
     If :NEW.U_ID = 0 THEN
     :new.U_ID := USER_ID;
    End if;
END;
/

--TRIGGERS FOR GENERATING SEQ IDS
CREATE SEQUENCE REIM_ID_SEQ;

CREATE OR REPLACE TRIGGER GIVE_R_ID
BEFORE INSERT ON ERS_REIMBURSEMENTS 
FOR EACH ROW
DECLARE 
    RE_ID NUMBER;
BEGIN
    SELECT REIM_ID_SEQ.NEXTVAL INTO RE_ID
     FROM DUAL;
     If :NEW.R_ID = 0 THEN
     :new.R_ID := RE_ID;
    End if;
END;
/
    
--Insert in USER_ROLE types
INSERT INTO ERS_USER_ROLES VALUES(0, 'Admin');
INSERT INTO ERS_USER_ROLES VALUES(1, 'Test');
INSERT INTO ERS_USER_ROLES VALUES(2, 'Employee');
INSERT INTO ERS_USER_ROLES VALUES(3, 'Manager');

--Insert into ERS_REIMBURSEMENT_TYPE
INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(0, 'Business');
INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(1, 'Travel');
INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(2, 'Medical');
INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(3, 'Other');

--Insert in ERS_REIMBURSMENT_STATUS
INSERT INTO ERS_REIMBURSEMENT_STATUS VALUES(0, 'Pending');
INSERT INTO ERS_REIMBURSEMENT_STATUS VALUES(1, 'Approved');
INSERT INTO ERS_REIMBURSEMENT_STATUS VALUES(2, 'Denied');


--Make some default users
insert into ERS_USERS values(1000, 'Admin', 'Admin', 'Admin', 'Admin', 'Admin@Admin.org', 0);
insert into ERS_USERS values(1001, 'testU', 'testP', 'testF', 'testL', 'test@test.test', 2);
insert into ERS_USERS values(1002, 'Emp', 'Emp', 'empF', 'empL', 'employee@emp.com', 2);
insert into ERS_USERS values(1003, 'Mange', 'Mange', 'manF', 'manL', 'manager@man.com', 3);

--Insert some "real" employees
insert into ERS_USERS values(1012, 'Dory', 'Dory', 'Dory', 'DeGeneres', 'justKeepSwimming@fishies.com', 2);
insert into ERS_USERS values(1013, 'Marlin', 'Marlin', 'Marlin', 'Dude', 'mySon@fishies.com', 2);
insert into ERS_USERS values(1014, 'MrRay', 'MrRay', 'Mr.', 'Ray', 'allAboard@fishies.com', 2);
insert into ERS_USERS values(1015, 'Squirt', 'Squirt', 'Squirt', 'Turtle', 'dude@fishies.com', 2);
insert into ERS_USERS values(1019, 'Crush', 'Crush', 'Crush', 'Turtle', 'grabShell@fishies.com', 2);
insert into ERS_USERS values(1016, 'Bruce', 'Bruce', 'Bruce', 'Shark', 'heresBrucey@fishies.com', 2);
insert into ERS_USERS values(1017, 'Anchor', 'Anchor', 'Anchor', 'Shark', 'fishAreFriends@fishies.com', 2);
insert into ERS_USERS values(1018, 'Chum', 'Chum', 'Chum', 'Shark', 'dolphinsAreNotCute@fishies.com', 2);

commit;

    