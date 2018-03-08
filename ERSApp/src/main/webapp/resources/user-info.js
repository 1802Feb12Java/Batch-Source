
window.onload = function()
{
	//overwrite form submit
	var form = document.getElementById("userForm");
	form.onsubmit = ajaxPost;
	
	
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
		
		xhr.send(formData);
		console.log("sent AJAX request");
		
		
}


function updatePage(xhr)
{
	//expect null response if request posted successfully
	if(!xhr.responseText)
	{
		alert("User info has been update successfully!");
		location.reload();
	}else
	{
		alert("Error: unable to update user at this time");
	}
	
}
