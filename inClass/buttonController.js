document.getElementById("viewPeopleButton").addEventListener("click", function(){
    var req = new XMLHttpRequest();

    req.open("GET", "http://localhot:8080/myFirstServlet", true);
    req.send(null);

    req.addEventListener('load', function () {
        if (req.status >= 200 && req.status < 300) {
            var resultDiv = document.getElementById("personSearchResult");
            var thePerson = req.responseText;
            var responsePageElement = document.createElement("p");
            responsePageElement.textContent = thePerson;
            resultDiv.appendChild(responsePageElement);
        }
    });
});
