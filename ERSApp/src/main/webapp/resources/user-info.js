
window.onload = function()
{
	//populate user info
	fillInUserInfo();
	
	//overwrite form submit
//	var form = document.getElementById("userForm");
//	form.onsubmit = ajaxPost;
	
	
}
function fillInUserInfo()
{
	console.log("starting AJAX request to fill in user info");
	var xhr = new XMLHttpRequest();
			//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var reqFound = JSON.parse(xhr.responseText);
		    console.log("Request " + reqFound);
		    fillInFields(reqFound);
		}
	};
	var url ="/ERSApp/secure/user/get";
	console.log("URL: "+url);
	xhr.open("GET", url, true);
	
	xhr.send();
	console.log("sent AJAX request");
};
function fillInFields(user)
{
	console.log("user JSON "+user);
	document.getElementById("username").value = user.username;
	document.getElementById("firstname").value = user.firstname;
	document.getElementById("lastname").value = user.lastname;
	document.getElementById("email").value = user.email;
    
}

function ajaxPost()
{
		//submit via ajax
		console.log("starting AJAX request");
		var xhr = new XMLHttpRequest();
				//|| new ActiveXObject("Microsoft.HTTPRequest");
		xhr.onreadystatechange = function() {
			// console.log(this.readyState);
			if (this.readyState == 4 && this.status == 200) {
				updatePage(this);
			}
		};
		var url ="/ERSApp/EditUserServlet";
		console.log("URL: "+url);
		//get form data
		var formData = new FormData(document.getElementById("userForm"));
		xhr.open("POST", url, true);
		console.log("form data ="+formData);
		xhr.send(formData);
		console.log("sent AJAX request");
		
}


function updatePage(xhr)
{
//	//expect null response if request posted successfully
//	if(!xhr.responseText)
//	{
//		alert("User info has been update successfully!");
//		location.reload();
//	}else
//	{
//		alert("Error: unable to update user at this time");
//	}
	
}
