/*
    Johne Vang
    SQL LAB 
    DUE 2/26/2018
*/

/*
    2.1 SELECT
    Task – Select all records from the Employee table.
    Task – Select all records from the Employee table where last name is King.
    Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
*/
SELECT * FROM EMPLOYEE;
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

/*
    2.2 ORDER BY
    Task – Select all albums in Album table and sort result set in descending order by title.
    Task – Select first name from Customer and sort result set in ascending order by city
*/
SELECT * FROM ALBUM ORDER BY TITLE DESC;
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;

/*
    2.3 INSERT INTO
    Task – Insert two new records into Genre table 
    Task – Insert two new records into Employee table
    Task – Insert two new records into Customer table
*/
INSERT INTO GENRE (GENREID, NAME) VALUES(26, 'TECHNO');
INSERT INTO EMPLOYEE 
(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, 
HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) 
VALUES(9, 'VANG', 'JOHNE', 'REGIONAL MANAGER', NULL, 
'14-AUG-92', '07-MAY-17', 'NOWHERE', 'CITY', 'STATE', 'USA', '99999', '123-456-7890', '987-654-3210', 'email@email.com');

INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
VALUES (60, 'No', 'ONE', 'NO COMPANY', 'NOADDRESS', 'CITY', 'STATE', 'COUNTRY', '12345', '123-4567', '987-6543', 'email@email.com', 5);

INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) 
VALUES (62, 'SOME', 'ONE', 'NO COMPANY', 'NOADDRESS', 'CITY', 'STATE', 'COUNTRY', '12345', '123-4567', '987-6543', 'email2@email.com', 5);

/*
    2.4 UPDATE
    Task – Update Aaron Mitchell in Customer table to Robert Walter
    Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
*/

UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE CUSTOMERID = 32;
UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

/*
    2.5 LIKE
    Task – Select all invoices with a billing address like “T%” 
*/
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

/*
    2.6 BETWEEN
    Task – Select all invoices that have a total between 15 and 50
    Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
*/
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

/*
    2.7 DELETE
    Task – Delete a record in Customer table where the name is Robert Walter 
    (There may be constraints that rely on this, find out how to resolve them).
*/
DELETE FROM INVOICELINE WHERE INVOICEID = 116;
DELETE FROM INVOICE WHERE CUSTOMERID = 32;
DELETE FROM CUSTOMER WHERE CUSTOMERID = 32;

/*
    3.1 System Defined Functions
    Task – Create a function that returns the current time.
    Task – create a function that returns the length of name in MEDIATYPE table
*/
CREATE OR REPLACE FUNCTION get_current_time
RETURN DATE AS get_current_time DATE;
    BEGIN
        SELECT CURRENT_DATE
        INTO get_current_time
        FROM DUAL;
    RETURN get_current_time;
    END;
/
SELECT TO_CHAR(get_current_time, 'HH:MM:SS' ) AS Current_Time FROM DUAL;

--struggling with finding the length of each name
CREATE OR REPLACE FUNCTION get_name_mediatype
RETURN VARCHAR2 AS name_mediatype VARCHAR2;
    BEGIN
    SELECT NAME INTO name_mediatype FROM MEDIATYPE;
    RETURN name_mediatype;
    END;
/
SELECT length(get_name_mediatype) FROM DUAL;

/*
    3.2 System Defined Aggregate Functions
    Task – Create a function that returns the average total of all invoices 
    Task – Create a function that returns the most expensive track
*/
CREATE OR REPLACE FUNCTION avgTotalInvoice
RETURN NUMBER AS average NUMBER;
    BEGIN
    SELECT cast(avg(TOTAL) as decimal(10, 2)) INTO average FROM INVOICE;
    RETURN average;
    END;
    /
SELECT avgTotalInvoice FROM DUAL;

CREATE OR REPLACE FUNCTION mostExpensiveTrack
RETURN NUMBER AS maxPrice NUMBER;
    BEGIN
    SELECT MAX(UNITPRICE) INTO maxPrice FROM TRACK;
    RETURN maxPrice;
    END;
    /
SELECT mostExpensiveTrack FROM DUAL;

/*
    3.3 User Defined Scalar Functions
    Update for 3.3 on the homework: Create a function that returns the average price of invoiceline items 
    (for each invoice) in the invoiceline table.
*/
CREATE OR REPLACE FUNCTION AVGPRICEINVOICELINE
RETURN NUMBER AS AVGPRICE NUMBER(3, 2);
    BEGIN
    SELECT avg(UNITPRICE) INTO AVGPRICE FROM INVOICELINE;
    RETURN AVGPRICE;
    END;
/
SELECT AVGPRICEINVOICELINE FROM DUAL;

/*
    3.4 User Defined Table Valued Functions
    Task – Create a function that returns all employees who are born after 1968.
    Stuck on comparing the date...
*/
SET SERVEROUTPUT ON;
DECLARE 
E_ID EMPLOYEE.EMPLOYEEID%TYPE;
E_FNAME EMPLOYEE.FIRSTNAME%TYPE;
E_LNAME EMPLOYEE.LASTNAME%TYPE;
E_BDAY EMPLOYEE.BIRTHDATE%TYPE;
CURSOR employees1968_curs IS
    SELECT EMPLOYEEID, LASTNAME, FIRSTNAME, BIRTHDATE FROM EMPLOYEE
    WHERE BIRTHDATE < TO_DATE('31-DEC-68', 'DD-MM-YY');
    BEGIN
    OPEN employees1968_curs;
    LOOP 
    FETCH employees1968_curs INTO E_ID, E_FNAME, E_LNAME, E_BDAY;
    EXIT WHEN employees1968_curs%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(E_ID ||  ' ' || E_FNAME || ' ' || E_LNAME || ' ' || E_BDAY);
    END LOOP;
    CLOSE employees1968_curs;
    END;
    /

/*
    4.0 Stored Procedures
    In this section you will be creating and executing stored procedures. 
    You will be creating various types of stored procedures that take input and output parameters.

    4.1 Basic Stored Procedure
    Task – Create a stored procedure that selects the first and last names of all the employees.
    -- stuck with calling the procedure
*/

CREATE OR REPLACE PROCEDURE get_first_and_last_names
(emp_cursor OUT get_first_last_cursor)
AS
BEGIN
   OPEN emp_cursor FOR 
   SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END get_first_and_last_names;
/

/*
    4.2 Stored Procedure Input Parameters
    Task – Create a stored procedure that updates the personal information of an employee.
    Task – Create a stored procedure that returns the managers of an employee.
    Didn't have time to go complete this
*/

/*
    4.3 Stored Procedure Output Parameters
    Task – Create a stored procedure that returns the name and company of a customer.
    Didn't have time to go complete this
*/

/*
    5.0 Transactions
    In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
    Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
    Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
    Didn't have time to go complete this
*/


/*
    6.0 Triggers
    In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.

    6.1 AFTER/FOR
    Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
    Task – Create an after update trigger on the album table that fires after a row is inserted in the table
    Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
    Didn't have time to go complete this
*/


/*
    7.1 INNER
    Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
*/
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID 
FROM CUSTOMER
INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

/*
    7.2 OUTER
    Task – Create an outer join that joins the customer and invoice table, 
    specifying the CustomerId, firstname, lastname, invoiceId, and total.
*/
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER
FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

/*
    7.3 RIGHT
    Task – Create a right join that joins album and artist specifying artist name and title.
*/
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ALBUM
RIGHT JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;

/*
    7.4 CROSS
    Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
*/
SELECT ARTIST.NAME FROM ALBUM
CROSS JOIN ARTIST ORDER BY ARTIST.NAME;

/*
    7.5 SELF
    Task – Perform a self-join on the employee table, joining on the reportsto column.
    
    filtering the result set so that employeeID is not equal to itself
*/
SELECT * FROM EMPLOYEE E, EMPLOYEE E2
WHERE E.EMPLOYEEID <> E2.EMPLOYEEID
AND E.REPORTSTO = E2.REPORTSTO
ORDER BY E.REPORTSTO;