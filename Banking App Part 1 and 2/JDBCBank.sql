CREATE TABLE userLog (
    userID int PRIMARY KEY,
    userName varchar(20),
    userPassword varchar(20),
    userType varchar(20),
    firstName varchar(20),
    lastName varchar(20)
);

CREATE TABLE  accountLog(
    accountID int PRIMARY KEY,
    userID int,
    adtnlUser int,
    accountStatus varchar(6),
    accountType varchar(20),
    balance decimal(10,2),
    FOREIGN KEY (userid)
        REFERENCES userLog(userID)
        ON DELETE CASCADE
);

CREATE TABLE applicationLog(
    applicationID int PRIMARY KEY,
    userID int,
    applicationType varchar(20),
    adtnlUser varchar(20),
    status varchar(20),
    FOREIGN KEY (userid)
        REFERENCES userLog(userID)
        ON DELETE CASCADE
);

CREATE TABLE transactionLog(
    transactionID int PRIMARY KEY,
    accountID int,
    transactionType varchar(20),
    amountOfTransaction int,
    dateOfTransaction date,
    performedBy int,
    FOREIGN KEY (accountid)
        REFERENCES accountLog(accountID)
        ON DELETE CASCADE,
    FOREIGN KEY (performedBy)
        REFERENCES userLog(userID)
        ON DELETE CASCADE
);

CREATE SEQUENCE userIDSequence
    START WITH 1000
    INCREMENT BY 1
;

CREATE SEQUENCE accountIDSequence
    START WITH 2000
    INCREMENT BY 1
;

CREATE SEQUENCE applicationIDSequence
    START WITH 3000
    INCREMENT BY 1
;

CREATE SEQUENCE transactionIDSequence
    START WITH 4000
    INCREMENT BY 1
;
COMMIT;
/


--the below functions are used to keep my sequences sequential.
CREATE OR REPLACE FUNCTION GETNEXTUSERID
RETURN INT AS NEXTINSEQ INT;
    BEGIN
        SELECT USERIDSEQUENCE.NEXTVAL INTO NEXTINSEQ FROM DUAL;
        RETURN NEXTINSEQ;
    END;
/

CREATE OR REPLACE FUNCTION GETNEXTACCOUNTID
RETURN INT AS NEXTINSEQ INT;
    BEGIN
        SELECT ACCOUNTIDSEQUENCE.NEXTVAL INTO NEXTINSEQ FROM DUAL;
        RETURN NEXTINSEQ;
    END;
/
CREATE OR REPLACE FUNCTION GETNEXTAPPID
RETURN INT AS NEXTINSEQ INT;
    BEGIN
        SELECT APPLICATIONIDSEQUENCE.NEXTVAL INTO NEXTINSEQ FROM DUAL;
        RETURN NEXTINSEQ;
    END;
/
CREATE OR REPLACE FUNCTION GETNEXTTRANSID
RETURN INT AS NEXTINSEQ INT;
    BEGIN
        SELECT TRANSACTIONIDSEQUENCE.NEXTVAL INTO NEXTINSEQ FROM DUAL;
        RETURN NEXTINSEQ;
    END;
/

--my stored procedure.
CREATE OR REPLACE PROCEDURE INSERTNEWUSER
(userName IN VARCHAR, pass IN VARCHAR, uType IN VARCHAR, fname IN VARCHAR, lname IN VARCHAR) AS
    BEGIN
        INSERT INTO userLog (userID, userName, userPassword, userType, firstName, lastName)
        VALUES (GETNEXTUSERID(), userName, pass, uType, fname, lname);
    COMMIT;
    END;
/    


