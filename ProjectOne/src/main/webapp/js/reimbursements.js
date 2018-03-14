function loadReimbursements(reimbursements) {
    
    var table = document.getElementById("reimbursements");

    for(var i=0; i<reimbursements.length; i++) {
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "reimbursement-" + i);
        table.appendChild(newrow);
        var row = document.getElementById("reimbursement-" + i);
        row.innerHTML += "<td>"+reimbursements[i].id+"</td>";
        row.innerHTML += "<td>$"+reimbursements[i].amount+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].description+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].receipt+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].submitted+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].resolved+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].employee+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].type+"</td>";
        row.innerHTML += "<td>"+reimbursements[i].status+"</td>";
        row.innerHTML += "<td>" +
        					"<form action='reimbursements' method='POST'>" +
        					"<input type='hidden' name='status' value='2'>" +
        					"<input type='hidden' name='reimbursementId' value=" + reimbursements[i].id + ">" +
        					"<button style='width:100%; padding:2px' class='btn btn-success btn-sm' type='submit'>Approve</button>" +
        					"</form>" +
        					"<form action='reimbursements' method='POST'>" +
        					"<input type='hidden' name='status' value='3'>" +
        					"<input type='hidden' name='reimbursementId' value=" + reimbursements[i].id + ">" +
        					"<button style='width:100%; margin-top:4px; padding:2px' class='btn btn-danger btn-sm' type='submit'>Deny</button>" +
        					"</form>" +
        					"</td>";
    }
}

function getRequestArray(){
    var xhr = new XMLHttpRequest();
   
    xhr.onreadystatechange= function(){
        if(xhr.readyState==4 && xhr.status== 200){
            var list = JSON.parse(xhr.responseText);
            loadReimbursements(list);
        }
    }

    xhr.open("GET","http://localhost:8080/ProjectOne/get-reimbursements", true);
    xhr.send();
}

window.onload = function(){
    getRequestArray();
}