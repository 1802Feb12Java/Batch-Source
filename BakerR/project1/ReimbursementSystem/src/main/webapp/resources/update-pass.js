function passMatch() {
    let updatePassBtn = document.querySelector('#updatePassBtn');
    if(document.querySelector('#new-password').value === document.querySelector('#confirm-new-password').value) {
        updatePassBtn.disabled = false;
    } else {
        updatePassBtn.disabled = true;
    }
}

document.querySelector('#new-password').onchange = passMatch;
document.querySelector('#confirm-new-password').onchange = passMatch;