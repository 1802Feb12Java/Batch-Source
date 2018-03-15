window.onload = function () {
    getFirstName();
    getOneUser();
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

function getOneUser() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var oneUser = JSON.parse(xhr.responseText);
            fillInputs(oneUser);
        }
    }
    xhr.open('GET', '/Project1/oneuser');
    xhr.send();
}

function fillInputs (tableUser) {
    document.getElementById("user").placeholder=tableUser[0];
    document.getElementById("fnam").placeholder=tableUser[1];
    document.getElementById("lnam").placeholder=tableUser[2];
    document.getElementById("emal").placeholder=tableUser[3];
}