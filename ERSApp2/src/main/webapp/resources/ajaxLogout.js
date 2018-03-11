
window.onload = function() {
	///Set up logout Ajax calls
	console.log("setting up Logout btn script");
	document.getElementById("logoutBtn").addEventListener("click", sendAjaxPost, false)
}
	
function sendAjaxPost() {
	console.log("starting AJAX request");
	var xhr = new XMLHttpRequest();
			//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			updatePage(this);
		}
	};
	var url ="/ERSApp/LogoutServlet";
	console.log("URL: "+url);
	xhr.open("POST", url, true);
	
	xhr.send();
	console.log("sent AJAX request");
	
};


function updatePage(xhr)
{
	//expect null response if request posted successfully
	if(!xhr.responseText)
	{
		alert("User is now logged out!");
		location.reload();
	}else
	{
		alert("Error: unable to logout at this time");
	}
	
}