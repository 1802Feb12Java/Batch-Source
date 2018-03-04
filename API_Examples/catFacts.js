document.getElementById("randomFactButton").addEventListener("click", function(){
    var req = new XMLHttpRequest();

    req.open("GET", "https://catfact.ninja/fact", true);
    req.send(null);

    req.addEventListener('load', function () {
        if (req.status >= 200 && req.status < 400) {
            var randomFactDiv = document.getElementById("randomFact");
            var responseDataObject = JSON.parse(req.responseText);
            var theFact = JSON.stringify(responseDataObject["fact"])
            var responsePageElement = document.createElement("p");
            responsePageElement.textContent = theFact;
            randomFactDiv.appendChild(responsePageElement);
        }
    });
});