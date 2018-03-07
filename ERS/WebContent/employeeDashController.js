

var cookieFromServer = document.cookie

function parseCookie(key) {
    var regexp = new RegExp("(?:^" + key + "|;\s*"+ key + ")=(.*?)(?:;|$)", "g");
    var result = regexp.exec(cookieFromServer);
    return (result === null) ? null : result[1];
  }

document.getElementById("userName").textContent = parseCookie("firstName");