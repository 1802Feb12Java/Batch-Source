function sendAjaxGet(url, func) {
	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("GET", url, true);
	xhr.send();
};

function sendAjaxPost(url, func) {
	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("POST", url, true);
	xhr.send();
};

function viewEmp(xhr) {
	var eachEmp = xhr.responseText.split("Employee");
	for (x = 1; x < eachEmp.length ; x++){
		var eachVal = eachEmp[x].split(":");
		var newrow = document.createElement("tr");
		newrow.setAttribute("id", "Employee " + x);
		document.getElementById("empList").appendChild(newrow);
		var col1 = document.createElement("td");
		var col2 = document.createElement("td");
		var col3 = document.createElement("td");
		var col4 = document.createElement("td");
		var col5 = document.createElement("td");
		var col6 = document.createElement("td");
		col1.textContent = eachVal[0];
		col2.textContent = eachVal[1];
		col3.textContent = eachVal[2];
		col4.textContent = eachVal[3];
		col5.textContent = eachVal[4];
		col6.textContent = eachVal[5];
		document.getElementById("Employee " + x).appendChild(col1);
		document.getElementById("Employee " + x).appendChild(col2);
		document.getElementById("Employee " + x).appendChild(col3);
		document.getElementById("Employee " + x).appendChild(col4);
		document.getElementById("Employee " + x).appendChild(col5);
		document.getElementById("Employee " + x).appendChild(col6);
	}
	
}

function pendingReq(xhr){
	var eachReq = xhr.responseText.split("Reimbursement");
	for (x = 1; x < eachReq.length ; x++){
		var eachVal = eachReq[x].split("|");
		var newrow = document.createElement("tr");
		newrow.setAttribute("id", "Pending " + x);
		document.getElementById("PendingList").appendChild(newrow);
		var col1 = document.createElement("td");
		var col2 = document.createElement("td");
		var col3 = document.createElement("td");
		var col4 = document.createElement("td");
		var col5 = document.createElement("td");
		var col6 = document.createElement("td");
		var col7 = document.createElement("td");
		var col8 = document.createElement("td");
		var col9 = document.createElement("td");
		var col10 = document.createElement("td");
		col1.textContent = eachVal[0];
		col2.textContent = eachVal[1];
		col3.textContent = eachVal[2];
		if (eachVal[3]== "null") {
			col4.textContent = "No receipt";
		} else {
		var img = document.createElement("img");
		img.src = "data:img/png;base64,"+ eachVal[3];
		img.width = "200";
		img.height = "300";
		} 
		col5.textContent = eachVal[4];
		col6.textContent = eachVal[5];
		col7.textContent = eachVal[6];
		col8.textContent = eachVal[7];
		col9.textContent = eachVal[8];
		col10.textContent = eachVal[9];
		document.getElementById("Pending " + x).appendChild(col1);
		document.getElementById("Pending " + x).appendChild(col2);
		document.getElementById("Pending " + x).appendChild(col3);
		if (eachVal[3] == "null"){
			document.getElementById("Pending " + x).appendChild(col4);			
		} else {
		document.getElementById("Pending " + x).appendChild(img);
		}
		document.getElementById("Pending " + x).appendChild(col5);
		document.getElementById("Pending " + x).appendChild(col6);
		document.getElementById("Pending " + x).appendChild(col7);
		document.getElementById("Pending " + x).appendChild(col8);
		document.getElementById("Pending " + x).appendChild(col9);
		document.getElementById("Pending " + x).appendChild(col10);
	}
}

function resolvedReq(xhr){
	var eachReq = xhr.responseText.split("Reimbursement");
	for (x = 1; x < eachReq.length ; x++){
		var eachVal = eachReq[x].split("|");
		var newrow = document.createElement("tr");
		newrow.setAttribute("id", "Resolved " + x);
		document.getElementById("ResolvedList").appendChild(newrow);
		var col1 = document.createElement("td");
		var col2 = document.createElement("td");
		var col3 = document.createElement("td");
		var col4 = document.createElement("td");
		var col5 = document.createElement("td");
		var col6 = document.createElement("td");
		var col7 = document.createElement("td");
		var col8 = document.createElement("td");
		var col9 = document.createElement("td");
		var col10 = document.createElement("td");
		col1.textContent = eachVal[0];
		col2.textContent = eachVal[1];
		col3.textContent = eachVal[2];
		if (eachVal[3]== "null") {
			col4.textContent = "No receipt";
		} else {
		var img = document.createElement("img");
		img.src = "data:img/png;base64,"+ eachVal[3];
		img.width = "200";
		img.height = "300";
		} 
		col5.textContent = eachVal[4];
		col6.textContent = eachVal[5];
		col7.textContent = eachVal[6];
		col8.textContent = eachVal[7];
		col9.textContent = eachVal[8];
		col10.textContent = eachVal[9];
		document.getElementById("Resolved " + x).appendChild(col1);
		document.getElementById("Resolved " + x).appendChild(col2);
		document.getElementById("Resolved " + x).appendChild(col3);
		if (eachVal[3] == "null"){
			document.getElementById("Resolved " + x).appendChild(col4);			
		} else {
		document.getElementById("Resolved " + x).appendChild(img);
		}
		document.getElementById("Resolved " + x).appendChild(col5);
		document.getElementById("Resolved " + x).appendChild(col6);
		document.getElementById("Resolved " + x).appendChild(col7);
		document.getElementById("Resolved " + x).appendChild(col8);
		document.getElementById("Resolved " + x).appendChild(col9);
		document.getElementById("Resolved " + x).appendChild(col10);
	}
}

function viewInfo (xhr){
		var Emp = xhr.responseText.split("Employee");
		var eachVal = Emp[1].split(":");
//		var newrow = document.createElement("tr");
//		newrow.setAttribute("id", "Me " + x);
//		document.getElementById("myList").appendChild(newrow);

		document.getElementById("Me1").textContent = eachVal[0];
		document.getElementById("Me2").textContent = eachVal[1];
		document.getElementById("Me3").textContent = eachVal[2];
		document.getElementById("Me4").textContent = eachVal[3];
		document.getElementById("Me5").textContent = eachVal[4];
		document.getElementById("Me6").textContent = eachVal[5];
		document.getElementById("usr1").value = eachVal[1];
		document.getElementById("psw1").value = eachVal[2];
		document.getElementById("fnm1").value = eachVal[3];
		document.getElementById("lnm1").value = eachVal[4];
		document.getElementById("eml1").value = eachVal[5];
}	
	
window.onload = function() {
	sendAjaxGet("http://localhost:8080/Project1/ViewEmp", viewEmp);
	sendAjaxGet("http://localhost:8080/Project1/Pending", pendingReq);
	sendAjaxGet("http://localhost:8080/Project1/Resolved", resolvedReq);
	sendAjaxGet("http://localhost:8080/Project1/ViewInfo", viewInfo);
}

function allEmpReq(){
	sendAjaxGet("http://localhost:8080/Project1/NewEmpServlet?filter=", empReq);
}

function empReq(xhr){
	var eachReq = xhr.responseText.split("Reimbursement");
	var user = document.getElementById("workId").value;
	var myNode = document.getElementById("EmpReqList");
	while (myNode.firstChild) {
	    myNode.removeChild(myNode.firstChild);
	}
	var newrow = document.createElement("tr");
		newrow.setAttribute("id", "Req " + 0);
		document.getElementById("EmpReqList").appendChild(newrow);
		var col1 = document.createElement("td");
		var col2 = document.createElement("td");
		var col3 = document.createElement("td");
		var col4 = document.createElement("td");
		var col5 = document.createElement("td");
		var col6 = document.createElement("td");
		var col7 = document.createElement("td");
		var col8 = document.createElement("td");
		var col9 = document.createElement("td");
		var col10 = document.createElement("td");
		col1.textContent = "ID";
		col2.textContent = "Amount";
		col3.textContent = "Description";
		col4.textContent = "Receipt";
		col5.textContent = "Submitted Date";
		col6.textContent = "Resolved Date";
		col7.textContent = "Author";
		col8.textContent = "Resolver";
		col9.textContent = "Type";
		col10.textContent = "Status";
		document.getElementById("Req " + 0).appendChild(col1);
		document.getElementById("Req " + 0).appendChild(col2);
		document.getElementById("Req " + 0).appendChild(col3);
		document.getElementById("Req " + 0).appendChild(col4);
		document.getElementById("Req " + 0).appendChild(col5);
		document.getElementById("Req " + 0).appendChild(col6);
		document.getElementById("Req " + 0).appendChild(col7);
		document.getElementById("Req " + 0).appendChild(col8);
		document.getElementById("Req " + 0).appendChild(col9);
		document.getElementById("Req " + 0).appendChild(col10);
	for (x = 1; x < eachReq.length ; x++){
		var eachVal = eachReq[x].split("|");
		if (eachVal[6] == user){
		var newrow = document.createElement("tr");
		newrow.setAttribute("id", "Req " + x);
		document.getElementById("EmpReqList").appendChild(newrow);
		var col1 = document.createElement("td");
		var col2 = document.createElement("td");
		var col3 = document.createElement("td");
		var col4 = document.createElement("td");
		var col5 = document.createElement("td");
		var col6 = document.createElement("td");
		var col7 = document.createElement("td");
		var col8 = document.createElement("td");
		var col9 = document.createElement("td");
		var col10 = document.createElement("td");
		
		col1.textContent = eachVal[0];
		col2.textContent = eachVal[1];
		col3.textContent = eachVal[2];
		if (eachVal[3]== "null") {
			col4.textContent = "No receipt";
		} else {
		var img = document.createElement("img");
		img.src = "data:img/png;base64,"+ eachVal[3];
		img.width = "200";
		img.height = "300";
		}
		col5.textContent = eachVal[4];
		col6.textContent = eachVal[5];
		col7.textContent = eachVal[6];
		col8.textContent = eachVal[7];
		col9.textContent = eachVal[8];
		col10.textContent = eachVal[9];
		document.getElementById("Req " + x).appendChild(col1);
		document.getElementById("Req " + x).appendChild(col2);
		document.getElementById("Req " + x).appendChild(col3);
		if (eachVal[3] == "null"){
			document.getElementById("Req " + x).appendChild(col4);			
		} else {
		document.getElementById("Req " + x).appendChild(img);
		}
		document.getElementById("Req " + x).appendChild(col5);
		document.getElementById("Req " + x).appendChild(col6);
		document.getElementById("Req " + x).appendChild(col7);
		document.getElementById("Req " + x).appendChild(col8);
		document.getElementById("Req " + x).appendChild(col9);
		document.getElementById("Req " + x).appendChild(col10);
		}
	}
}