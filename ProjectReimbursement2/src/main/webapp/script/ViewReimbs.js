window.onload=function(){
	
	sendRequest();
	sendRequestNow();
}

function sendRequest()
{
	
	console.log("starting AJAX request to fill in Riem table");
	var xhr = new XMLHttpRequest();
	//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var reqFound = JSON.parse(xhr.responseText);
			console.log("Request " + reqFound);
			FillTable(reqFound);
		}
	};
	var url = "/ers/ViewRiem";
	console.log("URL: " + url);
	xhr.open("GET", url, true);

	xhr.send();
	console.log("sent AJAX request");
}

function sendRequestNow(){
	
	console.log("starting AJAX request to fill in Riem table");
	var xhr = new XMLHttpRequest();
	//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var reqFound = JSON.parse(xhr.responseText);
			console.log("Request " + reqFound);
			FillDetailTable(reqFound);
		}
	};
	var url = "/ers/UserDetails";
	console.log("URL: " + url);
	xhr.open("GET", url, true);

	xhr.send();
	console.log("sent AJAX request");
}

function FillTable(JSONarray){
	
	for(var i = 0; i < JSONarray.length; i++){
		
		var reimb = JSONarray[i];
		console.log("we are in the script file");
		
		// making a row
		
		var row = document.getElementById('employee-table').insertRow(0);
		
		//making col
		
		var call = row.insertCell(0);
		call.innerHTML = reimb["request_id"];	
		call = row.insertCell(1);
		call.innerHTML = reimb["request_type"];
		call = row.insertCell(2);
		call.innerHTML = reimb["description"];
		call = row.insertCell(3);
		call.innerHTML = reimb["amount"];
		call = row.insertCell(4);
		call.innerHTML = reimb["status"];
		call = row.insertCell(5);
		call.innerHTML = reimb["date_submitted"];
		call = row.insertCell(6);
		call.innerHTML = reimb["date_resolved"];
		call = row.insertCell(7);
		call.innerHTML = reimb["request_resolver"];	
	}
}

function FillDetailTable(JSONarray){
	
	var reimb = JSONarray[i];
	console.log("we are in the script file");
	
	// making a row
	
	var row = document.getElementById('user-details').insertRow(0);
	
	//making col
	
	var call = row.insertCell(0);
	call.innerHTML = reimb["user_id"];	
	call = row.insertCell(1);
	call.innerHTML = reimb["username"];
	call = row.insertCell(2);
	call.innerHTML = reimb["password"];
	call = row.insertCell(3);
	call.innerHTML = reimb["fname"];
	call = row.insertCell(4);
	call.innerHTML = reimb["lname"];
	call = row.insertCell(5);
	call.innerHTML = reimb["email"];
}