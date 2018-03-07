document.getElementById("viewPeopleButton").addEventListener("click", function(){
    var req = new XMLHttpRequest();
    var idNumber = document.getElementById("idnumber").textContent;
    req.open("GET", "http://localhot:8080/myFirstServlet?idnumber=" + idNumber, true);
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


document.getElementById("deletePersonButton").addEventListener("click", function(){
    var req = new XMLHttpRequest();
    var idNumber = document.getElementById("idnumber").textContent;
     
})