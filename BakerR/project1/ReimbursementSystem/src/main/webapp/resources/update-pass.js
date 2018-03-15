function passMatch() {
    let updatePassBtn = document.querySelector('#updatePassBtn');
    if(document.querySelector('#new-password').value === document.querySelector('#confirm-new-password').value) {
        updatePassBtn.disabled = false;
    } else {
        updatePassBtn.disabled = true;
    }
}

document.querySelector('#new-password').oninput = passMatch;
document.querySelector('#confirm-new-password').oninput = passMatch;