
resultDiv = document.getElementById("resultCell");
resultElements = document.getElementsByClassName("results");
var modalButtons = document.getElementsByClassName("updateInfoBtn")
for (var i = 0; i < modalButtons.length; i++) {
    modalButtons[i].addEventListener("click", sendUpdate);
}
var passwordUpdateBtn = document.getElementById("passwordUpdateBtn");
passwordUpdateBtn.addEventListener("click", updatePassword)

var req = new XMLHttpRequest();
req.open("GET", "http://localhost:8080/ERS/EmployeeInfo", true);
req.send(null);
req.addEventListener("load", function () {
    if (req.status >= 200 && req.status < 300) {
        var results = req.responseText;
        results = JSON.parse(results);
        document.getElementById("firstName").textContent = results.firstName;
        document.getElementById("lastName").textContent = results.lastName;
        document.getElementById("email").textContent = results.email;
        document.getElementById("userName").textContent = results.userName;
    }
});

function sendUpdate() {

    var fieldToChange = this.id;
    var newValue = document.getElementById("newValue").value;
    var req = new XMLHttpRequest();
    var params = "?column=" + fieldToChange + "&newValue=" + newValue;
    req.open("PUT", "http://localhost:8080/ERS/EmployeeInfo" + params, true);
    req.send(null);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 300) {
            resultDiv.textContent = "Information Sucessfully Updated. Refresh to see changes.";
            resultDiv.classList.add("bg-success", "text-white");
            for (var i = 0; i < resultElements.length; i++) {
                resultElements[i].hidden = false;
            }

            if (req.status == 401) {
                resultDiv.textContent = "ERROR. Email is already in use.";
                resultDiv.classList.add("bg-danger", "text-white");
                for (var i = 0; i < resultElements.length; i++) {
                    resultElements[i].hidden = false;
                }
            }
            if (req.status == 402) {
                resultDiv.textContent = "ERROR. Username is already in use.";
                resultDiv.classList.add("bg-danger", "text-white");
                for (var i = 0; i < resultElements.length; i++) {
                    resultElements[i].hidden = false;
                }
            }
        }
    });
}

function updatePassword() {
    var passwordInput = document.getElementById("passwordUpdateField");
    var passwordConfirm = document.getElementById("passwordConfirm");

    if (passwordInput.value !== passwordConfirm.value) {
        resultDiv.textContent = "ERROR. Passwords do not match.";
        resultDiv.classList.add("bg-danger", "text-white");
        for (var i = 0; i < resultElements.length; i++) {
            resultElements[i].hidden = false;
        }
    }
    else {
        var fieldToChange = "password"
        var newValue = passwordConfirm.value;
        var req = new XMLHttpRequest();
        var params = "?column=" + fieldToChange + "&newValue=" + newValue;
        req.open("PUT", "http://localhost:8080/ERS/EmployeeInfo" + params, true);
        req.send(null);
        req.addEventListener("load", function () {
            if (req.status >= 200 && req.status < 300) {
                resultDiv.textContent = "Password Changed Sucessfully.";
                resultDiv.classList.add("bg-success", "text-white");
                for (var i = 0; i < resultElements.length; i++) {
                    resultElements[i].hidden = false;
                }
            }
        });
    }

}
