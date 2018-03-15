window.onload = function () {
    getFirstName();
    getAllUsers();
};

function getAllUsers() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var tableUsers = JSON.parse(xhr.responseText);
            displayUsers(tableUsers);
        }
    }
    xhr.open('GET', '/Project1/allusers');
    xhr.send();
}

function displayUsers(tableUsers) {
    //clear table
    $('#dataTable').DataTable().clear();
    for (let i = 0; i < tableUsers.length; i++) {
        $('#dataTable').DataTable().row.add(tableUsers[i], true);
    }
    $('#dataTable').DataTable().draw();
}

function getFirstName() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var firstName = xhr.responseText;
            displayName(firstName);
            return firstName;
        }
    }
    xhr.open('GET', '/Project1/firstname');
    xhr.send();
}

function displayName(updateToThis) {
    document.getElementById("navbarTopLeft").innerText = "Welcome, " + updateToThis;
}