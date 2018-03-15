/**
 * 
 */

var xhttp = new XMLHttpRequest();

function getUsers(){
    

    var ersURL = "http://localhost:8080/Johne_ERS_Project/AllEmployees";
    xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var response= JSON.parse(xhttp.responseText)["UsersList"];
            var table = document.getElementById("users");
            
            var tr1 = document.createElement("tr");
            
            //create individual header for table row
            var userID_th = document.createElement("th");
            var username_th = document.createElement("th");
            var firstName_th = document.createElement("th");
            var lastName_th = document.createElement("th");
            var email_th = document.createElement("th");
            var role_th = document.createElement("th");

            //set the header values
            var uID_th = document.createTextNode("User ID");
            var uUsername_th = document.createTextNode("Username");
            var uFirstname_th = document.createTextNode("First Name");
            var uLastname_th = document.createTextNode("Last Name");
            var uEmail_th = document.createTextNode("Email Address");
            var uRole_th = document.createTextNode("User Role");

            //put header text in each cell
            userID_th.appendChild(uID_th);
            username_th.appendChild(uUsername_th);
            firstName_th.appendChild(uFirstname_th);
            lastName_th.appendChild(uLastname_th);
            email_th.appendChild(uEmail_th);
            role_th.appendChild(uRole_th);

            //put header in each rows
            tr1.appendChild(userID_th);
            tr1.appendChild(username_th);
            tr1.appendChild(firstName_th);
            tr1.appendChild(lastName_th);
            tr1.appendChild(email_th);
            tr1.appendChild(role_th);

            table.appendChild(tr1);

			for(var i = 0; i<response.length; i++){
                var user = response[i];

                var tr = document.createElement("tr");
            
            //create individual header for table row
            var userID_td = document.createElement("td");
            var username_td = document.createElement("td");
            var firstName_td = document.createElement("td");
            var lastName_td = document.createElement("td");
            var email_td = document.createElement("td");
            var role_td = document.createElement("td");

            //set the header values
            var userID = document.createTextNode(user.ID);
            var username = document.createTextNode(user.Username);
            var firstname = document.createTextNode(user.FirstName);
            var lastname = document.createTextNode(user.LastName);
            var email = document.createTextNode(user.Email);
            var role = document.createTextNode(user.URole);

            //put header text in each cell
            userID_td.appendChild(userID);
            username_td.appendChild(username);
            firstName_td.appendChild(firstname);
            lastName_td.appendChild(lastname);
            email_td.appendChild(email);
            role_td.appendChild(role);

            //put header in each rows
            tr.appendChild(userID_td);
            tr.appendChild(username_td);
            tr.appendChild(firstName_td);
            tr.appendChild(lastName_td);
            tr.appendChild(email_td);
            tr.appendChild(role_td);

            table.appendChild(tr);

			}
		}
	}
    xhttp.open("GET", ersURL);
    xhttp.send();
            
}

function deleteDivChildren(){
    var users = document.getElementById("users");
    var children = users.children;
    for(var i=0; i < children.length; i++){
        users.removeChild(children[i]);
    }
    users.innerHTML ="";
}


function getReimbursements() {

    deleteDivRChildren();

    var ersReURL = "http://localhost:8080/Johne_ERS_Project/AllReimbursements";
    xhttp.onreadystatechange = function() 
    {
        if(this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(xhttp.responseText)["reimbursementList"];
            var table = document.getElementById("reimbursement");

            var tr1 = document.createElement("tr");
            
            //create individual header for table row
            var id_th = document.createElement("th");
            var amount_th = document.createElement("th");
            var descript_th = document.createElement("th");
            var receipt_th = document.createElement("th");
            var timeSubmit_th = document.createElement("th");
            var timeResolve_th = document.createElement("th");
            
            var authorID_th = document.createElement("th");
            var authorFirstName_th = document.createElement("th");
            var authourLastname_th = document.createElement("th");

            var resolverID_th = document.createElement("th");
            var resolverFirstname_th = document.createElement("th");
            var resolverLastname_th = document.createElement("th");
            
            var rType_th = document.createElement("th");
            var rStatus_th = document.createElement("th");

            //set the header values
            var reimID_th = document.createTextNode("R ID");
            var rAmount_th = document.createTextNode("Amount");
            var rDesc_th = document.createTextNode("Description");
            var rReceipt_th = document.createTextNode("Reimbursement Receipt");
            var rTimeSubmit_th = document.createTextNode("Time Submitted");
            var rTimeResolve_th = document.createTextNode("Time Resolved");
            
            var rAuthorID_th = document.createTextNode("Author ID");
            var rAuthorFirstname_th = document.createTextNode("Author Firstname");
            var rAuthorLastname_th = document.createTextNode("Author Lastname");

            var rResolverID_th = document.createTextNode("Resolver ID");
            var rResovlerFirstname_th = document.createTextNode("Resolver Firstname");
            var rResolverLastname_th = document.createTextNode("Resolver Lastname");

            var reimType_th = document.createTextNode("Reimburse Type");
            var reimStatus_th = document.createTextNode("Reimburse Status");

            //put header text in each cell
            id_th.appendChild(reimID_th);
            amount_th.appendChild(rAmount_th);
            descript_th.appendChild(rDesc_th);
            receipt_th.appendChild(rReceipt_th);
            rType_th.appendChild(reimType_th);
            rStatus_th.appendChild(reimStatus_th);
            timeSubmit_th.appendChild(rTimeSubmit_th);
            timeResolve_th.appendChild(rTimeResolve_th);
            authorID_th.appendChild(rAuthorID_th);
            authorFirstName_th.appendChild(rAuthorFirstname_th);
            authourLastname_th.appendChild(rAuthorLastname_th);
            resolverID_th.appendChild(rResolverID_th);
            resolverFirstname_th.appendChild(rResovlerFirstname_th);
            resolverLastname_th.appendChild(rResolverLastname_th);

            //put header in each rows
            tr1.appendChild(id_th);
            tr1.appendChild(amount_th);
            tr1.appendChild(descript_th);
            tr1.appendChild(receipt_th);
            tr1.appendChild(rType_th);
            tr1.appendChild(rStatus_th);
            tr1.appendChild(timeSubmit_th);
            tr1.appendChild(timeResolve_th);
            tr1.appendChild(authorID_th);
            tr1.appendChild(authorFirstName_th);
            tr1.appendChild(authourLastname_th);
            tr1.appendChild(resolverID_th);
            tr1.appendChild(resolverFirstname_th);
            tr1.appendChild(resolverLastname_th);

            table.appendChild(tr1);

            for(var i = 0; i < response.length; i++) {
                var reimbursements = response[i];

                //create a row tag
                var tr = document.createElement("tr");

                //create individual cells for table row
                var id_td = document.createElement("td");
                var amount_td = document.createElement("td");
                var descript_td = document.createElement("td");
                var receipt_td = document.createElement("td");
                var rType_td = document.createElement("td");
                var rStatus_td = document.createElement("td");
                var timeSubmit_td = document.createElement("td");
                var timeResolve_td = document.createElement("td");
                
                var authorID_td = document.createElement("td");
                var authorFirstname_td = document.createElement("td");
                var authorLastname_td = document.createElement("td");

                var resolverID_td = document.createElement("td");
                var resolverFirstname_td = document.createElement("td");
                var resolverLastname_td = document.createElement("td");
                
                var edit_td = document.createElement("a");

                
                //get the values
                var reimID = document.createTextNode(reimbursements.ID);
                var rAmount = document.createTextNode(reimbursements.amount);
                var rDesc = document.createTextNode(reimbursements.description);
                var rReceipt = document.createTextNode(reimbursements.receipt);
                var rType = document.createTextNode(reimbursements.reimType);
                var rStatus = document.createTextNode(reimbursements.reimStatus);
                var rTimeSubmit = document.createTextNode(reimbursements.timeSubmitted);
                var rTimeResolve = document.createTextNode(reimbursements.timeResolved);
                
                var rAuthorID = document.createTextNode(reimbursements.authorID);
                var rAuthorFirstname = document.createTextNode(reimbursements.author_firstname);
                var rAuthorLastname = document.createTextNode(reimbursements.author_lastname);

                var rResolverID = document.createTextNode(reimbursements.managerID);
                var rResolverFirstname = document.createTextNode(reimbursements.resolver_firstName);
                var rResolverLastname = document.createTextNode(reimbursements.resolver_lastname);
                
                
                var edit = document.createTextNode("Edit");
                edit_td.setAttribute('href', 'EditStatus?r_id='+ reimbursements.ID);

                //put reimbursement properties in each cell
                id_td.appendChild(reimID);
                amount_td.appendChild(rAmount);
                descript_td.appendChild(rDesc);
                receipt_td.appendChild(rReceipt);
                rType_td.appendChild(rType);
                rStatus_td.appendChild(rStatus);
                timeSubmit_td.appendChild(rTimeSubmit);
                timeResolve_td.appendChild(rTimeResolve);
                
                authorID_td.appendChild(rAuthorID);
                authorFirstname_td.appendChild(rAuthorFirstname);
                authorLastname_td.appendChild(rAuthorLastname);

                resolverID_td.appendChild(rResolverID);
                resolverFirstname_td.appendChild(rResolverFirstname);
                resolverLastname_td.appendChild(rResolverLastname);
                
                edit_td.appendChild(edit);

                //put cell in each rows
                tr.appendChild(id_td);
                tr.appendChild(amount_td);
                tr.appendChild(descript_td);
                tr.appendChild(receipt_td);
                tr.appendChild(rType_td);
                tr.appendChild(rStatus_td);
                tr.appendChild(timeSubmit_td);
                tr.appendChild(timeResolve_td);
                
                tr.appendChild(authorID_td);
                tr.appendChild(authorFirstname_td);
                tr.appendChild(authorLastname_td);

                tr.appendChild(resolverID_td);
                tr.appendChild(resolverFirstname_td);
                tr.appendChild(resolverLastname_td);
                
                tr.appendChild(edit_td);

                
                //put all cells in table
                table.appendChild(tr);
            
            }
        }
    }
    xhttp.open("GET", ersReURL);
    xhttp.send();
}

function deleteDivRChildren(){
    var users = document.getElementById("reimbursement");
    var children = users.children;
    for(var i=0; i < children.length; i++){
        users.removeChild(children[i]);
    }
    users.innerHTML ="";
}