//the argument magic holds the results
function loadUsers(userInfo){

    console.log(userInfo.length);

    for(i = 0; i < userInfo.length; i++){

        var table = document.getElementById("tableBody");
        var row = table.insertRow(i);
        
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        
        cell1.innerHTML = userInfo[i].username;
        cell2.innerHTML = userInfo[i].firstName;
        cell3.innerHTML = userInfo[i].lastName;
        cell4.innerHTML = userInfo[i].email;

    }
}

function getUsers(){
    //get the information that was submitted in the "magicID" input field

    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();

    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange= function(){
        if(xhr.readyState==4 && xhr.status== 200){
            console.log(xhr.responseText);
            var userInfo = JSON.parse(xhr.responseText);
            loadUsers(userInfo);
        }
    }

    //Step 3! Open request/connection
   xhr.open("GET","http://localhost:8080/EmployeeReimbursementSystem/GetAllEmployees", true);
   
   //Step 4! send request
   xhr.send();
}

window.onload = function(){
    console.log("in onLoad");
    getUsers();
}