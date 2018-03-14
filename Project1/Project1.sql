BEGIN       --something I found online to drop all my chinook stuff easier
   FOR cur_rec IN (SELECT object_name, object_type FROM user_objects WHERE object_type IN ('TABLE', 'VIEW', 'PACKAGE', 'PROCEDURE', 'FUNCTION', 'SEQUENCE'))
   LOOP
      BEGIN
         IF cur_rec.object_type = 'TABLE' --sees if its a table
         THEN
            EXECUTE IMMEDIATE    'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '" CASCADE CONSTRAINTS';  --goes through constraints too if it's a table
         ELSE
            EXECUTE IMMEDIATE    'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"';    --otherwise, just drop it straight up
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            DBMS_OUTPUT.put_line ('FAILED: DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"');
      END;
   END LOOP;
END;
/

create table ERS_REIMBURSEMENTS(
  r_id number(*,0) primary key,
  r_amount number(22,2) not null,
  r_description varchar2(100),
  r_receipt blob,
  r_submitted timestamp not null,
  r_resolved timestamp,
  u_id_author number(*,0) not null,
  u_id_resolver number(*,0),
  rt_type number(*,0) not null,
  rt_status number(*,0) not null
);

create table ERS_USERS(
  u_id number(*,0) primary key,
  u_username varchar2(40) not null unique,
  u_password varchar2(40) not null,
  u_firstname varchar2(30),
  u_lastname varchar2(30),
  u_email varchar2(100) unique,
  ur_id number(*,0) not null
);

create table ERS_USER_ROLES(
  ur_id number(*,0) primary key,
  ur_role varchar2(40)
);

create table ERS_REIMBURSEMENT_STATUS(
  rs_id number(*,0) primary key,
  rs_status varchar2(30) not null
);

create table ERS_REIMBURSEMENT_TYPE(
  rt_id number(*,0) primary key,
  rt_type varchar2(30) not null
);

create or replace procedure update_reimbursement_status
(reim_id in int, newStatus in int, resolver_id in int) is
begin
  update ers_reimbursements set rt_status = newStatus where r_id = reim_id;
  update ers_reimbursements set r_resolved = CURRENT_TIMESTAMP where r_id = reim_id;
  update ers_reimbursements set u_id_resolver = resolver_id where r_id = reim_id;
  commit;
end;
/

--Sequences
create sequence useridsequence
minvalue 1
maxvalue 99999999999999999
start with 1
increment by 1;

create sequence reimbursementidsequence
minvalue 1
maxvalue 99999999999999999
start with 1
increment by 1;

alter table ERS_REIMBURSEMENTS add foreign key (u_id_author) references ERS_USERS(u_id);
alter table ERS_REIMBURSEMENTS add foreign key (u_id_resolver) references ERS_USERS(u_id);
alter table ERS_REIMBURSEMENTS add foreign key (rt_type) references ERS_REIMBURSEMENT_TYPE(rt_id);
alter table ERS_REIMBURSEMENTS add foreign key (rt_status) references ERS_REIMBURSEMENT_STATUS(rs_id);
alter table ERS_USERS add foreign key (ur_id) references ERS_USER_ROLES(ur_id);

insert into ers_user_roles values(1,'manager');
insert into ers_user_roles values(2,'employee');

insert into ers_reimbursement_status values (1, 'pending');
insert into ers_reimbursement_status values (2, 'approved');
insert into ers_reimbursement_status values (3, 'denied');

insert into ers_reimbursement_type values (1, 'business');
insert into ers_reimbursement_type values (2, 'travel');
insert into ers_reimbursement_type values (3, 'medical');

insert into ers_users values(useridsequence.nextval, 'joe', '@@', 'Jim', 'George', 'joe@email.com', 1);  --manager
insert into ers_users values(useridsequence.nextval, 'jim', '@!', 'Joe', 'Bob', 'jim@email.com', 2);  --employee

insert into ers_reimbursements values(reimbursementidsequence.nextval, 20.50, 'desc1', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, null, 1, 3);
insert into ers_reimbursements values(reimbursementidsequence.nextval, 91.13, 'desc2', null, CURRENT_TIMESTAMP, null, 1, null, 2, 1);

commit;