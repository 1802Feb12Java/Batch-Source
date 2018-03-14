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

// window.onload = function(){
//     console.log("in onLoad");
//     document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);    
// }




function loadDef(word){
    document.getElementById("defList").innerHTML = "";
    var listOfDefs = word.list;
    console.log(word.list[0].definition);
  
    for(var i = 0; i < listOfDefs.length; i++){
        var aDef = listOfDefs[i].definition;
        var panelItem = document.createElement("div");
        panelItem.setAttribute("class","panel panel-default");
        panelItem.setAttribute("style","padding: 2%; width:70%; position:relative; left:15%; border:2px solid #dadfe8; border-radius: 3px");
        //var listItem = document.createElement("li");
        //listItem.textContent = aDef;
        panelItem.textContent = aDef;
        document.getElementById("defList").appendChild(panelItem);
        //document.getElementById("defList").lastElementChild.innerHTML = aDef;
    }
    // console.log(defString);

     document.getElementById("word").innerHTML = word.list[0].word;
    //  document.getElementById("definitions").innerHTML = 
     //console.log(word);
}

function getDefinition(){
   //console.log("well hello there");
   var theWord= document.getElementById("yourWord").value;
   //Step 1! Open XHR
   var xhr = new XMLHttpRequest();
   //Step 2! function to handle readystatechange of response
   xhr.onreadystatechange= function(){
       //console.log("");
       if(xhr.readyState==4 && xhr.status== 200){
           //console.log(xhr.responseText);
           var definition = JSON.parse(xhr.responseText);
           loadDef(definition);
       }
   }
   //Step 3! Open request/connection
   xhr.open("GET","http://api.urbandictionary.com/v0/define?term=" + theWord, true);
//    http://api.urbandictionary.com/v0/define?term={word}
   //Step 4! send request
   xhr.send();
}

//window.onload = function(){
    console.log("in onLoad");
    document.getElementById("pokemonSubmit").addEventListener("click", getPokemon, false);    
    document.getElementById("wordSubmit").addEventListener("click", getDefinition, false);    

//}