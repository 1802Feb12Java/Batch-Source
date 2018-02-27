--BEGIN       --something I found online to drop all my chinook stuff easier
--   FOR cur_rec IN (SELECT object_name, object_type FROM user_objects WHERE object_type IN ('TABLE', 'VIEW', 'PACKAGE', 'PROCEDURE', 'FUNCTION', 'SEQUENCE'))
--   LOOP
--      BEGIN
--         IF cur_rec.object_type = 'TABLE' --sees if its a table
--         THEN
--            EXECUTE IMMEDIATE    'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '" CASCADE CONSTRAINTS';  --goes through constraints too if it's a table
--         ELSE
--            EXECUTE IMMEDIATE    'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"';    --otherwise, just drop it straight up
--         END IF;
--      EXCEPTION
--         WHEN OTHERS
--         THEN
--            DBMS_OUTPUT.put_line ('FAILED: DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"');
--      END;
--   END LOOP;
--END;
--/

--Sequences
create sequence useridsequence
minvalue 1
maxvalue 99999999999999999
start with 1
increment by 1;

create sequence accountidsequence
minvalue 1
maxvalue 99999999999999999
start with 1
increment by 1;

--Table Creation
create table usertable(
  user_id int primary key,
  accesslevel number(1,0) not null check (accesslevel = 1 or accesslevel = 0), --1 will be superuser, 0 will be normal user
  username varchar(100) not null,
  pass varchar(100) not null,
  fullname varchar(100) not null
);
create table transactions(
  user_id int not null,
  time_of_transaction varchar(200) not null,
  description varchar(200) not null,
  foreign key (user_id) references usertable(user_id)
);
create table accounts(
  account_id int,
  user_id int not null,
  second_user_id int,
  balance number(22,2) not null,
  approved number(1,0) not null check (approved = 1 or approved = 0),    --1 will be approved, 0 will be not approved
  foreign key (user_id) references usertable(user_id),
  foreign key (second_user_id) references usertable(user_id)
);

--Triggers
create or replace trigger user_insert_trigger
after insert on usertable
for each row
begin
  insert into transactions values (useridsequence.currval, current_timestamp, 'New user ' || useridsequence.currval || ' created');
end;
/
create or replace trigger account_insert_trigger
before insert on accounts
begin
  insert into transactions values (-1, current_timestamp, 'New account ' || accountidsequence.currval || ' created');
end;
/

--Stored Procedures
create or replace procedure user_delete_procedure
(u_id in int) is
begin
  delete from accounts where user_id = u_id;
  update transactions set user_id = -1 where user_id = u_id;    --transactions with user -1 will be from deleted users
  delete from usertable where user_id = u_id;
  commit;
end;
/

--RESET (in case I messed up with jdbc)
--truncate table transactions;
--truncate table accounts;
--delete from usertable;
--
--drop trigger user_insert_trigger;   --wouldn't let me put -1 if that happened
--insert into usertable values (-1, 0, 'deleted user', 'deleted user', 'deleted user');   --dummy value for transactions to point to if a user is deleted (see user_delete_procedure)
--insert into usertable values (-2, 0, 'super user', 'super user', 'super user');   --reference for the super user to use
--create or replace trigger user_insert_trigger
--after insert on usertable
--for each row
--begin
--  insert into transactions values (useridsequence.currval, current_timestamp, 'New user ' || useridsequence.currval || ' created');
--end;
--/
--drop sequence accountidsequence;
--drop sequence useridsequence;
--create sequence useridsequence
--minvalue 1
--maxvalue 99999999999999999
--start with 1
--increment by 1;
--
--create sequence accountidsequence
--minvalue 1
--maxvalue 99999999999999999
--start with 1
--increment by 1;
