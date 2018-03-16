var populatedRequest = false;
document.getElementById("headingOne").addEventListener('click', function(){
    // console.log("popreq: " + populatedRequest);
    getReimbursements(populatedRequest);
    populatedRequest = true;
    // console.log("popreq: " + populatedRequest);
});

function getReimbursements(populatedRequests){
    var req = new XMLHttpRequest();
    // console.log("settng up request");
    req.open("GET", "http://localhost:8080/ProjectOne/allreimbursementservlet", true);
    req.send();
    // console.log("Button was clicked and request sent");
    req.addEventListener('load', function () {
        
        if (req.status >= 200 && req.status < 300) {
            if(!populatedRequests){
                populatedRequests = true;
                // console.log(req.responseText);
                populateRequestTable(JSON.parse(req.responseText));
            }
        }
    });
}

function populateRequestTable(respText){
    // console.log("Adding table");
    var table = document.getElementById("table1");
    for(var i = 0; i < respText.length; i++){
        var index = table.rows[table.rows.length - 1].cells[0].innerHTML;
        // console.log("index: "+index)
        if(index == "#"){
            index=0;
        }
        var descript = respText[i].descript
        if(descript==undefined){
            descript = "";
        }
        var tr = document.createElement("tr");
        tr.setAttribute("id","reimbRow"+index);  
        tr.setAttribute("status",""+respText[i].statusStr);
        tr.setAttribute("reimbId", ""+respText[i].id);
            

        tr.innerHTML = "<th scope='row'>" + (+index + 1) + "</th>\
        <td id='employee'>"+ respText[i].author_fullname    +"</td>\
        <td id='Type'>"+ respText[i].typeStr                +"</td>\
        <td id='Amount'>"+ "$"+ respText[i].amount          +"</td>\
        <td id='Request'>"+ respText[i].id                  +"</td>\
        <td id='tdstatus"+index+"'>"+ respText[i].statusStr            +"\
        <button class='inline' id='approvebtn"+index+"' index='"+index+"' type='button'>&#9989;</button>\
        <button class='inline' id='rejectbtn"+index+"' index='"+index+"' type='button'>&#10060;</button>\
        </td>\
        <td id='Resolver'>"+ respText[i].resolver           +"</td>\
        <td id='Submitted'>"+ respText[i].submitted         +"</td>\
        <td id='Submitted'>"+ respText[i].resovled         +"</td>\
        <td id='Description'>"+descript                     +"</td>\
        <td id='Receipt'>"+ "X"                             +"</td>\
        ";

        table.appendChild(tr);

        if(respText[i].statusStr != "Pending"){
            document.getElementById("approvebtn"+index).setAttribute("style","display: none;");
            document.getElementById("rejectbtn"+index).setAttribute("style","display: none;");
        }

        var appbtn = document.getElementById("approvebtn"+index);
        appbtn.addEventListener("click", function(){
            var indexx = this.getAttribute("index");
            this.setAttribute("style","display: none;");
            document.getElementById("rejectbtn"+indexx).setAttribute("style","display: none;");
            console.log("tdstatus"+indexx);

            document.getElementById("tdstatus"+indexx).innerText = "Approved";
            document.getElementById("tdstatus"+indexx).parentElement.setAttribute("status","Approved");
            document.getElementById("reimbRow"+indexx).setAttribute("status","Approved");
        
            pendingButton(indexx, 1);
            // getReimbursements(false);
            console.log("buttons should be hidden");
        });

        var rejbtn = document.getElementById("rejectbtn"+index);
        rejbtn.addEventListener("click", function(){
            var indexx = this.getAttribute("index");
            this.setAttribute("style","display: none;");
            document.getElementById("approvebtn"+indexx).setAttribute("style","display: none;");
            document.getElementById("tdstatus"+indexx).innerText = "Denied";
            document.getElementById("tdstatus"+indexx).parentElement.setAttribute("status","Rejected");

            document.getElementById("reimbRow"+indexx).setAttribute("status","Rejected");
            pendingButton(indexx, -1);
            // getReimbursements(false);
        });

    }
}

function pendingButton(index, approval){

    var req = new XMLHttpRequest();
    
    var params;
    var appType = 1;
    if(approval < 0){
        appType = -1;
    }
    
    var elem = document.getElementById("reimbRow"+index);
    params = "?ur_id="+elem.getAttribute("reimbId")+"&";
    params += "status="+appType;
    // console.log(params);
    req.open("GET", "http://localhost:8080/ProjectOne/approvereimbursementservlet"+params, true);
    req.send();
    // console.log("Button was clicked and request sent");
    req.addEventListener('load', function () {
        
        if (req.status >= 200 && req.status < 300) {
            //?? why is this even here. Too afraid to remove it.
        }
    });
}

var cb0 = document.getElementById("checkbox0");
var cb1 = document.getElementById("checkbox1");
var cbN1 = document.getElementById("checkbox-1");
function radioClick(elem, status){
    var allElem = document.querySelectorAll("tr");
    var allElems = [];
    for(j=0;j<allElem.length;j++){
        if(allElem[j].getAttribute("status")==status){
            allElems.push(allElem[j]);
        }
    }
    var state = "";
    if(elem.checked){
        state = "";
    } else {
        state = "none";
    }   
    for(var i = 0; i < allElems.length;i++){
        allElems[i].setAttribute("style","display: "+state+";");
    }

}

cb0.addEventListener("click", function(){
    radioClick(cb0, "Pending");
});
cb1.addEventListener("click", function(){
    radioClick(cb1, "Approved");
});
cbN1.addEventListener("click", function(){
    radioClick(cbN1, "Rejected");
});

document.getElementById("headingTwo").addEventListener("onload", function(){
    console.log("employees should be onload");
});

// document.getElementById("headingTwo").addEventListener("load", r);
var populatedEmployees = false;
document.getElementById("headingTwo").addEventListener("click", function(){
   requestEmpTable();
});
var populatedEmployees = false;

function requestEmpTable(){
    var req = new XMLHttpRequest();
    console.log("settng up employee request");
    req.open("GET", "http://localhost:8080/ProjectOne/allemployeeservlet", true);
    req.send();
    // console.log("Button was clicked and request sent");
    req.addEventListener('load', function () {
        
        if (req.status >= 200 && req.status < 300) {
            // console.log("success");
            if(!populatedEmployees){
                populatedEmployees = true;
                // console.log(req.responseText);
                populateEmployeeTable(JSON.parse(req.responseText));
            }
        }
    });
}

function populateEmployeeTable(respText){

    // <th scope="col">#</th>
    // <th scope="col">Employee</th>
    // <th scope="col">First Name</th>
    // <th scope='col'>Last Name</th>
    // <th scope="col">Employee ID</th>
    // <th scope="col">Email</th>

    var table = document.getElementById("tableUser");
    for(var i = 0; i < respText.length; i++){
        var index = table.rows[table.rows.length - 1].cells[0].innerHTML;
        // console.log("index: "+index)
        if(index == "#"){
            index=0;
        }
        
        var tr = document.createElement("tr");
        tr.setAttribute("id","employee"+index);  
        // tr.setAttribute("empId", ""+respText[i].id);
            

        tr.innerHTML = "<th scope='row'>" + (+index + 1) + "</th>\
        <td><button class='inline' id='viewReimb"+index+"' employee='"+respText[i].username+"' type='button'>&#9989;</button></td>\
        <td id='employee'>"+ checkUndefined(respText[i].username)               +"</td>\
        <td id='firstname'>"+ checkUndefined(respText[i].firstname)             +"</td>\
        <td id='lastname'>"+ checkUndefined(respText[i].lastname )              +"</td>\
        <td id='email'>"+ checkUndefined(respText[i].email )           +"\
        <td id='password'>"+ checkUndefined(respText[i].password)              +"</td>\
        ";

        table.appendChild(tr);

        // var appbtn = document.getElementById("employeebtn"+index);
        // appbtn.addEventListener("click", function(){
        //     // console.log("edit user button clicked");
        // });

        var appbtn2 = document.getElementById("viewReimb"+index);
        appbtn2.addEventListener("click", function(){
            // console.log("view user reimb button clicked");
            document.getElementById("collapseThree").setAttribute("class","collapse show");
            getEmployeeReimbursements(this.getAttribute("employee"));
        });

    }
}

function checkUndefined(str){
    if(str == undefined || str == null){
        str=""
    }
    return str;
}

function getEmployeeReimbursements(username){
    var req = new XMLHttpRequest();

    var url = "";

    url+="?username="+username;

    // console.log("settng up employee request");
    req.open("GET", "http://localhost:8080/ProjectOne/employeereimbursementservlet"+url, true);
    req.send();
    // console.log("Button was clicked and request sent: emp reimb");
    req.addEventListener('load', function () {
        
        if (req.status >= 200 && req.status < 300) {
            
            populateEmployeeReimbCollapse(JSON.parse(req.responseText));
        }
    });
}

function populateEmployeeReimbCollapse(respText){
    // console.log("get 1 emp reimb clicked")
    var table = document.getElementById("table3body");

    //clear out table
    var newTab3 = document.createElement("tbody");
    newTab3.setAttribute("id","table3body");
    table.parentNode.replaceChild(newTab3, table);

    table = newTab3;

    for(var i = 0; i < respText.length; i++){
        var index = 0;//table.rows[table.rows.length - 1].cells[0].innerHTML;
        // console.log("index: "+index)
        if(index == "#"){
            index=0;
        }
        var descript = respText[i].descript
        if(descript==undefined){
            descript = "";
        }
        var tr = document.createElement("tr");
        tr.setAttribute("id","reimbRow"+index);  
        tr.setAttribute("status",""+respText[i].statusStr);
        tr.setAttribute("reimbId", ""+respText[i].id);
            

        tr.innerHTML = "<th scope='row'>" + (+index + 1) + "</th>\
        <td id='employee'>"+ respText[i].author_fullname    +"</td>\
        <td id='Type'>"+ respText[i].typeStr                +"</td>\
        <td id='Amount'>"+ "$"+ respText[i].amount          +"</td>\
        <td id='Request'>"+ respText[i].id                  +"</td>\
        <td id='tdstatus"+index+"'>"+ respText[i].statusStr +"</td>\
        <td id='Resolver'>"+ respText[i].resolver           +"</td>\
        <td id='Submitted'>"+ respText[i].submitted         +"</td>\
        <td id='Description'>"+descript                     +"</td>\
        <td id='Receipt'>"+ "X"                             +"</td>\
        ";

        table.appendChild(tr);
        index = index+1;
    }
}
