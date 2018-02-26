/* 2.1 Select */

SELECT * FROM EMPLOYEE; /* select all records*/

SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King'; /* select all records where lastname is King*/

SELECT * FROM EMPLOYEE WHERE LASTNAME = 'Andrew' AND REPORTSTO IS NULL;

/* 2.2 ORDER BY */
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'King';

SELECT TITLE FROM ALBUMS ORDER BY TITLE DESC;

/* 2.3 INSERT INTO*/

INSERT INTO GENRE VALUES ('1','Joe');
INSERT INTO GENRE VALUES ('2','Bob');

INSERT INTO EMPLOYEE VALUES ('1','Smith','Will','Mr','5','10-JUL-1994','01-FEB-2018','3 Kong Drive','Banana','Barrel','USA','03431','6033211121','8987217727','donkeykong@gmail.com');
INSERT INTO EMPLOYEE VALUES ('2','Bob','Joe','Mr','5','05-JAN-1994','07-DEC-2016','5 Phillip Road','Corn','Candy','USA','03030','6851219121','7177887427','blitzking@gmail.com');

INSERT INTO CUSTOMER VALUES ('1','John','Talanian','Revature','3 Pond Drive','Sanscript','New Hampshire','03811','6033277411','8986471134','talanianj@gmail.com','2');
INSERT INTO CUSTOMER VALUES ('2','Abagail','Newland','Microsoft','11 Quincy Drive','Bankville','Ohio','81314','5138784412','8986661134','abigailn@gmail.com','1');

/*2.4 UPDATE */
--Update Aaron Mitchell in Customer table & update it to Robert Walter
UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell'; 
UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

--2.5 LIKE
--Select grab all the invoices that start with a capital T using 'T%' 
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
--2.6 BETWEEN
--Grab all invoices between integers 15 and 50
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;

--Return all employee records where hiredate is in between 2 params
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04'; 

--2.7 DELETE
--All 3 Correct lines for deleting Robert Walter by removing specific constraints , deleting from customer & invoice
DELETE FROM INVOICE WHERE CUSTOMERID = 32;

DELETE FROM customer
WHERE
    firstname = 'Robert'
    AND   lastname = 'Walter';
--Now we delete Robert. Adios!
DELETE FROM invoiceline
WHERE
    invoiceid IN (
        SELECT
            invoiceid
        FROM
            invoice
        WHERE
            customerid = 32
    );

--3.1 System Defined Functions
SELECT
    CURRENT_TIMESTAMP
FROM
    DUAL; --returns current time using CURRENT_TIMESTAMP from system
    
SELECT
    NAME,
    LENGTH(NAME)
FROM
    MEDIATYPE; --returns length of name for each row

--3.2 System Defined: Aggregate Functions
SELECT AVG(TOTAL) FROM INVOICE;  --averages total column

SELECT NAME, UNITPRICE FROM TRACK WHERE UNITPRICE = (SELECT MAX(UNITPRICE) FROM TRACK);
--inner select return the highest priced unit

--3.3 User Defined: Scalar Functions
CREATE OR REPLACE FUNCTION FUNC1
RETURN NUMBER IS AVERAGEINVOICELINE NUMBER(3,2); 
BEGIN
  SELECT AVERAGE(UNITPRICE) INTO AVERAGEINVOICELINE FROM INVOICELINE;
  RETURN AVERAGEINVOICELINE;
END FUNC1;
/
SELECT FUNC1() FROM DUAL;
SELECT AVG(UNITPRICE) FROM INVOICELINE;

--3.4 User Defined Table: Valued Functions
CREATE OR REPLACE FUNCTION FUNC1
RETURN SYS_REFCURSOR AS OLDEMPLOYEENAMES SYS_REFCURSOR;
BEGIN
  OPEN OLDEMPLOYEENAMES FOR SELECT LASTNAME FROM EMPLOYEE WHERE BIRTHDATE > '31-DEC-68'; --31st of 1968 is the final date, so we return all users after this date
  RETURN OLDEMPLOYEENAMES;
END;
/
SELECT FUNC1() FROM DUAL;
SELECT * FROM EMPLOYEE WHERE BIRTHDATE > '31-DEC-68';

--4.1 Basic Stored Procedure
CREATE OR REPLACE PROCEDURE PROC1
(PRINTER OUT SYS_REFCURSOR) IS --parameter of cursor
BEGIN
  OPEN PRINTER FOR SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;  --fill cursor with first & last name from employee
  DBMS_SQL.RETURN_RESULT(PRINTER); --print results
END;
/
VAR REFCURS REFCURSOR;
EXECUTE PROC1(:REFCURS);

--4.2 Stored Procedure Input Params
CREATE OR REPLACE PROCEDURE PROC1
(IDNUM IN INT, NEWFIRSTNAME IN VARCHAR2) IS --Create a proc
BEGIN
  UPDATE EMPLOYEE SET FIRSTNAME = NEWFIRSTNAME WHERE EMPLOYEEID = IDNUM; --update at IDNUM each time
END;
/
EXECUTE PROC1(8, 'Jill'); --execute proc 4 times with different data
EXECUTE PROC1(22, 'Guy');
EXECUTE PROC1(3, 'Jennifer');
EXECUTE PROC1(11, 'Bill');

CREATE OR REPLACE PROCEDURE PROC1
(IDNUM IN INT, PRINT OUT SYS_REFCURSOR) IS
BEGIN
  OPEN PRINT FOR SELECT CONCAT(A.FIRSTNAME, A.LASTNAME) AS SUBORDINATE, CONCAT(B.FIRSTNAME, B.LASTNAME) AS BOSS FROM EMPLOYEE A JOIN EMPLOYEE B ON B.EMPLOYEEID = A.REPORTSTO AND A.EMPLOYEEID = IDNUM;
  DBMS_SQL.RETURN_RESULT(PRINT); --print results
END;
/
VAR REFCURS REFCURSOR;
EXECUTE PROC1(1, :REFCURS); --no return
EXECUTE PROC1(2, :REFCURS);

--4.3 Stored Procedure Output Params
CREATE OR REPLACE PROCEDURE PROC1
(IDNUM IN INT, PRINT OUT SYS_REFCURSOR) IS
BEGIN
  OPEN PRINT FOR SELECT FIRSTNAME, LASTNAME, COMPANY FROM CUSTOMER WHERE CUSTOMERID = IDNUM; --fill cursor with all first and last names from employee
  DBMS_SQL.RETURN_RESULT(PRINT); --print resultset
END;
/
VAR REFCURS REFCURSOR;
EXECUTE PROC1(1, :REFCURS); --execute defined proc 
EXECUTE PROC1(2, :REFCURS);

--5.0 Transactions
CREATE OR REPLACE PROCEDURE PROC1
(IDNUM IN INT) IS
BEGIN
  DELETE FROM INVOICELINE WHERE INVOICEID = IDNUM;  --remove all invoicelines with foreign keys pointing to given invoiceid
  DELETE FROM INVOICE WHERE INVOICEID = IDNUM;    --remove invoice from: invoice table
  COMMIT; --commit the change
END;
/
EXECUTE PROC1(200); --execute the proc with value 200

CREATE OR REPLACE PROCEDURE PROC1
(CID IN INT, FNAME IN VARCHAR2, LNAME IN VARCHAR2, AA IN VARCHAR2, CITY IN VARCHAR2, STAT IN VARCHAR2, COUNTR IN VARCHAR2, POST IN VARCHAR2, PHONENUM IN VARCHAR2, EMAIL IN VARCHAR2, REPID IN INT) IS
BEGIN    --define a set of parameters to then be executed for an insert statement when called in EXECUTE below
  INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, EMAIL, SUPPORTREPID) VALUES (CID, FNAME, LNAME, AA, CITY, STAT, COUNTR, POST, PHONENUM, EMAIL, REPID);
END;
/
EXECUTE PROC1(130, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 99);


--6.1 AFTER&FOR 
CREATE SEQUENCE EMPLOYEEIDSEQUENCEINCREMENT --increments customer id on every insert
MINVALUE 0
MAXVALUE 9999999
START WITH 3
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER EMPLOYEETRIGGER --Create trigger to update customerid to value from our employeeidsequenceincrement
AFTER INSERT ON EMPLOYEE
BEGIN
UPDATE EMPLOYEE SET EMPLOYEEID = EMPLOYEEIDSEQUENCEINCREMENT.NEXTVAL; 
END;
/
CREATE OR REPLACE TRIGGER EMPLOYEETRIGGER --creating the trigger to update the customerid to the value from the sequence
AFTER INSERT ON EMPLOYEE
BEGIN
UPDATE EMPLOYEE SET FIRSTNAME = 'CAROL'; --jeez, carol . (sets name to carol. too many carols)
END;
/

INSERT INTO EMPLOYEE VALUES (50, 'aa', 'bb', 'cc', 'dd', '01-JAN-99', '02-FEB-99', '3 Metroid Prime Drive', 'Aran', 'DZ', 'USA', '99999', '1234555', '678999', 'onemoretime@gmail.com');

CREATE OR REPLACE TRIGGER ALBUMTRIGGER
AFTER INSERT ON ALBUM
BEGIN
UPDATE ALBUM SET TITLE = 'The Alphabet' WHERE TITLE LIKE 'Goner%';
END;
/
INSERT INTO ALBUM VALUES (222, 'RevatureMusic', 10); 

CREATE OR REPLACE TRIGGER CUSTOMERTRIGGER
AFTER DELETE ON CUSTOMER
BEGIN
UPDATE CUSTOMER SET COMPANY = 'Revature' WHERE CUSTOMERID = 2;
END;
/
DELETE FROM INVOICELINE WHERE INVOICEID IN (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 1); --delete all the invoices from customer 1
DELETE FROM INVOICE WHERE CUSTOMERID = 1;  --also delete it from the invoice table
DELETE FROM CUSTOMER WHERE CUSTOMERID = 1; --also delete it from the customer table

--7.1 INNER JOIN
SELECT C.FIRSTNAME, I.INVOICEID FROM CUSTOMER C INNER JOIN INVOICE I ON I.CUSTOMERID = C.CUSTOMERID;  --inner join where customerid ==, alias customer as: C, invoice as: I

--7.2 OUTER JOIN
SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME, I.INVOICEID, I.TOTAL FROM CUSTOMER C FULL OUTER JOIN INVOICE I ON I.CUSTOMERID = C.CUSTOMERID;
--outer join the two on same condition as last
-- return requested data

--7.3 RIGHT JOIN
SELECT ART.NAME, ALB.TITLE FROM ALBUM ALB RIGHT JOIN ARTIST ART ON ART.ARTISTID = ALB.ARTISTID;   --return album title and artist when both match

--7.4 CROSS JOIN
SELECT * FROM ALBUM ALB CROSS JOIN ARTIST ART ORDER BY ART.NAME ASC; --return most records with a cross join

--7.5 SELF JOIN
SELECT CONCAT(A.FIRSTNAME, A.LASTNAME) AS SUBORDINATE, CONCAT(B.FIRSTNAME, B.LASTNAME) AS BOSS FROM EMPLOYEE A JOIN EMPLOYEE B ON A.REPORTSTO = B.EMPLOYEEID;
      --concat the firstname and lastname of each. alias each. joins where first employee table reportsto == second employee id

