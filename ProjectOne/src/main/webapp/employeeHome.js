document.getElementById("dropdownMenu").addEventListener("click", function(){
    // console.log("drop down clicked");

});

var populatedRequests = false;
document.getElementById("headingOne").addEventListener("click", function(){

    // console.log("saw click");
    
    var req = new XMLHttpRequest();
    // console.log("settng up request");
    req.open("GET", "http://localhost:8080/ProjectOne/employeereimbursementtableservlet", true);
    req.send();
    console.log("Button was clicked and request sent");
    req.addEventListener('load', function () {
        
        if (req.status >= 200 && req.status < 300) {
            console.log("populate? "+populatedRequests);
            if(!populatedRequests){
                populatedRequests = true;
                populateRequestTable(JSON.parse(req.responseText));
            }
        }
    });
});

function populateRequestTable(respText){
    console.log("Called populate table");
    var table = document.getElementById("table1");
    
    for(var i = 0; i < respText.length; i++){
        var index = table.rows[table.rows.length - 1].cells[0].innerHTML;
        if(index=="#"){
            index=0;
        }
        var descript = respText[i].descript
        if(descript==undefined){
            descript = "";
        }
        var tr = document.createElement("tr");  
        tr.innerHTML = "<th scope='row'>" + (+index + 1) + "</th>\
        <td>"+ respText[i].statusStr    +"</td>\
        <td>"+ respText[i].id           +"</td>\
        <td>"+ respText[i].typeStr      +"</td>\
        <td>"+ "$"+ respText[i].amount  +"</td>\
        <td>"+ respText[i].submitted    +"</td>\
        <td>"+ descript                 +"</td>\
        <td>"+                          +"</td>\
        ";
        
        table.appendChild(tr);
    }
}

function getUserProfile(){
    var req = new XMLHttpRequest();
    // console.log("settng up request");
    req.open("GET", "http://localhost:8080/ProjectOne/viewprofileservlet", true);
    req.send();
    // console.log("ViewProfile was clicked and request sent");
    req.addEventListener('load', function () {
        
    var username = document.getElementById("userUsername");
    var fName = document.getElementById("userFirstname");
    var lName = document.getElementById("userLastname");
    var email = document.getElementById("userEmail");

        if (req.status >= 200 && req.status < 300) {
            var resp = JSON.parse(req.responseText);
            username.innerHTML = resp.username;
            fName.innerHTML = resp.firstname;
            lName.innerHTML = resp.lastname;
            email.innerHTML = resp.email;
        } else {

        }
    });
}

var populatedUserInfo = false;
document.getElementById("DPbtn1").addEventListener("click", function(){
    if(!populatedUserInfo){
        getUserProfile(document.getElementById("profile"));
    }
    var div = document.getElementById("profile");
    if(div.getAttribute("style") == "display: none;"){
        // console.log("show div");
        getUserProfile(div)
        div.setAttribute("style", "display: block;");
    } else {
        // console.log("hide div");
        div.setAttribute("style", "display: none;");
    }

});

document.getElementById("DPbtn2").addEventListener("click", function(){
    //Display profile element if its hidden
    document.getElementById("profile").setAttribute("style", "display: block");
    //displays hidden input fields
    document.getElementById("profileFirstname").setAttribute("style", "display: block");
    document.getElementById("profileLastname").setAttribute("style", "display: block");
    document.getElementById("profileEmail").setAttribute("style", "display: block");
    document.getElementById("profileEdit").setAttribute("style", "display: block");
});

// document.getElementById("profileEdit").addEventListener("click", function(){

//     console.log("edit profile button clicked");

//     let json = {};

//     json.profileFirstname = document.getElementById("profileFirstname").value;
//     json.profileLastname = document.getElementById("profileLastname").value;
//     json.profileEmail = document.getElementById("profileEmail").value;

//     console.log(json);

//     var req = new XMLHttpRequest();
//     // console.log("settng up request");
//     req.open("POST", "http://localhost:8080/ProjectOne/editprofileservlet", true);
//     req.send(JSON.stringify(json));
//     console.log("EditProfile was clicked and request sent");
//     req.addEventListener('load', function () {

        
//         if (req.status >= 200 && req.status < 300) {
//             // var resp = JSON.parse(req.responseText);
//             // fName.innerHTML = resp.firstname;
//             // lName.innerHTML = resp.lastname;
//             // email.innerHTML = resp.email;
//             console.log("got response 11");
           
//         }
//     });
// });