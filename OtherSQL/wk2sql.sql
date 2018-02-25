select * from album where albumid = 2 or albumid = 235;
select name from artist where artistid = 2;

--select aggregate from group by having
select count(employeeid), country, city from employee group by country, city having count(employeeid)>1;

--order by
select count(employeeid), country, city from employee group by country, city having count(employeeid)>1 order by count(employeeid) desc;

--scalar function
select concat(name, bytes) from track where bytes > 10000000 order by bytes desc;
select name, bytes from track where bytes > 10000000 order by bytes desc;

--inner join
select invoice.total, invoice.billingcity from invoice inner join customer on invoice.customerid=customer.customerid where invoice.customerid=1;

--right join
select invoice.total, invoice.billingcity from invoice right join customer on invoice.customerid=customer.customerid where invoice.customerid=1;

--left join
select invoice.total, invoice.billingcity from invoice left join customer on invoice.customerid=customer.customerid where invoice.customerid=1;

--full outer join
select invoice.total, invoice.billingcity from invoice full outer join customer on invoice.customerid=customer.customerid where invoice.customerid=1;

create table school(
  schoolid int,
  name varchar(10)
);

create table student(
  studentid int,
  name varchar(10),
  schoolid int
);

insert into school values (10, 'AL');
insert into school values (20, 'UGA');
insert into school values (30, 'OSU');
insert into school values (40, 'USF');
insert into school values (50, 'UNR');

insert into student values (1, 'Matt', 10);
insert into student values (2, 'Josh', 20);
insert into student values (3, 'Justin', 30);
insert into student values (4, 'Tim', 40);

update student set studentid = 5 where name = 'Seal';

select * from student left join school on school.schoolid = student.schoolid where school.name = 'UNR'; --nobody's in UNR, returns blank

--full join
select * from school full outer join student on school.schoolid = student.schoolid;

--sequence
create sequence myseq1
minvalue 2
maxvalue 99999999999999999
start with 99999999999999999
increment by -400;

select myseq1.nextval from dual;

--Trigger
create or replace trigger myinserttrigger
after insert on school
begin
update school set schoolid = myseq1.nextval;
end;


--truncate table school;
insert into school values (10, 'AL');
insert into school values (20, 'UGA');
insert into school values (30, 'OSU');
insert into school values (40, 'USF');
insert into school values (50, 'UNR');
/
--delimiter, seperates things, can't have anything after it

-- view to a kill
create view myview2
as select title, artist.name from album
join artist on artist.artistid = album.artistid;
/

select * from myview2;

--create function
create or replace function calc_tax
(t_id in number, tax in number)
  return number as
  total_price number(10,2);
  begin
  select unitprice into total_price
  from track where trackid=t_id;
  return total_price + (total_price*tax);
  end;
/
select calc_tax(100, .07) from dual;


/
create or replace procedure schinsertpro
(id in int, name in varchar2) as 
  begin
  insert into school values(id,name);
  commit;
  end;
  /

execute schinsertpro(70, 'UF');





create or replace procedure printstring
(string1 varchar2) as 
begin
    DBMS_OUTPUT.put_line(string1);
end;
  /
  
  execute printstring('Revature is a company');