function loadMTGCard(result){
    console.log(result.card);
    document.getElementById("name").innerHTML = result.card.name;
    document.getElementById("mana").innerHTML = result.card.cmc + ': ' + result.card.manaCost;
    document.getElementById("type").innerHTML = result.card.type;
    document.getElementById("power").innerHTML = "Power/Toughness: " +result.card.power + '/' +result.card.toughness;
    document.getElementById("originalText").innerHTML = result.card.originalText;
    document.getElementById("layout").innerHTML = result.card.layout;
    document.getElementById("cardImage").src = result.card.imageUrl;
}

function getCard(){
   console.log("hi nerd");
   var cardId= document.getElementById("cardId").value;
   //Step 1! Open XHR
   var xhr = new XMLHttpRequest();
   //Step 2! function to handle readystatechange of response
   xhr.onreadystatechange= function(){
       if(xhr.readyState==4 && xhr.status== 200){
            // console.log(xhr.XMLHttpRequest)
            var result = JSON.parse(xhr.responseText);
           loadMTGCard(result);
       } 
   }
   //Step 3! Open request/connection
   xhr.open("GET","https://api.magicthegathering.io/v1/cards/" + cardId, true);

   //Step 4! send request
   xhr.send();
}

window.onload = function(){
    console.log("in onLoad");
    document.getElementById("cardSubmit").addEventListener("click", getCard, false);    
}
