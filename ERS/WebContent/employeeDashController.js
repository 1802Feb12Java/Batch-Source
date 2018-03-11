

var cookieFromServer = document.cookie

function parseCookie(key) {
    var regexp = new RegExp("(?:^" + key + "|;\s*"+ key + ")=(.*?)(?:;|$)", "g");
    var result = regexp.exec(cookieFromServer);
    return (result === null) ? null : result[1];
  }

document.getElementById("userName").textContent = parseCookie("firstName");

function fillInCards() {
  var req = new XMLHttpRequest();
  req.open("GET", "http://localhost:8080/ERS/empDash", true);
  req.send(null);
  req.addEventListener("load", function() {
    if (req.status >= 200 && req.status < 300) {
      var results = req.responseText; 
      results = JSON.parse(results);
      document.getElementById("pending").textContent = results.pending;
      document.getElementById("totalRequests").textContent = results.total;
      document.getElementById("approved").textContent = results.approved;
    }
  });
}
fillInCards();