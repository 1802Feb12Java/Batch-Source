/*
Week 2 sql homework

In this section you will be performing various queries against the Oracle Chinook database.
2.1 SELECT
Task – Select all records from the Employee table.
*/

/*
Task – Select all records from the Employee table where last name is King.
*/
SELECT * FROM EMPLOYEE WHERE LASTNAME='King';
/*
Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
*/
SELECT * FROM EMPLOYEE WHERE FIRSTNAME='Andrew' AND REPORTSTO=NULL;
/*


2.2 ORDER BY
Task – Select all albums in Album table and sort result set in descending order by title.
*/
SELECT * FROM ALBUM ORDER BY TITLE DESC;
/*
Task – Select first name from Customer and sort result set in ascending order by city
*/
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;
/*


2.3 INSERT INTO
Task – Insert two new records into Genre table 
*/
INSERT INTO GENRE VALUES (26, 'Revature'); 
INSERT INTO GENRE VALUES (26, 'Roll Tide!!');
/*
Task – Insert two new records into Employee table
*/
SELECT * FROM EMPLOYEE;
INSERT INTO EMPLOYEE VALUES(9, 'Revature', 'Cowabunga', 'President', NULL, '01-JAN-55', '02-JAN-55', '1000 Revature ave', 'Tampa', 'FL', 'US', '30392', '232-233-2344',  '232-849-8757',  'cowabunga@revature.com');
INSERT INTO EMPLOYEE VALUES(10, 'Revature', 'YooHoo', 'Vice President', 9, '25-DEC-77', '01-JAN-10', '1000 Revature ave', 'Tampa', 'FL', 'US', '30392', '232-233-2344', '232-849-8757', 'yoohoo@revature.com' );
/*
Task – Insert two new records into Customer table
*/
SELECT * FROM CUSTOMER;
INSERT INTO CUSTOMER VAlUES(60, 'Revature', 'BullDog', 'Revature', '1000 Revature ave', 'Tampa', 'FL', 'US', '30392', '232-233-2344',  '232-849-8757',  'cowabunga@revature.com', 1);
INSERT INTO CUSTOMER VAlUES(61, 'Revature', 'Falcon', 'Revature', '1000 Revature ave', 'Tampa', 'FL', 'US', '30392', '232-233-2344',  '232-849-8757',  'cowabunga@revature.com', 1);
/*


2.4 UPDATE
Task – Update Aaron Mitchell in Customer table to Robert Walter
*/
UPDATE CUSTOMER SET FIRSTNAME='Robert', LASTNAME='Walter' WHERE FIRSTNAME='Aaron' AND LASTNAME='Mitchell';
/*
Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
*/
UPDATE ARTIST SET NAME='CCR' WHERE NAME='Creedence Clearwater Revival';

/*


2.5 LIKE
Task – Select all invoices with a billing address like “T%” 
*/
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
/*


2.6 BETWEEN
Task – Select all invoices that have a total between 15 and 50
*/
SELECT * FROM INVOICE WHERE TOTAL>=15 AND TOTAL<=50;
/*
Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
*/
SELECT * FROM EMPLOYEE WHERE HIREDATE>'01-JUN-03' AND HIREDATE<'01-MAR-04';
/*


2.7 DELETE
Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
*/
SELECT FIRSTNAME, LASTNAME FROM CUSTOMER WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';
DELETE FROM INVOICELINE WHERE INVOICEID IN (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID IN (SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME='Robert'  AND LASTNAME='Walter'));
DELETE FROM INVOICE WHERE CUSTOMERID=(SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME='Robert'  AND LASTNAME='Walter');
DELETE FROM CUSTOMER WHERE FIRSTNAME='Robert'  AND LASTNAME='Walter';
/*


SQL Functions
In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
3.1 System Defined Functions
------------------------------------------------------------------------------------------------------
Task – Create a function that returns the current time.
------------------------------------------------------------------------------------------------------
               FUNCTION
------------------------------------------------------------------------------------------------------
*/
create or replace FUNCTION GETCURRTIME 
RETURN TIMESTAMP WITH TIME ZONE
IS 
CURRTIME TIMESTAMP WITH TIME ZONE;
BEGIN
   SELECT CURRENT_TIMESTAMP INTO CURRTIME  FROM DUAL;
   RETURN CURRTIME;
END GETCURRTIME;
/
SELECT GETCURRTIME FROM DUAL;
/*
------------------------------------------------------------------------------------------------------
Task – create a function that returns the length of name in MEDIATYPE table
------------------------------------------------------------------------------------------------------
               FUNCTION
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE FUNCTION NAMELEN(MEDIA_ID IN INTEGER)
RETURN INTEGER AS NAMELENGTH INTEGER;
BEGIN
    SELECT LENGTH(NAME) INTO NAMELENGTH FROM MEDIATYPE WHERE MEDIATYPEID=MEDIA_ID;
    RETURN NAMELENGTH;
END;
/
SELECT NAMELEN(2) FROM DUAL;
SELECT NAME FROM MEDIATYPE WHERE MEDIATYPEID=2;
/*
------------------------------------------------------------------------------------------------------
                        3.2 System Defined Aggregate Functions
Task – Create a function that returns the average total of all invoices 
------------------------------------------------------------------------------------------------------
               FUNCTION
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE FUNCTION GETINVOICEAVG
RETURN INTEGER IS INVTOTAL INTEGER;
BEGIN
    SELECT AVG(TOTAL) INTO INVTOTAL FROM INVOICE;
    RETURN INVTOTAL;
END GETINVOICEAVG;
/
SELECT GETINVOICEAVG FROM DUAL;
/*
------------------------------------------------------------------------------------------------------
Task – Create a function that returns the most expensive track
------------------------------------------------------------------------------------------------------
                FUNCTION
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE FUNCTION GETMAXPRICE 
RETURN INTEGER IS MAXPRICE INTEGER;
BEGIN
    SELECT MAX(UNITPRICE) INTO MAXPRICE FROM TRACK;
    RETURN MAXPRICE;
END GETMAXPRICE;
/
SELECT GETMAXPRICE FROM DUAL;
/*
------------------------------------------------------------------------------------------------------
                            3.3 User Defined Scalar Functions
Task – Create a function that returns the average price of invoiceline items in the invoiceline table
------------------------------------------------------------------------------------------------------
               FUNCTION
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE FUNCTION GETAVGPRICE
RETURN NUMBER AS AVGPRICE NUMBER(10, 2);
BEGIN
    SELECT AVG(UNITPRICE) INTO AVGPRICE FROM INVOICELINE;
    RETURN AVGPRICE;
END GETAVGPRICE;
/
SELECT GETAVGPRICE FROM DUAL;
/*
------------------------------------------------------------------------------------------------------
3.4 User Defined Table Valued Functions
Task – Create a function that returns all employees who are born after 1968.
------------------------------------------------------------------------------------------------------
               FUNCTION
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE FUNCTION YOUNGEMPLOYEES
RETURN REFCURSOR AS YOUNGNS SYS_REFCURSOR;
BEGIN
    SELECT * INTO YOUNGNS FROM EMPLOYEE WHERE BIRTHDATE>='01-JAN-69';
    RETURN YOUNGNS;
    CLOSE YOUNGNS;
END YOUNGEMPLOYEES;
/
SELECT YOUNGEMPLOYEES FROM DUAL;

/*
4.0 Stored Procedures
 In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
4.1 Basic Stored Procedure
Task – Create a stored procedure that selects the first and last names of all the employees.
------------------------------------------------------------------------------------------------------
               PROCEDURE
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE PROCEDURE GETALLEMPLOYEES(
INCURSOR OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN INCURSOR FOR SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
    DBMS_SQL.RETURN_RESULT(INCURSOR);
    CLOSE INCURSOR;
END GETALLEMPLOYEES;
/
VAR OUTCURSOR2 REFCURSOR;
EXECUTE GETALLEMPLOYEES(:OUTCURSOR2);
CLOSE OUTCURSOR2;
/*
4.2 Stored Procedure Input Parameters
Task – Create a stored procedure that updates the personal information of an employee.
------------------------------------------------------------------------------------------------------
               PROCEDURE
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE PROCEDURE UPDATEEMPLOYEE
(EMPID IN INTEGER,
EMPTITLE IN VARCHAR2)
AS
BEGIN
    UPDATE EMPLOYEE SET TITLE=EMPTITLE WHERE EMPLOYEEID=EMPID;
    COMMIT;
END UPDATEEMPLOYEE;
/
EXECUTE UPDATEEMPLOYEE(1, 'Revature');
SELECT EMPLOYEEID, TITLE FROM EMPLOYEE WHERE EMPLOYEEID=1;

/*
Task – Create a stored procedure that returns the managers of an employee.
------------------------------------------------------------------------------------------------------
               PROCEDURE
------------------------------------------------------------------------------------------------------
*/
create or replace PROCEDURE EMPMANAGER(
EMPID IN INTEGER, INCURSOR2 OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN INCURSOR2 FOR SELECT * FROM EMPLOYEE WHERE EMPLOYEEID IN (SELECT REPORTSTO FROM EMPLOYEE);
    DBMS_SQL.RETURN_RESULT(INCURSOR2);
    CLOSE INCURSOR2;
END EMPMANAGER;
/

VAR EMPMAN REFCURSOR;
EXECUTE EMPMANAGER(4, :EMPMAN);
CLOSE EMPAN;
SELECT * FROM EMPLOYEE WHERE EMPLOYEEID=2;
    
/*
4.3 Stored Procedure Output Parameters
Task – Create a stored procedure that returns the name and company of a customer.
------------------------------------------------------------------------------------------------------
               PROCEDURE
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE PROCEDURE EMPNAMECOMP(
custid in INTEGER, INCURSOR3 OUT SYS_REFCURSOR)
AS
BEGIN
    DECLARE
    CURSOR TESTCURSOR IS
    OPEN INCURSOR3 FOR SELECT FIRSTNAME, LASTNAME, COMPANY FROM CUSTOMER WHERE CUSTOMERID=custid;
    DBMS_SQL.RETURN_RESULT(INCURSOR3);
    CLOSE INCURSOR3;
END EMPNAMECOMP;
/
VAR OUTCURSOR3 REFCURSOR;
EXECUTE EMPNAMECOMP(4, :OUTCURSOR3);
CLOSE OUTCURSOR3;
/
/*
5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
Task – Create a transaction that given a invoiceId will delete that invoice 
(There may be constraints that rely on this, find out how to resolve them).
------------------------------------------------------------------------------------------------------
               TRANSACTION
------------------------------------------------------------------------------------------------------
*/
create or replace PROCEDURE DELINVOICE(
INVID IN INTEGER)
AS
BEGIN
    DELETE FROM INVOICELINE WHERE INVOICEID=INVID;
    DELETE FROM INVOICE WHERE INVOICEID=INVID;
    COMMIT;
END DELINVOICE;
/
/*
Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table

------------------------------------------------------------------------------------------------------
               TRANSACTION
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE PROCEDURE INSERTNEWCUSTOMER
AS
BEGIN
    BEGIN
        INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (10000, 'Leonie', 'Köhler', 'Theodor-Heuss-Straße 34', 'Stuttgart', 'Germany', '70174', '+49 0711 2842222', 'leonekohler@surfeu.de', 5);
    END;
END;
/
EXECUTE INSERTNEWCUSTOMER;
SELECT * FROM CUSTOMER WHERE CUSTOMERID=10000;
/*
6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
6.1 AFTER/FOR
Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
------------------------------------------------------------------------------------------------------
               TRIGGER
------------------------------------------------------------------------------------------------------
*/
CREATE TRIGGER RANDOMTRIGGER
AFTER INSERT ON CUSTOMER
BEGIN
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) VALUES (100001, 'François', 'Tremblay', '1498 rue Bélanger', 'Montréal', 'QC', 'Canada', 'H2G 1A7', '+1 (514) 721-4711', 'ftremblay@gmail.com', 3);
END RANDOMTRIGGER;
/
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (10003, 'Leonie', 'Köhler', 'Theodor-Heuss-Straße 34', 'Stuttgart', 'Germany', '70174', '+49 0711 2842222', 'leonekohler@surfeu.de', 5);
SELECT * FROM CUSTOMER WHERE CUSTOMERID>=10000;
/*
Task – Create an after update trigger on the album table that fires after a row is inserted in the table
------------------------------------------------------------------------------------------------------
               TRIGGER
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE TRIGGER RANDOMTRIGGER2
AFTER UPDATE ON CUSTOMER
BEGIN
    INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (67, 'Leonie', 'Köhler', 'Theodor-Heuss-Straße 34', 'Stuttgart', 'Germany', '70174', '+49 0711 2842222', 'leonekohler@surfeu.de', 5);
END RANDOMTRIGGER2;
/
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (65, 'Leonie', 'Köhler', 'Theodor-Heuss-Straße 34', 'Stuttgart', 'Germany', '70174', '+49 0711 2842222', 'leonekohler@surfeu.de', 5);
UPDATE CUSTOMER SET CUSTOMERID=66 WHERE CUSTOMERID=65;

/*
Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
------------------------------------------------------------------------------------------------------
               TRIGGER
------------------------------------------------------------------------------------------------------
*/
CREATE OR REPLACE TRIGGER AFTERDEL
AFTER DELETE ON CUSTOMER
BEGIN
    UPDATE CUSTOMER SET FIRSTNAME='Gabe' where COMPANY='Revature';
END;
/*
7.0 JOINS
In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
7.1 INNER
Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
*/
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID=INVOICE.CUSTOMERID;
/*
7.2 OUTER
Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
*/
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, INVOICE.INVOICEID, INVOICE.TOTAL FROM CUSTOMER FULL OUTER JOIN INVOICE  ON CUSTOMER.CUSTOMERID=INVOICE.CUSTOMERID;
/*
7.3 RIGHT
Task – Create a right join that joins album and artist specifying artist name and title.
*/
SELECT NAME, TITLE FROM ALBUM RIGHT JOIN ARTIST ON ARTIST.ARTISTID=ALBUM.ALBUMID;
/*
7.4 CROSS
Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
*/
SELECT * FROM ALBUM CROSS JOIN ARTIST ORDER BY ARTIST.NAME ASC;
/*
7.5 SELF
Task – Perform a self-join on the employee table, joining on the reportsto column.
*/
SELECT * FROM EMPLOYEE A, EMPLOYEE B WHERE A.REPORTSTO=B.REPORTSTO;

