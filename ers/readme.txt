*****NOTE:  View resolved reimbursements WILL work with one change to the GetResolvedReimbursements DAO:
When building the sql string for the prepared statement, if 
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR=? AND RT_STATUS=? AND RT_STATUS=?";
is changed to 
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR=? AND RT_STATUS=? OR RT_STATUS=?";
The test entries that are resolved will show up.  (Discovered 3/17 6:13p)


SQL file: Project1.sql

***TOMCAT SERVER VERSION:  8.0.50

Tested with StarkJ

Admin users :  Password

pm  :  eyepatch
rs  :  wubbalubbadubdub
mm  :  lookatme

Employee  :  Password

js  :  pathetic
bs  :  morecomplete
ss  :  summerissafe
tr  :  imtinyrick
