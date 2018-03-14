function loadEmps(array) {

    var existingrows = document.getElementsByName("deleteable");
    while (existingrows[0]) existingrows[0].parentNode.removeChild(existingrows[0])

    var listOfReqs = array;

    for (let i = 0; i < array.length; i++) {
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "emp" + i);
        newrow.setAttribute("name", "deleteable");
        newrow.setAttribute("class", "row100 body");
        document.getElementById("emplist").appendChild(newrow);

        var col1 = document.createElement("td");
        var col2 = document.createElement("td");
        var col3 = document.createElement("td");
        var col4 = document.createElement("td");
        var col5 = document.createElement("td");
        var col6 = document.createElement("td");

        col1.textContent = array[i].u_id;
        col2.textContent = array[i].u_username;
        col3.textContent = array[i].u_name;
        col4.textContent = array[i].u_email;
        col5.textContent = array[i].u_role;
        col6.innerHTML = "Pending Requests: " + array[i].r_pend + "<br/>" + "Total Requests: " + array[i].r_total;
        
        document.getElementById("emp" + i).appendChild(col1);
        document.getElementById("emp" + i).appendChild(col2);
        document.getElementById("emp" + i).appendChild(col3);
        document.getElementById("emp" + i).appendChild(col4);
        document.getElementById("emp" + i).appendChild(col5);
        document.getElementById("emp" + i).appendChild(col6);
    }//end for

}

function getAllUserArray() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var list = JSON.parse(xhr.responseText);
            loadEmps(list);
        }
    }

    xhr.open("GET", "http://localhost:8080/Project1ERS/viewempinfoservlet?filter=3", true);
    xhr.send();
}

function getEmpArray() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var list = JSON.parse(xhr.responseText);
            loadEmps(list);
        }
    }

    xhr.open("GET", "http://localhost:8080/Project1ERS/viewempinfoservlet?filter=1", true);
    xhr.send();
}

function getManagerArray() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var list = JSON.parse(xhr.responseText);
            loadEmps(list);
        }
    }

    xhr.open("GET", "http://localhost:8080/Project1ERS/viewempinfoservlet?filter=2", true);
    xhr.send();
}
// based on selected call different servlet, change getpending servlet to have the same json format as view all except with nulls in the null spaces

window.onload = function () {
    getAllUserArray();
}


var radioButtons = document.getElementsByName("viewfilter");
//console.log(radioButtons);
var buttons = radioButtons;

for (var i = 0; i < buttons.length; i++) {
    buttons[i].onchange = function () {
        for (var i = 0; i < buttons.length; i++) {
            if (buttons[i].checked == true) {
                if (buttons[i].value == 1) {
                    getEmpArray();
                } else if (buttons[i].value == 2) {
                    getManagerArray();
                } else if(buttons[i].value == 3){
                    getAllUserArray();
                }
            }
        }

    }
}