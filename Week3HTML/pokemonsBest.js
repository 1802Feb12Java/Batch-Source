function loadCharacterName(character){
    document.getElementById("name").innerHTML = character.name;
}

function getCharacterName(){
    var personID = document.getElementById("ID").value;
    //Step 1: Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2: function to handle readystatechange of response
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){       //readyState = 4 means response ready, status = 200 means success
            var character = JSON.parse(xhr.responseText);
            loadCharacterName(character);
        }
    }
    //Step 3: Open request/connection
    xhr.open("GET", "https://swapi.co/api/people/"+personID, true);
    //Step 4: Send request
    xhr.send();
}

window.onload = function(){
    this.document.getElementById("idSubmit").addEventListener("click", getCharacterName, false);
}