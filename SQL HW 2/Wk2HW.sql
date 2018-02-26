/* 
Author - Sonam Sherpa
Assignment - SQL Lab 
Week 2 assignment
*/

-- 1. Opened Chinook_Oracle.sql file and executed scripts

-- 2.1 SQL Queries - SELECT
-- Select all records from the Employee table.
SELECT * FROM EMPLOYEE;

-- Select all records from the Employee table where last name is King.
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';

-- Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM EMPLOYEE WHERE FIRSTNAME ='Andrew' AND REPORTSTO IS NULL;

-- 2.2 SQL Queries - ORDER BY
-- Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM ALBUM ORDER BY TITLE DESC;

-- Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;

-- 2.3 SQL Queries - INSERT INTO
-- Insert two new records into Genre table 
INSERT INTO GENRE VALUES(26,'EDM');
INSERT INTO GENRE VALUES(27,'Party On');

-- Insert two new records into Employee table
INSERT INTO EMPLOYEE VALUES (9,'Sherpa','Sonam','CEO',(null),'14-FEB-95','12-FEB-18','3220 Banyan Dr','Lutz','FL','Merica','31533','1 (781) 345-6789','1 (781) 123-4567','sonam@chinookcorp.com');
INSERT INTO EMPLOYEE VALUES (10,'Smith','John','IT Staff',6,'10-FEB-83','23-JAN-16','213 Real St','Lutz','FL','Merica','31533','1 (213) 213-2130','1 (781) 999-8888','john@chinookcorp.com');

-- Insert two new records into Customer table
INSERT INTO CUSTOMER VALUES(60,'Emma','Stone',(null),'2 New Road','New York','NY','USA',12322,'+12 123 123 123',(null),'emma.stone@yahoo.us',5);
INSERT INTO CUSTOMER VALUES(61,'Brock','Lesner',(null),'3 New Road','New York','NY','USA',12322,'+78 619 784 222',(null),'brock.lesner@yahoo.us',3);

-- 2.4 SQL Queries - UPDATE
-- Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER SET FIRSTNAME = 'Robert',LASTNAME='Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

-- Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
UPDATE ARTIST SET NAME ='CCR' WHERE NAME = 'Creedence Clearwater Revival';

-- 2.5 SQL Queries - LIKE
-- Select all invoices with a billing address like “T%” 
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

-- 2.6 SQL Queries - BETWEEN
-- Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;

-- Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-2003' AND '01-MAR-2004';

-- 2.7 SQL Qureies - DELETE
-- Delete a record in Customer table where the name is Robert Walter 
-- Resolved integrity constraint by finding relationship. CUSTOMERID also used in INVOICE so deleting records referencing it
-- INVOICE also was referenced in INVOICELINE so deleting it from there as well
DELETE FROM INVOICELINE WHERE INVOICEID = ANY(SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32);
DELETE FROM INVOICE WHERE CUSTOMERID = 32;
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

-- 3.1 SQL Functions - System Defined Functions
-- Create a function that returns the current time.
SELECT CURRENT_TIMESTAMP FROM dual;

-- create a function that returns the length of name in MEDIATYPE table
SELECT LENGTH(NAME) FROM MEDIATYPE;
    
-- 3.2 SQL Functions - System Defined Aggregate Functions
-- Create a function that returns the average total of all invoices 
SELECT AVG(TOTAL) FROM INVOICE;

-- Create a function that returns the most expensive track
SELECT NAME FROM TRACK WHERE UNITPRICE = (SELECT MAX(UNITPRICE) FROM TRACK);

--3.3 SQL Function - User Defined Scalar Functions
-- Create a function that returns the average price of invoiceline items (for each invoice) in the invoiceline table
CREATE OR REPLACE FUNCTION avrg_price_invoice 
    RETURN NUMBER AS
    price_avg number(10,2);
    
    BEGIN
    SELECT AVG (UNITPRICE) INTO price_avg
    FROM INVOICELINE;
    RETURN price_avg;
    END;
/

SELECT avrg_price_invoice() FROM DUAL;

-- 3.4 SQL Function - User Defined Table Valued Functions
-- Create a function that returns all employees who are born after 1968.
-- Created an object that has all the necessary information
CREATE OR REPLACE TYPE EMP_OBJ_TYPE
AS OBJECT
( E_ID NUMBER, F_NAME VARCHAR2(20), L_NAME VARCHAR2(20), 
TITLE VARCHAR2(20), BIRTHDATE DATE);
/

-- Created a nested table for the object
CREATE OR REPLACE TYPE EMP_TAB_TYPE
IS TABLE OF EMP_OBJ_TYPE;
/

-- Created a function that can return objects that fit the condition
CREATE OR REPLACE FUNCTION post_1968_employee 
    RETURN EMP_TAB_TYPE
    IS
    E_ID NUMBER; 
    F_NAME VARCHAR2(20);
    L_NAME VARCHAR2(20);
    TITLE VARCHAR2(20);
    BIRTHDATE DATE;
    EMP_DETAILS EMP_TAB_TYPE := EMP_TAB_TYPE ();
    BEGIN
    SELECT EMP_OBJ_TYPE(EMPLOYEEID,FIRSTNAME,LASTNAME,TITLE,BIRTHDATE)
    bulk collect INTO EMP_DETAILS
    FROM EMPLOYEE E
    WHERE E.BIRTHDATE >= '01-JAN-1969';
    RETURN EMP_DETAILS;
    END;
/

SELECT * FROM TABLE(post_1968_employee);

-- 4.1 Stored Procedures - Basic Stored Procedure
-- Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE fullname
(printer out sys_refcursor) 
IS 
BEGIN
    OPEN printer FOR SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
    DBMS_SQL.RETURN_RESULT(printer);
END;
/
VAR refcurs refcursor;
EXECUTE fullname(:refcurs);
/
-- 4.2 Stored Procedure - Input Parameters
-- Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE E_PHONE_UPDATE (E_ID NUMBER, NUMB NUMBER)
IS
BEGIN
    UPDATE EMPLOYEE
    SET PHONE = numb
    WHERE EMPLOYEEID = E_ID;
    DBMS_OUTPUT.PUT_LINE('Phone updated for ' || E_ID);
END;
/

-- Tested out updating phone number
SET SERVEROUTPUT ON;
EXEC E_PHONE_UPDATE(8,123456789);
    
-- Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE MANS_MANAGER (E_ID NUMBER)
IS
P_FNAME EMPLOYEE.FIRSTNAME%TYPE;
P_LNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
    SELECT FIRSTNAME,LASTNAME
    INTO P_FNAME, P_LNAME
    FROM EMPLOYEE
    WHERE EMPLOYEEID = (SELECT REPORTSTO FROM EMPLOYEE WHERE EMPLOYEEID = E_ID);
    DBMS_OUTPUT.PUT_LINE('Manager for Employee ' || E_ID || ' is ' || P_FNAME || ' ' || P_LNAME);
END;
/

SET SERVEROUTPUT ON;
EXEC MANS_MANAGER(7);

-- 4.3 Stored Procedure - Output Parameters
-- Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE C_INFO (C_ID NUMBER, C_FNAME OUT VARCHAR2 ,C_LNAME OUT VARCHAR2,C_COMPANY OUT VARCHAR2)
IS
BEGIN
    SELECT FIRSTNAME, LASTNAME, COMPANY
    INTO C_FNAME, C_LNAME, C_COMPANY
    FROM CUSTOMER
    WHERE CUSTOMERID = C_ID;
END;
/

SET SERVEROUTPUT ON;
DECLARE
C_F CUSTOMER.FIRSTNAME%TYPE;
C_L CUSTOMER.LASTNAME%TYPE;
C_C CUSTOMER.COMPANY%TYPE;
BEGIN
C_INFO(17,C_F,C_L,C_C);
DBMS_OUTPUT.PUT_LINE (C_F || ' ' || C_L || ' is part of ' || C_C);
END;
/

-- 5.0 SQL Queries -  Transactions
-- In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
-- Create a transaction that given a invoiceId will delete that invoice 
-- Deleted invoiceline which referenced invoice first and then the invoice record
CREATE OR REPLACE PROCEDURE DEL_IN (I_ID NUMBER)
IS
BEGIN
DELETE FROM INVOICELINE
WHERE INVOICEID = I_ID;
DELETE FROM INVOICE
WHERE INVOICEID = I_ID;
END;
/

EXEC DEL_IN(216);

-- Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE NEW_CUS (CUSTOMERID NUMBER, FIRSTNAME VARCHAR2, LASTNAME VARCHAR2, COMPANY  VARCHAR2,
ADDRESS  VARCHAR2, CITY  VARCHAR2, STATE  VARCHAR2, COUNTRY  VARCHAR2, POSTALCODE VARCHAR2, PHONE  VARCHAR2, FAX  VARCHAR2,
EMAIL  VARCHAR2, SUPPORTREPID NUMBER)
IS
BEGIN
INSERT INTO CUSTOMER
VALUES (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID);
END;
/

EXEC NEW_CUS(60,'SONAM','SHERPA','REVATURE','123 ROAD ST.','LUTZ','FLORIDA','USA','00001','123456789','987654321','SOSHERPA@REVATURE.COM',3);

-- 6.1 SQL Triggers - AFTER/FOR
-- CREATED TABLE FOR INPUTTING TRIGGER RESPONSES
CREATE TABLE RESULT6(R_ID INT,R_WORD VARCHAR2(20));

-- Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER NEW_EMPLOYEE
    AFTER INSERT ON EMPLOYEE
    FOR EACH ROW
    BEGIN
    INSERT INTO RESULT6 VALUES(1,'NEW EMPLOYEE ADDED');
    END;
/

INSERT INTO EMPLOYEE 
VALUES (10,'LASTNAME', 'FIRSTNAME', 'TITLE', NULL, '01-JAN-1969', '02-FEB-2000', 'ADDRESS', 'CITY', 'STATE', 'COUNTRY', 'POSTALCODE', 'PHONE', 'FAX', 'EMAIL');

-- Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER NEW_ALBUM 
AFTER INSERT ON ALBUM
FOR EACH ROW
BEGIN
    INSERT INTO RESULT6 VALUES(10,'NEW ALBUM ADDED');
END;
/
INSERT INTO ALBUM
VALUES (400,'SONAM IS THE BEST',20);

--DELETE FROM ALBUM
--WHERE ALBUMID = 400;

-- Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER BYE_CUSTOMER
    AFTER DELETE ON CUSTOMER
    BEGIN
    INSERT INTO RESULT6 VALUES(20,'CUSTOMER REMOVED');
END;
/

DELETE FROM CUSTOMER
WHERE CUSTOMERID = 60;

-- 7.1 JOINS - INNER
-- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT CUSTOMER.FIRSTNAME,CUSTOMER.LASTNAME,INVOICE.INVOICEID
FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- 7.2 JOINS - OUTER
-- Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- 7.3 JOINS - RIGHT
-- Create a right join that joins album and artist specifying artist name and title.
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ALBUM RIGHT JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;

-- 7.4 JOINS - CROSS
-- Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * FROM ALBUM CROSS JOIN ARTIST ORDER BY ARTIST.NAME;

-- 7.5 JOINS - SELF
-- Perform a self-join on the employee table, joining on the reportsto column.
SELECT E1.REPORTSTO, E2.REPORTSTO
FROM EMPLOYEE E1, EMPLOYEE E2 WHERE E1.REPORTSTO <> E2.REPORTSTO;



