//the argument magic holds the results
function loadUser(userInfo){
    //will change the information in the header with the id
    //magicName
    document.getElementById("username").innerHTML =  userInfo.username;
    document.getElementById("usernameText").value =  userInfo.username;
    document.getElementById("passwordText").value =  userInfo.password;
    document.getElementById("firstName").value =  userInfo.firstName;
    document.getElementById("lastName").value =  userInfo.lastName;
    document.getElementById("email").value =  userInfo.email;

}

function getUser(){
    //get the information that was submitted in the "magicID" input field

    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();

    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange= function(){
        if(xhr.readyState==4 && xhr.status== 200){
            console.log(xhr.responseText);
            var userInfo = JSON.parse(xhr.responseText);
            loadUser(userInfo);
        }
    }

    //Step 3! Open request/connection
   xhr.open("GET","http://localhost:8080/EmployeeReimbursementSystem/UserServlet", true);
   
   //Step 4! send request
   xhr.send();
}

window.onload = function(){
    console.log("in onLoad");
    getUser();
}