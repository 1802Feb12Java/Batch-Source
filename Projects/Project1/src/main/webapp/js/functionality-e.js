window.onload = function () {
    getFirstName();
    getEmployeeReimbursements();
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

function getEmployeeReimbursements() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var reimbursementData = JSON.parse(xhr.responseText);
            displayReimbursements(reimbursementData);
        }
    }
    xhr.open('GET', '/Project1/onereimbursement');
    xhr.send();
}

function displayReimbursements(reimbursementData) {
    $('#userReimbursements').DataTable().clear();
    for (let i = 0; i < reimbursementData.length; i++) {
        $('#userReimbursements').DataTable().row.add(reimbursementData[i], true);
    }
    $('#userReimbursements').DataTable().draw();
}