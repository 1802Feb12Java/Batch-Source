var tableUsers;

window.onload = function () {
    getAllUsers();
};

function getAllUsers() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            tableUsers = JSON.parse(xhr.responseText);
            displayUsers();
        }
    }

    xhr.open('GET', '/Project1/allusers');

    xhr.send();
}

function displayUsers() {
    //clear table
    $('#dataTable').DataTable().clear();

    for (let i = 0; i < tableUsers.length; i++) {
        $('#dataTable').DataTable().row.add(tableUsers[i], true);
    }

    $('#dataTable').DataTable().draw();

}