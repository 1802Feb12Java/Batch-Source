function loadPokemon(pokemon){
    
    document.getElementById("name").innerHTML = pokemon.name;
}

function getPokemon(){
   console.log("well hello there");
   var pokemonId= document.getElementById("pokeId").value;
   //Step 1! Open XHR
   var xhr = new XMLHttpRequest();
   //Step 2! function to handle readystatechange of response
   xhr.onreadystatechange= function(){
       console.log("roll tide");
       if(xhr.readyState==4 && xhr.status== 200){
           console.log(xhr.responseText);
           var pokemon = JSON.parse(xhr.responseText);
           loadPokemon(pokemon);
       }
   }
   //Step 3! Open request/connection
   xhr.open("GET","https://pokeapi.co/api/v2/pokemon/" + pokemonId, true);

   //Step 4! send request
   xhr.send();
}

function loadFact(fact) {
    document.getElementById("fact").innerText = fact;
}

function getFact() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(xhr.readyState==4 && xhr.status == 200) {
            let fact = JSON.parse(xhr.responseText);

            // Get fact text.
            loadFact(fact['value']['joke']);
        }
    }

    // Get name & generate parameter string
    let count = 0;
    let fname = document.getElementById('fname').value;
    let lname = document.getElementById('lname').value;
    let fnameParam, lnameParam;

    if(fname != "") {
        fnameParam = "firstName="+fname;
        ++count;
    }
    if(lname != "") {
        lnameParam = "lastName="+lname;
        ++count;
    }

    let paramStr;

    if(count == 0) {
        paramStr = "/";
    } else if(count == 1) {
        if(fnameParam != undefined) {
            paramStr = "?" + fnameParam;
        } else {
            paramStr = "?" + lnameParam;
        }
    } else if(count == 2) {
        paramStr = "?" + fnameParam + '&' + lnameParam;
    }
    

    xhr.open("GET", "http://api.icndb.com/jokes/random" + paramStr, true);
    xhr.send();
}

window.onload = function(){
    console.log("in onLoad");
    document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);
    document.getElementById("submitName").addEventListener("click", getFact, false);
    document.getElementById("clearFact").addEventListener("click", function() {
        document.getElementById("fact").innerText = '';
    }, false);
}