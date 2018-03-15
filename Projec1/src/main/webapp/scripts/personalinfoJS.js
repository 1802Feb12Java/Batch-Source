
//pull information from java servlet and display on the page
function displayInfo(){
    console.log("function is running");
    var req = new XMLHttpRequest();
    var info = document.getElementById("infoholder");
   
   
    req.onreadystatechange = function(){
            if (req.status >= 200 && req.status < 300) {
                console.log("goes through onreadystatchange");
                info.innerHTML = req.responseText;
                console.log("display message success");
            }
        }
        req.open("GET", "http://localhost:8080/Projec1/piservlet", true);
        req.send(null);
    
}
//pull information from java servlet and display on the page
function displayPersonalInfo(){
    console.log("function is running");
    var xhr = new XMLHttpRequest();
   // var info = document.getElementById("infoholder");
   var id = document.getElementById("idholder");
   var fname = document.getElementById("fnameholder");
   var lname = document.getElementById("lnameholder");
   var email = document.getElementById("emailholder");

   
    xhr.onreadystatechange = function(){
            if (xhr.status >= 200 && xhr.status < 300) {
                console.log("goes through onreadystatchange");
                var user = JSON.parse(xhr.responseText); //convert json into js object
                id.innerHTML = "User ID :" + user.u_id;
                fname.innerHTML = "First Name: " + user.u_firstname;
                lname.innerHTML = "Last Name: " + user.u_lastname;
                email.innerHTML = "Email: " + user.u_email

                console.log("display message success");
            }
        }
        xhr.open("GET", "http://localhost:8080/Projec1/piservlet", true);
        xhr.send(null);
    
}

// // -------------------- Update user's information using put
function updateUser(){

}
function changePass(){
    var xhr = new XMLHttpRequest();

    var oldpass = document.getElementById("oldpass").textContent;
    var newpass = document.getElementById("newpass").textContent;
     //idnumber=" + idNumber + '&' + 'name=' + newName
    xhr.onreadystatechange = function(){
        if (xhr.status >= 200 && xhr.status < 300) {
          alert("password is updated")
            console.log("pass changed success");
        }
    }
    xhr.open("put", "http://localhost:8080/Projec1/piservlet", true);
    xhr.send('oldpass=' + oldpass +'&newpass=' + newpass);
}
function changeLName(){
    var lname = document.getElementById("lname").textContent;
    var xhr = new XMLHttpRequest();
     
    xhr.onreadystatechange = function(){
        if (xhr.status >= 200 && xhr.status < 300) {
           alert("Lastname is updated")
            console.log("last name changed success");
        }
    }
    xhr.open("put", "http://localhost:8080/Projec1/piservlet", true);
    xhr.send('lname=' + lname);


}
function changeFName(){
    var fname = document.getElementById("fname").textContent;
    var xhr = new XMLHttpRequest();
     
    xhr.onreadystatechange = function(){
        if (xhr.status >= 200 && xhr.status < 300) {
            alert("First name is updated")

            console.log("firstname change success  success");
        }
    }
    xhr.open("put", "http://localhost:8080/Projec1/piservlet", true);
    xhr.send('fname=' + fname);


}
function changeEmail(){
    var email = document.getElementById("email").textContent;
    var xhr = new XMLHttpRequest();
     
    xhr.onreadystatechange = function(){
        if (xhr.status >= 200 && xhr.status < 300) {
            alert("Email is updated");
            console.log("email changed success");
        }
    }
    xhr.open("put", "http://localhost:8080/Projec1/piservlet", true);
    xhr.send('email=' + email);


}