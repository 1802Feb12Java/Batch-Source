//populates the pending table
function populateTable(data){
    var table = document.getElementById("remtable");
    console.log(data);
    for(let i = 0; i < data.length; i++){
        var row = table.insertRow(i+1);
        var cell11 = row.insertCell(0);
            if(data[i] ["status"] == "pending"){
                cell11.innerHTML = '<form role="form" action="/Project1/ApproveServlet" method="post"> <fieldset> <div class="form-group"> <input class="form-control" type="hidden" name="remid" autofocus value=' + data[i] ["R_ID"] + '> </div> <input type="submit" value="Approve" class="btn btn-sm btn-success btn-block"> </fieldset> </form> <form role="form" action="/Project1/DenyServlet" method="post"> <fieldset> <div class="form-group"> <input class="form-control" type="hidden" name="remid" autofocus value=' + data[i] ["R_ID"] + '> </div> <input type="submit" value="Deny" class="btn btn-sm btn-danger btn-block"> </fieldset> </form>';
                
            } else{
                cell11.innerHTML = "";
            }
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

//populates the resolved table
function populateTableRes(data){
    var table = document.getElementById("restable");
    for(let i = 0; i < data.length; i++){
        var row = table.insertRow(i+1);
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
    xhr.open("GET", "/Project1/ResTable", true);
    xhr.send();
}

window.onload = function(){
    this.console.log("in onLoad");
    getData();
    getResolvedData()
}




// '<form role="form" action="/Project1/ApproveServlet" method="post"> <fieldset> <div class="form-group"> <input class="form-control" name="remid" autofocus hidden="true" value=' + data[i] ["R_ID"] + '> </div> <input type="submit" value="Approve" class="btn btn-sm btn-success btn-block"> </fieldset> </form> <form role="form" action="/Project1/DenyServlet" method="post"> <fieldset> <div class="form-group"> <input class="form-control" name="remid" autofocus hidden="true" value=' + data[i] ["R_ID"] + '> </div> <input type="submit" value="Deny" class="btn btn-sm btn-deny btn-block"> </fieldset> </form>'


// '<button type="button" class="btn btn-success" id="approve' + i + '">Approve</button> <button type="button" class="btn btn-danger" id="deny' + i + '">Deny</button>'

// //document.getElementById("approve" + i).onclick = function(){
//     var xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function(){
//         if(xhr.readyState==4 && xhr.status==200){
            
//         }
//     }
//     xhr.open("POST", "/Project1/ApproveServlet", true);
//     xhr.send("rid", data[i] ["R_ID"]);
// }
// document.getElementById("deny" + i).onclick = function(){
    
// }