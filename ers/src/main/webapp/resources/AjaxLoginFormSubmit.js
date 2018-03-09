/**
 * 
 */
function getxmlHttpRequest(servletName, formName, responseDiv, responseMsg){
	console.log("This is what I see: " + servletName + formName + responseDiv + responseMsg);
	var form=document.forms[formName];
	console.log("This is what forms shows: " + form);
	console.log("Form length: " + form.length);
	for(var i = 0; i<form.length; i++){
		console.log("Form[" + i + "]= " + form[i].name);
		console.log("Form[" + i + "]= " + form[i].type);
		console.log("Form[" + i + "]= " + form[i].id);
	}
	console.log(form);
}