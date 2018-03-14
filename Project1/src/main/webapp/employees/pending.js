//populates the pending table
function populateTable(data){
    var table = document.getElementById("remtable");
    console.log(data);
    for(let i = 0; i < data.length; i++){
        var row = table.insertRow(1);
            var cell1 = row.insertCell(0);
            cell1.innerHTML = data[i] ["type"];
            var cell2 = row.insertCell(0);
            cell2.innerHTML = data[i] ["status"];
            var cell3 = row.insertCell(0);
            cell3.innerHTML = data[i] ["resolver"];
            var cell4 = row.insertCell(0);
            cell4.innerHTML = data[i] ["author"];
            var cell5 = row.insertCell(0);
            cell5.innerHTML = data[i] ["time_resolved"];
            var cell6 = row.insertCell(0);
            cell6.innerHTML = data[i] ["time_submitted"];
            var cell7 = row.insertCell(0);
            cell7.innerHTML = data[i] ["receipt"];
            var cell8 = row.insertCell(0);
            cell8.innerHTML = data[i] ["description"];
            var cell9 = row.insertCell(0);
            cell9.innerHTML = data[i] ["amount"];
            var cell10 = row.insertCell(0);
            cell10.innerHTML = data[i] ["R_ID"];

    }
}

function getData(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            var data = JSON.parse(xhr.responseText);
            populateTable(data);
        }
    }
    xhr.open("GET", "/Project1/Receipt", true);
    xhr.send();
}

//populates the table with all reimbursements by this user
function populateTableAll(data){
    console.log("this is my alltable");
    console.log(data);
    var table = document.getElementById("alltable");
    for(let i = 0; i < data.length; i++){
        var row = table.insertRow(1);
            var cell1 = row.insertCell(0);
            cell1.innerHTML = data[i] ["type"];
            var cell2 = row.insertCell(0);
            cell2.innerHTML = data[i] ["status"];
            var cell3 = row.insertCell(0);
            cell3.innerHTML = data[i] ["resolver"];
            var cell4 = row.insertCell(0);
            cell4.innerHTML = data[i] ["author"];
            var cell5 = row.insertCell(0);
            cell5.innerHTML = data[i] ["time_resolved"];
            var cell6 = row.insertCell(0);
            cell6.innerHTML = data[i] ["time_submitted"];
            var cell7 = row.insertCell(0);
            cell7.innerHTML = data[i] ["receipt"];
            var cell8 = row.insertCell(0);
            cell8.innerHTML = data[i] ["description"];
            var cell9 = row.insertCell(0);
            cell9.innerHTML = data[i] ["amount"];
            var cell10 = row.insertCell(0);
            cell10.innerHTML = data[i] ["R_ID"];

    }
}

function getAllData(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            var data = JSON.parse(xhr.responseText);
            populateTableAll(data);
        }
    }
    xhr.open("GET", "/Project1/AllReceipt", true);
    xhr.send();
}
//populates the resolved table
function populateTableRes(data){
    var table = document.getElementById("restable");
    for(let i = 0; i < data.length; i++){
        var row = table.insertRow(1);
            var cell1 = row.insertCell(0);
            cell1.innerHTML = data[i] ["type"];
            var cell2 = row.insertCell(0);
            cell2.innerHTML = data[i] ["status"];
            var cell3 = row.insertCell(0);
            cell3.innerHTML = data[i] ["resolver"];
            var cell4 = row.insertCell(0);
            cell4.innerHTML = data[i] ["author"];
            var cell5 = row.insertCell(0);
            cell5.innerHTML = data[i] ["time_resolved"];
            var cell6 = row.insertCell(0);
            cell6.innerHTML = data[i] ["time_submitted"];
            var cell7 = row.insertCell(0);
            cell7.innerHTML = data[i] ["receipt"];
            var cell8 = row.insertCell(0);
            cell8.innerHTML = data[i] ["description"];
            var cell9 = row.insertCell(0);
            cell9.innerHTML = data[i] ["amount"];
            var cell10 = row.insertCell(0);
            cell10.innerHTML = data[i] ["R_ID"];

    }
}

function getResolvedData(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            var data = JSON.parse(xhr.responseText);
            populateTableRes(data);
        }
    }
    xhr.open("GET", "/Project1/ResReceipt", true);
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    getData();
    getAllData();
    getResolvedData()
}
