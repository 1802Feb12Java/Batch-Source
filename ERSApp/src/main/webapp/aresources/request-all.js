//GLOBAL VAR :D
var statusColNum = -1;
var reqTbl = document.getElementById("requestTable");

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
	var url ="/ERSApp/admin/request/get/all";
	console.log("URL: "+url);
	xhr.open("GET", url, true);
	
	xhr.send();
	console.log("sent AJAX request");
};

//generates table via ajax info received
function fillInFields(requests)
{
	console.log("request JSON "+requests);
	
	//get all keys!
	 var keys = [];
	   for(var k in requests[0]) keys.push(k);
	   
	//remove reimId key
	let index = keys.indexOf('reimId');
	keys.splice(index, 1);
	console.log("keys: "+keys);
	
	//go through each row and add it
	for(var rowI = 0; rowI < requests.length; rowI++) {
	    var req = requests[rowI];
	    console.log(req);
	    var r = reqTbl.insertRow(rowI);
	    
	    //FILL IN ROW
	    for(let colJ = 0; colJ< keys.length; colJ++)
		{
	    	console.log("row check 1 ="+colJ);
			var c = r.insertCell(0);
			//set col value in row
			//set status color
			if(keys[colJ]=='status'){
				
				let statusVal = req[keys[colJ]];
				c.className = getStatusClass(statusVal);
				//set which column the status is in
				statusColNum = colJ+1;
				
				//BUTTONS!
				//putting in two buttons for approve/deny
				//after status col
				console.log('making buttons')
				c.innerHTML = statusVal;
				
				//make a new cell for appr/deny buttons
				let e = r.insertCell(0);
				
				//only show/make if pending
				if(statusVal == 'Pending'){
					let approveBtn = "ApproveButton" + colJ;
					let denyBtn = "denyButton" + colJ;
					
					e.innerHTML = '<button class="btn btn-success btn-xs my-xs-btn"'
						+ ' type="button" id="'+approveBtn+'">'
					    + 'Approve</button> <button class="btn btn-danger btn-xs my-xs-btn"'
						+ ' type="button" id="'+denyBtn+'">'
					    + 'Deny</button>';
					
					//get reim id
					let reimId = req["receipt"];
					
					let rowN = (keys.length - rowI + 1);
					//set button listeners
					document.getElementById(approveBtn).onclick = 
					function(){
						console.log("row num? "+rowN);
						onRowClick(reimId,  rowN, "approved");
						
					};
					document.getElementById(denyBtn).onclick = 
						function(){
						console.log("row num? "+rowN);
						onRowClick(reimId, rowN, "denied");
					};
				}
				//normal cell data input
			}
			else if(keys[colJ] == 'receipt'){//add button into
				console.log('**recipt part***')
				console.log(req[keys[colJ]]);
				
				if(req[keys[colJ]] != null){
					let tempId = "button"+rowI;
					
					c.innerHTML = '<button class="btn btn-primary btn-xs my-xs-btn"'
						+ ' type="button" id="'+tempId+'">'
					    + ' View</button>';
					console.log('ROW 2 check = '+rowI)
					let imageId = req[keys[colJ]];
					document.getElementById(tempId).onclick = 
						function(){
							//SHOW THE image
							console.log("Image id="+imageId);
							loadImage(imageId, tempId);
							console.log('loaded img');
						
					}
				};
			}
			else{
				c.innerHTML = req[keys[colJ]];
			}
		}
	    
//	    let x = requests.length - i;
//	    console.log("Passing i as.. "+x);
//	    r.onclick = function(){onRowClick(req["reimId"], x);};
	    
	}
	
	//lastly put in TH with Filter Clicks
	let header = reqTbl.createTHead();
	let hRow = header.insertRow(0);
	
	for(let k=0; k< keys.length; k++)
	{
		let h = hRow.insertCell(0);
		h.innerHTML = keys[k];
	
		//set onclick listener to table header
		h.onclick = function() { sortTable( i );}
	}
	//blank cell for approve/deny
	hRow.insertCell(0);
    
}
function onRowClick(reimId, rowNum, decision)
{
	//make and ajax call to update reimbursemetn status
	console.log("starting AJAX request to fill in user reqs");
	var xhr = new XMLHttpRequest();
			//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
		    console.log("Response " + xhr.responseText);
		    
		    //successful, update row color
		    console.log(rowNum);
		    let colU = reqTbl.rows[rowNum].cells[statusColNum];
		    //set column attributes
		    colU.className = getStatusClass("Approved"); 
		    colU.innerHTML = 'Approved';
		    console.log("updated row status");
		}
	};
	let url ="/ERSApp/admin/request/post/"+decision;//send approved in url to set status
	console.log("URL: "+url);
	xhr.open("POST", url, true);
	console.log("Sending reimId "+reimId);
	//set type
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//application/x-www-form-urlencoded Content Type
//	let tempJson = '{ "reimId":' + reimId+ '}';
//	console.log(tempJson);
//	xhr.send(tempJson);
	let formData = "reimId="+reimId;
	console.log(formData);
	xhr.send(formData);
	console.log("sent AJAX request");
	
	
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
///////FILTERING FUNCTIONS

//Sort table function from W3 Schools
//https://www.w3schools.com/howto/howto_js_sort_table.asp
function sortTable(colNum) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  
  switching = true;
  // Set the sorting direction to ascending:
  
  dir = "asc"; 
  
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = reqTbl.getElementsByTagName("TR");
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[colNum];
      y = rows[i + 1].getElementsByTagName("TD")[colNum];
      /* Check if the two rows should switch place,
      based on the direction, asc or desc: */
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++; 
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}
function loadImage(imgId, id)
{

	console.log("starting AJAX request to fill pic");
	var xhr = new XMLHttpRequest();
			//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var reqFound = xhr.responseText;
		    //console.log("Request " + reqFound);
		    fillInPhoto(reqFound, id);
		}
	};
	var url ="/ERSApp/PhotoServlet/get/"+imgId;
	console.log("URL: "+url);
	xhr.open("GET", url, true);
	
	xhr.send();
	console.log("sent AJAX request -pic");
	
}
function fillInPhoto(img, id)
{
	console.log("Filling in img");
	let pic = document.createElement("img");
	pic.src = 'data:image/jpeg;base64,'+img;
	
	console.log("ID replacing="+id);
	let replaced = document.getElementById(id);
	console.log(replaced);
	
	replaced.parentNode.replaceChild(pic, replaced);
	console.log("if you are seeing this, there should be an image")
	
	
}



