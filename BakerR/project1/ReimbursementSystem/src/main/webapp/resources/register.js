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

document.querySelector('#password').onchange = passMatch;
document.querySelector('#confirm-password').onchange = passMatch;