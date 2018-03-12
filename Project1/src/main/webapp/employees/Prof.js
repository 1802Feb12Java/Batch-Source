function loadName(name){
    document.getElementById("greeting").innerHTML = name;
}

function getName(){
    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange = function(){
        console.log("Roll tide");
        if(xhr.readyState==4 && xhr.status==200){
            console.log(xhr.responseText);
            var greeting = xhr.responseText;
            loadName(greeting);
        }
    }
    //Step 3! Open requests/connections
    xhr.open("GET", "/Project1/Welcome", true);
    //Step 4! Send Request
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    getName();
}