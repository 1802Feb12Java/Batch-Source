
function createUser()
{
    var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() 
    {
            if (this.readyState == 4 && this.status == 200) {
        // Typical action to be performed when the document is ready:
            
        // var JSONObj = JSON.parse(xhttp.responseText);
        
        }
    }
    var url = "/ERS/createUser"
    var username = document.getElementById("Username")
    var password = document.getElementById("Password")
    var firstname = document.getElementById("Firstname")
    var lastname = document.getElementById("Lastname")
    var email = document.getElementById("Email")
    var sendUserInfo = {username:username.value, password:password.value, 
        firstname:firstname.value, lastname:lastname.value, 
        email:email.value};
    var myjson = JSON.stringify(sendUserInfo)
    xhttp.open("POST", url , true);
    xhttp.send("x=" + myjson);
}
function updateUser()
{
    var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() 
    {
            if (this.readyState == 4 && this.status == 200) {
        // Typical action to be performed when the document is ready:
            
        // var JSONObj = JSON.parse(xhttp.responseText);
        
        }
    }
    var url = "/ERS/updateUser"
    var username = document.getElementById("Username")
    var password = document.getElementById("Password")
    var firstname = document.getElementById("Firstname")
    var lastname = document.getElementById("Lastname")
    var email = document.getElementById("Email")
    var sendUserInfo = {username:username.value, password:password.value, 
        firstname:firstname.value, lastname:lastname.value, 
        email:email.value};
    var myjson = JSON.stringify(sendUserInfo)
    xhttp.open("POST", url , true);
    xhttp.send("x=" + myjson);
}