document.querySelectorAll('.status-select').forEach((selectMenu) => {
    if(!selectMenu.disabled) { // Menu isn't disabled.
        selectMenu.onchange = function() {
            // Create request
            let xhr = new XMLHttpRequest();

            let recordNode = this.parentNode.parentNode;
            let params = 'id=' + recordNode.querySelector('.id-cell').outerText
                        + '&' + 'status=' + this.querySelector(':checked').value;
            
            xhr.onreadystatechange = function() {
                if(this.readyState == 4 && this.status == 200) {
                    recordNode.innerHTML = xhr.responseText;
                }
            };

            xhr.open("post", "update-request?" + params, true);
            xhr.send(params);
        };
    }
});