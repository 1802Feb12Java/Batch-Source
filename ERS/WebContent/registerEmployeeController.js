document.getElementById("registerEmployee").addEventListener("click", registerEmployee);

function registerEmployee() {
    var firstName = document.getElementById("firstNameRegister").value;
    var lastName = document.getElementById("lastNameRegister").value;
    var email = document.getElementById("emailRegister").value;
    var userName = document.getElementById("userNameRegister").value;

    //open HTTP request to send POST
    var req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8080/ERS/ManagerRequestEmployeesServlet", true);
    //payload = JSON.stringify(payload);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    payload = "firstName=" + firstName +"&lastName=" + lastName + "&email=" + email + "&username=" + userName;
    req.send(payload);
    req.addEventListener("load", function() {
        if (req.status >= 200 && req.status < 300) {
            alert("Employee Sucessfully Registered");
            document.getElementById("firstNameRegister").value = "";
            document.getElementById("lastNameRegister").value = "";
            document.getElementById("emailRegister").value = "";
            document.getElementById("userNameRegister").value = "";
        }
        else if (req.status == 401) {
            alert("Username already taken. Please choose a different one.");
            document.getElementById("userNameRegister").value = "";
        }
        else if (req.status == 402) {
            alert("Email already taken. Please choose a different one.");
            document.getElementById("emailRegister").value = "";
        }


    });
}
