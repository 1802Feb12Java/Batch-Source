Add all jars to project build path
	The jars are found in:
		src main webapp WEB-INF lib

Update the JDBC URL to AWS RDS that you need to create
	jdbc:oracle:thin:@localhost:1521:xe <--this is locally not remote change it to yours
	
Run the DDL/DML Script to have my Banking ERD on your Oracle RDS

Run project on Tomcat then hit login.html and login with: Steve 123
Create your own user if you wish
Only the login and changing views is finished
You can submit a withdrawal/deposit but it only sends the information to the server,
	the server doesn't persist this change to the DB