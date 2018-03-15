window.onload = function () {
    getFirstName();
    getTotalAppliedReimbursments();
    getTotalApprovedReimbursments();
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