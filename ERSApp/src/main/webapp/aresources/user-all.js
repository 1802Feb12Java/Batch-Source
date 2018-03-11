window.onload = function()
{
	//populate page info
	populateUserRequests();
}

function populateUserRequests()
{
	console.log("starting AJAX request to fill in user reqs");
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
	var url ="/ERSApp/admin/user/get/all";
	console.log("URL: "+url);
	xhr.open("GET", url, true);
	
	xhr.send();
	console.log("sent AJAX request");
};
function fillInFields(requests)
{
	var reqTbl = document.getElementById("requestTable");
	console.log("request JSON "+requests);
	
	//get all keys!
	 var keys = [];
	   for(var k in requests[0]) keys.push(k);
	
	//go through each row and add it
	for(let i = 0; i < requests.length; i++) {
	    var req = requests[i];
	    console.log(req);
	    var r = reqTbl.insertRow(0);
	    
	    //FILL IN ROW
	    for(let i=0; i< keys.length; i++)
		{
			let c = r.insertCell(i);
			//set col value in row
			//set status color
			if(keys[i]=='status')
				c.className = getStatusClass(req[keys[i]]);
		    c.innerHTML = req[keys[i]];
		}
	    
	    console.log("row done "+r);
	}
	
	//lastly put in TH
	var header = reqTbl.createTHead();
	var hRow = header.insertRow(0);
	for(let i=0; i< keys.length; i++)
	{
		let h = hRow.insertCell(i);
		h.innerHTML = keys[i];
		
	}
	console.log("header done "+hRow)
    
}
function getStatusClass(status)
{
	if(status == 'Denied')
		return 'bg-danger text-white table-hover';
	if(status == 'Pending')
		return 'bg-warning text-white table-hover';
	if(status == 'Approved')
		return 'bg-success text-white table-hover';
	//default
	return 'bg-light text-dark table-hover';
	
}


