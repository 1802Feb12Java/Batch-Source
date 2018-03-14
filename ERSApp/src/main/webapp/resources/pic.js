window.onload = function()
{
	//img
	loadImage();
}

//Global
var postCallRequests;

function loadImage()
{

	console.log("starting AJAX request to fill pic");
	var xhr = new XMLHttpRequest();
			//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var reqFound = xhr.responseText;
		    console.log("Request " + reqFound);
		    fillInPhoto(reqFound);
		}
	};
	var url ="/ERSApp/PhotoServlet/get";
	console.log("URL: "+url);
	xhr.open("GET", url, true);
	
	xhr.send();
	console.log("sent AJAX request -pic");
	
}
function fillInPhoto(img)
{
	console.log("Filling in img");
	document.getElementById("target").src = 'data:image/jpeg;base64,'+img;
	
	var largeImage = document.getElementById('target');
	   largeImage.style.display = 'block';
	   largeImage.style.width=200+"px";
	   largeImage.style.height=200+"px";
	   var url=largeImage.getAttribute('src');
	   window.open(url,'Image','width=largeImage.stylewidth,height=largeImage.style.height,resizable=1');

	
	
}


//MODAL
//document.getElementById("pop").onclick = function(){
//	document.getElementById("imagepreview").src = document.getElementById("imageresource").src;
//	document.getElementById("imagemodal").show();
//};
//	
//
//function viewImage(row)
//{
//	//fetch image from json
//	console.log("clicked row "+row);
//	let req = postCallRequests[row];
//	console.log("request grabbed: ");
//	let pic = req["receipt"];
//	console.log("PIC "+pic);
//	
//	
//}

