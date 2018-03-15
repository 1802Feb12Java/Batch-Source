function sendAjaxGet(url, func) {
	var xhr = new XMLHttpRequest() 
					|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("GET", url, true);
	xhr.send();
};

function populateUser(xhr) {
	var res = JSON.parse(xhr.responseText);
	if (res.username != "null") { //error checking could be improved 
		document.getElementById("c-username").textContent = res.username;

		if(window.location.href.indexOf("ProjectOne/admin-dashboard") > -1) {
			document.getElementById("reimbursementsCount").textContent = res.reimbursementsCount;
			document.getElementById("usersCount").textContent = res.usersCount;
		}

	} else {
		window.location = "http://localhost:8080/ProjectOne/login";
	}
}

function loadReimbursements(xhr) {
    
	var reimbursements = JSON.parse(xhr.responseText);
	var table = document.getElementById("reimbursements");
	var approveHtml, denyHtml;

    for(var i=0; i<reimbursements.length; i++) {
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "reimbursement-" + i);
        table.appendChild(newrow);
        var row = document.getElementById("reimbursement-" + i);
        row.innerHTML += "<td>"+reimbursements[i].id+"</td>";
        row.innerHTML += "<td>$"+reimbursements[i].amount+"</td>";
        row.innerHTML += "<td>"
					  + '<button type="button" class="btn btn-primary btn-sm btn-custom" data-toggle="modal" data-target="#descriptionModal-'+reimbursements[i].id+'">'
					  + 'View Description'
					  + '</button>'
					  + '<div class="modal fade" id="descriptionModal-'+reimbursements[i].id+'" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'
					  + '<div class="modal-dialog" role="document">'
					  + '<div class="modal-content">'
					  + '<div class="modal-header">'
					  + '<h5 class="modal-title" id="exampleModalLabel">Description</h5>'
					  + '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
					  + '<span aria-hidden="true">&times;</span>'
					  + '</button>'
					  + '</div>'
					  + '<div class="modal-body">'
					  + '<p>'+reimbursements[i].description+'</p>'
					  + '</div>'
					  + '<div class="modal-footer">'
					  + '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
					  + '</div>'
					  + '</div>'
					  + '</div>'
					  + '</div>';
					  + '</td>';
        row.innerHTML += "<td>"
					  + '<button type="button" class="btn btn-info btn-sm btn-custom" data-toggle="modal" data-target="#receiptModal-'+reimbursements[i].id+'">'
					  + 'View Receipt'
					  + '</button>'
					  + '<div class="modal fade" id="receiptModal-'+reimbursements[i].id+'" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'
					  + '<div class="modal-dialog" role="document">'
					  + '<div class="modal-content">'
					  + '<div class="modal-header">'
					  + '<h5 class="modal-title" id="exampleModalLabel">Receipt Image</h5>'
					  + '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
					  + '<span aria-hidden="true">&times;</span>'
					  + '</button>'
					  + '</div>'
					  + '<div class="modal-body">'
					  + '<img class="img-fluid" src="data:img/png;base64,'+reimbursements[i].receipt+'">'
					  + '</div>'
					  + '<div class="modal-footer">'
					  + '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
					  + '</div>'
					  + '</div>'
					  + '</div>'
					  + '</div>';
					  + '</td>';
        row.innerHTML += "<td>"+reimbursements[i].submitted+"<br>"+reimbursements[i].person+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].resolved+"<br>"+reimbursements[i].resolver+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].type+"</td>";
		row.innerHTML += "<td>"+reimbursements[i].status+"</td>";
		
		var status = reimbursements[i].status;
		if(status == 'Pending' || status == 'Denied') {
			approveHtml = "<form action='reimbursements' method='POST'>" +
							"<input type='hidden' name='status' value='2'>" +
							"<input type='hidden' name='reimbursementId' value=" + reimbursements[i].id + ">" +
							"<button class='btn btn-success btn-sm btn-custom' type='submit'>Approve</button>" +
							"</form>";
		} else {
			approveHtml = "";
		}
		if(status == 'Pending' || status == 'Approved') {
			denyHtml = "<form action='reimbursements' method='POST'>" +
						"<input type='hidden' name='status' value='3'>" +
						"<input type='hidden' name='reimbursementId' value=" + reimbursements[i].id + ">" +
						"<button style='margin-top:4px;' class='btn btn-danger btn-sm btn-custom' type='submit'>Deny</button>" +
						"</form>";
		} else {
			denyHtml = "";
		}

		row.innerHTML += "<td>" + approveHtml + denyHtml + "</td>";
	}
	
	// load data table
	$(document).ready(function() {
		$('#ajaxTable').DataTable();
	})
}



function loadUsers(xhr) {
    
	var users = JSON.parse(xhr.responseText);
    var table = document.getElementById("users");

    for(var i=0; i<users.length; i++) {
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "user-" + i);
        table.appendChild(newrow);
        var row = document.getElementById("user-" + i);
        row.innerHTML += "<td>"+users[i].id+"</td>";
        row.innerHTML += "<td>"+users[i].username+"</td>";
        row.innerHTML += "<td>"+users[i].firstName+"</td>";
        row.innerHTML += "<td>"+users[i].lastName+"</td>";
        row.innerHTML += "<td>"+users[i].email+"</td>";
        row.innerHTML += "<td>"+users[i].role+"</td>";
	}
	
	// load data table
	$(document).ready(function() {
		$('#ajaxTable').DataTable();
	})
}

window.onload = function() {
	
	// Get logged in user
	sendAjaxGet("http://localhost:8080/ProjectOne/session", populateUser);
	
	// Get current URL
	var location = window.location.href;
	
	// run ajax request if on reimbursements page
	if (location.indexOf("ProjectOne/reimbursements") > -1) {
		sendAjaxGet("http://localhost:8080/ProjectOne/get-reimbursements", loadReimbursements);
	}
	
	// run ajax request if on users page
	if (location.indexOf("ProjectOne/users") > -1) {
		sendAjaxGet("http://localhost:8080/ProjectOne/get-users", loadUsers);
	}
	
}