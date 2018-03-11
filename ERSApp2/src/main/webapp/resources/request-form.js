/**
 * 
 */
var submitBtn = document.getElementById("submitBtn");
window.onload = function()
{
	//TODO xml configure servlet properties?
//	submitBtn.onclick = sendAjaxPost("/ERSApp/RequestFormServlet", updatePage);
}

function sendAjaxPost(url, func) {
	console.log("Creating ajax post")
	var xhr = new XMLHttpRequest();
			//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	console.log("URL: "+url);
	xhr.open("POST", url, true);
	//get form data
	//create form data obj to append fields to
	var formData = new FormData(document.getElementById("requestForm"));
//	formData.append("amount",document.getElementById("fAmount"));
//	formData.append("description",document.getElementById("fDescription"));
//	formData.append("type",document.getElementById("fType"));
//	//blobs are special
//	var blob = new Blob
//	formData.append("receipt",document.getElementById("fRecipt"));
	
	
	
	//send json
	console.log("FORM DATA: "+formData);
//	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(formData);
	
	//disable submit button
//	submitBtn.disabled = true;
};


function updatePage(xhr)
{
	//expect null response if request posted successfully
	if(xhr.responseText)
	{
		alert("Reimbursement Request was sent!");
		//location.reload();
	}else
	{
		alert("Error: unable to submit request at this time");
	}
	
}
///////////Refresh prompt?//////////////////////
//window.onbeforeunload = function() {
//    return 'You have unsaved changes!?';
//}

