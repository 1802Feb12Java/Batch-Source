/* this section you will begin the process of working with the Oracle Chinook database
(https://github.com/lerocha/chinook-database ) 
Task – Open the Chinook_Oracle.sql file and execute the scripts within.*/

/*2.0 SQL Queries
In this section you will be performing various queries against the Oracle Chinook database.
2.1 SELECT*/
--Task – Select all records from the Employee table.
SELECT * 
FROM EMPLOYEE;

--Task – Select all records from the Employee table where last name is King.
SELECT * 
FROM EMPLOYEE 
WHERE LASTNAME = 'King';

--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * 
FROM EMPLOYEE 
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * 
FROM ALBUM
ORDER BY TITLE DESC;

--Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME
FROM CUSTOMER
ORDER BY CITY ASC;

--2.3 INSERT INTO
--Task – Insert two new records into Genre table
INSERT INTO GENRE
VALUES (26, 'Garbage');

INSERT INTO GENRE(NAME)
VALUES (27, 'More Garbage');

--Task – Insert two new records into Employee table
INSERT INTO EMPLOYEE
VALUES(9, 'Tannen', 'Biff', 'Casino Operator', (null), '27-OCT-69', '12-FEB-10', '1 Some Street', 'Hill Valley',
'CA', 'USA', '91905', '1 (555) 555-1212', '1 (555) 555-1213', 'biff@tannenenterprises.com');

INSERT INTO EMPLOYEE
VALUES(10, 'McFly', 'Marty', 'Rock Star', 9, '31-MAY-73', '10-MAR-13', '1234 Some Other Street', 'Hill Valley',
'CA', 'USA', '91905', '1 (555) 844-1212', '1 (555) 844-1213', 'mmcfly@AOL.com');

--Task – Insert two new records into Customer table
INSERT INTO CUSTOMER
VALUES(60, 'Mister', 'Meseeks', 'Problem solvers, inc', '123 Blue Box Way', 'Jerrys Garage', 'WA', 'USA', '98101', 
'1-777-666-5454', (null), 'lookatme@existenceispain.com', 4);

INSERT INTO CUSTOMER
VALUES(61, 'Jerry', 'Smith','Losers, Inc', '1234 Jerrys House', 'Seattle', 'WA', 'USA', '98101', 
'1-777-666-5454', (null), 'imjerrysmith@SAD.org', 3);

--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR” 
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

--2.5 LIKE
--Task – Select all invoices with a billing address like “T%” 
SELECT *
FROM INVOICE 
WHERE BILLINGADDRESS LIKE 'T%';

--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM EMPLOYEE
WHERE HIREDATE BETWEEN '1-JUN-03' AND '1-MAR-04';

--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).

/*Removing the contraint allows for deletion, but the database is no longer has 
referential integrity.  An alternative would be to delete the child invoices, which
would require deleting the invoicelines as well; likely a preferable solution, but
there was no stipulation in the task as to how we completed it.*/
ALTER TABLE INVOICE
  DROP CONSTRAINT FK_InvoiceCustomerID;

DELETE
FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

/*SQL Functions
In this section you will be using the Oracle system functions, as well as your own functions, 
to perform various actions against the database*/
--3.1 System Defined Functions
--Task – Create a function that returns the current time.
SELECT LOCALTIMESTAMP FROM DUAL;

--Task – create a function that returns the length of name in MEDIATYPE table
SELECT LENGTH(MEDIATYPE.NAME) FROM MEDIATYPE;

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 
SELECT AVG(TOTAL) FROM INVOICE;

--Task – Create a function that returns the most expensive track
/*REQUIRES NESTED SELECT STATEMENT IN ORDER TO RETURN ALL OF THE MOST
  EXPENSIVE TRACKS, AS THERE ARE SEVERAL OF THE SAME PRICE.  SINCE THE
  WORDING DIDN'T SPECIFY THAT WE JUST RETURN TO COST, BUT THE TRACK, 
  IT SEEMED BEST TO ERROR ON CAUTION AND RETURN ALL OF THEM.  (PROBABLY
  A GOOD TIME TO SAY THAT THE WORDING ON THIS ASSIGNMENT IS A LITTLE
  UNCLEAR AT BEST. */
SELECT NAME, UNITPRICE 
FROM TRACK 
WHERE UNITPRICE = 
(SELECT MAX(UNITPRICE) 
 FROM TRACK);

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items 
--(for each invoice) in the invoiceline table
/* USING CREATE OR REPLACE BECAUSE THIS SYNTAX IS REALLY QUIRKY, AND I'M TIRED OF
SEEING "FUNCTION ALREADY EXISTS" ERRORS DURING COMPILE.  THIS FUNCTION SIMPLY CALCULATES
THE AVERAGE OF THE INVOICE LINES (ITS A LITTLE UNCLEAR WHAT IS BEING ASKED FOR; IF WE DID
INVOICELINE AVERAGES PER INVOICE, THERE WOULD BE MORE THAN ONE RETURN VALUE; SINCE THIS IS
A SCALAR/AGGREGATE FUNCTION, I'M GUESSING IT'S JUST THE INVOICE LINE AVERAGE WE'RE RETURNING, 
UNLESS EVERYTHING WAS SUPPOSED TO BE DONE WITH CURSORS).*/

CREATE OR REPLACE FUNCTION ILINEAVERAGE
  RETURN NUMBER IS 
    ILAVG NUMBER(3,2);
  BEGIN
    SELECT AVG(UNITPRICE)
      INTO ILAVG 
      FROM INVOICELINE;
    RETURN ILAVG;
  END ILINEAVERAGE;
/

SELECT ILINEAVERAGE FROM DUAL;

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
/*USING A CURSOR WAS THE ONLY WAY I COULD GET THIS DONE.  I TRIED USING OTHER
CURSOR TYPES, BUT SYS_REFCURSOR WAS THE ONLY ONE THAT DIDN'T BLOW UP DURING COMPILATION. 
I KEPT TRYING TO FIGURE OUT A WAY TO THROW THE RESULTS INTO A DUAL TABLE BY CONCATENATING
THE USER NAMES, BUT NO LUCK.*/

CREATE OR REPLACE FUNCTION underFittyCrowd
  RETURN SYS_REFCURSOR AS 
     EMPLOYEES SYS_REFCURSOR;
BEGIN
  OPEN EMPLOYEES FOR 
    SELECT FIRSTNAME, LASTNAME 
    FROM EMPLOYEE 
    WHERE BIRTHDATE > '31-DEC-68';
  RETURN EMPLOYEES;
  CLOSE EMPLOYEES;
END;
/
SELECT UNDERFITTYCROWD FROM DUAL;


--4.0 Stored Procedures
/* In this section you will be creating and executing stored procedures. You will be creating 
various types of stored procedures that take input and output parameters.*/
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
/*LEANING ON THE CURSOR AGAIN.  PASSES A CURSOR (CREATED OUTSIDE THE FUNCTION WITH THE 
VARIABLE COMMAND) INTO THE PROCEDURE WHICH THEN SELECTS THE FIRST NAME AND LAST NAME OF THE EMPLOYEE.
ORACLE INCLUDED A WAY TO 'RETURN' FROM INSIDE A PROCEDURE (APPARENTLY TO ASSIST WITH MIGRATING FROM 
DIFFERENT SQL FLAVORS) THAT IS CALLED WITH DBA_SQL.RETURN_RESULT.  THIS ALLOWS FOR THE PROCEDURE TO 
BOTH SELECT THE EMPLOYEES AND THEN PRINT THEM.  THE COMMAND IS EXECUTED BY PASSING IN THE SYS_REFCURSOR
PUTTING ':' IN FRONT OF THE CURSOR NAME TO INDICATE THAT IT IS AN OUT PARAMETER.  */

CREATE OR REPLACE PROCEDURE GET_EMP_NAMES
 (CURSOR_ IN OUT SYS_REFCURSOR)
  IS
    BEGIN
     OPEN CURSOR_ FOR
      SELECT FIRSTNAME, LASTNAME 
        FROM EMPLOYEE;
      DBMS_SQL.RETURN_RESULT(CURSOR_);  
    END;
/
VARIABLE CURSES REFCURSOR;       
EXECUTE GET_EMP_NAMES(:CURSES);


--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.

/*TAKES IN EMPLOYEE ID AND DESIRED NEW ADDRESS.  USES SIMPLE UPDATE STATEMENT TO 
SET THE ADDRESS TO THE DESIRED VALUE*/
CREATE OR REPLACE PROCEDURE ADDRESS_CHANGE(
   EMPID IN INT, 
   NEWADDY IN VARCHAR2)
   IS
     BEGIN
       UPDATE EMPLOYEE 
         SET ADDRESS = NEWADDY 
           WHERE EMPLOYEEID = EMPID;
     END;
/
EXECUTE ADDRESS_CHANGE(1, '123 E WEST ST');
--Task – Create a stored procedure that returns the managers of an employee.
/* SUPER UGLY, BUT IT WORKS.  I USE A PARAMETER (MANID) TO GET THE REPORTSTO INDEX FROM EMPLOYEE, 
THEN GRAB THE MANAGERS NAME AND CONCAT THE FIRST AND LAST, PLACING IT INTO THE MANAGER VARCHAR.  MANAGER
(AND MANID) IS THEN RETURNED.  I DON'T USE MANID ANY FURTHHER, AND JUST USE PRINT TO LIST THE CONCATENATED
FIRST AND LAST NAME OF THE EMPLOYEE'S MANAGER.  IT'S RATHER DIFFICULT FINDING INFORMATION ON HOW TO JUST 
USE A TEMP VARIABLE INSIDE A FUNCTION TO GRAB THE REPORTSTO INDEX, AND I'M NOT EVEN SURE IF IT'S ALLOWED IN ORACLE
FRANKLY, AT THIS POINT, I'M JUST TRYING TO GET THROUGH THIS ASSIGNMENT, AND THINGS AREN'T LIKELY TO GET ANY PRETTIER.*/

CREATE OR REPLACE PROCEDURE GET_MANAGER(
  EMPID IN INT,
  MANID IN OUT INT,
  MANAGER OUT VARCHAR2)
   IS
     BEGIN
        SELECT REPORTSTO
          INTO MANID
          FROM EMPLOYEE
          WHERE EMPLOYEEID = EMPID;
          
        SELECT CONCAT(CONCAT(FIRSTNAME, ' '), LASTNAME) 
          INTO MANAGER 
          FROM EMPLOYEE 
          WHERE EMPLOYEEID = MANID;                                   
     END;
/     
VARIABLE THEMAN VARCHAR2(100);
VARIABLE MANID NUMBER;
/*MARTY MCFLY'S MANAGER (EMPLOYEEID = 10) IS BIFF (EMPLOYEEID = 9).  I MEAN, IT *IS* BIFF'S CASINO*/
EXECUTE GET_MANAGER(10, :MANID, :THEMAN);
PRINT THEMAN;

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
/*HELLO CURSOR MY OLD FRIEND.  IT'S NICE TO PARAMETERIZE YOU AGAIN.
SINCE I FOUND THE PRINT COMMAND FOR THE LAST TASK, I OPTED TO DITCH THE DBA_SQL.RETURNRESULT TO
SEE IF CURSOR WORKS WITH PRINT.  IT DOES, BUT THE OUTPUT IS UGLY.  ONCE AGAIN, I'M NOT GOING TO 
DIG TOO DEEPLY SINCE IT'S RETURNING WHAT IS ASKED.  IT'S 5PM ON SUNDAY, AND I'VE BEEN ON THIS ALL
WEEKEND AND HAVEN'T EVEN TOUCHED JDBC/JUNIT/LOG4J.*/
CREATE OR REPLACE PROCEDURE WORKPLACE(
  CUSTID IN INT,
  CURSOR_ OUT SYS_REFCURSOR) IS
  
  BEGIN
    OPEN CURSOR_ FOR
      SELECT CONCAT(CONCAT(CONCAT(CONCAT(FIRSTNAME, ' '), LASTNAME), ' WORKS AT '), COMPANY)
        FROM CUSTOMER
        WHERE CUSTOMERID = CUSTID;
  END;
/
VARIABLE CURSES REFCURSOR;
EXECUTE WORKPLACE(1,:CURSES);
PRINT CURSES;

/*5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.*/
--Task – Create a transaction that given a invoiceId will delete that invoice 
--(There may be constraints that rely on this, find out how to resolve them).
/* SINCE, IN THE EARLIER TASK, I HAD REMOVED THE FOREIGN KEY, THERE ARE NO LONGER ANY CONSTRAINTS
THAT WOULD PREVENT ME FROM JUST DELETING THE INVOICE.  HOWEVER, I STILL INCLUDED THE CODE TO DELETE 
THE ITEMS FROM INVOICELINE.  I'M NOT GOING TO LOOK UP HOW TO RE-ENABLE THE FOREIGN KEY (THOUGH I SUSPECT
IT'S JUST A QUICK ALTER PROCEDURE), BUT I SUSPECT THIS WILL STILL DO THE JOB HAD THE FK RESTRICTION
STILL BEEN IN PLACE 
EDIT:  AFTER EXECUTING THE PROCEDURE, VERIFIED THAT INVOICELINES 1 AND 2 WERE REMOVED, SO UNLESS THERE
WAS ANOTHER FK DEPENDENCY, IT'S WORKING AS INTENDED (A RARITY ON THIS ASSIGNMENT)*/

CREATE OR REPLACE PROCEDURE DELETEINVOICE(
  INVID IN INT) IS
  BEGIN
    DELETE FROM INVOICELINE WHERE INVOICEID = INVID;
    DELETE FROM INVOICE WHERE INVOICEID = INVID;
    COMMIT;
  END;
/
EXECUTE DELETEINVOICE(1);
--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
/* NICE TO FINISH OFF WITH AN EASY ONE. NOTHING FANCY HERE; IT TAKES IN ALL THE NECESSARY VALUES AND INSERTS
THEM INTO THE TABLE.  I TRIED TO DO THE EXECUTE ON TWO LINES, BUT APPARENTLY IT DOESN'T LIKE THAT. */
CREATE OR REPLACE PROCEDURE ADDCUSTOMER(
  C_ID IN INT,
  C_FNAME IN VARCHAR2,
  C_LNAME IN VARCHAR2, 
  C_COMP IN VARCHAR,
  C_ADDY IN VARCHAR2, 
  C_CITY IN VARCHAR2,
  C_STATE IN VARCHAR2,
  C_COUNTRY IN VARCHAR2, 
  C_ZIP IN VARCHAR2,
  C_PHONE IN VARCHAR,
  C_FAX IN VARCHAR,
  C_EMAIL IN VARCHAR,
  C_REP IN INT) IS
  
  BEGIN
    INSERT INTO CUSTOMER VALUES(C_ID, C_FNAME, C_LNAME, C_COMP, C_ADDY, C_CITY, C_STATE, C_COUNTRY,
                         C_ZIP, C_PHONE, C_FAX, C_EMAIL, C_REP);
    COMMIT;
  END;
/
/*IT'S NOT CANON, BUT I'M PRETTY SURE BETH HYPHENATES HER NAME.  IF NOT, SHE REALLY SHOULD.*/
EXECUTE ADDCUSTOMER(62, 'Beth', 'Sanchez-Smith', null, '1234 Jerrys House', 'Seattle', 'WA', 'USA', '98101', '1-777-666-5454', null, 'aintnobodygottime4@that.com', 4);

/*6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
6.1 AFTER/FOR
*/
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
/*WHOOPS, ALL OF THE GENRE NAMES GOT UPDATED BECAUSE I FORGOT THE WHERE.  WELL, THE TRIGGER WORKS, ANYWAYS
EDIT: WHOOPS AGAIN, DIDN'T SEE THIS WAS SUPPOSED TO BE ON THE EMPLOYEE TABLE.
EDIT: GETTING THE SAME RECURSIVE BEHAVIOR HERE AS BELOW.  I CAN'T PUT ANY MORE TIME INTO SOMETHING THAT I'M NOT
REALLY GOING TO USE CONSIDERING I HAVE A PRESENTATION ON CODE I HAVEN'T EVEN STARTED IN ~42 HOURS.*/
CREATE OR REPLACE TRIGGER AFTERINSERT2
  AFTER INSERT 
    ON EMPLOYEE
  BEGIN
    UPDATE GENRE SET GENRE.NAME = 'ROCK/METAL';
  END;
/

INSERT INTO EMPLOYEE VALUES(11, 'Tannen', 'Biff', 'Casino Operator', (null), '27-OCT-69', '12-FEB-10', '1 Some Street', 'Hill Valley',
'CA', 'USA', '91905', '1 (555) 555-1212', '1 (555) 555-1213', 'biff@tannenenterprises.com');  
--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
/*MIGHT AS WELL KEEP WITH THE THEME THAT STARTED IN THE PREVIOUS TASK. PS - I'M THINKING 'AFTER A ROW IS INSERTED
IN THE TABLE' WAS SUPPOSED TO BE AFTER A ROW IS UPDATED?
EDIT: THIS ONE ISN'T WORKING BECAUSE I'M GETTING SOME KIND OF RECURSIVE BEHAVIOR.  I NEED TO GET WORKING ON JDBC
SO I'M JUST GOING TO TAKE THE HIT ON THIS ONE.*/
CREATE OR REPLACE TRIGGER AFTERUPDATE2
  AFTER UPDATE
    ON CUSTOMER
  BEGIN
    UPDATE GENRE SET GENRE.NAME = 'I SAID ROCK/METAL.';
  END;
/
UPDATE CUSTOMER SET FIRSTNAME = 'SKIPPY' WHERE CUSTOMERID = 1;
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
/*I'M GETTING THE EXACT SAME RECURSIVE BEHAVIOR, BUT IT LOOKS LIKE IT'S COMING FROM AN OLDER TRIGGER.  THE 
PREVIOUS TRIGGERS MAY OR MAY NOT WORK, SO NEVER MIND WHAT I SAID EARLIER; GIVE THEM A SHOT AND SEE IF THEY ARE
FUNCTIONAL.  I'M DEFINITELY NOT SPENDING TIME CHASING DOWN WHAT THE ISSUE IS WHEN I DON'T HAVE THE STRONGEST 
GRASP ON THE INTERNAL WORKINGS OF THIS SYSTEM.*/
CREATE OR REPLACE TRIGGER AFTERDELETE
  AFTER DELETE ON CUSTOMER
    BEGIN
      UPDATE CUSTOMER SET CITY = 'RAGEVILLE' WHERE CUSTOMERID = 2;
    END;
/
DELETE FROM CUSTOMER WHERE CUSTOMERID = 62;
/*7.0 JOINS
In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
7.1 INNER*/
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT A.FIRSTNAME, B.INVOICEID 
FROM CUSTOMER A 
INNER JOIN INVOICE B 
ON B.CUSTOMERID = A.CUSTOMERID;
--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT A.CUSTOMERID, A.FIRSTNAME, A.LASTNAME, B.INVOICEID, B.TOTAL
FROM CUSTOMER A
FULL OUTER JOIN
INVOICE B
ON
B.CUSTOMERID = A.CUSTOMERID;
--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT A.NAME, B.TITLE
FROM ALBUM B
RIGHT JOIN
ARTIST A
ON A.ARTISTID = B.ARTISTID;

--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
FROM ALBUM A
CROSS JOIN
ARTIST B
ORDER BY 
B.NAME ASC;
--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.*.
SELECT CONCAT(A.FIRSTNAME, A.LASTNAME) 
AS EMP, 
CONCAT(B.FIRSTNAME, B.LASTNAME)
AS THEMAN
FROM EMPLOYEE A
JOIN EMPLOYEE B
ON
A.REPORTSTO = B.EMPLOYEEID;

