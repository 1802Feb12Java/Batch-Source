/*2.0 SQL Queries*/

/*2.1 SELECT*/

SELECT * FROM Employee;
SELECT * FROM Employee WHERE Lastname = 'King';
SELECT * FROM Employee WHERE Firstname = 'Andrew' AND Reportsto = NULL;

/*2.2ORDER BY*/

SELECT Title FROM Album ORDER BY Title DESC;
SELECT Firstname FROM Customer ORDER BY City;

--/*2.3INSERT INTO*/

SELECT * FROM Genre;
INSERT INTO Genre VALUES(26,'a1');
INSERT INTO Genre VALUES(27,'a2');

SELECT * FROM Employee;

INSERT INTO employee(employeeid,lastname,firstname,title,reportsto,birthdate,hiredate,address,city,state,country,postalcode,phone,fax,email)
VALUES(9,'Doe','John','IT Manager', 6,'22-DEC-56','26-FEB-14','8888 sw. 8 st.','Chicago','IL','USA','33333','777-777-7777','888-888-8888','myemail@gmail.com');
INSERT INTO employee(employeeid,lastname,firstname,title,reportsto,birthdate,hiredate,address,city,state,country,postalcode,phone,fax,email)
VALUES(10,'Smith','John','IT Staff', 6,'24-JAN-65','22-SEP-14','9999 sw. 9 st.','Minneapolis','MN','USA','22222','666-666-6666','999-999-9999','myemail2@gmail.com');


SELECT * FROM Customer;
INSERT INTO Customer VALUES(60,'John','Doe','Dell','5555 sw 5 st','Miami','FL','USA','22222','111-111-1111','222-222-2222','myemail3@gmail.com',6);
INSERT INTO Customer VALUES(61,'John','Slack','Dell','5556 sw 5 st','Miami','FL','USA','22223','444-444-4444','333-333-3333','myemail4@gmail.com',6);


/*2.4UPDATE*/

UPDATE Customer SET Firstname = 'Robert', Lastname = 'Walter' WHERE Firstname = 'Aaron' AND Lastname = 'Mitchell';
UPDATE Artist SET Name = 'CCR' WHERE Name = 'Creedence Clearwater Revival';

/*2.5LIKE*/

SELECT Billingaddress FROM Invoice WHERE Billingaddress LIKE 'T%';

/*2.6BETWEEN*/

SELECT Total FROM Invoice WHERE Total BETWEEN 15 AND 50;

/*2.7DELETE*/
ALTER TABLE Invoice
DROP CONSTRAINT FK_INVOICECUSTOMERID;

ALTER TABLE Invoice
ADD CONSTRAINT FK_INVOICECUSTOMERID FOREIGN KEY(CUSTOMERID)
REFERENCES CUSTOMER(CUSTOMERID)
ON DELETE CASCADE;

ALTER TABLE Invoiceline
DROP CONSTRAINT FK_INVOICELINEINVOICEID;

ALTER TABLE Invoiceline
ADD CONSTRAINT FK_INVOICELINEINVOICEID FOREIGN KEY(INVOICEID)
REFERENCES Invoice(INVOICEID)
ON DELETE CASCADE;

DELETE FROM Customer WHERE Firstname = 'Robert' AND Lastname = 'Walter';

/*3.SQL Functions*/

/*3.1System Defined Functions*/

 CREATE OR REPLACE FUNCTION getDate

    RETURN date IS
    k date;     
	BEGIN
	k := SYSDATE;
    RETURN k;
    END getDate;
    /
    
SELECT getDate FROM dual;

CREATE OR REPLACE FUNCTION getLength

    RETURN NUMBER IS
    len NUMBER;     
	BEGIN
	SELECT max(LENGTH(NAME))
    INTO len
    FROM MEDIATYPE;
    
    RETURN len;
    END getLength;
    /
SELECT getLength FROM dual;

/*3.2System defined Aggregate functions*/

CREATE OR REPLACE FUNCTION totalInvoices

    RETURN NUMBER IS
    totalInv NUMBER;     
	BEGIN
	SELECT AVG(TOTAL)
    INTO totalInv
    FROM INVOICE;
    
    RETURN totalInv;
    END totalInvoices;
    /
SELECT totalInvoices FROM dual;

CREATE OR REPLACE FUNCTION maxValTrack

    RETURN NUMBER IS
    highestTrack NUMBER;     
	BEGIN
	SELECT MAX(unitprice)
    INTO highestTrack
    FROM TRACK;
    
    RETURN highestTrack;
    END maxValTrack;
    /
SELECT maxValTrack FROM dual;

/*3.3User Defined Scalar Functions*/

SELECT AVG(unitprice) FROM INVOICELINE;

/*3.4User defined table valued functions*/


    SELECT FIRSTNAME, LASTNAME 
    FROM EMPLOYEE
    WHERE EXTRACT(YEAR FROM BIRTHDATE) > 1968;
    

/*4.Stored Procedures*/

/*4.1Basic stored procedures*/
CREATE OR REPLACE PROCEDURE employeeNames1 AS
    firstn varchar(20);
    lastn varchar(20);
   BEGIN
      SELECT FIRSTNAME, LASTNAME
      INTO firstn, lastn
      FROM EMPLOYEE;
   END;
/
/*4.2Stored procedure input parameters*/
CREATE OR REPLACE PROCEDURE employeePhoneUpdate1 (updatePhoneID NUMBER) AS
   BEGIN
     UPDATE EMPLOYEE
     SET PHONE = '777-777-7777'
     WHERE EMPLOYEEID = updatePhoneID;
   END;
/
--CREATE PROCEDURE execManagers AS
--        firstn varchar2(20);
--        lastn varchar2(20);
--   BEGIN
--     SELECT firstname, lastname
--     INTO firstn , lastn
--     FROM EMPLOYEE
--     WHERE EMPLOYEEID = REPORTSTO;
--   END;
--/
/*4.3Stored procedure output parameters*/
CREATE OR REPLACE PROCEDURE nameAndCompany (getID NUMBER) AS
    firstn varchar2(20);
    lastn varchar2(20);
    comp varchar2(20);
   BEGIN
     SELECT firstname, lastname, company
     INTO firstn, lastn, comp
     FROM CUSTOMER
     WHERE CUSTOMERID = getID;
   END;
/

/*5.Transactions*/

DELETE FROM Invoice WHERE InvoiceID = 1;
COMMIT;

CREATE OR REPLACE PROCEDURE nameAndCompany (getID NUMBER) AS
   myCount NUMBER; 
   BEGIN
   SELECT COUNT(CUSTOMERID)
   INTO myCount
   FROM CUSTOMER;
   INSERT INTO CUSTOMER VALUES(myCount,'J','Mac','Revature','Addison Oaks','Tampa','FL','USA','33333','444-444-4444','555-555-5555','myemail@gmail.com', 1);
   COMMIT;
   END;
/

/*6.Triggers*/

/*6.1AFTER/FOR*/

--CREATE OR REPLACE  TRIGGER trigger_1  
--AFTER
--INSERT    
--ON EMPLOYEE  
--REFERENCING OLD AS o NEW AS n  
--FOR EACH ROW  
--   
--DECLARE 
--   --Declaration-statements 
--BEGIN  
--   --Executable-statements 
--EXCEPTION 
--   --Exception-handling-statements 
--END;

--CREATE OR REPLACE  TRIGGER trigger_2  
--AFTER
--INSERT    
--ON ALBUM  
--REFERENCING OLD AS o NEW AS n  
--FOR EACH ROW  
--   
--DECLARE 
--   --Declaration-statements 
--BEGIN  
--   --Executable-statements 
--EXCEPTION 
--   --Exception-handling-statements 
--END;

--CREATE OR REPLACE  TRIGGER trigger_3  
--AFTER
--DELETE    
--ON CUSTOMER  
--REFERENCING OLD AS o NEW AS n  
--FOR EACH ROW  
--   
--DECLARE 
--   --Declaration-statements 
--BEGIN  
--   --Executable-statements 
--EXCEPTION 
--   --Exception-handling-statements 
--END;

/*7.JOINS*/

/*7.1INNER*/
SELECT firstname, lastname, invoiceID
FROM CUSTOMER INNER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
/*7.2OUTER*/
SELECT firstname, lastname, customerID, invoiceID, total
FROM CUSTOMER OUTER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
/*7.3RIGHT*/
SELECT name, title
FROM ALBUM RIGHT OUTER JOIN ARTIST
ON ALBUM.ARTISTID = ARTIST.ARTISTID;
/*7.4CROSS*/
SELECT NAME
FROM ARTIST CROSS JOIN ALBUM
ORDER BY NAME ASC;

/*7.5SELF*/
SELECT a.firstname, a.lastname, b.reportsTo
FROM EMPLOYEE a , EMPLOYEE b;
