window.onload = function () {
    getAllUserArray();
}


function getAllUserArray() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var list = JSON.parse(xhr.responseText);
            loadEmps(list);
        }
    }

    xhr.open("GET", "http://localhost:8080/Project1ERS/viewuserinfoservlet", true);
    xhr.send();
}

function loadEmps(array) {
        console.log(array);
        var col1 = document.createElement("p");
        var col2 = document.createElement("p");
        var col3 = document.createElement("p");
        var col4 = document.createElement("p");
        var col5 = document.createElement("p");
        var col6 = document.createElement("p");

        col1.innerHTML = array[0].u_id;
        col2.textContent = array[0].u_username;
        document.getElementById('updateusername').value = array[0].u_username;
        col3.textContent = array[0].u_name;
        document.getElementById('updatename').value = array[0].u_name;
        col4.textContent = array[0].u_email;
        document.getElementById('updateemail').value = array[0].u_email;
        col5.innerHTML = array[0].r_pend;
        col6.innerHTML = array[0].r_total;
        
        document.getElementById("empinfo").appendChild(col1);
        document.getElementById("empinfo").appendChild(col2);
        document.getElementById("empinfo").appendChild(col3);
        document.getElementById("empinfo").appendChild(col4);
        document.getElementById("empinfo").appendChild(col5);
        document.getElementById("empinfo").appendChild(col6);

}