function sendAjaxGet(url, func) {
    var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("GET", url, true);
	xhr.send();
};

function populateUser(xhr) {

    var res = JSON.parse(xhr.responseText);
	document.getElementById("c-username").textContent = res.username;

	// check if on dahboard page
	var location = window.location.href;
	if(location.indexOf("http://localhost:8080/ProjectOne/employee-dashboard") > -1) {
		document.getElementById("userIdInfo").textContent = res.id;
		document.getElementById("usernameInfo").textContent = res.username;
		document.getElementById("firstNameInfo").textContent = res.firstName;
		document.getElementById("lastNameInfo").textContent = res.lastName;
		document.getElementById("emailInfo").textContent = res.email;

		// edit form values
		document.getElementById("updateUsername").value = res.username;
		document.getElementById("updateEmail").value = res.email;
	}

}

// function loadReimbursements(xhr) {
    
// 	var reimbursements = JSON.parse(xhr.responseText);
//     var table = document.getElementById("reimbursements");

//     for(var i=0; i<reimbursements.length; i++) {
//         var newrow = document.createElement("tr");
//         newrow.setAttribute("id", "reimbursement-" + i);
//         table.appendChild(newrow);
//         var row = document.getElementById("reimbursement-" + i);
//         row.innerHTML += "<td>"+reimbursements[i].id+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].amount+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].description+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].receipt+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].submitted+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].resolved+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].employee+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].type+"</td>";
//         row.innerHTML += "<td>"+reimbursements[i].status+"</td>";
//         row.innerHTML += "<td>" +
//         					"<form action='reimbursements' method='POST'>" +
//         					"<input type='hidden' name='status' value='2'>" +
//         					"<input type='hidden' name='reimbursementId' value=" + reimbursements[i].id + ">" +
//         					"<button style='width:100%; padding:2px' class='btn btn-success btn-sm' type='submit'>Approve</button>" +
//         					"</form>" +
//         					"<form action='reimbursements' method='POST'>" +
//         					"<input type='hidden' name='status' value='3'>" +
//         					"<input type='hidden' name='reimbursementId' value=" + reimbursements[i].id + ">" +
//         					"<button style='width:100%; margin-top:4px; padding:2px' class='btn btn-danger btn-sm' type='submit'>Deny</button>" +
//         					"</form>" +
//         					"</td>";
// 	}
	
// 	// load data table
// 	$(document).ready(function() {
// 		$('#ajaxTable').DataTable();
// 	})
// }



// function loadUsers(xhr) {
    
// 	var users = JSON.parse(xhr.responseText);
//     var table = document.getElementById("users");

//     for(var i=0; i<users.length; i++) {
//         var newrow = document.createElement("tr");
//         newrow.setAttribute("id", "user-" + i);
//         table.appendChild(newrow);
//         var row = document.getElementById("user-" + i);
//         row.innerHTML += "<td>"+users[i].id+"</td>";
//         row.innerHTML += "<td>$"+users[i].username+"</td>";
//         row.innerHTML += "<td>"+users[i].firstName+"</td>";
//         row.innerHTML += "<td>"+users[i].lastName+"</td>";
//         row.innerHTML += "<td>"+users[i].email+"</td>";
//         row.innerHTML += "<td>"+users[i].role+"</td>";
// 	}
	
// 	// load data table
// 	$(document).ready(function() {
// 		$('#ajaxTable').DataTable();
// 	})
// }




window.onload = function() {
	
	// Get logged in user
	sendAjaxGet("http://localhost:8080/ProjectOne/employee-session", populateUser);
	
	// // Get current URL
	// var location = window.location.href;
	
	// // run ajax request if on reimbursements page
	// if (location.indexOf("http://localhost:8080/ProjectOne/reimbursements") > -1) {
	// 	sendAjaxGet("http://localhost:8080/ProjectOne/get-reimbursements", loadReimbursements);
	// }
	
	// // run ajax request if on users page
	// if (location.indexOf("http://localhost:8080/ProjectOne/users") > -1) {
	// 	sendAjaxGet("http://localhost:8080/ProjectOne/get-users", loadUsers);
	// }
	
}