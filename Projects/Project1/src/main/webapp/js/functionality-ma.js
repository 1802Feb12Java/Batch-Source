window.onload = function () {
    getFirstName();
    getAllReimbursements();
};

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

function getAllReimbursements() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var reimbursementData = JSON.parse(xhr.responseText);
            displayReimbursements(reimbursementData);
        }
    }
    xhr.open('GET', '/Project1/allreimbursements');
    xhr.send();
}

function displayReimbursements(reimbursementData) {
    //clear table
    $('#reimbursementTable').DataTable().clear();
    for (let i = 0; i < reimbursementData.length; i++) {
        $('#reimbursementTable').DataTable().row.add(reimbursementData[i], true);
    }
    $('#reimbursementTable').DataTable().draw();
}