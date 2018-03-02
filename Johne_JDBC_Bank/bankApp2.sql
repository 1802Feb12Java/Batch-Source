/*******************************************************************************
   BankApp Database pt2
   Script: bankApp.sql
   Description: Creates and populates the BankApp database.
   DB Server: Oracle
   Author: Johne Vang
********************************************************************************/

DROP TABLE BANKADMIN;
DROP TABLE BANKACCOUNT;
DROP TABLE CUSTOMER;

DROP SEQUENCE admin_ID_seq;
DROP SEQUENCE bank_ID_seq;
DROP SEQUENCE customers_ID_seq;


/*
    Customer Table
*/
CREATE TABLE CUSTOMER
(
    Customer_ID INT NOT NULL,
    Customer_Username VARCHAR(20) NOT NULL,
    Customer_Password VARCHAR(20) NOT NULL,
    CONSTRAINT CUSTOMERS_pk PRIMARY KEY (Customer_ID)
);

CREATE SEQUENCE customers_ID_seq
    START WITH 1000
    INCREMENT BY 1;

INSERT INTO CUSTOMER (Customer_ID, Customer_Username, Customer_password)
VALUES (customers_ID_seq.NEXTVAL, 'blake', 'blake');

SELECT * FROM CUSTOMER;
/*
    Bank Account table
*/
CREATE TABLE BANKACCOUNT
(
    Account_id INT NOT NULL,
    Customer_id INT NOT NULL,
    balance NUMBER(10,2) NOT NULL,
    CONSTRAINT BANKACCOUNT_pk PRIMARY KEY (Account_id),
    CONSTRAINT CUSTOMERS_BANKACCOUNT_fk FOREIGN KEY (Customer_id)
    REFERENCES CUSTOMER (Customer_id)
);

CREATE SEQUENCE bank_ID_seq
    START WITH 1000
    INCREMENT BY 1;

INSERT INTO BANKACCOUNT (account_id, customer_id, balance)
VALUES (bank_id_seq.NEXTVAL, 1000, 1000);

SELECT * FROM BANKACCOUNT;
/*
    Bank Admin
*/
CREATE TABLE BANKADMIN
(
    BankAdmin_ID INT NOT NULL,
    BankAdmin_username VARCHAR(20) NOT NULL,
    BankAdmin_password VARCHAR(20) NOT NULL,
    CONSTRAINT BANKADMINS_pk PRIMARY KEY (BankAdmin_ID)
);

CREATE SEQUENCE admin_ID_seq
    START WITH 1000
    INCREMENT BY 1;
    
INSERT INTO BANKADMIN (BankAdmin_ID, BankAdmin_username, BankAdmin_password)
VALUES (admin_ID_seq.NEXTVAL, 'admin', 'admin');

SELECT * FROM BANKADMIN;