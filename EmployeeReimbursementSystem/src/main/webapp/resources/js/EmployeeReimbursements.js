//the argument magic holds the results
function loadReimbursements(reimbursementInfo){

    console.log(reimbursementInfo.length);

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
        
        cell1.innerHTML = reimbursementInfo[i].reimbursementTypeIDString;
        cell2.innerHTML = reimbursementInfo[i].amount;
        cell3.innerHTML = "RECEIPT";
        cell4.innerHTML = reimbursementInfo[i].description;
        cell5.innerHTML = "DATE";
        cell6.innerHTML = reimbursementInfo[i].userIDResolverString;
        cell7.innerHTML = "RESOLVER";

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
            var reimbursementInfo = JSON.parse(xhr.responseText);
            loadReimbursements(reimbursementInfo);
        }
    }

    //Step 3! Open request/connection
   xhr.open("GET","http://localhost:8080/EmployeeReimbursementSystem/UserReimbursementServlet", true);
   
   //Step 4! send request
   xhr.send();
}

window.onload = function(){
    console.log("in onLoad");
    getReimbursement();
}