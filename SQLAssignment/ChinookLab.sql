--SQL ASSIGNMENT--
--Scott Bennett--

--2.1 Select--
SELECT * from employee;

SELECT * FROM employee
WHERE lastname = 'King';

SELECT * FROM employee
WHERE firstname = 'Andrew' and reportsto IS NOT NULL;

--2.2 Order By--
SELECT * FROM album
ORDER BY title DESC;

SELECT firstname FROM customer
ORDER BY city ASC;

--2.3 Insert Into--
INSERT INTO genre
VALUES (100, 'Musical');

INSERT INTO genre
VALUES (200, 'World');

INSERT INTO employee
VALUES (10, 'Beckham', 'Victorie', 'Posh Spice', 6, '18-FEB-69', '12-DEC-89', '123 Street St.', 'Edmonton', 'AB', 'Canada', 'HSK 123', '123-234-1234', '321-123-1234', 'vBeck@hinook.com');

INSERT INTO employee
VALUES (11, 'Beckham', 'David', 'Tennis Manager', 5, '18-JAN-69', '12-FEB-89', '123 Street St.', 'Edmonton', 'AB', 'Canada', 'HSK 123', '123-234-1234', '321-123-1234', 'dBeck@hinook.com');

INSERT INTO customer
VALUES (100, 'John', 'Jacobs', 'SomeCompany', '123 Company Ave', 'LA', 'CA', 'USA', '09867', '323-126-7645', '456-765-7890', 'BusyMe@gmail.com', 7);

INSERT INTO customer
VALUES (150, 'Billy', 'Bob-Thornton', 'SomeOtherCompany', '321 Company Ave', 'LA', 'CA', 'USA', '09867', '323-126-7645', '456-765-7890', 'BillBob@gmail.com', 7);

--2.4 Update--
UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' 
AND lastname = 'Mitchell';

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--2.5 Like--
SELECT * FROM invoice
WHERE billingaddress LIKE 'T%';

--2.6 Between--
SELECT * from invoice
WHERE total BETWEEN 15 AND 50;

SELECT * from employee 
WHERE hiredate BETWEEN '1-JUN-03' AND '1-MAR-04';

--2.7 Delete--
--DELETE InvoiceLine child tables--
DELETE FROM invoiceline il
WHERE il.invoiceid IN (
SELECT i.invoiceid FROM invoice i
WHERE i.customerid = ( 
SELECT c.customerid FROM customer c
WHERE c.firstname = 'Robert' AND c.lastname = 'Walter'));

--DELETE from invoice child table--
DELETE FROM invoice i
WHERE i.customerid = ( 
SELECT c.customerid FROM customer c
WHERE c.firstname = 'Robert' AND c.lastname = 'Walter');

--DELETE Robert Walter--
DELETE FROM customer c
WHERE c.firstname = 'Robert' AND c.lastname = 'Walter';

--3.0 SQL Functions--

--3.1 System Defined Functions--
ALTER SESSION SET TIME_ZONE = '-5:00';
SELECT LOCALTIMESTAMP FROM DUAL;
SELECT LENGTH(name) FROM mediatype;

--3.2 Aggregate Functions--
--Function to return total average of all invoices--
create or replace FUNCTION INVOICE_AVERAGE
RETURN NUMBER IS v_avg NUMBER;
BEGIN
    SELECT avg(invoice.total)
    INTO v_avg
    FROM invoice;
    RETURN v_avg;
END;

--Function to return the most expensive track--
create or replace FUNCTION SELECT_MOST_EXPENSIVE_TRACK
RETURN number IS v_trackprice number;
BEGIN
    SELECT track.unitprice
    INTO v_trackprice
    FROM track
    ORDER BY track.unitprice desc
    FETCH NEXT 1 ROW ONLY;
    RETURN v_trackprice;
END;

--3.3 Scalar Functions--
--Function to return the avg of invoices grouped by invoice id--
CREATE OR REPLACE FUNCTION AVG_INVOICELINE 
RETURN SYS_REFCURSOR AS v_refcursor SYS_REFCURSOR;
BEGIN
    OPEN v_refcursor FOR
    SELECT invoiceid, (avg(unitprice)) FROM invoiceline
    GROUP BY invoiceid;
  RETURN v_refcursor;
END AVG_INVOICELINE;

--3.4 Table Valued Functions--
--Function to return employees born after 1968 i.e. those born 1/1/69 or later--
create or replace FUNCTION SELECT_BY_BIRTH 
RETURN SYS_REFCURSOR AS v_cursor SYS_REFCURSOR;
BEGIN
    open v_cursor FOR
        SELECT * from employee
        WHERE birthdate >= date '1969-1-1';
    RETURN v_cursor;
END SELECT_BY_BIRTH;
--4.0 Stored Procedures--

--4.1 Basic Stored Procedures--
--PROCEDURE to select first and last names of all employees--
create or replace PROCEDURE FIRST_AND_LAST_FROM_EMPLOYEE
(v_cursor OUT sys_refcursor)
AS 
 BEGIN
    OPEN v_cursor FOR 
    SELECT firstname, lastname
    FROM employee;
END;

--4.2 Stored Procedure Input Parameters--
--Procedure to update an employee's personal info - name in this case--
create or replace PROCEDURE UPDATE_EMPLOYEE_NAME
(v_empID IN number, v_empFname in varchar2, v_empLname in varchar2)
AS 
BEGIN
  UPDATE employee
  SET firstname= v_empFname, lastname = v_empLname
  WHERE employeeid = v_empId;
END;

--Procedure to get an employee's manager--
create or replace PROCEDURE GET_MANAGER
(v_empId IN number, v_firstName OUT varchar2, v_lastname OUT varchar2)
AS 
BEGIN
  SELECT firstName, lastname
  INTO v_firstname, v_lastname
  FROM employee
  where employeeid = 
    (SELECT reportsto FROM employee WHERE employeeid = v_empid);
END;
--4.3 Stored Procedure Output Parameters--
--Procedure to return name and company of a customer--
create or replace PROCEDURE SELECT_NAME_AND_COMPANY 
(
  V_CUSTOMERID IN NUMBER, 
  V_CUSTOMERNAME OUT VARCHAR2, 
  V_CUSTOMERCOMPANY OUT VARCHAR2 
) AS 
BEGIN
  SELECT firstName, company
  INTO V_CUSTOMERNAME, V_CUSTOMERCOMPANY
  FROM customer
  WHERE customer.customerid = v_customerid;
END SELECT_NAME_AND_COMPANY;

--5.0 Transactions --
--NOTE all transactions stored inside a procedure--
--Stored pocedure with transaction to delete from invoice by ID--
create or replace PROCEDURE DELETE_BY_INVOICEID 
(
  V_INVOICEID IN NUMBER 
) AS 
BEGIN
    --DELETE FROM INVOICE LINE CHILD TABLE--
    DELETE FROM invoiceline il
    WHERE il.invoiceid IN (
    SELECT i.invoiceid FROM invoice i
    WHERE i.invoiceid = V_INVOICEID);
    
    --DELETE from invoice table--
    DELETE FROM invoice i
    WHERE i.invoiceid = V_INVOICEID;
    
    commit;
END DELETE_BY_INVOICEID;

--Stored Procedure with transaction to insert customer--
create or replace PROCEDURE ADD_CUSTOMER AS 
BEGIN
  INSERT INTO customer
    VALUES (991122, 'Orion', 'Daniels', 'SomeCompany', '123 Company Ave', 'LA', 'CA', 'USA', 
            '09867', '323-126-7645', '456-765-7890', 'BusyMe@gmail.com', 7);
    commit;

END;

--6.0 Triggers--
--6.1 After/For--
--After insert trigger on the employee table fired after a new record is inserted into the table--
create or replace TRIGGER AFTER_INSERT_EMPLOYEE 
AFTER INSERT ON EMPLOYEE 
BEGIN
  DBMS_OUTPUT.PUT_LINE('INSERTED INTO EMPLOYEE');
END;

--After update trigger on the album table that fires after a row is inserted in the table--
create or replace TRIGGER AFTER_UPDATE_ALBUM
AFTER UPDATE ON album
BEGIN
   DBMS_OUTPUT.PUT_LINE('INSERTED INTO ALBUM');
END;

--After delete trigger on the customer table that fires after a row is deleted from the table--
create or replace TRIGGER AFTER_DELETE_CUSTOMER
AFTER DELETE ON customer
BEGIN
   DBMS_OUTPUT.PUT_LINE('CUSTOMER DELETED');
END;


--7.1 Inner Join--
SELECT c.firstname, c.lastname, i.invoiceid FROM customer c
INNER JOIN invoice i
ON c.customerid = i.customerid;

--7.2 Outer Join--
SELECT c.firstname, c.lastname, i.invoiceid, i.total FROM customer c
FULL OUTER JOIN invoice i
ON c.customerid = i.customerid;

--7.3 Right Join--
SELECT ar.name, al.title FROM album al
RIGHT JOIN artist ar
ON ar.ARTISTID=al.ARTISTID;

--7.4 Cross Join--
SELECT * FROM album al
CROSS JOIN artist ar
ORDER BY ar.name ASC;

--7.5 SELF join--
SELECT * FROM employee e1, employee e2
WHERE e1.reportsto = e2.reportsto;
