function logout(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            console.log("in here");
        }
    }
    xhr.open("GET", "/Project1/LogoutServlet", true);
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    logout();
}