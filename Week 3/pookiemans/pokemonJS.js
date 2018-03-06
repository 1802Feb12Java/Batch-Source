function loadPokemon(pokemon){
	
    document.getElementById("name").innerHTML = pokemon.name;
    
}
function loadCatFact(kitty){
    document.getElementById("divCat").innerHTML=kitty.fact;
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

function getKittyFact(){
    console.log("catfacts!");
    var catfactId=document.getElementById("catFact").value;
    var xhr1 = new XMLHttpRequest();
    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange= function(){
        console.log("roll tide");
        if(xhr1.readyState==4 && xhr.status== 200){
            console.log(xhr1.responseText);
            var kitty = JSON.parse(xhr1.responseText);
            loadCatFact(kitty);
        }
    }
    //Step 3! Open request/connection
    xhr.open("GET","https://cat-fact.herokuapp.com/#/facts" +catfact, true);

    //Step 4! send request
    xhr.send();

}
window.onload = function(){
	console.log("in onLoad");
    document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);	
    document.getElementById("catsubmit").addEventListener("click", getKittyFact, false);
}