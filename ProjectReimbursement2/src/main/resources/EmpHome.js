// insert a new employee to database if they do not exist

function insert(){
	console.log("Hi");
	var func = new Packages.pov.EmployeePOV();
	var me = new Packages.connect.DAO();
	var bool = func.empLogin();
	
	if(bool == 0){
		
		//create a popup asking to create a new account
		document.write(me.addEmp());
	}
	
	console.log("Hi");
	return;
}