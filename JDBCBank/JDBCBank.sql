CREATE TABLE CustomerAccount(
    CustomerID int Primary Key,
    Username Varchar(25) Unique,
    Pass Varchar(25),
    FirstName Varchar(25),
    LastName Varchar(25),
    Age int,
    AccountNumber int,
    TotalBalance decimal,
    CheckingBalance decimal, 
    SavingsBalance decimal,
    Active Number(1));
    
CREATE TABLE EmployeeAccount(
    EmployeeID int,
    Username Varchar(25) Unique,
    Pass Varchar(25),
    FirstName Varchar(25),
    LastName Varchar(25));

--DROP TABLE EmployeeAccount;
--DROP TABLE  AdminAccount;
DROP TABLE CUSTOMERACCOUNT;
Commit;

CREATE TABLE AdminAccount(
    AdminID int,
    Username Varchar(25) Unique,
    Pass Varchar(25),
    FirstName Varchar(25),
    LastName Varchar(25));
    
CREATE TABLE JointAccount(
    JointID int,
    CUSTOMERID int, 
    Username Varchar(25) Unique,
    Pass Varchar(25),
    FirstName Varchar(25),
    LastName Varchar(25),
    FOREIGN KEY (CUSTOMERID) REFERENCES CustomerAccount(CustomerID));
    
CREATE SEQUENCE CustomerIDSequence
MINVALUE 0
MAXVALUE 9999
START WITH 0
INCREMENT BY 1;

CREATE SEQUENCE EmployeeIDSequence
MINVALUE 0
MAXVALUE 9999
START WITH 0
INCREMENT BY 1;

CREATE SEQUENCE AdminIDSequence
MINVALUE 0
MAXVALUE 9999
START WITH 0
INCREMENT BY 1;

CREATE SEQUENCE JointAIDSequence
MINVALUE 0
MAXVALUE 9999
START WITH 0
INCREMENT BY 1;

CREATE SEQUENCE CustomerAccNumSequence
MINVALUE 100000000
MAXVALUE 1000000000
START WITH 100000000
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER InsertCustomerTrigger
AFTER INSERT ON CustomerAccount
BEGIN
    UPDATE CustomerAccount 
    SET CustomerID = CustomerIDSequence.NEXTVAL
    WHERE CustomerID = 1000;
END;

CREATE OR REPLACE TRIGGER InsertEmployeeTrigger
AFTER INSERT ON EmployeeAccount
BEGIN
    UPDATE EmployeeAccount 
    SET EmployeeID = EmployeeIDSequence.NEXTVAL
    WHERE EmployeeID = 1000;
END;

CREATE OR REPLACE TRIGGER InsertAdminTrigger
AFTER INSERT ON AdminAccount
BEGIN
    UPDATE AdminAccount 
    SET AdminID = AdminIDSequence.NEXTVAL
    WHERE AdminID = 1000;
END;

CREATE OR REPLACE TRIGGER AccountNumberTrigger
AFTER INSERT ON CustomerAccount
BEGIN
    UPDATE CustomerAccount 
    SET AccountNumber = CustomerAccNumSequence.NEXTVAL
    WHERE AccountNumber = 1;
END;

CREATE OR REPLACE TRIGGER InsertJointTrigger
AFTER INSERT ON JointAccount
BEGIN
    UPDATE JointAccount 
    SET JointID = JointAIDSequence.NEXTVAL
    WHERE JointID = 1000;
END;

INSERT INTO CustomerAccount
Values(1000, 'Zelarous', 'something', 'Seth', 'Maize', 23, 1, 0, 0, 0, 0);

INSERT INTO CustomerAccount
Values(1000, 'Sekatsu', 'something', 'Seth', 'Maize', 23, 1, 0, 0, 0, 0);

INSERT INTO CustomerAccount
Values(1000, 'Sekatsu1', 'something', 'Seth', 'Maize', 23, 1, 0, 0, 0, 0);

INSERT INTO CustomerAccount
Values(1000, 'Sekatsu12', 'something', 'Seth', 'Maize', 23, 1, 0, 0, 0, 0);

INSERT INTO JointAccount
Values(1000, 43, 'Joint', 'Joint', 'Joint', 'Joint');

INSERT INTO EmployeeAccount
Values(1000, 'Steve3789', 'secret', 'Steve', 'Hogger');

INSERT INTO AdminAccount
Values(1000, 'Admin', 'Admin', 'Admin', 'Admin');

INSERT INTO AdminAccount
Values(1000, 'Seth', 'password', 'Seth', 'Maize');

SELECT * FROM AdminAccount;

SELECT * FROM JointAccount;