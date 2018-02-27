/************************************
 *  CREATE TABLES FOR THE BANKING APP
 ************************************/

--Users
CREATE TABLE Users
(
	UserId NUMBER NOT NULL,
	AccessLevel NUMBER(1) NOT NULL,
	FirstName VARCHAR2(20) NOT NULL,
	LastName VARCHAR2(20) NOT NULL,
	Username VARCHAR2(16) NOT NULL,
	Password VARCHAR2(20) NOT NULL,
	Address VARCHAR2(40) NOT NULL, 
	City VARCHAR2(15) NOT NULL,
	Zip VARCHAR2(5) NOT NULL,
	State VARCHAR2(2) NOT NULL,
	Country VARCHAR2(20) NOT NULL,
	Phone VARCHAR2(13) NOT NULL,
	CONSTRAINT PK_Users PRIMARY KEY (UserId)
);

--Accounts
CREATE TABLE Accounts
(
	AccountId NUMBER NOT NULL,
	Owner NUMBER NOT NULL,
	AccNumber VARCHAR2(19) NOT NULL,
    Balance DEC(38,2) NOT NULL,
	IsJoint NUMBER(1) NOT NULL,
	JointOwner NUMBER,
	Status NUMBER(1) NOT NULL,
	CONSTRAINT PK_Accounts PRIMARY KEY (AccountId),
    --Adding Foreign Key to the Accounts table
    CONSTRAINT FK_AccountOwnerId FOREIGN KEY (Owner) REFERENCES Users (UserId)
);


--Adding Foreign Key to the Accounts table
--ALTER TABLE Accounts ADD CONSTRAINT FK_AccountOwnerId FOREIGN KEY (Owner) REFERENCES Users (UserId);

--Sequence for incrementing user id's
CREATE SEQUENCE USER_ID
MINVALUE 1
MAXVALUE 999999
START WITH 1
INCREMENT BY 1;

--Sequence for incrementing account id's
CREATE SEQUENCE ACCOUNT_ID
MINVALUE 1
MAXVALUE 999999
START WITH 1
INCREMENT BY 1;
