function getUser(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status ==200)
			{
			var data = JSON.parse(xhr.responseText);
			fillTable(data);
			}
	}
	xhr.open("GET", "/Reimbursements/Single", true);
	xhr.send();
}

function fillTable(data){
	var table = document.getElementById("empTable");
	console.log(data);
	
		var row = table.insertRow(1);
			var cell1 = row.insertCell(0);//this will be the rightmost. 
			cell1.innerHTML = data ["UR_ID"];
			var cell2 = row.insertCell(0); 
			cell2.innerHTML = data ["U_EMAIL"];
			var cell3 = row.insertCell(0); 
			cell3.innerHTML = data ["U_LASTNAME"];
			var cell4 = row.insertCell(0); 
			cell4.innerHTML = data ["U_FIRSTNAME"];
			var cell7 = row.insertCell(0);
			cell7.innerHTML = "***"
			var cell5 = row.insertCell(0); 
			cell5.innerHTML = data ["U_USERNAME"];
			var cell6 = row.insertCell(0);//this will be the leftmost. 
			cell6.innerHTML = data ["U_ID"];
			
}

function changePass() {
	
}



window.onload = function(){
	var reimbtn = document.getElementById("reims");
	reimbtn.onclick=function(){
		window.location.href = "http://localhost:8080/Reimbursements/EmpView.html";
	}
	var empsbtn = document.getElementById("newReim");
	empsbtn.onclick=function(){
		window.location.href = "http://localhost:8080/Reimbursements/Request";
	}	
	var logoutbtn = document.getElementById("logouts");
	logoutbtn.onclick=function(){
		window.location.href = "http://localhost:8080/Reimbursements/logout";
	}
	var passbtn = document.getElementById("newPass");
	
	getUser();
	passbtn.onclick=changePass();
	
}