document.getElementById("viewPeopleButton").addEventListener("click", function(){
    var req = new XMLHttpRequest();
    var idNumber = document.getElementById("personIdSearch").value;
    req.open("GET", "http://localhost:8080/ServletDemo/myfirstservlet?idnumber=" + idNumber, true);
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
    var idNumber = document.getElementById("personIdSearch").value;
    req.open("DELETE","http://localhost:8080/ServletDemo/myfirstservlet?idnumber=" + idNumber, true );
    req.send(null);
    req.addEventListener("load", function() {
        if (req.status >= 200 && req.status < 300) {
            alert("Person Deleted!");
        }
    }); 
});

document.getElementById("updatePeopleButton").addEventListener("cick", function() {
    var req = new XMLHttpRequest();
    var idNumber = document.getElementById("personIdSearch").value;
    req.open("PUT", "http://localhost:8080/ServletDemo/myfirstservlet?idnumber=" + idNumber, true);
    req.send(null);
    req.addEventListener("load", function() {
        if (req.status >= 200 && req.status <300) {
            alert("person updated");
        }
    })
});