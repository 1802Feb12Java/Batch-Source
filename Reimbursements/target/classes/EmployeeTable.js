function getUsers(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status ==200)
			{
			var data = JSON.parse(xhr.responseText);
			fillTable(data);
			}
	}
	xhr.open("GET", "/Reimbursements/EmployeeTable", true);
	xhr.send();
}

function fillTable(data){
	var table = document.getElementById("empTable");
	console.log(data);
	for(let i =0; i< data.length; i++){
		var row = table.insertRow(i+1);
			var cell1 = row.insertCell(0);//this will be the rightmost. 
			cell1.innerHTML = data[i] ["UR_ID"];
			var cell2 = row.insertCell(0); 
			cell2.innerHTML = data[i] ["U_EMAIL"];
			var cell3 = row.insertCell(0); 
			cell3.innerHTML = data[i] ["U_LASTNAME"];
			var cell4 = row.insertCell(0); 
			cell4.innerHTML = data[i] ["U_FRISTNAME"];
			var cell7 = row.insertCell(0);
			cell7.innerHTML = "***"
			var cell5 = row.insertCell(0); 
			cell5.innerHTML = data[i] ["U_USERNAME"];
			var cell6 = row.insertCell(0);//this will be the leftmost. 
			cell6.innerHTML = data[i] ["U_ID"];
			
	}
}



window.onload = function(){
	getUsers();
	
}
