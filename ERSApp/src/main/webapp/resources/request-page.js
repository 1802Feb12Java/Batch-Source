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
    var url = "/ERSApp/secure/request/get";
    console.log("Ajax Request to URL: " + url);
    xhr.open("GET", url, true);

    xhr.send();
    console.log("sent AJAX request");
};
function fillInFields(requests) {

    var reqTbl = document.getElementById("requestTable");

    //get all keys!
    var keys = [];
    for (var k in requests[0]) keys.push(k);

    //remove reimId key from key list, don't want to display it
    let index = keys.indexOf('reimId');
    keys.splice(index, 1);

    //go through each row and add a reimbursement
    // 1 Row = 1 Reimbursement JSON Obj
    for (let rowI = 0; rowI < requests.length; rowI++) {
        //get reim obj index
        var req = requests[rowI];
        // console.log(req);
        var r = reqTbl.insertRow(0);

        //FILL IN COLs Values
        for (let colJ = 0; colJ < keys.length; colJ++) {
            //insert cell
            let c = r.insertCell(colJ);

            //set status color if status cell
            if (keys[colJ] == 'status')
                c.className = getStatusClass(req[keys[colJ]]);

            if (keys[colJ] != 'receipt') {
                c.innerHTML = req[keys[colJ]];
            } else {
                //add button to view receipt
                console.log('Adding view button');

                //only add view button if a photo exists
                let picId = req[keys[colJ]];
                if (picId) {

                    let tempId = "button" + rowI;

                    //create button
                    c.innerHTML = '<button class="btn btn-primary btn-xs my-xs-btn"'
                        + ' type="button" id="' + tempId + '">'
                        + ' View</button>';

                    //set on click 
                    document.getElementById(tempId).onclick =
                        function () {
                            //SHOW THE image
                            console.log("Reim id=" + picId);
                            loadImage(picId, tempId);
                            console.log('loaded img');
                        }
                }
                else//pic value =null?
                    console.log('No pic id');

            }
        }
        console.log("row done " + r);
    }

    //lastly put in TH
    var header = reqTbl.createTHead();
    var hRow = header.insertRow(0);
    for (let i = 0; i < keys.length; i++) {
        let h = hRow.insertCell(i);
        h.innerHTML = keys[i];

        //set onclick listener to table header
        let sortCol = (keys.length - i) - 1;
        console.log('setting funtion to sort col ' + sortCol);
        h.onclick = function () { sortTable(sortCol); }

    }
    console.log("header done " + hRow)

};

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

    xhr.onreadystatechange = function () {
        // console.log(this.readyState);
        if (this.readyState == 4 && this.status == 200) {
            var reqFound = xhr.responseText;
            //console.log("Request " + reqFound);
            //see if an image was found to return
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
    console.log("URL: " + url);
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
    console.log(replaced);

    replaced.parentNode.replaceChild(pic, replaced);
    console.log("if you are seeing this, there should be an image loaded")


}
///////FILTERING FUNCTION
//Sort table function from W3 Schools
//https://www.w3schools.com/howto/howto_js_sort_table.asp
function sortTable(colNum) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;

    var reqTbl = document.getElementById("requestTable");
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
