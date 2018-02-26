--2.1----------------------------------------------------------------------------------------------------------------------
SELECT * FROM EMPLOYEE;

SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';

SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

--2.2----------------------------------------------------------------------------------------------------------------------
SELECT * FROM ALBUM ORDER BY TITLE DESC;

SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;

--2.3----------------------------------------------------------------------------------------------------------------------

INSERT INTO GENRE VALUES(26, 'Folk');
INSERT INTO GENRE VALUES(27, 'Do Bop');

INSERT INTO EMPLOYEE VALUES(9, 'Tentacles', 'Squidward', 'Cashier', 2, NULL, NULL, '2 BIKINI BOTTOM LN', 'BIKINI BOTTOM',
'CA', 'USA', '12489', '1 (222) 869-4728', '1 (222) 555-1222', 'squidwardrocks1@gmail.com');
INSERT INTO EMPLOYEE VALUES(10, 'Deschain', 'Roland', 'Gunslinger', NULL, NULL, NULL, '1 Temple Court', 'Gilead',
'', 'In World', '19991', '1 (191) 919-1919', '1 (521) 323-8673', 'roland@aol.com');

INSERT INTO CUSTOMER VALUES(60, 'Billy', 'Le Kid', NULL, '1 Main Street', 'Butts', 'MO', 'USA', '42301', '1 (800) 726-7816',
'1 (555) 192-2954', 'blk@hotmail.com', 4);
INSERT INTO CUSTOMER VALUES(61, 'Frank', 'Sinatra', NULL, '1 Heart Drive', 'Los Angeles', 'CA', 'USA', '90001', '1 (800) 123-7816',
'1 (222) 595-2464', 'sinatraf@hotmail.com', 3);

--2.4----------------------------------------------------------------------------------------------------------------------
UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

--2.5----------------------------------------------------------------------------------------------------------------------
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

--2.6----------------------------------------------------------------------------------------------------------------------
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;

SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

--2.7----------------------------------------------------------------------------------------------------------------------
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
--SELECT * FROM INVOICE WHERE CUSTOMERID = 32; I used this to find the Invoice ids for robert so I could know which rows to delete in Invoiceline
--DELETE FROM INVOICE WHERE CUSTOMERID = 32;
--DELETE FROM INVOICELINE WHERE INVOICEID = 50 OR INVOICEID = 61;

--3.1----------------------------------------------------------------------------------------------------------------------
SELECT CURRENT_TIMESTAMP FROM DUAL;

SELECT LENGTH(NAME) FROM MEDIATYPE;

--3.2----------------------------------------------------------------------------------------------------------------------
SELECT AVG(TOTAL) FROM INVOICE;

SELECT MAX(UNITPRICE) FROM TRACK;

--3.3----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION AVGPRICE
    (ID_NUM IN INT)
    RETURN NUMBER AS 
    AVGUNITPRICE NUMBER(10, 2);
BEGIN
    SELECT AVG(UNITPRICE) INTO AVGUNITPRICE FROM INVOICELINE WHERE INVOICEID = ID_NUM;
    RETURN AVGUNITPRICE;
END;
/
SELECT AVGPRICE(103) FROM INVOICELINE FETCH FIRST ROW ONLY;


--3.4----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION BIRTHDAY
RETURN VARCHAR IS PEEPS VARCHAR(20);
BEGIN
SELECT FIRSTNAME INTO PEEPS FROM EMPLOYEE WHERE BIRTHDATE > '31-DEC-1968';
RETURN PEEPS;
END;
/
SELECT BIRTHDAY FROM DUAL;
SELECT * FROM EMPLOYEE WHERE BIRTHDATE > '31-DEC-1968';
--4.1----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION NAMES1
(EMPID INT)
RETURN VARCHAR AS FULLNAME VARCHAR(60);
BEGIN
    SELECT CONCAT(FIRSTNAME, LASTNAME) INTO FULLNAME FROM EMPLOYEE WHERE EMPLOYEEID = EMPID;
END;
/
CREATE OR REPLACE PROCEDURE NAMES
AS
BEGIN
    SELECT NAMES1(1) FROM DUAL;
    SELECT NAMES1(2) FROM DUAL;
    SELECT NAMES1(3) FROM DUAL;
    SELECT NAMES1(4) FROM DUAL;
    SELECT NAMES1(5) FROM DUAL;
    SELECT NAMES1(6) FROM DUAL;
    SELECT NAMES1(7) FROM DUAL;
    SELECT NAMES1(8) FROM DUAL;
    SELECT NAMES1(9) FROM DUAL;
END;
/
EXECUTE NAMES;

--4.2----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UPDATE_PERSONAL
(EID IN NUMBER, L_N IN VARCHAR2, F_N IN VARCHAR2, N_A IN VARCHAR2, N_C IN VARCHAR2, N_S IN VARCHAR2, N_PC IN VARCHAR2, N_P IN VARCHAR2, N_FAX IN VARCHAR2, N_EMAIL IN VARCHAR2) AS
BEGIN
    UPDATE EMPLOYEE SET LASTNAME = L_N, FIRSTNAME = F_N, ADDRESS = N_A, CITY = N_C, STATE = N_S, POSTALCODE = N_PC,
    PHONE = N_P, FAX = N_FAX, EMAIL = N_EMAIL WHERE EMPLOYEEID = EID;
END;
/
EXECUTE UPDATE_PERSONAL(9, 'Deschain', 'Roland', '1 Temple Court', 'Gilead', 'IN WORLD', '19991', '(191) 919-1919', '(521) 323-8673', 'ROLAND@GMAIL.COM');

CREATE OR REPLACE FUNCTION HELPME
(M_ID IN INT) 
RETURN VARCHAR2 AS MAN_NAME VARCHAR2(20);
BEGIN
    SELECT FIRSTNAME INTO MAN_NAME FROM EMPLOYEE WHERE EMPLOYEEID = M_ID;
    RETURN MAN_NAME;
END;
/
CREATE OR REPLACE FUNCTION HELPME2
(EID IN INT)
RETURN INT AS MID INT;
BEGIN
    SELECT REPORTSTO INTO MID FROM EMPLOYEE WHERE EMPLOYEEID = EID;
    RETURN MID;
END;
/

CREATE OR REPLACE PROCEDURE WHOS_THAT_MANAGER
(EID IN INT) 
AS
BEGIN
    SELECT HELPME2(EID) FROM DUAL;
END;
/

--4.3----------------------------------------------------------------------------------------------------------------------
DECLARE 
C_FNAME CUSTOMER.FIRSTNAME%TYPE;
C_LNAME CUSTOMER.LASTNAME%TYPE;
C_COMP CUSTOMER.COMPANY%TYPE;
CURSOR C_CURS IS SELECT FIRSTNAME, LASTNAME, COMPANY FROM CUSTOMER;
BEGIN
    OPEN C_CURS;
    LOOP
    FETCH C_CURS INTO C_FNAME, C_LNAME, C_COMP;
    EXIT WHEN C_CURS%NOTFOUND;
    END LOOP;
    CLOSE C_CURS;
END;
/

CREATE OR REPLACE PROCEDURE NAME_AND_COMP
(CID IN INT) IS
BEGIN
    OPEN C_CURS;
    SELECT C_CURS FROM CUSTOMER WHERE CID = CUSTOMERID;
    CLOSE C_CURS;
END;
/
    

--5.0----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE DELETE_INVOICE
(IID IN INT)
AS
BEGIN
    DELETE FROM INVOICELINE WHERE IID = INVOICEID;
    DELETE FROM INVOICE WHERE IID = INVOICEID;
    COMMIT;
END;
/
EXECUTE DELETE_INVOICE(61);

CREATE OR REPLACE PROCEDURE NEW_CUST
(ID IN INT, FNAME IN VARCHAR2, LNAME IN VARCHAR2, EMAIL IN VARCHAR2)
AS
BEGIN
    INSERT INTO CUSTOMER VALUES( ID, FNAME, LNAME, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, EMAIL, NULL);
    COMMIT;
END;
/
EXECUTE NEW_CUST(84, 'CHARLES', 'SPURGEON', 'LAKJDF@GMAIL.COM');

--6.1----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER EMPTRIG
AFTER INSERT ON EMPLOYEE

BEGIN
    UPDATE EMPLOYEE SET LASTNAME = 'Butts';
END;
/
INSERT INTO EMPLOYEE VALUES(9, 'Tentacles', 'Squidward', 'Cashier', 2, NULL, NULL, '2 BIKINI BOTTOM LN', 'BIKINI BOTTOM',
'CA', 'USA', '12489', '1 (222) 869-4728', '1 (222) 555-1222', 'squidwardrocks1@gmail.com');

CREATE OR REPLACE TRIGGER ALBUMTRIG
AFTER UPDATE ON ALBUM
BEGIN
    INSERT INTO ALBUM VALUES (9000, 'BABY ITS COLD OUTSIDE', 1);
END;
/
UPDATE ALBUM SET TITLE = 'FISH' WHERE ALBUMID = 1;

CREATE OR REPLACE TRIGGER CUSTOMERTRIG
AFTER DELETE ON CUSTOMER
BEGIN
    INSERT INTO CUSTOMER VALUES(59,	'Puja',	'Srivastava', 'HELLO', '3 Raj Bhavan Road',	'Bangalore', NULL, 'India', '560001', '+91 080 22289999', 
    NULL, 'puja_srivastava@yahoo.in', 3);
END;
/
DELETE FROM CUSTOMER WHERE CUSTOMERID = 59;

--7.1 INNER JOIN----------------------------------------------------------------------------------------------------------------------
SELECT FIRSTNAME, LASTNAME, INVOICEID FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.2 OUTER JOIN----------------------------------------------------------------------------------------------------------------------
SELECT CUSTOMER.CUSTOMERID, FIRSTNAME, LASTNAME, INVOICEID, TOTAL FROM CUSTOMER FULL OUTER JOIN 
INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.3 RIGHT JOIN----------------------------------------------------------------------------------------------------------------------
SELECT NAME, TITLE FROM ALBUM RIGHT JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;

--7.4 CROSS JOIN----------------------------------------------------------------------------------------------------------------------
SELECT * FROM ALBUM CROSS JOIN ARTIST ORDER BY ARTIST.NAME;

--7.5 SELF JOIN----------------------------------------------------------------------------------------------------------------------
SELECT AL.FIRSTNAME AS SERVANT, B.FIRSTNAME AS MASTA 
FROM EMPLOYEE AL JOIN EMPLOYEE B ON B.EMPLOYEEID = AL.REPORTSTO; 