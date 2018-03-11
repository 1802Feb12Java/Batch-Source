
firstName = document.getElementById("firstName");
lastName = document.getElementById("lastName");
email = document.getElementById("email");
userName = document.getElementById("userName");
theButton = document.getElementById("confirmInfo").addEventListener("click", sendData);

function fillInFields() {
    var req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/ERS/EmployeeInfo", true);
    req.send(null);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 300) {
            var results = req.responseText;
            results = JSON.parse(results);
            firstName.value = results.firstName;
            lastName.value = results.lastName;
            email.value = results.email;
            userName.value = results.userName;
        }
    });
}

fillInFields();

function sendData() {
    var req = new XMLHttpRequest();
    var params = "?firstName=" + firstName.value + "&lastName=" + lastName.value + "&email=" + email.value + "&userName=" + userName.value;
    req.open("PUT", "http://localhost:8080/ERS/EmployeeInfo" + params, true);
    req.send(null);
    req.addEventListener("load", function() {
        if (req.status >= 200 && req.status <300) {
            var results = req.responseText;
            if (results == "SUCCESS") {
                document.getElementById("success").removeAttribute("hidden");
            }
            if (results == "FAILURE") {
                document.getElementById("failure").removeAttribute("hidden");
            }
        }
    });
}