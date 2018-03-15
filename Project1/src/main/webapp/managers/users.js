function populateUsers(data){
    var table = document.getElementById("utable");
    for(let i = 0; i < data.length; i++){
        var row = table.insertRow(i+1);
            var cell1 = row.insertCell(0);
            cell1.innerHTML = data[i] ["role"];
            var cell2 = row.insertCell(0);
            cell2.innerHTML = data[i] ["email"];
            var cell3 = row.insertCell(0);
            cell3.innerHTML = data[i] ["password"];
            var cell4 = row.insertCell(0);
            cell4.innerHTML = data[i] ["username"];
            var cell5 = row.insertCell(0);
            cell5.innerHTML = data[i] ["lastname"];
            var cell6 = row.insertCell(0);
            cell6.innerHTML = data[i] ["firstname"];
            var cell7 = row.insertCell(0);
            cell7.innerHTML = data[i] ["U_ID"];

    }
}

function getUserData(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            var data = JSON.parse(xhr.responseText);
            populateUsers(data);
        }
    }
    xhr.open("GET", "/Project1/UserTable", true);
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    getUserData()
}