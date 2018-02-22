-- 2.0 SQL Queries
--In this section you will be performing various queries against the Oracle Chinook database.
--2.1 SELECT
--Task – Select all records from the Employee table.
select * from employee; --grab everything from employee
--Task – Select all records from the Employee table where last name is King.
select * from employee where lastname = 'King'; --grab only where lastname = king
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
select * from employee where firstname = 'Andrew' and reportsto is null;  --grab where first name is Andrew and reportsto column is null
--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
Select title from album order by title; --order by title
--Task – Select first name from Customer and sort result set in ascending order by city
Select firstname from customer order by city asc;  -- order by asc 
--2.3 INSERT INTO
--Task – Insert two new records into Genre table 
insert into genre values (26, 'House'); --inserting with all slots filled
insert into genre values (27, 'Nightcore'); --have to do a different id
--Task – Insert two new records into Employee table
insert into employee values (9, 'Smith', 'Jim', 'Jester', 1, '09-FEB-83', '20-FEB-18', '445 Lake Daisy Drive', 'Winter Haven', 'FL', 'America', '33884', '+1 (863) 324-3824', '+1 (863) 345-1562', 'email@email.com');  --making up stuff to fill each slot
insert into employee values (10, 'Dgeoi', 'Dnioa', 'Distraction', null, '16-JUL-76', '30-JAN-17', '444 Lake Daisy Drive', 'Winter Haven', 'FL', 'America', '33884', '+1 (863) 981-6432', '+1 (863) 164-6781', 'betteremail@email.com'); --making up part 2
--Task – Insert two new records into Customer table
insert into customer values (60, 'Goivh', 'Azxkno', null, '390 Winter Ham Road', 'Kansas City', 'Kansas', 'America', '29387', '+1 (732) 238-1092', '+1 (732) 012-1284', 'thirdemail@email.com', 4); --making up stuff for each slot again
insert into customer values (61, 'Qoifd', 'Pcskjhif', null, '391 Winter Ham Road', 'Kansas City', 'Kansas', 'America', '29387', '+1 (216) 564-8913', '+1 (216) 568-5487', 'fourthemail@email.com', 4);  --part 2 again
--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
update customer set firstname = 'Robert', lastname = 'Walter' where firstname = 'Aaron' and lastname = 'Mitchell';  --find Aaron Mitchell in customer, set their info to something else
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
Update artist set name = 'CCR' where name = 'Creedence Clearwater Revival'; --find "Creedence Clearwater Revival", set name to 'CCR'
--2.5 LIKE
--Task – Select all invoices with a billing address like “T%” 
select * from invoice where billingaddress like 'T%';   --Grabs all the invoices with that start with T
--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
select * from invoice where total between 15 and 50;    --grabs all the invoices with totals between 15 and 50
--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
select * from employee where hiredate between '01-JUN-03' and '01-MAR-04';  --looks at hiredate of all employees, returns the ones between those dates
--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
alter table invoice drop constraint FK_INVOICECUSTOMERID;   --removes the constraint of the foreign key that doesn't let me delete
delete from customer where firstname = 'Robert' and lastname = 'Walter';  --deletes [it'll be fine without this constraint (: ]

--SQL Functions
--In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
--3.1 System Defined Functions
--Task – Create a function that returns the current time.
select current_timestamp from dual;   --returns current time
--Task – create a function that returns the length of name in MEDIATYPE table
select name, length(name) from mediatype;   --returns length of a string/varchar, also included name to compare
--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 
select avg(total) from invoice;   --doing what's asked
--Task – Create a function that returns the most expensive track
select max(unitprice) from invoiceline;   --returning the max value
--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items (for each invoice) in the invoiceline table
select avg(unitprice) from invoiceline;   --what is this one saying, this is my best guess
--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
select * from employee where birthdate > '31-DEC-68';

--4.0 Stored Procedures
-- In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
--Task – Create a stored procedure that returns the managers of an employee.
--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.

--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table

--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.

--7.0 JOINS
--In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c.firstname, i.invoiceid from customer c inner join invoice i on i.customerid = c.customerid;  --inner join where customerid's same, alias customer as "c" & invoice as "i"
--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c.customerid, c.firstname, c.lastname, i.invoiceid, i.total from customer c full outer join invoice i on i.customerid = c.customerid;
          --outer join the two on the same condition as last, return the things requested
--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
select ar.name, al.title from album al right join artist ar on ar.artistid = al.artistid;   --return album title and artist when the artist id match
--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
select * from album al cross join artist ar order by ar.name asc;   --wow this returns so many rows because it's like all possible combinations
--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
select concat(a.firstname, a.lastname) as subordinate, concat(b.firstname, b.lastname) as boss from employee a join employee b on a.reportsto = b.employeeid;
      --concatting the firstname and last name of each to be able to alias them, joining where the first employee table's reportsto is equal to the second one's id
      --renaming the concats to show which name is which (the subordinate comes from table a, because b is the "employeeid" that table a "reportsto")
      
--Upload a .sql file containing your answers to your branch with each solution to each part marked with a descriptive comment.*/