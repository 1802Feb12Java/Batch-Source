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
delete from customer where firstname = 'Robert' and lastname = 'Walter';  --FAILS, doesn't work cuz there's a constraint in invoice that points to customerid's
select customerid from customer where firstname = 'Robert' and lastname = 'Walter';   --find out Robert Walter's customerid (it's 32)
delete from invoice where customerid = 32;        --FAILS, find out there's another constraint in invoiceline that points to the invoiceid's
delete from invoiceline where invoiceid in (select invoiceid from invoice where customerid = 32);   --delete all invoiceline id's that have the invoiceid from Robert Walter
delete from invoice where customerid = 32;        --delete all the invoices related to robert walter
delete from customer where firstname = 'Robert' and lastname = 'Walter';    --finally delete robert walter from customer table now that all FK's that point to it are gone

--SQL Functions
--In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
--3.1 System Defined Functions
--Task – Create a function that returns the current time.
select current_timestamp from dual;   --returns current time, using system function

--Task – create a function that returns the length of name in MEDIATYPE table
select name, length(name) from mediatype;   --returns length of name for each row (also included the name so I could see them)

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 
select avg(total) from invoice;   --returns the average of the "total" column in the invoice table

--Task – Create a function that returns the most expensive track
select name, unitprice from track where unitprice = (select max(unitprice) from track);   --inner select returns the highest unit price
  --outer select returns the name and unitprice of all tracks that have the highest unit price

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items (for each invoice) in the invoiceline table
create or replace function func1
return number is avgInvoiceline number(3,2);
begin
  select avg(unitprice) into avgInvoiceline from invoiceline;   --what is this one saying, this is my best guess
  return avgInvoiceline;
end func1;
/
select func1() from dual;
select avg(unitprice) from invoiceline;   --what is this one saying, this is my best guess

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
create or replace function func1
return sys_refcursor as oldempnames sys_refcursor;  --returning a cursor (kinda works for a table)
begin
  open oldempnames for select lastname from employee where birthdate > '31-DEC-68'; --add the lastname to the cursor if birthdate's after 1968
  return oldempnames;
end;
/
select func1() from dual;
select * from employee where birthdate > '31-DEC-68';


--4.0 Stored Procedures
-- In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
create or replace procedure proc1
(printer out sys_refcursor) is  --out parameter of a cursor
begin
  open printer for select firstname, lastname from employee;  --fillthe cursor with every first/last name from employee
  DBMS_SQL.RETURN_RESULT(printer);  --print the results
end;
/
var refcurs refcursor;    --creating an instance of a ref cursor to give to the procedure, I think?
execute proc1(:refcurs);
--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
create or replace procedure proc1
(idnum in int, newfirstname in varchar2) is
begin
  update employee set firstname = newfirstname where employeeid = idnum;  --typical update statement
end;
/
execute proc1(1, 'Joe');
execute proc1(4, 'Bib');
execute proc1(7, 'Mal');
execute proc1(10, 'Jack');
--Task – Create a stored procedure that returns the managers of an employee.
create or replace procedure proc1
(idnum in int, printer out sys_refcursor) is
begin
  open printer for select concat(a.firstname, a.lastname) as subordinate, concat(b.firstname, b.lastname) as boss from employee a join employee b on b.employeeid = a.reportsto and a.employeeid = idnum;  --fillthe cursor with every first/last name from employee
  DBMS_SQL.RETURN_RESULT(printer);  --print the results
end;
/
var refcurs refcursor;
execute proc1(1, :refcurs);     --returns nothing because that's the GM, doesn't report to anyone
execute proc1(2, :refcurs);
--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
create or replace procedure proc1
(idnum in int, printer out sys_refcursor) is
begin
  open printer for select firstname, lastname, company from customer where customerid = idnum;  --fillthe cursor with every first/last name from employee
  DBMS_SQL.RETURN_RESULT(printer);  --print the results
end;
/
var refcurs refcursor;
execute proc1(1, :refcurs);
execute proc1(2, :refcurs);     --oh yeah, this is the one I changed to work at the hamster factory

--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
create or replace procedure proc1
(idnum in int) is
begin
  delete from invoiceline where invoiceid = idnum;  --gotta remove all the invoicelines with foreign keys pointing to the given invoiceid
  delete from invoice where invoiceid = idnum;    --then remove it from the invoice table
  commit;
end;
/
execute proc1(216);   --yup, it definitely deleted invoiceid 216 from the invoice table
--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
create or replace procedure proc1
(cid in int, fname in varchar2, lname in varchar2, ad in varchar2, city in varchar2, st in varchar2, coun in varchar2, post in varchar2, phone in varchar2, email in varchar2, repid in int) is
begin       -- ^ (above = take in all the necessary variables to create a new line in customer)
  INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) values (cid, fname, lname, ad, city, st, coun, post, phone, email, repid);
end;
/
execute proc1(59, 'sdj', 'jd', 'sg', 'jfd', 'sdd', 'ksd', 'sdk', 'qio', 'sfoi', 5);   --lol this is a disgusting line in the table but it's definitely added

--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
create sequence employeeidsequence    --creating a sequence to use to increment the customerid each time there's one inserted
minvalue 0
maxvalue 99999999999999999
start with 11
increment by 1;

create or replace trigger empinserttrigger   --creating the trigger to update the customerid to the value from the sequence
after insert on employee
begin
update employee set employeeid = employeeidsequence.nextval;   --finding out apparently i can't use it for customerid because there's a foreign key that points to it immediately
end;
/
create or replace trigger empinserttrigger   --creating the trigger to update the customerid to the value from the sequence
after insert on employee
begin
update employee set firstname = 'Bert';   --just gunna make every employee into George now because that doesn't have a foreign key attached
end;
/
--delimiter so I can test it some
insert into employee values (40, 'shf', 'sdfh', 'erhd', null, '09-FEB-83', '20-FEB-18', '445 Lake Daisy Drive', 'Winter Haven', 'FL', 'America', '33884', '+1 (863) 324-3824', '+1 (863) 345-1562', 'email@email.com');  --making up stuff to fill each slot
delete from employee where employeeid = 40;    --just keeping this here for testing purposes

--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
create or replace trigger albumUpdateTrigger   --creating the trigger to update the customerid to the value from the sequence
after insert on album
begin
update album set title = 'Relapse' where title like 'Lost%';   --every album that starts with "Lost" is Relapse now, deal with it
end;
/
insert into album values (348, 'Recovery', 14); 
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
create or replace trigger custDeleteTrigger
after delete on customer
begin
update customer set company = 'Hamster Factory' where customerid = 2;
end;
/
delete from invoiceline where invoiceid in (select invoiceid from invoice where customerid = 59);   --just gunna delete customerid 59 because i don't like them, so gotta delete its foreign keys
delete from invoice where customerid = 59;        --delete all the invoices from customer 59
delete from customer where customerid = 59;    --finally delete customer 59 from customer table now that all FK's that point to it are gone
--trigger went off, now customer 2 works at the hamster factory

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