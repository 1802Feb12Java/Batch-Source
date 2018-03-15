function clickDisable(func, clickDisabled){
      	func();
		/*$button=$(event.target);
		console.log($button.data().events);
		button= event.target;
		button.setAttribute('disabled', true);
		func()
    	setTimeout(function(){
			button.removeAttribute('disabled');
		}, 3000);*/
	}
	var files;
	var filesBase64;
	var FR;
	window.onload = function(){
		/*
		$("#createMRImage").on("change",function(e) {
				files = e.target.files;
				FR= new FileReader();
				FR.readAsDataURL( files[0] );
				filesBase64 = FR.result;
				FR.addEventListener("load", function(e) {
					FR.readAsDataURL( files[0] );
					filesBase64 = FR.result;
				});
				//filesBase64 = FR.readAsDataURL( files[0] );
				console.log("set files");
				console.log(FR);
				console.log(files);
				console.log(filesBase64);
		});*/
		let updateClick = false;	
		document.getElementById("updateUserForm").addEventListener("submit", function(event){ event.preventDefault(); clickDisable(updateUser, updateClick);})
		let loginClick=false;
		document.getElementById("loginForm").addEventListener("submit", function(event){ event.preventDefault(); clickDisable(login, loginClick); });
		let updateUserClick = false;
		//document.getElementById("updateUser").addEventListener("click", function(){  clickDisable(updateUser, updateUserClick); });
		let logoutClick = false;
		document.getElementById("logout").addEventListener("click", function(){  clickDisable(logout, logoutClick); });
		let refreshEmployeeClick = false;
		document.getElementById("refreshEmployeeTable").addEventListener("click", function(){  clickDisable(refreshEmployeeTable, refreshEmployeeClick); });
		let refreshMRClick = false;
		document.getElementById("refreshManagerReimbursementTable").addEventListener("click", function(){  clickDisable(refreshManagerReimbursementTable, refreshMRClick); });
		let refreshERClick = false;
		document.getElementById("refreshEmployeeReimbursementTable").addEventListener("click", function(){  clickDisable(refreshEmployeeReimbursementTable, refreshERClick); });
		let createMRClick = false;
		document.getElementById("managerCreateReimbursmentForm").addEventListener('submit', function(event){ event.preventDefault(); clickDisable(createManagerReimbursement, createMRClick); });
		let createERClick = false;
		document.getElementById("employeeCreateReimbursementForm").addEventListener('submit', function(event){ event.preventDefault();  clickDisable(createEmployeeReimbursement, createERClick); });
		let createEClick = false;
		document.getElementById("createEmployeeForm").addEventListener('submit', function(event){ event.preventDefault(); clickDisable(createEmployee, createEClick); });
		//createReimbursment
		//$("#Submit-Button").on("click", createReimbursement)
	};
	function xhr(method, url, content, func){
		let xhr=new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200) {
				console.log(xhr.responseText);
				let response = JSON.parse(xhr.responseText);
				if(typeof response["problem"] !='undefined'){
					document.getElementById("problem").innerHTML="Error: " + response["problem"];
					console.log("problem " + JSON.stringify(response["problem"]));
					return ;
				}else if(response.length ==0){
					document.getElementById("problem").innerHTML="Error: incomplete response. Check console";
					console.log(JSON.stringify(response));
				}else{
					for(var i=0; i<response.length; i++){
						console.log("response " + response["employees"]);					
					}
					func(response);
				}
			}else{
				if(this.status > 400){
					//logout();
					//window.location.href="http://localhost:8080/ExpenseReimbursement/Index";
				}
			}
		}
		xhr.open(method, url);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(content));
	}
	function logout(){
		xhr("DELETE", "http://localhost:8080/ExpenseReimbursement/Index", null, function(response){
			window.location.href="http://localhost:8080/ExpenseReimbursement/Index";
		});
	}
	function login(e){
		let content = {};
		content.username = document.getElementById("loginUsername").value;
		content.password = document.getElementById("loginPassword").value;
		xhr("POST", "http://localhost:8080/ExpenseReimbursement/Index", content, function(response){
			document.getElementById("problem").innerHTML="Logged in";
			let userRole=response["userRole"];
			if (userRole=="Manager"){
				document.getElementById("login").style.display="none";
				setUser(response);
				document.getElementById("user").style.display="block";
				document.getElementById("manager").style.display="block";
				document.getElementById("employee").style.display="none";
				setManager(response);
			}else if(userRole=="Employee"){
				document.getElementById("login").style.display="none";
				setUser(response);
				document.getElementById("user").style.display="block";
				document.getElementById("employee").style.display="block";
				document.getElementById("manager").style.display="none";
				setEmployee(response);
			}else{
				document.getElementById("problem").innerHTML="Error: incomplete response. Check console";
				console.log(response);
			}
		});
	}
	function setUser(response){
		document.getElementById("userRole").innerHTML=response["userRole"] + "\'s Portal";
		document.getElementById("userFirstName").value=response["userFirstName"];
		document.getElementById("userLastName").value= response["userLastName"]
		document.getElementById("userEmail").value=response["email"];
		document.getElementById("username").value=response["username"];
		return;
	}
	
	function setManager(response){
		let employeeTable = document.getElementById("employeeTableBody");
		let reimbursementTable = document.getElementById("managerReimbursementTableBody");
		setOptions(response, document.getElementById("managerTypeOptions"), response.types, "type");
		setOptions(response, document.getElementById("createEmployeeRoleOptions"), response.roles, "role");
		//getAllReimbursements(reimbursementTable);
		//getAllEmployees(employeeTable);
		return;
	}
	function setEmployee(response){
		let reimbursementTable = document.getElementById("employeeReimbursementTableBody");
		setOptions(response, document.getElementById("employeeTypeOptions"), response.types, "type");
		//getEmployeeReimbursements(reimbursementTable);
		return;
	}
	function setOptions(response, select, options, key){
		console.log(options.children);
		if(select.children.length==1){
			for(var i=0; i< options.length; i++){
				let option=document.createElement("option");
				let optionText = document.createTextNode(options[i][key]);
				option.value=options[i].id;
				option.appendChild(optionText);
				select.appendChild(option);
			}
		}
	}
	function createEmployee(){
		let form = document.getElementById("createEmployee");
		let content= {"create": "employee"};
		let rows = form.children;	
		content.firstName=rows[0].children.firstName.children.firstName.value;
		content.lastName=rows[0].children.lastName.children.lastName.value;
		content.username=rows[0].children.username.children.username.value
		content.email=rows[0].children.email.children.email.value;
		content.password=rows[1].children.password.children.password.value;
		content.roleId=rows[0].children.role.children.role[rows[0].children.role.children.role.selectedIndex].value;
		content.role=rows[0].children.role.children.role[rows[0].children.role.children.role.selectedIndex].innerHTML;
		console.log("new employee");
		console.log(JSON.stringify(content));
		
		xhr("POST", "http://localhost:8080/ExpenseReimbursement/Manager?create=employee", content, function(response){
			if (response["create"]!=undefined){
				alert(JSON.stringify(response["create"]));
			}else{
				alert(JSON.stringify(response["problem"]));
			}
			
		});
		refreshEmployeeTable();
	}
	function createManagerReimbursement(){
		let url = "http://localhost:8080/ExpenseReimbursement/Manager?create=reimbursement";
		let row = document.getElementById("createNewReimbursementManager");
		let content= {"create": "reimbursement"};
		let cells = row.children;
		content.amount = +cells.amount.children.amount.value;
		content.description = cells.description.children.description.value;
		let receiptInput = cells.receipt.children.receipt;
		let receipt;
		content.type = +cells.type.children.type[cells.type.children.type.selectedIndex].value;
		if("files" in receiptInput){
			let files=receiptInput.files;
			if (files.length ==1){
				let fileReader = new FileReader();
				fileReader.onload=function(event){
					console.log("filereader onload");
					receipt = event.target.result;
					content.receipt=receipt;
					console.log(content);
					createReimbursement(content, "http://localhost:8080/ExpenseReimbursement/Manager?create=reimbursement");
					refreshManagerReimbursementTable();
				}
				fileReader.readAsDataURL(files[0]);
			}else if (files.length >1){
				alert("please select one file only");
				return;
			}
		}		
		//createReimbursement(content, "http://localhost:8080/ExpenseReimbursement/Manager?create=reimbursement");
		
		//console.log("end of create reimbursement");
	}
	function createEmployeeReimbursement(){
		let url = "http://localhost:8080/ExpenseReimbursement/Employee?create=reimbursement";
		let row = document.getElementById("createNewReimbursementEmployee");
		let content= {"create": "reimbursement"};
		let cells = row.children;
		content.amount = +cells.amount.children.amount.value;
		content.description = cells.description.children.description.value;
		let receiptInput = cells.receipt.children.receipt;
		let receipt;
		content.type = +cells.type.children.type[cells.type.children.type.selectedIndex].value;
		if("files" in receiptInput){
			let files=receiptInput.files;
			if (files.length ==1){
				let fileReader = new FileReader();
				fileReader.onload=function(event){
					console.log("filereader onload");
					receipt = event.target.result;
					content.receipt=receipt;
					console.log(content);
					createReimbursement(content, "http://localhost:8080/ExpenseReimbursement/Employee?create=reimbursement");
					refreshManagerReimbursementTable();
				}
				fileReader.readAsDataURL(files[0]);
			}else if (files.length >1){
				alert("please select one file only");
				return;
			}
		x
	}

	function createReimbursement(reimbursement, url){
		let reimbursements = xhr("POST", url, reimbursement, function(response){
			alert(response["create"]);
		});				
		
	}
	function refreshManagerReimbursementTable(){
		let table = document.getElementById("managerReimbursementTableBody");
		table.innerHTML ="";
		getAllReimbursements(table);
	}
	function refreshEmployeeReimbursementTable(){
		let table = document.getElementById("employeeReimbursementTableBody");
		table.innerHTML ="";
		getEmployeeReimbursements(table);
	}
	function clearTable(table){
		//let createRow = table.children[0];
		table.innerHTML="";
		//table.appendChild(createRow);
	}
	let statuses = {};
	function getAllReimbursements(reimbursementTable){
		let content= null;
		let reimbursements = xhr("GET", "http://localhost:8080/ExpenseReimbursement/Manager?get=reimbursements", content, function(response){
			let count = response["count"];
			if(count < 1){
				return;
			}
			for(var i = 0; i < response.statuses.length; i++){
				let id= response.statuses[i].id + "";
				let status=response.statuses[i].status;
				statuses[status]=id;
			}
			reimbursementTable.innerHTML="";
			let reimbursements= response["reimbursements"];
			for(var i=0; i< reimbursements.length; i++){
				let row = document.createElement("tr");
				reimbursementTable.appendChild(row);
				row.className += " row";
				let cell1=document.createElement("td");
				cell1.attributes.name="id";
				let cell11=document.createTextNode(reimbursements[i]["reimbursementId"]);
				cell1.className += " col-sm-1";
				cell1.appendChild(cell11);
				row.appendChild(cell1);
				let cell2=document.createElement("td"); //checkbox
				cell2.className += " col-sm-1";
				//row.appendChild(cell2);
				let cell3=document.createElement("td"); 
				cell3.className += " col-sm-1";
				let status = reimbursements[i]["status"];
				let cell31;
				console.log(status=="Pending");
				if(status!="Pending"){
					cell31=document.createTextNode(reimbursements[i]["status"]);
				}else{
					cell31=document.createElement("div");
					let approve=document.createElement("button");
					approve.className +=" col-sm-6 btn btn-success";
					let approveCheckmark = document.createElement("span");
					approveCheckmark.className += "glyphicon glyphicon-ok";
					approve.appendChild(approveCheckmark);
					approve.addEventListener("click", function(){ clickDisable( function(){approveReimbursement(row)}); }); 
					let deny=document.createElement("button");
					deny.addEventListener("click", function(){ clickDisable( function(){denyReimbursement(row)}); }); 
					deny.className +=" col-sm-6 btn btn-danger";
					let denyXmark = document.createElement("span");
					denyXmark.className+=" glyphicon glyphicon-remove";
					deny.appendChild(denyXmark);
					cell31.append(approve);
					cell31.append(deny);
				}
				cell3.appendChild(cell31);
				row.appendChild(cell3);
				let cell4=document.createElement("td");
				cell4.className += " col-sm-1";
				let cell41=document.createTextNode(reimbursements[i]["type"]);
				cell4.appendChild(cell41);
				row.appendChild(cell4);
				let cell5=document.createElement("td");
				cell5.className += " col-sm-1";
				let cell51=document.createTextNode("$" + reimbursements[i]["amount"].toFixed(2));
				cell5.appendChild(cell51);
				row.appendChild(cell5);
				let cell6=document.createElement("td");
				cell6.className += " col-sm-2";
				let cell61=document.createTextNode(reimbursements[i]["description"]);
				cell6.appendChild(cell61);
				row.appendChild(cell6);
				let cell7=document.createElement("td");
				cell7.className += " col-sm-1";
				let cell71=document.createTextNode(reimbursements[i]["authorUsername"]);
				cell7.appendChild(cell71);
				row.appendChild(cell7);
				let cell8=document.createElement("td");
				cell8.className += " col-sm-1";
				let cell81=document.createTextNode(reimbursements[i]["authorFirstName"]);
				cell8.appendChild(cell81);
				row.appendChild(cell8);
				let cell9=document.createElement("td");
				cell9.className += " col-sm-1";
				let cell91=document.createTextNode(reimbursements[i]["authorLastName"]);
				cell9.appendChild(cell91);
				row.appendChild(cell9);
				let cell10=document.createElement("td");
				cell10.className += " col-sm-3";
				//let cell101=document.createTextNode(reimbursements[i]["receipt"]);
				let cell101=document.createElement("img");
				if(reimbursements[i]["receiptBase64"]!=""){
					cell101.src="data:image/png;base64,"+ reimbursements[i]["receiptBase64"];
				cell10.appendChild(cell101);
				}
				cell10.appendChild(cell101);
				row.appendChild(cell10);
			}
		});
	}
	function refreshEmployeeTable(){
		let table = document.getElementById("employeeTableBody");
		table.innerHTML ="";
		getAllEmployees(table);
	}
	function getAllEmployees(employeeTable){
		let oldEmployees=employeeTable.children
		for(var i=1; i<oldEmployees.length; i++){
			employeeTable.removeChild(oldEmployees[i]);
		}
		let content= null;
		let employees = xhr("GET", "http://localhost:8080/ExpenseReimbursement/Manager?get=employees", content, function(response){
			let count = response["count"];
			if(count < 1){
				return;
			}
			employeeTable.innerHTML="";
			let employees = response["employees"];
			for(var i=0; i< employees.length; i++){
				let row = document.createElement("tr");
				row.className = "row";
				let cell1= document.createElement("td");
				cell1.className += " col-sm-1";
				let cell1text=document.createTextNode(employees[i]["userId"]);
				cell1.appendChild(cell1text);
				row.appendChild(cell1);
				let cell2= document.createElement("td");
				cell2.className += " col-sm-2";
				let cell2text=document.createTextNode(employees[i]["username"]);
				cell2.appendChild(cell2text);
				row.appendChild(cell2);
				let cell3= document.createElement("td");
				cell3.className += " col-sm-2";
				let cell3text=document.createTextNode(employees[i]["userRole"]);
				cell3.appendChild(cell3text);
				row.appendChild(cell3);
				let cell4= document.createElement("td");
				cell4.className += " col-sm-2";
				let cell4text=document.createTextNode(employees[i]["firstName"]);
				cell4.appendChild(cell4text);
				row.appendChild(cell4);
				let cell5= document.createElement("td");
				cell5.className += " col-sm-2";
				let cell5text=document.createTextNode(employees[i]["lastName"]);
				cell5.appendChild(cell5text);
				row.appendChild(cell5);
				let cell6= document.createElement("td");
				cell6.className += " col-sm-3";
				let cell6text=document.createTextNode(employees[i]["email"]);
				cell6.appendChild(cell6text);
				row.appendChild(cell6);				
				employeeTable.appendChild(row);
			}
		});
		return employees;
	}
	function getEmployeeReimbursements(reimbursementTable){
		let content= {"get": "reimbursement"};
		let reimbursements = xhr("GET", "http://localhost:8080/ExpenseReimbursement/Employee?get=reimbursements", content, function(response){
			let count = response["count"];
			reimbursementTable.innerHTML="";
			let reimbursements= response["reimbursements"];
			for(var i=0; i< reimbursements.length; i++){
				let row = document.createElement("tr");
				row.className += " row";
				let cell1=document.createElement("td");
				cell1.className += " col-sm-2";
				let cell11=document.createTextNode(reimbursements[i]["reimbursementId"]);
				cell1.appendChild(cell11);
				row.appendChild(cell1);
				let cell2=document.createElement("td"); 
				let cell21=document.createTextNode(reimbursements[i]["status"]);
				cell2.appendChild(cell21);
				row.appendChild(cell2);
				let cell3=document.createElement("td");
				let cell31=document.createTextNode(reimbursements[i]["type"]);
				cell3.appendChild(cell31);
				row.appendChild(cell3);
				let cell4=document.createElement("td");
				let cell41=document.createTextNode("$"+reimbursements[i]["amount"].toFixed(2));
				cell4.appendChild(cell41);
				row.appendChild(cell4);
				let cell5=document.createElement("td");
				let cell51=document.createTextNode(reimbursements[i]["description"]);
				cell5.appendChild(cell51);
				row.appendChild(cell5);
				let cell6=document.createElement("td");
				//let cell61=document.createTextNode(reimbursements[i]["receipt"]);
				let cell61=document.createElement("img");
				if(reimbursements[i]["receiptBase64"]!=""){
					cell61.src="data:image/png;base64,"+ reimbursements[i]["receiptBase64"];
					cell6.appendChild(cell61);
				}
				cell6.appendChild(cell61);
				row.appendChild(cell6);
				reimbursementTable.appendChild(row);
			}
		});
		
	}
	function updateUser(userId){
		let content= {"update": "user", "userId": userId};
		content.firstName=document.getElementById("userFirstName").value;
		content.lastName=document.getElementById("userLastName").value;
		content.username=document.getElementById("username").value;
		content.email=document.getElementById("userEmail").value;
		content.password=document.getElementById("userPassword").value;
		let reimbursements = xhr("PUT", "http://localhost:8080/ExpenseReimbursement/Index", content, function(response){
			console.log(JSON.stringify());
			alert(response["update"]);
		});
	}
	function approveReimbursement(reimbursementRow){
		let reimbursement = {};
		let id = +reimbursementRow.children[0].textContent;
		reimbursement.id=id;
		console.log("statuses");
		console.log(statuses);
		console.log("statuses");
		reimbursement.statusId=+statuses.Approved;
		updateReimbursement(reimbursement);
	}
	function denyReimbursement(reimbursementRow){
		let reimbursement = {};
		let id = +reimbursementRow.children[0].textContent;
		reimbursement.id=id;
		reimbursement.statusId=+statuses.Denied;
		updateReimbursement(reimbursement);
	}
	function updateReimbursement(reimbursement){
		let content= reimbursement;
		content.update="reimbursement";
		console.log(JSON.stringify(content));
		xhr("PUT", "http://localhost:8080/ExpenseReimbursement/Manager?update=reimbursement", content, function(response){
			alert(response["update"]);
			refreshManagerReimbursementTable();
		});
	}
	}	