--2.1
SELECT * FROM EMPLOYEE;
--Select all records from the Employee table

SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
--Select all records from the Employee table where last name is King

SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
--Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL

--2.2
SELECT * FROM ALBUM ORDER BY TITLE DESC;
--Select all albums in Album table and sort result set in descending order by title

SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;
--Select first name from Customer and sort result set in ascending order by city

--2.3
INSERT INTO GENRE VALUES (26, 'Drum N Bass');
INSERT INTO GENRE (GENREID, NAME) VALUES (27, 'House');
--Insert two new records into Genre table 

INSERT INTO EMPLOYEE VALUES (16, 'Smith', 'John', 'Janitor', 1, '01-JAN-69', '20-FEB-18', '1234 Street St', 'Tampa', 'FL', 'USA', 33647, '+1 (813) 123-4567', '+ (813) 123-4568', 'john@chinookcorp.com');
INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME) VALUES (9, 'Bob', 'Tim') ;
--Insert two new records into Employee table

INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) VALUES (70, 'Jack', 'Hoff' , 'jackhoff@hotmail.com');
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) VALUES (71, 'David', 'Anderson' , 'danderson@gmail.com');
--Insert two new records into Customer table

--2.4
UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
--Update Aaron Mitchell in Customer table to Robert Walter

UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';
--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”

--2.5
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';
--Select all invoices with a billing address like “T%” 

--2.6
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
--Select all invoices that have a total between 15 and 50

SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN TO_DATE('01/06/2003', 'dd/mm/yyyy') AND TO_DATE('01/03/2004', 'dd/mm/yyyy');
--Select all employees hired between 1st of June 2003 and 1st of March 2004

--2.7
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
--Delete a record in Customer table where the name is Robert Walter. Constraint modified to cascade on delete

--3.1
CREATE OR REPLACE FUNCTION get_time_now
RETURN TIMESTAMP
IS time_now TIMESTAMP;
BEGIN
    SELECT LOCALTIMESTAMP
    INTO time_now
    FROM DUAL;
    RETURN(time_now);
END;
/
--Create a function that returns the current time.

SELECT get_time_now FROM DUAL;

CREATE OR REPLACE FUNCTION get_length_name
(TYPEID IN NUMBER)
RETURN NUMBER
IS length_name NUMBER;
BEGIN
    SELECT LENGTH(NAME)
    INTO length_name
    FROM MEDIATYPE
    WHERE MEDIATYPEID = TYPEID;
    RETURN(length_name);
END;
/
--create a function that returns the length of name in MEDIATYPE table

SELECT get_length_name(1) FROM DUAL;

--3.2
CREATE OR REPLACE FUNCTION get_invoice_average
RETURN NUMBER
IS average_invoice NUMBER (10, 2);
BEGIN
    SELECT AVG(TOTAL)
    INTO average_invoice
    FROM INVOICE;
    RETURN (average_invoice);
END;
/
--Create a function that returns the average total of all invoices 

SELECT get_invoice_average FROM DUAL;

CREATE OR REPLACE FUNCTION most_expensive_track
RETURN SYS_REFCURSOR
IS my_cursor SYS_REFCURSOR;
BEGIN
    OPEN my_cursor FOR SELECT NAME
        FROM TRACK
        WHERE UNITPRICE = (
            SELECT MAX(UNITPRICE)
            FROM TRACK
        )
    ;
    RETURN (my_cursor);
END;
/
--Create a function that returns the most expensive track

SELECT most_expensive_track FROM DUAL;

--3.3
CREATE OR REPLACE FUNCTION average_price_invoiceline
RETURN NUMBER
AS average_price NUMBER(10, 2);
BEGIN
    SELECT AVG(UNITPRICE) INTO average_price
    FROM INVOICELINE;
    RETURN (average_price);
END;
/
--Create a function that returns the average price of invoiceline items in the invoiceline table

SELECT average_price_invoiceline FROM DUAL;

--3.4
CREATE OR REPLACE FUNCTION employees_born_after_1968
RETURN SYS_REFCURSOR
IS my_cursor SYS_REFCURSOR;
BEGIN
    OPEN my_cursor FOR SELECT FIRSTNAME, LASTNAME
        FROM EMPLOYEE
        WHERE BIRTHDATE > TO_DATE('12/12/1968', 'mm/dd/yyyy')
    ;
    RETURN (my_cursor);
END;
/
--Create a function that returns all employees who are born after 1968.

SELECT employees_born_after_1968 FROM DUAL;

--4.1
CREATE OR REPLACE PROCEDURE get_employee_names
AS my_cursor SYS_REFCURSOR;
BEGIN
    OPEN my_cursor FOR SELECT FIRSTNAME, LASTNAME
        FROM EMPLOYEE
    ;
    DBMS_SQL.RETURN_RESULT(my_cursor);
END;
/
--Create a stored procedure that selects the first and last names of all the employees

EXECUTE get_employee_names();

--4.2
CREATE OR REPLACE PROCEDURE update_employee
(EID IN NUMBER, FNAME IN VARCHAR, LNAME IN VARCHAR)
AS
BEGIN
    UPDATE EMPLOYEE
    SET FIRSTNAME = FNAME, LASTNAME = LNAME
    WHERE EMPLOYEEID = EID;
END;
/
--Create a stored procedure that updates the personal information of an employee

INSERT INTO EMPLOYEE (EMPLOYEEID, FIRSTNAME, LASTNAME) VALUES (69, 'Jack', 'Daniels');

EXECUTE update_employee(69, 'Jack', 'Hoff');

CREATE OR REPLACE PROCEDURE get_managers_of_employee
(EID IN NUMBER)
AS my_cursor SYS_REFCURSOR;
BEGIN
    OPEN my_cursor FOR SELECT REPORTSTO
    FROM EMPLOYEE
    WHERE EMPLOYEEID = EID;
    DBMS_SQL.RETURN_RESULT(my_cursor);
END;
/
--Create a stored procedure that returns the managers of an employee

EXECUTE get_managers_of_employee(8);

--4.3
CREATE OR REPLACE PROCEDURE get_name_company_of_customer
(CID IN NUMBER, CNAME OUT VARCHAR, CLNAME OUT VARCHAR, CONAME OUT VARCHAR)
AS my_cursor SYS_REFCURSOR;
BEGIN
    OPEN my_cursor FOR SELECT FIRSTNAME, LASTNAME, COMPANY INTO CNAME, CLNAME, CONAME
    FROM CUSTOMER
    WHERE CUSTOMERID = CID;
    DBMS_SQL.RETURN_RESULT(my_cursor);
END;
/
--Create a stored procedure that returns the name and company of a customer

DECLARE
    FNAME CUSTOMER.FIRSTNAME%TYPE;
    LNAME CUSTOMER.LASTNAME%TYPE;
    CONAME CUSTOMER.COMPANY%TYPE;
BEGIN
    get_name_company_of_customer(17, FNAME, LNAME, CONAME);
END;
/

--5.0
CREATE OR REPLACE PROCEDURE delete_invoice
(ID IN NUMBER)
AS
BEGIN
    SAVEPOINT before_change;
    
    DELETE FROM INVOICE WHERE INVOICEID = ID;

    COMMIT;
END;
/
----Create a transaction that given a invoiceId will delete that invoice

EXECUTE delete_invoice(400);

CREATE OR REPLACE PROCEDURE insert_customer_transaction
(IDIN IN NUMBER, FNAME IN VARCHAR, LNAME IN VARCHAR, EM IN VARCHAR)
AS
BEGIN
    SAVEPOINT before_change;
    INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) VALUES (IDIN, FNAME, LNAME, EM);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insert_customer
(IDIN IN NUMBER, FNAME IN VARCHAR, LNAME IN VARCHAR, EM IN VARCHAR)
AS
BEGIN
    insert_customer_transaction(IDIN, FNAME, LNAME, EM);
END;
/

----Create a transaction nested within a stored procedure that inserts a new record in the Customer table

EXECUTE insert_customer(70, 'David', 'Hasselhoff', 'davidh@gmail.com');

--6.1
CREATE OR REPLACE TRIGGER insert_employee_trigger
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
DECLARE
    time_now TIMESTAMP;
BEGIN
    SELECT LOCALTIMESTAMP INTO time_now FROM DUAL;
    INSERT INTO EMPLOYEE_ADDED VALUES (time_now);
END;
/
--Create an after insert trigger on the employee table fired after a new record is inserted into the table
--Inserts into employee_added table the time when an employee was added

INSERT INTO EMPLOYEE (EMPLOYEEID, FIRSTNAME, LASTNAME) VALUES(11, 'Michael', 'Hawkins');

CREATE OR REPLACE TRIGGER update_album_trigger
AFTER UPDATE ON ALBUM
FOR EACH ROW
DECLARE
    time_now TIMESTAMP;
BEGIN
    SELECT LOCALTIMESTAMP INTO time_now FROM DUAL;
    INSERT INTO ALBUM_UPDATED VALUES (time_now);
END;
/
--Create an after update trigger on the album table that fires after a row is inserted in the table
--Inserts into album_updated table the time when an album was updated

INSERT INTO ARTIST VALUES (290, 'Five Finger Death Punch');
INSERT INTO ALBUM VALUES(350, 'Got Your Six', 290);

UPDATE ALBUM SET ALBUMID = 351 WHERE ALBUMID = 350;

CREATE OR REPLACE TRIGGER delete_customer_trigger
AFTER DELETE ON CUSTOMER
FOR EACH ROW
DECLARE
    time_now TIMESTAMP;
BEGIN
    SELECT LOCALTIMESTAMP INTO time_now FROM DUAL;
    INSERT INTO CUSTOMER_DELETED VALUES (time_now);
END;
/
--Create an after delete trigger on the customer table that fires after a row is deleted from the table
--Inserts into customer_deleted table the time when a customer was deleted

DELETE FROM CUSTOMER WHERE CUSTOMERID = 70;

--7.1
SELECT C.FIRSTNAME, C.LASTNAME, I.INVOICEID 
FROM CUSTOMER C
INNER JOIN INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;
--Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId

--7.2
SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME, I.INVOICEID, I.TOTAL
FROM CUSTOMER C
LEFT OUTER JOIN INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;
--Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total

--7.3
SELECT R.NAME, B.TITLE
FROM ARTIST R
RIGHT JOIN ALBUM B
ON R.ARTISTID = B.ARTISTID;
--Create a right join that joins album and artist specifying artist name and title

--7.4
SELECT *
FROM ARTIST R
CROSS JOIN ALBUM B
ORDER BY R.NAME ASC;
--Create a cross join that joins album and artist and sorts by artist name in ascending order

--7.5
SELECT A.FIRSTNAME AS "Employee First", A.LASTNAME AS "Employee Last", B.FIRSTNAME AS "Manager First", B.LASTNAME AS "Manager Last"
FROM EMPLOYEE A, EMPLOYEE B
WHERE A.REPORTSTO = B.EMPLOYEEID;
--Perform a self-join on the employee table, joining on the reportsto column