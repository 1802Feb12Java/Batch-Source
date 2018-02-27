/* Set up the tables */
create table users (
    userID          int             NOT NULL,
  	userName        varchar2(30)    NOT NULL,
	pass            varchar2(30)    NOT NULL,
	firstName       varchar2(30)    NOT NULL,
	lastName        varchar2(30)    NOT NULL,
	streetAddress   varchar2(50),
	city            varchar2(20),
	state           varchar2(2),
	phoneNumber     varchar2(12),
    userType        varchar2(13)    NOT NULL,
    account1        int,
    account2        int,
    account3        int,
    account4        int,
	accountHolder   varchar2(1));
/

create table account (
    balance         number(9,2)     NOT NULL,
	accountNumber   number(15)      NOT NULL,
	status          varchar2(7)     NOT NULL,
	accountType     varchar2(8)     NOT NULL,
	accountHolder   int             NOT NULL)
/

CREATE SEQUENCE GENERATE_USER_ID
    START WITH 1
    INCREMENT BY 1;
/

CREATE SEQUENCE GENERATE_ACCOUNT_NUMBER
    START WITH 10000
    INCREMENT BY 1;
/

/* DESTROY EVERYTHING */

BEGIN   
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
