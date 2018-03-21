

function getReims(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status ==200)
			{
			var data = JSON.parse(xhr.responseText);
			fillTable(data);
			}
	}
	xhr.open("GET", "http://localhost:8080/Reimbursements/ReimburseTable", true);
	xhr.send();
}

function fillTable(data){
	var table = document.getElementById("reimTable");
	console.log(data);
	for(let i =0; i< data.length; i++){
		var row = table.insertRow(i+1);
			var cell10 = row.insertCell(0);
			if(data[i]["RT_STATUS"]==1){//deny
				cell10.innerHTML='<form role="form" action="/Reimbursements/DenyServlet" method="post"> <fieldset> <div class="form-group"> <input class="form-control" type="hidden" name="remid" autofocus value=' + data[i] ["R_ID"] + '> </div> <input type="submit" value="Deny" class="btn btn-sm btn-danger btn-block"> </fieldset> </form>'
			}
			var cell11 = row.insertCell(0);
			if(data[i]["RT_STATUS"]==1){//approve
				cell11.innerHTML='<form role="form" action="/Reimbursements/ApproveServlet" method="post"> <fieldset> <div class="form-group"> <input class="form-control" type="hidden" name="remid" autofocus value=' + data[i] ["R_ID"] + '> </div> <input type="submit" value="Approve" class="btn btn-sm btn-success btn-block"> </fieldset> </form>'
			}
		
			var cell1 = row.insertCell(0);//this will be the rightmost. 
			cell1.innerHTML = data[i] ["RT_STATUS"];
			var cell2 = row.insertCell(0); 
			cell2.innerHTML = data[i] ["RT_TYPE"];
			var cell3 = row.insertCell(0); 
			cell3.innerHTML = data[i] ["U_ID_RESOLVER"];
			var cell4 = row.insertCell(0); 
			cell4.innerHTML = data[i] ["U_ID_AUTHOR"];
			var cell5 = row.insertCell(0);
			cell5.innerHTML = data[i] ["R_RESOLVED"];
			var cell6 = row.insertCell(0); 
			cell6.innerHTML = data[i] ["R_SUBMITTED"];
			var cell7 = row.insertCell(0);
			cell7.innerHTML = "null"
			//data[i] ["R_RECEIPT"];
			var cell8 = row.insertCell(0);
			cell8.innerHTML = data[i] ["R_DESCRIPTION"];
			var cell8 = row.insertCell(0); 
			cell8.innerHTML = data[i] ["R_AMOUNT"];
			var cell9 = row.insertCell(0);//this will be the leftmost. 
			cell9.innerHTML= data[i] ["R_ID"];
			
	}
}



window.onload = function(){
	getReims();
}