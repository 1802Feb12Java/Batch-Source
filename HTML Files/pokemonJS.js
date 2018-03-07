

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

window.onload = function(){
    console.log("in onLoad");
    document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);  
    document.getElementById("starSubmit").addEventListener("click", getPlanet, false);    
  
}



//============================================================================================================
function loadSWPlanet(swPlanet){
    document.getElementById("star").innerHTML = swPlanet.name;
}

function getPlanet(){
    console.log("*cue star wars intro song*");
    //var planetNum= document.getElementById("pokeId").value;
    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange= function(){
        console.log("roll tide");
        if(xhr.readyState==4 && xhr.status== 200){
            console.log(xhr.responseText);
            var planet = JSON.parse(xhr.responseText);
            loadSWPlanet(planet);
        }
    }
    //Step 3! Open request/connection
    xhr.open("GET","https://swapi.co/api/planets/3" ,  true);
 
    //Step 4! send request
    xhr.send();
 }

 window.onload = function(){
    console.log("in onLoad");
    document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);  
    document.getElementById("starSubmit").addEventListener("click", getPlanet, false);    
  
}