//GLOBAL VAR :D
var statusColNum = -1;
var reqTbl = document.getElementById("requestTable");

window.onload = function () {
	//populate page info
	populateUserRequests();
}


function populateUserRequests() {
	console.log("starting AJAX request to fill in user reqs");
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function () {

		if (this.readyState == 4 && this.status == 200) {
			var reqFound = JSON.parse(xhr.responseText);

			fillInFields(reqFound);
		}
	};
	var url = "/ERSApp/admin/request/get/all";
	console.log("Ajax Request to URL: " + url);
	xhr.open("GET", url, true);

	xhr.send();

	console.log("sent AJAX request");
};

//generates table via ajax info received
function fillInFields(requests) {
	//get all the keys!
	var keys = [];
	for (var k in requests[0]) keys.push(k);

	//remove reimId key from list, don't want to display it
	let index = keys.indexOf('reimId');
	keys.splice(index, 1);
	console.log("Obj keys setting: " + keys);

	//go through each row and add it
	for (var rowI = 0; rowI < requests.length; rowI++) {
		var req = requests[rowI];
		var r = reqTbl.insertRow(rowI);

		//FILL IN ROW
		for (let colJ = 0; colJ < keys.length; colJ++) {
			var c = r.insertCell(0);
			//set col value in row
			//set status color
			if (keys[colJ] == 'status') {

				let statusVal = req[keys[colJ]];
				c.className = getStatusClass(statusVal);
				//set which column the status is in
				statusColNum = (keys.length - colJ) + 1;

				c.innerHTML = statusVal;

				//BUTTONS!
				//putting in two buttons for approve/deny
				//after status col
				//make a new cell for appr/deny buttons
				let e = r.insertCell(0);

				//only show/make if request has a 'pending' status
				if (statusVal == 'Pending') {
					let approveBtn = "ApproveButton" + colJ;
					let denyBtn = "denyButton" + colJ;

					//make approve/deny buttons
					e.innerHTML = '<button class="btn btn-success btn-xs my-xs-btn"'
						+ ' type="button" id="' + approveBtn + '">'
						+ 'Approve</button> <button class="btn btn-danger btn-xs my-xs-btn"'
						+ ' type="button" id="' + denyBtn + '">'
						+ 'Deny</button>';

					//get reim id
					let reimId = req["receipt"];

					let rowN = (rowI + 1);
					//set button listeners
					document.getElementById(approveBtn).onclick =
						function () {
							onRowClick(reimId, rowN, "Approved");

						};
					document.getElementById(denyBtn).onclick =
						function () {
							onRowClick(reimId, rowN, "Denied");
						};
				}
			}//add button for receipt col - to view image
			else if (keys[colJ] == 'receipt') {//add button into
				console.log("Receipt id=" + req[keys[colJ]]);

				//show 'view' button if id exists
				if (req[keys[colJ]] != null) {
					let tempId = "button" + rowI;

					//make view button
					c.innerHTML = '<button class="btn btn-primary btn-xs my-xs-btn"'
						+ ' type="button" id="' + tempId + '">'
						+ ' View</button>';


					let imageId = req[keys[colJ]];

					//set onclick event to replace button with image
					document.getElementById(tempId).onclick =
						function () {
							//SHOW THE image
							console.log("Image id=" + imageId);
							loadImage(imageId, tempId);
							console.log('loaded img');

						}
				};
			}
			else {
				c.innerHTML = req[keys[colJ]];
			}
		}

	}

	//lastly put in TH with Filter Clicks
	let header = reqTbl.createTHead();
	let hRow = header.insertRow(0);

	for (let k = 0; k < keys.length; k++) {
		let h = hRow.insertCell(0);
		h.innerHTML = keys[k];

		//set onclick listener to table header
		let sortCol = (keys.length - k) - 1;
		console.log('setting funtion to sort col ' + sortCol);
		h.onclick = function () { sortTable(sortCol); }
	}
	//blank cell for approve/deny
	hRow.insertCell(0);

}

//Updates reimId and updates status col
function onRowClick(reimId, rowNum, decision) {
	//make and ajax call to update reimbursemetn status
	console.log("starting AJAX request to fill in user reqs");

	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function () {

		if (this.readyState == 4 && this.status == 200) {
			console.log("Response " + xhr.responseText);

			//successful, update row color
			console.log("Updating row " + rowNum + ", Col " + statusColNum);

			let cellUpdate = reqTbl.rows[rowNum].cells[statusColNum];

			//set column attributes
			cellUpdate.className = getStatusClass(decision);
			cellUpdate.innerHTML = decision;

			console.log("updated cell status" + cellUpdate);
		}
	};

	let url = "/ERSApp/admin/request/post/" + decision;//send approved in url to set status
	console.log("POST AJAX Call to URL: " + url);
	xhr.open("POST", url, true);
	console.log("Sending reimId " + reimId);
	//set type
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//application/x-www-form-urlencoded Content Type
	//	let tempJson = '{ "reimId":' + reimId+ '}';
	//	console.log(tempJson);
	//	xhr.send(tempJson);
	let formData = "reimId=" + reimId;
	console.log(formData);
	xhr.send(formData);
	console.log("sent AJAX request");


}
function getStatusClass(status) {
	if (status == 'Denied')
		return 'bg-danger text-white table-hover';
	if (status == 'Pending')
		return 'bg-warning text-white table-hover';
	if (status == 'Approved')
		return 'bg-success text-white table-hover';
	//default
	return 'bg-light text-dark table-hover';

}

function loadImage(imgId, id) {

	console.log("starting AJAX request to fill pic");
	var xhr = new XMLHttpRequest();
	//|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		// console.log(this.readyState);
		if (this.readyState == 4 && this.status == 200) {
			var reqFound = xhr.responseText;

			//fill in photo if exists
			if (reqFound) { fillInPhoto(reqFound, id); }
			else {
				//if not image found, just remove button
				let badge = document.createElement("a");
				badge.className = 'badge';
				badge.innerHTML = 'No image';

				let elem = document.getElementById(id);
				elem.parentElement.replaceChild(badge, elem);

				console.log('no image found for receipt');
			}

		}
	};
	var url = "/ERSApp/PhotoServlet/get/" + imgId;
	console.log("GET Ajax Request to URL: " + url);
	xhr.open("GET", url, true);

	xhr.send();
	console.log("sent AJAX request for pic");

}
function fillInPhoto(img, id) {
	console.log("Filling in img");
	let pic = document.createElement("img");
	pic.src = 'data:image/jpeg;base64,' + img;

	console.log("ID replacing=" + id);
	let replaced = document.getElementById(id);
	console.log("replacing node " + replaced + " \n\twith pic");

	replaced.parentNode.replaceChild(pic, replaced);
	console.log("Image loaded")


}
///////FILTERING FUNCTION
//Sort table function from W3 Schools
//https://www.w3schools.com/howto/howto_js_sort_table.asp
function sortTable(colNum) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;

	console.log("SORTING COL" + colNum);
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
				//pic case
				if (!x) return;

				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					// If so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					// If so, mark as a switch and break the loop:
					shouldSwitch = true;
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
			switchcount++;
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


