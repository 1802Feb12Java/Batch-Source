/**
 * 
 */
window.onload = function () {
	//populate welcome jumbotron
	fillInJumboTron();
}

function fillInJumboTron() {
	console.log("starting AJAX request to fill in user name");
	var xhr = new XMLHttpRequest();
	//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var reqFound = JSON.parse(xhr.responseText);
			console.log("Request " + reqFound);
			fillInFields(reqFound);
		}
	};
	var url = "/ERSApp/secure/user/get";
	console.log("URL: " + url);
	xhr.open("GET", url, true);

	xhr.send();
	console.log("sent AJAX request");
};
function fillInFields(user) {
	console.log("user JSON " + user);
	document.getElementById("nameHere").innerHTML = 'Welcome ' + user.firstname + ' ' + user.lastname + '!';

}