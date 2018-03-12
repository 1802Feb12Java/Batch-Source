var empTable = document.getElementById("mgrViewEmployeeTable");

var req = new XMLHttpRequest();
req.open("GET", "http://localhost:8080/ERS/ManagerRequestEmployeesServlet", true);
req.send(null);

req.addEventListener("load", function() {
    if (req.status >= 200 && req.status < 300) {
        results = req.responseText;
        results = JSON.parse(results);

        for (var i = 0; i < results.length; i++) {
            var newRow = document.createElement("tr");
            var fName = document.createElement("td");
            var lName = document.createElement("td");
            var email = document.createElement("td");
            var userName = document.createElement("td");
            
            fName.textContent = results[i].firstName;
            lName.textContent = results[i].lastName;
            email.textContent = results[i].email;
            userName.textContent = results[i].userName;

            newRow.appendChild(fName);
            newRow.appendChild(lName);
            newRow.appendChild(email);
            newRow.appendChild(userName);
            empTable.appendChild(newRow);
        }
    }
});