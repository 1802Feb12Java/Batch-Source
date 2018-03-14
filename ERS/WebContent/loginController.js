document.getElementById("login").addEventListener("click", function() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8080/ERS/login", true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.send("username=" + username + "&password=" + password);
    req.addEventListener("load", function() {
      if (req.status == 400) {
        document.getElementById("errorMessage").hidden = false;
      }
      else if (req.status == 200) {
          if (req.responseText == "MANAGER") {
            window.location.replace("managerDash.html");
          }
          else if (req.responseText == "EMPLOYEE") {
            window.location.replace("empDash.html");
          }
      }
    });
});