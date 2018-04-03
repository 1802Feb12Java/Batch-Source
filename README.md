# Revature Training program Batch 1802-feb12  
This branch contains notes/examples, assignments, and projects from the Revature training program by Kevin Hsieh from Batch 1802.  

## Project 1 - Expense Reimbursement System

For Angular prart go to https://github.com/khsieh/revature-project1-ng  
should be able to do CORS with Angular running on port 4200 and Tomcat running java backend on port 8080  

### Instructions to build
#### setup Server
1. Go to ExpenseReimbursementSystem directory
2. Load the folder as a project in SprintToolSuite  
3. Run as server using Tomcat8.5

#### setup Client
1. Go to https://github.com/khsieh/revature-project1-ng and download the project
2. Load the project in Visual Studio Code
3. execute `npm install` for node_modules
4. Execute 	`ng serve`

#### Using the application
1. Once both server and client are setup go to `localhost:4200/` in your browswer


### Technologies used
	Java 1.8  
	git  
	oracale SQL DB  
	sql Developer  
	Angular 4   




## JDBC Banking App Part II
Project is in JDBCBank directory  
JDBC file is in SQL directory  

### Path Change
change paths in  
  log4j.properties: "log4j.appender.file.File" to where you want the bank.log  
  com.revature.util.ConnFactory: "fileParth" to where the database.properties' path  

### Technologies used  
 	Log4j  
 	Java 1.8  
 	git  
	oracale SQL Database  
	sqldeveloper
