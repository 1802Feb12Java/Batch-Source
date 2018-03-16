This is my ERS project. I have code implementing the required functionalities, however I have displayed only insert. An employee or manager can login to their accounts, an employee can create new reimbursements and have it displayed back to them everytime they reenter their homepage. A manager could approve or deny requests in my earlier version using jsp files, but because I have switched to html and java files, the table is unselectable. The functionalities are not visible to the user. The user and manager home pages have a functional logout.
I have used 4 tables for this project; ers_reimbursements, reimbursement_type, reimbursement_status, ers_users, ers_user_roles labeling a 2 for employee and 1 for manager
users already in the system are provided below;

id	username	password
1	spiderman	$2a$06$l36iDioZrZXgj4UkwquM2u4YuHinXcnT5XPRn8bOFJeJXZ5boR2Wu	Peter	Parker	peterp@dailybugle.com		2
2	ironman		$2a$06$/YXI.ipwOsoQEmEp6y3IouK/s0/CjENon/Fye.Z2kbihRm0D6wFiW	Tony	Stark	ceo@starkindustries.com		1
3	hulk		$2a$06$NJ6E3iE2rhupQHtdDk0P.OzFovfGQlKZTOML8fHSsOIhii8OZFl/6	Bruce	Banner	bruceb@harvard.edu		1
4	cap		murica								Steve	Rogers	captain.america@usa.com		2
5	jjones		killgrave							Jessica	Jones	jjones.pi@gmail.com		2
6	blackwidow	vision								Natasha	Romanoff	bwidow@avengers.com	1
7	john		$2a$06$6DUkBpqIy.CHP42m2DfAl.atcspPkSVRfFawwvPX5NZZGmmS/jvpu	John	Smith	jsmith@hotmail.com		2
8	jane		$2a$06$1RYeJilQUPbEuhKz0mb/2ORPM2H4jXo3NDqbH5/n4wWtmJjW1T0dq	Jane	Doe	jdoe@gmail.com			2