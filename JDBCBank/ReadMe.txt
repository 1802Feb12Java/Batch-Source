To run this program:

Make sure to redirect the "log4j.appender.file.File" line to where you have a .log file in the log4j.properties file.
Right-click JDBCBank (the main project folder) -> Build Path -> Add Libraries -> JUnit
Keep database.properties in the main directory for the project (on the same level as the src folders).

Current database values:

--//USERNAMES AND PASSWORDS ARE CASE-SENSITIVE\\--
User -1 (dummy value to show deleted users, transactions table will point to this if they're deleted)
User 1 (username = "joe", password = "@@",	name = "Jim Bob", owns accounts 1 and 2)
User 2 (username = "jim", password = "@13", name = "Joe Johnson", owns accounts 3 and 4)
SuperUser (all SuperUsers use the same account, not actually stored in DB - username = "superuser", password = "superpass")

Account 1 (user_id = 1 (joe), balance = 500, approved = 1 (true))
Account 2 (user_id = 1 (joe), balance = 250, approved = 1 (true))
Account 3 (user_id = 2 (jim), balance = 200, approved = 1 (true))
Account 4 (user_id = 2 (jim), balance = 80, approved = 1 (true))

To reset the database to these values:
Uncomment and run lines 84-109 in JDBCBank.sql on SQL Developer
Comment lines 61-104 in BankApp2Driver
Uncomment lines 110-165 in BankApp2Driver
Import java.sql.PreparedStatement into BankApp2Driver
Run BankApp2Driver