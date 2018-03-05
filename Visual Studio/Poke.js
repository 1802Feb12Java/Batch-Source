// function loadPokemon(pokemon){
//     document.getElementById("name").innerHTML = pokemon.name;
// }

// function getPokemon(){
//     console.log("well hello there");
//     var pokemonId= document.getElementById("pokeId").value;
//     //Step 1! Open XHR
//     var xhr = new XMLHttpRequest();
//     //Step 2! function to handle readystatechange of response
//     xhr.onreadystatechange = function(){
//         console.log("Roll tide");
//         if(xhr.readyState==4 && xhr.status==200){
//             //xhr.responseText is a JSON
//             console.log(xhr.responseText);
//             var pokemon = JSON.parse(xhr.responseText);
//             loadPokemon(pokemon);
//         }
//     }
//     //Step 3! Open requests/connections
//     xhr.open("GET", "https://pokeapi.co/api/v2/pokemon/" + pokemonId, true);
//     //Step 4! Send Request
//     xhr.send();
// }

// window.onload = function(){
//     this.console.log("in onLoad");
//     document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);
// }


//this is my example
function loadCountry(country){
    document.getElementById("country").innerHTML = country.name;
}

function getCountry(){
    var factId= document.getElementById("countryId").value;
    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange = function(){
        console.log("Roll Tide");
        if(xhr.readyState==4 && xhr.status==200){
            console.log("Roll");
            var countryf = JSON.parse(xhr.responseText);
            console.log(countryf);
            loadCountry(countryf);
        }
    }
    //Step 3! Open requests/connections
    xhr.open("GET", "http://services.groupkt.com/country/get/iso3code/" + factId, true);
    //Step 4! Send Request
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    document.getElementById("countrySubmit").addEventListener("click", getCountry, false);
}