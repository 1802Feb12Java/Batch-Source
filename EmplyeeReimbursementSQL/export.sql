--------------------------------------------------------
--  File created - Thursday-March-15-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence ID_GEN
--------------------------------------------------------

   CREATE SEQUENCE  "SGM4789"."ID_GEN"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE   ;
--------------------------------------------------------
--  DDL for Sequence REIBURSEMENTSEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "SGM4789"."REIBURSEMENTSEQUENCE"  MINVALUE 1 MAXVALUE 1000000000 INCREMENT BY 1 START WITH 32 CACHE 20 NOORDER  NOCYCLE   ;
--------------------------------------------------------
--  DDL for Table ERS_REIMBURSEMENTS
--------------------------------------------------------

  CREATE TABLE "SGM4789"."ERS_REIMBURSEMENTS" 
   (	"R_ID" NUMBER(*,0), 
	"R_AMOUNT" NUMBER(22,2), 
	"R_DESCRIPTION" VARCHAR2(100 BYTE), 
	"R_RECEIPT" BLOB, 
	"R_SUBMITTED" TIMESTAMP (6), 
	"U_ID_AUTHOR" NUMBER(*,0), 
	"U_ID_RESOLVER" NUMBER(*,0), 
	"RT_TYPE" NUMBER(*,0), 
	"RT_STATUS" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("R_RECEIPT") STORE AS SECUREFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES 
  STORAGE(INITIAL 106496 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table ERS_REIMBURSEMENT_STATUS
--------------------------------------------------------

  CREATE TABLE "SGM4789"."ERS_REIMBURSEMENT_STATUS" 
   (	"RS_ID" NUMBER(*,0), 
	"RS_STATUS" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ERS_REIMBURSEMENT_TYPE
--------------------------------------------------------

  CREATE TABLE "SGM4789"."ERS_REIMBURSEMENT_TYPE" 
   (	"RT_ID" NUMBER(*,0), 
	"RT_TYPE" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ERS_USERS
--------------------------------------------------------

  CREATE TABLE "SGM4789"."ERS_USERS" 
   (	"U_ID" NUMBER(*,0), 
	"U_USERNAME" VARCHAR2(40 BYTE), 
	"U_PASSWORD" VARCHAR2(40 BYTE), 
	"U_FIRSTNAME" VARCHAR2(30 BYTE), 
	"U_LASTNAME" VARCHAR2(30 BYTE), 
	"U_EMAIL" VARCHAR2(100 BYTE), 
	"UR_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ERS_USER_ROLES
--------------------------------------------------------

  CREATE TABLE "SGM4789"."ERS_USER_ROLES" 
   (	"UR_ID" NUMBER(*,0), 
	"UR_ROLE" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into SGM4789.ERS_REIMBURSEMENTS
SET DEFINE OFF;
Insert into SGM4789.ERS_REIMBURSEMENTS (R_ID,R_AMOUNT,R_DESCRIPTION,R_SUBMITTED,U_ID_AUTHOR,U_ID_RESOLVER,RT_TYPE,RT_STATUS) values (1,1000,'may i have some more',null,0,1,1,2);
Insert into SGM4789.ERS_REIMBURSEMENTS (R_ID,R_AMOUNT,R_DESCRIPTION,R_SUBMITTED,U_ID_AUTHOR,U_ID_RESOLVER,RT_TYPE,RT_STATUS) values (11,10000,'and some more',null,0,2,1,0);
Insert into SGM4789.ERS_REIMBURSEMENTS (R_ID,R_AMOUNT,R_DESCRIPTION,R_SUBMITTED,U_ID_AUTHOR,U_ID_RESOLVER,RT_TYPE,RT_STATUS) values (5,233,'This is another description',null,0,1,0,2);
Insert into SGM4789.ERS_REIMBURSEMENTS (R_ID,R_AMOUNT,R_DESCRIPTION,R_SUBMITTED,U_ID_AUTHOR,U_ID_RESOLVER,RT_TYPE,RT_STATUS) values (12,100000,'this is the last time i swear',null,0,2,1,0);
Insert into SGM4789.ERS_REIMBURSEMENTS (R_ID,R_AMOUNT,R_DESCRIPTION,R_SUBMITTED,U_ID_AUTHOR,U_ID_RESOLVER,RT_TYPE,RT_STATUS) values (4,240,'This is a description.',null,0,1,0,1);
REM INSERTING into SGM4789.ERS_REIMBURSEMENT_STATUS
SET DEFINE OFF;
Insert into SGM4789.ERS_REIMBURSEMENT_STATUS (RS_ID,RS_STATUS) values (0,'Pending');
Insert into SGM4789.ERS_REIMBURSEMENT_STATUS (RS_ID,RS_STATUS) values (1,'Approved');
Insert into SGM4789.ERS_REIMBURSEMENT_STATUS (RS_ID,RS_STATUS) values (2,'Denied');
REM INSERTING into SGM4789.ERS_REIMBURSEMENT_TYPE
SET DEFINE OFF;
Insert into SGM4789.ERS_REIMBURSEMENT_TYPE (RT_ID,RT_TYPE) values (0,'Travel');
Insert into SGM4789.ERS_REIMBURSEMENT_TYPE (RT_ID,RT_TYPE) values (1,'Business');
Insert into SGM4789.ERS_REIMBURSEMENT_TYPE (RT_ID,RT_TYPE) values (2,'Medical');
REM INSERTING into SGM4789.ERS_USERS
SET DEFINE OFF;
Insert into SGM4789.ERS_USERS (U_ID,U_USERNAME,U_PASSWORD,U_FIRSTNAME,U_LASTNAME,U_EMAIL,UR_ID) values (0,'test','pass','RonaldMcDonald','Testerson','changedEmail@gmail.com',0);
Insert into SGM4789.ERS_USERS (U_ID,U_USERNAME,U_PASSWORD,U_FIRSTNAME,U_LASTNAME,U_EMAIL,UR_ID) values (1,'TheBigJ','dunny','Jeffy','Dunham','bigJeffy@gmail.com',1);
Insert into SGM4789.ERS_USERS (U_ID,U_USERNAME,U_PASSWORD,U_FIRSTNAME,U_LASTNAME,U_EMAIL,UR_ID) values (2,'user','user','kdnjk','Testsdajkdsham','sdanksd@gmail.com',0);
REM INSERTING into SGM4789.ERS_USER_ROLES
SET DEFINE OFF;
Insert into SGM4789.ERS_USER_ROLES (UR_ID,UR_ROLE) values (0,'Employee');
Insert into SGM4789.ERS_USER_ROLES (UR_ID,UR_ROLE) values (1,'Manager');
--------------------------------------------------------
--  DDL for Trigger REIMBURSEMENTTRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "SGM4789"."REIMBURSEMENTTRIGGER" 
AFTER INSERT ON ERS_REIMBURSEMENTS
BEGIN
    UPDATE ERS_REIMBURSEMENTS
    SET R_ID = ReibursementSequence.NEXTVAL
    WHERE R_ID = 1000000001;
END;
/
ALTER TRIGGER "SGM4789"."REIMBURSEMENTTRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Procedure NEW_PERSON
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "SGM4789"."NEW_PERSON" 
(FNAME IN VARCHAR2, LNAME IN VARCHAR2)
AS
BEGIN
    INSERT INTO PEOPLES VALUES(ID_GEN.NEXTVAL, FNAME, LNAME);
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure REMOVE_EMP
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "SGM4789"."REMOVE_EMP" () AS
   BEGIN
      select ERS_REIMBURSEMENTS.R_AMOUNT, ERS_REIMBURSEMENTS.R_DESCRIPTION, ERS_REIMBURSEMENTS.R_RECEIPT, ERS_REIMBURSEMENTS.R_SUBMITTED, ERS_USERS.U_USERNAME AS U_ID_AUTHOR,
    ERS_REIMBURSEMENT_TYPE.RT_TYPE AS RT_TYPE, ERS_REIMBURSEMENT_STATUS.RS_STATUS AS RT_STATUS
    from ers_reimbursements
    left join ers_users
    on u_id_author = ers_users.u_id
    left join ers_reimbursement_type
    on ers_reimbursements.rt_type = ers_reimbursement_type.rt_id
    left join ers_reimbursement_status
    on ers_reimbursements.rt_status = ers_reimbursement_status.rs_id;
   END;

/
--------------------------------------------------------
--  Constraints for Table ERS_REIMBURSEMENTS
--------------------------------------------------------

  ALTER TABLE "SGM4789"."ERS_REIMBURSEMENTS" ADD PRIMARY KEY ("R_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ERS_REIMBURSEMENT_TYPE
--------------------------------------------------------

  ALTER TABLE "SGM4789"."ERS_REIMBURSEMENT_TYPE" ADD PRIMARY KEY ("RT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ERS_USER_ROLES
--------------------------------------------------------

  ALTER TABLE "SGM4789"."ERS_USER_ROLES" ADD PRIMARY KEY ("UR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ERS_REIMBURSEMENT_STATUS
--------------------------------------------------------

  ALTER TABLE "SGM4789"."ERS_REIMBURSEMENT_STATUS" ADD PRIMARY KEY ("RS_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ERS_USERS
--------------------------------------------------------

  ALTER TABLE "SGM4789"."ERS_USERS" ADD UNIQUE ("U_EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SGM4789"."ERS_USERS" ADD UNIQUE ("U_PASSWORD")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SGM4789"."ERS_USERS" ADD UNIQUE ("U_USERNAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SGM4789"."ERS_USERS" ADD PRIMARY KEY ("U_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SGM4789"."ERS_USERS" MODIFY ("U_USERNAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table ERS_REIMBURSEMENTS
--------------------------------------------------------

  ALTER TABLE "SGM4789"."ERS_REIMBURSEMENTS" ADD FOREIGN KEY ("U_ID_AUTHOR")
	  REFERENCES "SGM4789"."ERS_USERS" ("U_ID") ENABLE;
  ALTER TABLE "SGM4789"."ERS_REIMBURSEMENTS" ADD FOREIGN KEY ("U_ID_RESOLVER")
	  REFERENCES "SGM4789"."ERS_USERS" ("U_ID") ENABLE;
  ALTER TABLE "SGM4789"."ERS_REIMBURSEMENTS" ADD FOREIGN KEY ("RT_TYPE")
	  REFERENCES "SGM4789"."ERS_REIMBURSEMENT_TYPE" ("RT_ID") ENABLE;
  ALTER TABLE "SGM4789"."ERS_REIMBURSEMENTS" ADD FOREIGN KEY ("RT_STATUS")
	  REFERENCES "SGM4789"."ERS_REIMBURSEMENT_STATUS" ("RS_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ERS_USERS
--------------------------------------------------------

  ALTER TABLE "SGM4789"."ERS_USERS" ADD FOREIGN KEY ("UR_ID")
	  REFERENCES "SGM4789"."ERS_USER_ROLES" ("UR_ID") ENABLE;
