document.querySelector('#cancelBtn').onclick = function() {
    window.location.href = 'login';
};


function passMatch() {
    let registerBtn = document.querySelector('#registerBtn');
    if(document.querySelector('#password').value === document.querySelector('#confirm-password').value) {
        registerBtn.disabled = false;
    } else {
        registerBtn.disabled = true;
    }
}

document.querySelector('#password').oninput = passMatch;
document.querySelector('#confirm-password').oninput = passMatch;