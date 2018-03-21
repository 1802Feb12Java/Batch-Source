window.onload = function(){
	var reimbtn = document.getElementById("reims");
	reimbtn.onclick=function(){
		window.location.href = "http://localhost:8080/Reimbursements/ReimburseTable.html";
	}
	var empsbtn = document.getElementById("emps");
	empsbtn.onclick=function(){
		window.location.href = "http://localhost:8080/Reimbursements/EmployeeTable.html";
	}	
	var logoutbtn = document.getElementById("logouts");
	logoutbtn.onclick=function(){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status ==200)
				{
				}
		}
		xhr.open("GET", "http://localhost:8080/Reimbursements/logout", true);
		xhr.send();
	}
	
}



