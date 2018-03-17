function login()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() 
    {
            if (this.readyState == 4 && this.status == 200) {
        // Typical action to be performed when the document is ready:
            
        JSON.parse(xhttp.responseText);
        
        
        }
    }
    var url = "/ERS/Login"
    var username = document.getElementById("username")
    var password = document.getElementById("password")
    var loginJSON = {username:username.value, password:password.value};
    var loginJSONStr = JSON.stringify(loginJSON)
    xhttp.open("POST", url , true);
    xhttp.send("x=" + loginJSONStr);
}
