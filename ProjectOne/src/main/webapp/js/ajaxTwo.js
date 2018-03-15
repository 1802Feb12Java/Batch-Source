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

function loadReimbursements(xhr) {
    
	var reimbursements = JSON.parse(xhr.responseText);
    var table = document.getElementById("reimbursements");

    for(var i=0; i<reimbursements.length; i++) {
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "reimbursement-" + i);
        table.appendChild(newrow);
        var row = document.getElementById("reimbursement-" + i);
        row.innerHTML += "<td>"+reimbursements[i].id+"</td>";
        row.innerHTML += "<td>$"+reimbursements[i].amount+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].description+"</td>";
		row.innerHTML += "<td>"
					  + '<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#receiptModal-'+reimbursements[i].id+'">'
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
        row.innerHTML += "<td>"+reimbursements[i].submitted+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].resolved+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].person+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].type+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].status+"</td>";
	}
	
	// load data table
	$(document).ready(function() {
		$('#ajaxTable').DataTable();
	})
}

window.onload = function() {
	
	// Get logged in user
	sendAjaxGet("http://localhost:8080/ProjectOne/employee-session", populateUser);
	
	// Get current URL
	var location = window.location.href;
	
	// run ajax request if on reimbursements page
	if (location.indexOf("ProjectOne/employee-reimbursements") > -1) {
		sendAjaxGet("http://localhost:8080/ProjectOne/get-reimbursements", loadReimbursements);
	}
	
}