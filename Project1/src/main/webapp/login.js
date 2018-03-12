function loadError(name){
    document.getElementById("error.msg").style.visibility = "visible";
}

function getError(){
    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange = function(){
        console.log("Roll tide");
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
            var greeting = xhr.responseText;
            if(greeting == "problem incorrect password")
            loadName(greeting);
        }
    }
    //Step 3! Open requests/connections
    xhr.open("POST", "/Project1/Login", true);
    //Step 4! Send Request
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    getError();
}