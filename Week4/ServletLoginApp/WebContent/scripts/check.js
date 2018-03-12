
function sendAjaxGet(url, func) {
	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("GET", url, true);
	xhr.send();
};

function populateUser(xhr) {
	var res = JSON.parse(xhr.responseText);
	if (res.username != "null") { //error checking could be improved 
		document.getElementById("user").innerHTML = "you are logged in as "
				+ res.username;
	} else {
		window.location = "http://localhost:8082/ServletLoginApp/login";
	}
}

window.onload = function() {
	sendAjaxGet("http://localhost:8082/ServletLoginApp/session", populateUser);
}