--Jessica Colson
--Chinook SQL Lab
--2.1
SELECT * FROM EMPLOYEE;
--returns every record from the Employee table

SELECT * FROM EMPLOYEE 
WHERE LASTNAME = 'King';
--returns all records from the Employee table where the LastName entry equals 'King'

SELECT * FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
--returns all records from Employee table where the FirstName entry equals 'Andrew'
--and the REPORTSTO entry is null

--2.2
SELECT * FROM ALBUM
ORDER BY TITLE DESC;
--returns all records from Album and sorts them in decending order by Title

SELECT FIRSTNAME FROM CUSTOMER
ORDER BY CITY;
--basically sorts the Customer table by City and returns only the FirstName column

--2.3
INSERT INTO GENRE VALUES(26, 'New Genre 1');
INSERT INTO GENRE VALUES(27, 'New Genre 2');
SELECT * FROM GENRE;
--Inserts two new records into the genre table
--I added a select, so I could see that they were added

SELECT MAX(EMPLOYEEID) FROM EMPLOYEE;
SELECT * FROM EMPLOYEE
WHERE EMPLOYEEID = 1;
INSERT INTO EMPLOYEE VALUES (9, 'Colson', 'Jessica', 'Trainee', NULL, '01-MAR-96','12-FEB-18', '1234 Her Address St',
'SomeCity', 'IL', 'USA', '60452', '+1 (888) 888-8888', '+1 (888) 888-8888','jcolson419@gmail.com');
INSERT INTO EMPLOYEE VALUES (10, 'Strocchia', 'Nicholas', 'Student', NULL, '16-NOV-94', Null, '1234 His Address St',
'SomeCity', 'IL', 'USA', '60452', '+1 (999) 999-9999', '+1 (999) 999-9999','nstrocc1@mail.com');
SELECT * FROM EMPLOYEE
WHERE EMPLOYEEID = 9 OR EMPLOYEEID = 10;
-- The first comand is to find out the maximum employeeID, so that the new records don't have an id thats already being used
-- the next command is so that I could see the format of the values in each column
-- then I add two new records to the Employee table
--finally I view the two new records that I added to the Employee table by specifying the employeeIDs of the new records

SELECT MAX(CUSTOMERID) FROM CUSTOMER;
SELECT * FROM CUSTOMER
WHERE CUSTOMERID = 1;
INSERT INTO CUSTOMER VALUES (60, 'Jessica', 'Colson', 'Revature', '1234 Their Address St',
'SomeCity', 'FL', 'USA', '00000', '+1 (888) 888-8888', '+1 (888) 888-8888','jcolson419@gmail.com', 3);
INSERT INTO CUSTOMER VALUES (61, 'Nicholas', 'Strocchia', 'Papa Joes', '1234 Some Address St',
'SomeCity', 'IL', 'USA', '60477', '+1 (999) 999-9999', '+1 (999) 999-9999','nstrocc1@mail.com', 3);
SELECT * FROM CUSTOMER
WHERE CUSTOMERID = 60 OR CUSTOMERID = 61;
-- The first comand is to find out the maximum customerID, so that the new records don't have an id thats already being used
-- the next command is so that I could see the format of the values in each column
-- then I add two new records to the Customer table
--finally I view the two new records that I added to the Customer table by specifying the customerIDs of the new records

--2.4
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
-- change the record in the Customer table where first name was Aaron and the last name was Mitchell
-- such that the first name is now Robert and the last name is now Walter

UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';
-- changes every record in Artist where the entry in the Name column is 'Creedence Clearwater Revival' to 'CCR'

--2.5
SELECT * FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';
-- selects all records from the Invoice table with a billingAddress that starts with a T

--2.6
SELECT * FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
-- selects all records from the Invoice table with Total between 15 and 50

SELECT * FROM EMPLOYEE
WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';
-- selects all records from the Employee table with a hireDate
--between the 1st of June 2003 and the 1st of March 2004

--2.7
SELECT CUSTOMERID FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
SELECT INVOICELINEID FROM INVOICELINE
WHERE invoiceline.invoiceid IN (
    SELECT INVOICEID FROM INVOICE
    WHERE CUSTOMERID = 32
); 
DELETE FROM INVOICELINE
WHERE invoiceline.invoiceid IN (
    SELECT INVOICEID FROM INVOICE
    WHERE CUSTOMERID = 32
);
DELETE FROM INVOICE
WHERE CUSTOMERID = 32;
DELETE FROM CUSTOMER
WHERE CUSTOMERID = 32;   
--I first found the primary key value for the record I wanted to delete
--since there were foreign keys relying on this record I had to search
--the child tables for the associated child-records
--then i deleted the grandchild-records, followed by deleting the child-records,
--then finally deleting the record I originally intended to delete

--3.1
SELECT LOCALTIMESTAMP FROM DUAL;
--returns the current time stamp

SELECT LENGTH(NAME) FROM MEDIATYPE;
--returns a column with the lengths of the names in the MediaType table

--3.2
SELECT AVG(TOTAL) FROM INVOICE;
--returns the average of all of the entries in the 
--Total column in the Invoice table

SELECT MAX(UNITPRICE) FROM TRACK;
--returns the max value in the UnitPrice column in the Track table

--3.3
CREATE OR REPLACE FUNCTION AVGPRICEBYINVOICE
(ID IN NUMBER)
RETURN NUMBER AS
AVG_INVOICE_PRICE NUMBER(10,2);
    BEGIN
    SELECT AVG(UNITPRICE) INTO AVG_INVOICE_PRICE
    FROM INVOICELINE
    WHERE INVOICEID = ID;
    RETURN AVG_INVOICE_PRICE;
    END;
/

SELECT AVGPRICEBYINVOICE(INVOICEID), INVOICEID FROM INVOICELINE
GROUP BY INVOICEID;
-- groups the output by invoiceID

--3.4
SELECT * FROM EMPLOYEE
WHERE BIRTHDATE > '31-DEC-1968';
--this is the easy, logical way to return all employees who were born after 1968. 
--below is the 'create your own function and receive ugly output' way

CREATE OR REPLACE FUNCTION BORNAFTER1968
RETURN SYS_REFCURSOR AS PRINTME SYS_REFCURSOR;
    BEGIN
        OPEN PRINTME FOR SELECT EMPLOYEEID, FIRSTNAME, LASTNAME FROM EMPLOYEE
        WHERE BIRTHDATE > '31-DEC-1986';
        RETURN PRINTME;
    END;
/
SELECT BORNAFTER1968() FROM DUAL;
--returns all records in Employee table where the employee was born after 1968

--4.1
CREATE OR REPLACE PROCEDURE SELECTNAMES
(PRINTER OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN PRINTER FOR SELECT FIRSTNAME, LASTNAME
        FROM EMPLOYEE;
        DBMS_SQL.RETURN_RESULT(PRINTER);
    END;
/
VAR CURSOR REFCURSOR;
EXECUTE SELECTNAMES(:CURSOR);
--uses a cursor to print out the first and last names of all employees

--4.2
CREATE OR REPLACE PROCEDURE UPDATEPERSONAL
(ID NUMBER, LSTNAME VARCHAR, FRSTNAME VARCHAR, BRTHDATE DATE,
ADDRSS VARCHAR, CTY VARCHAR, STATE2 VARCHAR, CNTRY VARCHAR,
PSTLCODE VARCHAR, PHNE VARCHAR, FX VARCHAR, EMAIL2 VARCHAR)
AS
    BEGIN
        UPDATE EMPLOYEE
        SET LASTNAME = LSTNAME, FIRSTNAME = FRSTNAME,
        BIRTHDATE = BRTHDATE, ADDRESS = ADDRSS, CITY = CTY,
        STATE = STATE2, COUNTRY = CNTRY, POSTALCODE = PSTLCODE,
        PHONE = PHNE, FAX = FX, EMAIL = EMAIL2
        WHERE EMPLOYEEID = ID;
    COMMIT;
    END;
/
--uses the employee ID to change that employee's personal information
--to the input values 

CREATE OR REPLACE PROCEDURE GETMANAGERNAME
(EMPID IN INT, PRINTER OUT SYS_REFCURSOR) AS
    BEGIN
        OPEN PRINTER FOR SELECT CONCAT(FIRSTNAME, LASTNAME)
        FROM EMPLOYEE
        WHERE EMPLOYEEID IN (
            SELECT REPORTSTO FROM EMPLOYEE
            WHERE EMPLOYEEID = EMPID
        );
        DBMS_SQL.RETURN_RESULT(PRINTER);
    END;
/

VAR PRINTER REFCURSOR;
EXECUTE GETMANAGERNAME(5,:PRINTER);
--returns the first and last name of the manager of the employee with employeeID = 5

--4.3
CREATE OR REPLACE PROCEDURE GETNAMEANDCOMPANY
(CUSTID IN NUMBER, PRINTER OUT SYS_REFCURSOR) AS
    BEGIN
        OPEN PRINTER FOR SELECT FIRSTNAME, LASTNAME, COMPANY
        FROM CUSTOMER
        WHERE CUSTOMERID = CUSTID;
        DBMS_SQL.RETURN_RESULT(PRINTER);
    END;
/
EXECUTE GETNAMEANDCOMPANY(60,:PRINTER);
--returns the first name, last name, and company of the customer with the specified customerID

--5.0 TRANSACTIONS
CREATE OR REPLACE PROCEDURE DELETEINVOICEBYID
(INV_ID IN NUMBER) AS
    BEGIN
        DELETE FROM INVOICELINE
        WHERE INVOICELINE.INVOICEID = INV_ID;
        DELETE FROM INVOICE
        WHERE INVOICE.INVOICEID = INV_ID;
    COMMIT;
    END;
/
--this procedure takes in an invoice ID and will then delete
--any invoice line items that have that invoice ID before deleting
--the invoice with that ID from the invoice table.

CREATE OR REPLACE FUNCTION MAXCUSTID
RETURN NUMBER AS MAX_ID NUMBER;
    BEGIN
        SELECT MAX(CUSTOMERID) INTO MAX_ID FROM CUSTOMER;
        RETURN MAX_ID;
    END;
/

CREATE OR REPLACE PROCEDURE INSERTNEWCUSTOMER
(FNAME IN VARCHAR, LNAME IN VARCHAR, COMPNY IN VARCHAR, ADDRSS IN VARCHAR,
CTY IN VARCHAR, ST IN VARCHAR, CNTRY IN VARCHAR, POSTCODE IN VARCHAR,
PHONENUM IN VARCHAR, FAX IN VARCHAR, EM IN VARCHAR, SUPPREPID IN NUMBER)
AS
    NEWCUSTID NUMBER;
    BEGIN
        NEWCUSTID := MAXCUSTID()+1;
        INSERT INTO CUSTOMER VALUES(
            NEWCUSTID, FNAME, LNAME, COMPNY, ADDRSS, CTY, ST, CNTRY, POSTCODE,
            PHONENUM, FAX, EM, SUPPREPID
        );
    COMMIT;
    END;
/

EXECUTE INSERTNEWCUSTOMER('Norma', 'Bates', 'Bates Motel', '1234 Some Street','White Pine Bay', 'Oregon', 'USA', '00000', '+1(111) 111-1111', '+1(111) 111-1111', 'nbates@psycho.net', 4);
--will create a new record in the customer table. Uses the maxcustid function to grab the max customer id currently in the 
--table and add one to it so that the customerID is unique and incrementing properly. I ran this twice to make sure that 
--the ID was incrementing correctly.

--6.0  TRIGGERS 
SET SERVEROUTPUT ON;
--6.1
CREATE OR REPLACE TRIGGER EMPLAFTERINSERT
AFTER INSERT ON EMPLOYEE
    BEGIN
    DBMS_OUTPUT.PUT_LINE('A new employee has been added to the employee table.');
    END;
/
--prints 'A new employee has been added to the employee table' to the console
--after any insert into the employee table

CREATE OR REPLACE TRIGGER ALBUMAFTERUPDATE
AFTER UPDATE ON ALBUM
    BEGIN
        DBMS_OUTPUT.PUT_LINE('The Album table has been updated');
    END;
/
--prints 'The album table has been updated' after any update statement is
--run on the album table.

CREATE OR REPLACE TRIGGER CUSTAFTERDELETE
AFTER DELETE ON CUSTOMER
    BEGIN
        DBMS_OUTPUT.PUT_LINE('A record has been deleted from the customer table.');
    END;
/
--prints 'A record has been deleted from the customer table.' after 
--a delete statement is called on the Customer table

--7.1
SELECT FIRSTNAME, LASTNAME, INVOICEID FROM CUSTOMER
INNER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
--returns the firstName and lastName columns from the Customer table
--and the invoiceID column from the Invoice table
-- the records are matched by the CustomerID field

--7.2
SELECT CUSTOMER.CUSTOMERID, FIRSTNAME, LASTNAME, INVOICEID, TOTAL
FROM CUSTOMER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
--outer join that joins the customer and invoice tables
--specifies the customerID, firstname, lastname, invoiceid,
--and total

--7.3
SELECT ARTIST.NAME, TITLE FROM ALBUM
RIGHT JOIN ARTIST
ON ARTIST.ARTISTID = ALBUM.ARTISTID;
--right join that joins the artist and album tables
--specified the artist name and album title

--7.4
SELECT * FROM ALBUM
CROSS JOIN ARTIST
ORDER BY ARTIST.NAME;
--cross join that joins the album and artist tables
--sorted by artist name in ascending order

--7.5
SELECT A.EMPLOYEEID AS "Emp_ID", A.FIRSTNAME AS "Emp_FirstName",
A.LASTNAME AS "Emp_LastName", B.EMPLOYEEID AS "Supv_ID",
B.FIRSTNAME AS "Supv_FirstName", B.LASTNAME AS "Supv_LastName"
FROM EMPLOYEE A, EMPLOYEE B
WHERE A.REPORTSTO = B.EMPLOYEEID;
--a self join that adds the supervisors first and last name to each record