function loadInfo(user){
    document.getElementById("fullname").innerHTML = user.fullname;
    document.getElementById("username").innerHTML = user.username;
    document.getElementById("email").innerHTML = user.email;
    document.getElementById("role").innerHTML = user.role;
}

function getInfo(){
    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange = function(){
        console.log("Roll tide");
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
            var user = xhr.responseText;
            loadInfo(user);
        }
    }
    //Step 3! Open requests/connections
    xhr.open("GET", "/Project1/UserInfoServlet", true);
    //Step 4! Send Request
    xhr.send();
}

window.onload = function(){
    getInfo();
}