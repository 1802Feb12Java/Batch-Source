
var reimbursementInfo;

//the argument magic holds the results
function loadReimbursements(reimbursementInfo){
	
	for(i = 0; i < reimbursementInfo.length; i++){

        var table = document.getElementById("tableBody");
        var row = table.insertRow(i);
        
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        var cell7 = row.insertCell(6);
        var cell8 = row.insertCell(7);
        var cell9 = row.insertCell(8);
        
        cell1.innerHTML = reimbursementInfo[i].userIDAuthorString;
        cell2.innerHTML = reimbursementInfo[i].amount;
        cell3.innerHTML = "RECEIPT";
        cell4.innerHTML = reimbursementInfo[i].reimbursementTypeIDString;
        cell5.innerHTML = reimbursementInfo[i].description;
        cell6.innerHTML = reimbursementInfo[i].submitted;
        
        if(reimbursementInfo[i].userIDResolverString != "test"){
        	cell7.innerHTML = reimbursementInfo[i].userIDResolverString;
        }else{
        	cell7.innerHTML = "UNRESOLVED";
        }
        
        cell8.innerHTML = reimbursementInfo[i].reimbursementTypeIDString;
        cell9.innerHTML = 
        	"<form method = \"post\" action=\"UpdateReimbursementServlet\" class=\"form-horizontal\">" +
        		"<div><button name = \"approve\" type=\"submit\" class=\"btn btn-primary\" value = "+reimbursementInfo[i].reiumbursementID+">Approve</button></div>" +
        		"<button name = \"deny\" type=\"submit\" class=\"btn btn-primary\" value = "+reimbursementInfo[i].reiumbursementID+">Deny</b>" +
        	"</form>"
        	//"<img width=\"150\" height=\"150\" src= data:image/png;base64," + encoded + " >"
        
    }
	
}

function search(){
	var myNode = document.getElementById("tableBody");
	while (myNode.firstChild) {
	    myNode.removeChild(myNode.firstChild);
	}
	var searchVal= document.getElementById('search').value
	
	var counter = 0;
	
	for(i = 0; i < reimbursementInfo.length; i++){
		console.log(searchVal + " == " + reimbursementInfo[i].userIDAuthorString );
		if(searchVal == reimbursementInfo[i].userIDAuthorString){
			var table = document.getElementById("tableBody");
	        var row = table.insertRow(counter);
	        counter += 1;
	        
	        var cell1 = row.insertCell(0);
	        var cell2 = row.insertCell(1);
	        var cell3 = row.insertCell(2);
	        var cell4 = row.insertCell(3);
	        var cell5 = row.insertCell(4);
	        var cell6 = row.insertCell(5);
	        var cell7 = row.insertCell(6);
	        var cell8 = row.insertCell(7);
	        var cell9 = row.insertCell(8);
	        
	        cell1.innerHTML = reimbursementInfo[i].userIDAuthorString;
	        cell2.innerHTML = reimbursementInfo[i].amount;
	        cell3.innerHTML = "RECEIPT";
	        cell4.innerHTML = reimbursementInfo[i].reimbursementTypeIDString;
	        cell5.innerHTML = reimbursementInfo[i].description;
	        cell6.innerHTML = reimbursementInfo[i].submitted;
	        
	        if(reimbursementInfo[i].userIDResolverString != "test"){
	        	cell7.innerHTML = reimbursementInfo[i].userIDResolverString;
	        }else{
	        	cell7.innerHTML = "UNRESOLVED";
	        }
	        
	        cell8.innerHTML = reimbursementInfo[i].reimbursementTypeIDString;
	        cell9.innerHTML = 
	        	"<form method = \"post\" action=\"UpdateReimbursementServlet\" class=\"form-horizontal\">" +
	        		"<div><button name = \"approve\" type=\"submit\" class=\"btn btn-primary\" value = "+reimbursementInfo[i].reiumbursementID+">Approve</button></div>" +
	        		"<button name = \"deny\" type=\"submit\" class=\"btn btn-primary\" value = "+reimbursementInfo[i].reiumbursementID+">Deny</b>" +
	        	"</form>"
		}
	}
}

function getReimbursement(){
    //get the information that was submitted in the "magicID" input field

    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();

    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange= function(){
        if(xhr.readyState==4 && xhr.status== 200){
            console.log(xhr.responseText);
            reimbursementInfo = JSON.parse(xhr.responseText);
            loadReimbursements(reimbursementInfo);
        }
    }

    //Step 3! Open request/connection
   xhr.open("GET","http://localhost:8080/EmployeeReimbursementSystem/GetEmployeeAndReimbursements", true);
   
   //Step 4! send request
   xhr.send();
}



window.onload = function(){
    console.log("in onLoad");
    getReimbursement();
}