create table customer (
    customerID      number(5)       NOT NULL,
  	userName        varchar2(30)    NOT NULL,
	pass            varchar2(30)    NOT NULL,
	firstName       varchar2(30)    NOT NULL,
	lastName        varchar2(30)    NOT NULL,
	streetAddress   varchar2(100)   NOT NULL,
	city            varchar2(50)    NOT NULL,
	state           varchar2(2)     NOT NULL,
	phoneNumber     varchar2(12)    NOT NULL,
    account1        number(20),
    account2        number(20),
    account3        number(20),
    account4        number(20),
	accountHolder   varchar2(1)     NOT NULL);
/


/* DESTROY EVERYTHING */
/*
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
*/