function loadPokemon(pokemon){
    document.getElementById("name").innerHTML = pokemon.name;
}

function getPokemon(){
    console.log("well hello there");
    var pokemonId = document.getElementById("pokeId").value;
    //Step 1: Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2: function to handle readystatechange of response
    xhr.onreadystatechange = function(){
        console.log("roll tide");
        if(xhr.readyState==4 && xhr.status==200){       //readyState = 4 means response ready, status = 200 means success
            //console.log(xhr.responseText);
            var pokemon = JSON.parse(xhr.responseText);
            loadPokemon(pokemon);
        }
    }
    //Step 3: Open request/connection
    xhr.open("GET", "https://pokeapi.co/api/v2/pokemon/" + pokemonId, true);
    //Step 4: Send request
    xhr.send();
}

window.onload = function(){
    console.log("in onLoad");
    this.document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);
}