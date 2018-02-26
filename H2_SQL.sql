/************************************
************* 2.1 start *************
*************************************/

-- Task - Select all records from the Employee table.
select * 
from employee;

-- Task - Select all records from the Employee table where last name is King.
select * 
from employee 
where lastname='King';

-- Task - Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
select * 
from employee 
where firstname='Andrew' and reportsto is null;

/************************************
************* 2.2 start *************
*************************************/

-- Task - Select all albums in Album table and sort result set in descending order by title.
select * 
from album 
order by title desc;

-- Task - Select first name from Customer and sort result set in ascending order by city
select firstname 
from customer 
order by city;

/************************************
************* 2.3 start *************
*************************************/

-- Task - Insert two new records into Genre table
insert into genre values(26, 'Acoustic');

-- Task - Insert two new records into Employee table
insert into employee(employeeid, lastname, firstname, title, reportsto, birthdate, 
    hiredate, address, city, state, country, postalcode, phone, fax, email)
    values(9, 'Bob', 'Billy', 'IT Staff', 6, '17-DEC-92', '10-JUN-05', '100 Example Ave', 
    'Atlanta', 'GA', 'USA', '30305', '+1 (404) 640-0093', '+1 (403) 555-6878', 'bbob@gmail.com');
    
insert into employee(employeeid, lastname, firstname, title, reportsto, birthdate, 
    hiredate, address, city, state, country, postalcode, phone, fax, email) 
    values(10, 'Rowe', 'Mike', 'IT Staff', 6, '10-AUG-88', '15-JAN-98', 
    '1001 Example Two Rd', 'Atlanta', 'GA', 'USA', '30305', '+1 (404) 890-4564', 
    '+1 (403) 555-4532', 'mrowe@gmail.com');
    
-- Task - Insert two new records into Customer table
insert into customer(customerid, firstname, lastname, company, address, city, state, 
    country, postalcode, phone, fax, email, supportrepid)
    values(60, 'Joe', 'Robinson', 'Revature', '13 Maplecrest Dr', 'Danbury', 'CT', 
    'USA', '06811', '+1 (432) 234-3215', null, 'jrob@gmail.com', 4);
    
insert into customer(customerid, firstname, lastname, company, address, city, state, 
    country, postalcode, phone, fax, email, supportrepid)
    values(61, 'Kevin', 'Nutley', 'Around The Rings', '100 Peachtree Ave', 'Atlanta', 
    'GA', 'USA', '30305', '+1 (423) 423-4324', null, 'knutley@gmail.com', 4);
    
/************************************
************* 2.4 start *************
*************************************/

-- Task - Update Aaron Mitchell in Customer table to Robert Walter
update customer 
set firstname='Robert', lastname='Walter' 
where firstname='Aaron' and lastname='Mitchell';

-- Task - Update name of artist in the Artist table "Creedence Clearwater Revival" to "CCR"
update artist 
set name='CCR'
where name='Creedence Clearwater Revival';

/************************************
************* 2.5 start *************
*************************************/

-- Task - Select all invoices with a billing address like "T"
select * 
from invoice 
where billingaddress like '%T%';

/************************************
************* 2.6 start *************
*************************************/

-- Task - Select all invoices that have a total between 15 and 50
select * 
from invoice 
where total > 15 and total < 50;

-- Task - Select all employees hired between 1st of June 2003 and 1st of March 2004
select * 
from employee 
where hiredate > '01-JUN-03' and hiredate < '01-MAR-04';

/**************************************
************** 2.7 start **************
***************************************/

-- Task - Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).

-- get user customerid value to delete records associated with user (32)
select * 
from customer 
where firstname='Robert' and lastname='Walter';

-- get invoice ids associated with customer (50,61,116,245,268,290,342)
select * 
from invoice 
where customerid=32 order by invoiceid;

-- delete records from invoiceline table
delete from invoiceline
where invoiceid in(
    select invoiceid from invoiceline
    where invoiceid in(50,61,116,245,268,290,342)
);

-- delete records from invoice
delete from invoice
where customerid=32;

-- delete customer
delete from customer
where customerid=32;

/**************************************
************** 3.1 start **************
***************************************/

-- Task - Create a function that returns the current time.
select to_char(sysdate, 'HH24:MI:SS') 
from dual;

-- Task - Create a function that returns the length of name in MEDIATYPE table
select length(name) 
from mediatype;

/**************************************
************** 3.2 start **************
***************************************/

-- Task – Create a function that returns the average total of all invoices
select avg(total) 
from invoice;

-- Task – Create a function that returns the most expensive track
select * 
from track 
where unitprice = (
    select max(unitprice) from track
);

/**************************************
************** 3.3 start **************
***************************************/

-- Task – Create a function that returns the average price of invoiceline items in the invoiceline table
create or replace function get_average_price
    return number as
        average number(10, 2);
    begin
        select avg(unitprice) into average 
        from invoiceline;
        return average;
    end;
    /
select get_average_price from dual;

/**************************************
************** 3.4 start **************
***************************************/

-- Task – Create a function that returns all employees who are born after 1968
create or replace type emp_object
    as object
    (emp_firstname varchar2, emp_lastname varchar2, emp_birthdate varchar2);
    /
    
create or replace type emp_table
    is table of emp_object;
    /
    
create or replace function emp_post_1968

    return emp_table is
        emp_object.emp_firstname varchar2(15);
        emp_object.emp_lastname varchar2(15);
        emp_object.emp_birthdate varchar2(15);
        my_variable emp_table := emp_table;
        
    begin
        select emp_object(firstname, lastname, birthdate)
        bulk collect into my_variable
        from employee
        where birthdate > '31-Dec-1968';
        return my_variable;
    end;
    /
    select * from table(emp_post_1968);

/**************************************
************** 4.1 start **************
***************************************/

-- Task – Create a stored procedure that selects the first and last names of all the employees.
create or replace procedure emp_names

    (curs out sys_refcursor) as

    begin
        open curs for
        select firstname, lastname from employee;
        dbms_sql.return_result(curs);
    end;
    /
    var curs refcursor
    execute emp_names(:curs);

/**************************************
************** 4.2 start **************
***************************************/

-- Task – Create a stored procedure that updates the personal information of an employee.
create or replace procedure update_emp

    (emp_id int, emp_title varchar2, emp_reportsto int) 
    
    as
    
    begin
        update employee set title=emp_title, reportsto=emp_reportsto
        where employeeid=emp_id;
    end;
    /
    execute update_emp(10, 'Database Manager', 1);
    
-- Task – Create a stored procedure that returns the managers of an employee.
create or replace procedure emp_manager

    (emp_id int) 
    
    as
    
    begin
        open curs for
        select * from employee where reportsto=emp_id;
        return m_firstname + m_lastname;
        dbms.output.put_line();
    end;
    /
    execute emp_manager(9);
    
/**************************************
************** 4.3 start **************
***************************************/

-- Task – Create a stored procedure that returns the name and company of a customer.
create or replace procedure cust_company

    (cust_id int, curs out sys_refcursor)

    as
    
    begin
        open curs for 
        select company
        from customer
        where customerid = cust_id;
        dbms_sql.return_result(curs);
    end;
    /
    var curs refcursor;
    execute cust_company(1, :curs);
    
    
/**************************************
************** 5.0 start **************
***************************************/

-- Task – Create a transaction that given a invoiceId will delete that invoice
create or replace procedure deleteInvoice(inv_id int) 
    
    as
    
    begin
        delete from invoiceline
        where invoiceid = inv_id;
        delete from invoice
        where invoiceid = inv_id;
    end;
    /
    execute deleteInvoice(111);
    
-- Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
    create or replace procedure insertCust
    
    (cust_id int, firstname varchar2, lastname varchar2, company varchar2, address varchar2, city varchar2,
    state varchar2, country varchar2, postal_code varchar2, phone varchar2, fax varchar2, email varchar2, rep_id int)
    
    as
    
    begin
        insert into customer
        values(cust_id, firstname, lastname, company, address, city, state, country, postal_code, phone, fax, email, rep_id);
    end;
    
/**************************************
************** 6.1 start **************
***************************************/ 

-- Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table
create or replace trigger new_emp
    
    after insert on employee
    
    begin
        dbms_output.put_line('New employee');
    end;
    /
    
-- Task – Create an after update trigger on the album table that fires after a row is inserted in the table
create or replace trigger new_album
    
    after insert on album
    
    begin
        update album set albumid = 1000 
        where albumid = 1;
    end;
    /

-- Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
create or replace trigger new_customer
    
    after insert on customer
    
    begin
        delete from customer
        where albumid = 1;
    end;
    /
    
/**************************************
************** 7.1 start **************
***************************************/

-- Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select customer.firstname, customer.lastname, invoice.invoiceid
from customer
inner join invoice
on customer.customerid = invoice.customerid
order by invoice.invoiceid;

/**************************************
************** 7.2 start **************
***************************************/

-- Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
from customer
full outer join invoice
on customer.customerid = invoice.customerid
order by invoice.invoiceid;

/**************************************
************** 7.3 start **************
***************************************/

-- Task – Create a right join that joins album and artist specifying artist name and title.
select artist.name, album.title
from artist
right join album
on artist.artistid = album.artistid
order by album.title;

/**************************************
************** 7.4 start **************
***************************************/

-- Task - Create a cross join that joins album and artist and sorts by artist name in ascending order.
select *
from artist
cross join album
order by artist.name asc;

/**************************************
************** 7.5 start **************
***************************************/

-- Task – Perform a self-join on the employee table, joining on the reportsto column.
select a.employeeid, b.employeeid, a.firstname, b.firstname, a.lastname, b.lastname, a.reportsto, b.reportsto
from employee a, employee b
where a.reportsto = b.reportsto
order by a.employeeid;
