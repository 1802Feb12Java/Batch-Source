------------------------ create customer table
CREATE TABLE Customer ( cid Number,  username varchar(20),
            password varchar(20), fname varchar(20), lname varchar(20),address varchar(50), phonenumber varchar(11),
            primary key(cid));
            
--------------------------create customeraccount table
create table CustomerAccount (caid number, cid number, balance number
             ,primary key(caid) , foreign key (cid) references Customer(cid));
--------------------------------------------------------Create transaction             
------------------------------------------Sequence used to generate cid for customer table
create sequence customerid
minvalue 1
maxvalue 1000000
start with 1
increment by 1;

drop sequence customerid;

---------------------------------------Sequence used to generate caid for customeraccount table
create sequence acid
minvalue 1
maxvalue 1000000
start with 1
increment by 1;

drop sequence acid;

----------------------------create a trigger that executes sequence after customeraccount is inserted

create or replace trigger addcaid
before insert on CustomerAccount
for each row
begin 
    :new.caid := acid.nextval; --set cid = the next sequence
end;
/
----------------------------create a trigger that executes sequence after customer is inserted

create or replace trigger addcidtocustomer
before insert on Customer
for each row
begin 
    :new.cid := customerid.nextval; --set cid = the next sequence
end;
/

----------------------------------------

----------------------------------------------drop table
drop table customeraccount;
drop table Customer;
------------------------------------------------------------------------

---create table for admin
create table admin( aid number, username varchar2(20), password varchar2(20), firstname varchar2(50), lastname varchar2(50),
            primary key (aid) );

insert into admin values (1, 'admin2','admin2', 'first', 'name');

--------------------------------- Trigger generates id for admin table
create or replace trigger addAdminId
before insert on admin
for each row
begin 
    :new.aid := adminid.nextval; --set cid = the next sequence
end;
/
----sequence for adminaccount
create sequence adminid
minvalue 1
maxvalue 1000000
start with 1
increment by 1;

drop sequence adminid;
drop table admin;
