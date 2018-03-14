window.onload = function () {
	//populate page info
	populateUserRequests();
}

function populateUserRequests() {
	console.log("starting AJAX request to fill in user reqs");
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
	var url = "/ERSApp/admin/user/get/all";
	console.log("URL: " + url);
	xhr.open("GET", url, true);

	xhr.send();
	console.log("sent AJAX request");
};
function fillInFields(users) {
	var reqTbl = document.getElementById("requestTable");

	//get all keys!
	// var keys = [];
	// for (var k in users[0]) keys.push(k);
	// console.log('Keys '+keys);

	//go through each row and add it
	for (let i = 0; i < users.length; i++) {
		var req = users[i];

		var r = reqTbl.insertRow(0);

		//FILL IN Cols
		//Col 1 FName
		let fName = r.insertCell(0);
		fName.innerHTML = req["firstname"];
		//Col 2 LName
		let lName = r.insertCell(1);
		lName.innerHTML = req["lastname"];
		//Col 3 email
		let email = r.insertCell(2);
		email.innerHTML = req["email"];
		//Col 4 username
		let username = r.insertCell(3);
		username.innerHTML = req["username"];


	}

	//Manual Headers
	var header = reqTbl.createTHead();
	header.className = 'thead-light';
	var hRow = header.insertRow(0);

	//Col 1 FName
	let fNameH = hRow.insertCell(0);
	fNameH.innerHTML = 'First Name';
	fNameH.onclick = function () { sortTable(0); }
	//Col 2 LName
	let lNameH = hRow.insertCell(1);
	lNameH.innerHTML = 'Last Name';
	lNameH.onclick = function () { sortTable(1); }
	//Col 3 email
	let emailH = hRow.insertCell(2);
	emailH.innerHTML = 'Email';
	emailH.onclick = function () { sortTable(2); }
	//Col 4 username
	let usernameH = hRow.insertCell(3);
	usernameH.innerHTML = 'Username'
	usernameH.onclick = function () { sortTable(3); }


	//OLD APPROACH
	// //lastly put in TH
	// var header = reqTbl.createTHead();
	// var hRow = header.insertRow(0);
	// for (let i = 0; i < keys.length; i++) {
	// 	let h = hRow.insertCell(i);
	// 	h.innerHTML = keys[i];

	// 	//set onclick listener to table header
	// 	let sortCol = (i);
	// 	console.log('setting funtion to sort col ' + sortCol);
	// 	h.onclick = function () { sortTable(i); }

	// }

}


///////FILTERING FUNCTION
//Sort table function from W3 Schools
//https://www.w3schools.com/howto/howto_js_sort_table.asp
function sortTable(colNum) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;

	console.log("SORTING COL" + colNum);
	switching = true;
	// Set the sorting direction to ascending:
	var reqTbl = document.getElementById("requestTable");

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