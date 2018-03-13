document.getElementById("login").addEventListener("click", function() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8080/ERS/login", true);
    req.send("username=" + username + "&password=" + password);
    req.addEventListener("load", function() {
      if (req.status == 400) {
        document.getElementById("errorMessage").hidden = false;
      }
    });
});