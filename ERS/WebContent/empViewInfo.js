
var req = new XMLHttpRequest();
req.open("GET", "http://localhost:8080/ERS/EmployeeInfo", true);
req.send(null);
req.addEventListener("load", function() {
    if (req.status >= 200 && req.status < 300) {
    var results = req.responseText; 
    results = JSON.parse(results);
    document.getElementById("firstName").textContent = results.firstName;
    document.getElementById("lastName").textContent = results.lastName;
    document.getElementById("email").textContent = results.email;
    document.getElementById("userName").textContent = results.userName;
    }
});
