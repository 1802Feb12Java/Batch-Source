function loadStarWarsCharacter(schar){
    document.getElementById("people").innerHTML = schar.name;
}

function getCharacter(){
   console.log("well hello there");
   var characterId= document.getElementById("starWarsID").value;

   //Step 1! Open XHR
   var xhr = new XMLHttpRequest();

   //Step 2! function to handle readystatechange of response
   xhr.onreadystatechange= function(){
       console.log("roll tide");

       if(xhr.readyState==4 && xhr.status== 200){
           console.log(xhr.responseText);
           var character = JSON.parse(xhr.responseText);
           loadStarWarsCharacter(character);
       }
   }

   //Step 3! Open request/connection
   xhr.open("GET","https://swapi.co/api/people/" + characterId, true);   
   
   //Step 4! send request
   xhr.send();

}window.onload = function(){
    console.log("in onLoad");
    document.getElementById("starWarsSubmit").addEventListener("click", getCharacter, false);
}