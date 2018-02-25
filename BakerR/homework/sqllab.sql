-- SQL Lab
-- rollback;
SET SERVEROUTPUT ON;

-- 2.1 select
-- Task: Select all records from employee
SELECT * FROM EMPLOYEE;

-- Task: Select all employees with the last name 'King'
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';

-- Task: Select all employees with the first name 'Andrew' which reports to nobody.
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
/

-- 2.2 order by
-- Task: Select all albums and order by descending title order
SELECT * FROM ALBUM ORDER BY TITLE DESC;

-- Task: Select the FIRSTNAME column from CUSTOMER and put in ascending order by city.
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;
/

-- 2.3 INSERT INTO
-- Task: Insert new genres KPOP and JPOP.
INSERT INTO GENRE VALUES(26, 'KPOP'); 
INSERT INTO GENRE VALUES(27, 'JPOP');

-- Task: Insert 2 new employees into employee table.
--  Note: Yes, these locations are real.
INSERT INTO EMPLOYEE VALUES(9, 'Hill', 'Hank', 'Sales Support Agent', 2,
    '20-JUL-1987', '02-DEC-2010', 
    '124 Memes Way', 'Wando', 'SC', 'United States', '29492', 
    '1-123-456-7890', '1-123-456-7899', 'best@memes.org');
INSERT INTO EMPLOYEE VALUES(10, 'Lee', 'Bob', 'IT Staff', 6,
    '30-NOV-1968', '18-JAN-2002', 
    '1 This St', 'Porters Lake', 'NS', 'Canada', 'B3E 1H4', 
    '1-987-654-3210', '1-987-654-3211', 'best@streetnames.net');

-- Task: Insert 2 new customers into customer table.
INSERT INTO CUSTOMER VALUES(60, 'Hank', 'Hill', 'Strickland Propane',
    '9001 Memes Way', 'Wando', 'SC', 'United States', '29492', 
    '1-123-456-7890', '1-123-456-7899', 'best@memes.org', 9);
INSERT INTO CUSTOMER VALUES(61, 'Bob', 'Lee', NULL,
    '1 This St', 'Porters Lake', 'NS', 'Canada', 'B3E 1H4', 
    '1-987-654-3210', '1-987-654-3211', 'best@streetnames.net', 10);
/


-- 2.4 UPDATE
-- Task: Change customer's name.
UPDATE CUSTOMER SET FIRSTNAME='Robert', LASTNAME='Walter'
WHERE FIRSTNAME='Aaron' AND LASTNAME='Mitchell';

-- Task: Change artist name to its acronym.
UPDATE ARTIST SET NAME='CCR' 
WHERE NAME='Creedence Clearwater Revival';
/

-- 2.5 LIKE
-- Task: Select all records with billing addresses starting with letter 'T'
SELECT * FROM INVOICE 
WHERE BILLINGADDRESS LIKE 'T%';
/

-- BETWEEN
-- Task: Select all invoices where total price is in the range [15,50]
SELECT * FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
-- Task: Select all employees hired between June 2003 and March 2004.
SELECT * FROM EMPLOYEE
WHERE HIREDATE BETWEEN '1-JUN-2003' AND '1-MAR-2004';
/

-- 2.7 DELETE
-- default constraint on FOREIGN KEY columns is ON DELETE RESTRICT; 
--  drop and set to ON DELETE CASCADE to delete record & dependents to avoid broken references.
ALTER TABLE INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;
ALTER TABLE INVOICE
ADD CONSTRAINT FK_INVOICECUSTOMERID
    FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID)
    ON DELETE CASCADE;

ALTER TABLE INVOICELINE
DROP CONSTRAINT FK_INVOICELINEINVOICEID;
ALTER TABLE INVOICELINE
ADD CONSTRAINT FK_INVOICELINEINVOICEID
    FOREIGN KEY(INVOICEID) REFERENCES INVOICE(INVOICEID)
    ON DELETE CASCADE;

-- Task: Delete the Customer 'Robert Walter'.    
DELETE FROM CUSTOMER
WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';
/

-- 3.1 System Defined Functions
-- Task: Get local time.
CREATE OR REPLACE FUNCTION GETSYSTIME
    RETURN TIMESTAMP
    IS
        TS TIMESTAMP;
    BEGIN
        SELECT LOCALTIMESTAMP INTO TS FROM DUAL;
        RETURN TS;
    END GETSYSTIME;
/

-- Task: Get length of the name of a media-type.
CREATE OR REPLACE FUNCTION MEDIATYPE_LEN(ID IN NUMBER)
    RETURN NUMBER
IS
    LEN NUMBER;
    NAME VARCHAR2;
BEGIN
    SELECT NAME INTO NAME FROM MEDIATYPE;
    LEN := LENGTH(NAME);
    RETURN LEN;
END MEDIATYPE_LEN;
/


-- 3.2 SYSTEM DEFINED AGGREGATE FUNCTIONS
-- Task: Get the average price of all the invoices.
CREATE OR REPLACE FUNCTION AVG_TTL_INVOICE
    RETURN NUMBER
IS
    AVG_INVOICE NUMBER(38,2);
BEGIN
    SELECT AVG(TOTAL) INTO AVG_INVOICE FROM INVOICE;
    RETURN AVG_INVOICE;
END AVG_TTL_INVOICE;
/

-- Task: Get the cost of the most expensive track(s).
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK
    RETURN NUMBER
IS
    MAXPRICE NUMBER;
BEGIN
    SELECT MAX(UNITPRICE) INTO MAXPRICE FROM TRACK
    ORDER BY TRACKID
    FETCH FIRST 1 ROWS ONLY;
    RETURN MAXPRICE;
END MOST_EXPENSIVE_TRACK;
/


-- 3.3 User-Defined Scalar Functions
-- Task: Return a cursor containing the average price of each
--          invoice.    
CREATE OR REPLACE FUNCTION AVG_INVOICE_PRICES
    RETURN SYS_REFCURSOR
IS
    AVG_VALS SYS_REFCURSOR;
BEGIN
    OPEN AVG_VALS FOR
        SELECT INVOICEID, (AVG(UNITPRICE)) FROM INVOICELINE
            GROUP BY INVOICEID
            ORDER BY INVOICEID ASC;
    RETURN AVG_VALS;
END AVG_INVOICE_PRICES;
/

-- 3.4 User-Defined Table valued Functions
-- Task: Returns a cursor containing all employees born after 1968.
CREATE OR REPLACE FUNCTION POST68_EMPLOYEES
    RETURN SYS_REFCURSOR
IS
    EMP SYS_REFCURSOR;
BEGIN
    OPEN EMP FOR
        SELECT * FROM EMPLOYEE
        WHERE BIRTHDATE > '31-DEC-1968';
    RETURN EMP;    
END POST68_EMPLOYEES;
/


-- 4.1 Basic Stored Procedure
-- Task: Output a cursor containing the first and last name columns of employees.
CREATE OR REPLACE PROCEDURE SEL_EMP_NAMES(EMPLOYEES OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN EMPLOYEES FOR
        SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END SEL_EMP_NAMES;
/

-- 4.2 Stored Procedure Input Parameters
-- Task: Update personal info of an employee
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE_ADDRESS
    (ID IN NUMBER, NADDR IN VARCHAR2, NCITY IN VARCHAR2, 
     NSTATE IN VARCHAR2, NCOUNTRY IN VARCHAR2, NZIP IN VARCHAR2)
IS
BEGIN
    UPDATE EMPLOYEE
    SET ADDRESS=NADDR, CITY=NCITY, STATE=NSTATE, COUNTRY=NCOUNTRY, POSTALCODE=NZIP
    WHERE EMPLOYEEID=ID;
END;
/

-- Task: Return Manager of an employee
CREATE OR REPLACE PROCEDURE GET_MANAGER(MANAGERS OUT SYS_REFCURSOR, EMP_ID IN NUMBER)
IS
    MANAGER_ID NUMBER;
BEGIN
    SELECT REPORTSTO INTO MANAGER_ID FROM EMPLOYEE;
    
    OPEN MANAGERS FOR
        SELECT * FROM EMPLOYEE WHERE EMPLOYEEID=MANAGER_ID;
END;
/

-- 5.0 Transactions
-- Task: Delete given invoice. During setup, set constraints of
-- relevant foreign keys to 'on delete cascade'. Refer to first task in 2.7.
CREATE OR REPLACE PROCEDURE DELETE_INVOICE(INVOICE_ID IN NUMBER)
IS
BEGIN
    DELETE FROM INVOICE
        WHERE INVOICEID=INVOICE_ID;
    COMMIT;
END;
/

-- Task: Insert a new customer in customer table
CREATE OR REPLACE PROCEDURE PUT_NEW_CUSTOMER
    (FNAME IN VARCHAR2, LNAME IN VARCHAR2, COMPANY IN VARCHAR2,
     CADDR IN VARCHAR2, CCITY IN VARCHAR2, CSTATE IN VARCHAR2, CCOUNTRY IN VARCHAR2, CZIP IN VARCHAR2,
     CPHONE IN VARCHAR2, CFAX IN VARCHAR2, CEMAIL IN VARCHAR2, CSUPPORTREPID IN NUMBER)
IS
    NEWID NUMBER;
BEGIN
    SELECT MAX(CUSTOMERID) INTO NEWID FROM CUSTOMER;
    NEWID := NEWID + 1;
    
    INSERT INTO CUSTOMER
        VALUES(NEWID, FNAME, LNAME, COMPANY, 
            CADDR, CCITY, CSTATE, CCOUNTRY, CZIP, 
            CPHONE, CFAX, CEMAIL, CSUPPORTREPID);
    COMMIT;
END;
/


-- 6.1 Triggers [After/For]:
-- Task: After-insert trigger on employee table; fired on new record insert.
CREATE OR REPLACE TRIGGER NEW_EMPLOYEE_TRIGGER
AFTER INSERT ON EMPLOYEE
BEGIN
    DBMS_OUTPUT.PUT_LINE('TRIGGERED EMPLOYEE AFTER INSERT');
END;
/

-- Task: After-update trigger on ALBUM; fires after row is inserted
CREATE OR REPLACE TRIGGER UPDATE_ALBUM_TRIGGER
AFTER UPDATE ON ALBUM
BEGIN
    DBMS_OUTPUT.PUT-LINE('TRIGGERED ALBUM AFTER UPDATE');
END;
/

-- Task: After-delete trigger on CUSTOMER; fires after row deleted
CREATE OR REPLACE TRIGGER DEL_CUSTOMER_TRIGGER
AFTER DELETE ON CUSTOMER
BEGIN
    DBMS_OUTPUT.PUT_LINE('TRIGGERED CUSTOMER AFTER DELETION');
END;
/


-- 7.0 Joins
-- 7.1 INNER JOIN
-- Task: Inner Join -> Customers and Orders; specify customer name and invoice id
SELECT INVO.INVOICEID, CUST.FIRSTNAME, CUST.LASTNAME
    FROM CUSTOMER CUST
INNER JOIN INVOICE INVO
ON CUST.CUSTOMERID=INVO.CUSTOMERID;
/

-- 7.2 OUTER JOIN
-- Task: Outer Join -> Customer & Invoice tables; customer first/last names, invoice id, & total 
SELECT INVO.INVOICEID, CUST.FIRSTNAME, CUST.LASTNAME, INVO.TOTAL
    FROM CUSTOMER CUST
FULL JOIN INVOICE INVO
ON CUST.CUSTOMERID=INVO.CUSTOMERID;
/

-- 7.3 RIGHT JOIN
-- Task: Right Join -> album and artist tables; artist name & album title
SELECT ART.NAME, ALB.TITLE
    FROM ALBUM ALB
RIGHT JOIN ARTIST ART
ON ALB.ARTISTID =ART.ARTISTID;
/

-- 7.4 CROSS JOIN
-- Task: Cross join -> album and artist; sort by artist name, ascending
SELECT ART.NAME, ALB.TITLE
    FROM ALBUM ALB
CROSS JOIN ARTIST ART
ORDER BY ART.NAME ASC;
/

-- 7.5 SELF JOIN
-- Task: Self join -> employee table, join on REPORTSTO column
SELECT E1.FIRSTNAME, E1.LASTNAME, E2.FIRSTNAME, E2.LASTNAME
    FROM EMPLOYEE E1, EMPLOYEE E2
    WHERE E1.REPORTSTO=E2.EMPLOYEEID;
/