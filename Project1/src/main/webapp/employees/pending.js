function populateTable(data){
    var table = document.getElementById("remtable");
    for(let i = 0; i < data.length; i++){
        var row = table.insertRow(1);
            var cell1 = row.insertCell(0);
            cell1.innerHTML = data[i].type;
            var cell2 = row.insertCell(0);
            cell2.innerHTML = data[i].status;
            var cell3 = row.insertCell(0);
            cell3.innerHTML = data[i].resolver;
            var cell4 = row.insertCell(0);
            cell4.innerHTML = data[i].author;
            var cell5 = row.insertCell(0);
            cell5.innerHTML = data[i].time_resolved;
            var cell6 = row.insertCell(0);
            cell6.innerHTML = data[i].time_submitted;
            var cell7 = row.insertCell(0);
            cell7.innerHTML = data[i].receipt;
            var cell8 = row.insertCell(0);
            cell8.innerHTML = data[i].description;
            var cell9 = row.insertCell(0);
            cell9.innerHTML = data[i].amount;
            var cell10 = row.insertCell(0);
            cell10.innerHTML = data[i].R_ID;

    }
}

function getData(){
    //Step 1! Open XHR
    var xhr = new XMLHttpRequest();
    //Step 2! function to handle readystatechange of response
    xhr.onreadystatechange = function(){
        console.log("Roll tide");
        if(xhr.readyState==4 && xhr.status==200){
            //console.log(xhr.responseText);
            var data = JSON.parse(xhr.responseText);
            populateTable(data);
        }
    }
    //Step 3! Open requests/connections
    xhr.open("GET", "/Project1/Receipt", true);
    //Step 4! Send Request
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    getData();
}

//make multiple ajax calls through the servlet to populate all tables at once, need more methods in servlet
//to call get all reimbursements and get resolved