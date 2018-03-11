
var req = new XMLHttpRequest();
req.open("GET", "http://localhost:8080/ERS/requestType", true);
req.send(null);

req.addEventListener("load", function() {
    var dropdownControl = document.getElementById("requestType");
    if (req.status >= 200 && req.status < 300) {
        var requestTypes = req.responseText;
        requestTypes = requestTypes.split(",");
        for (var i = 0; i < requestTypes.length - 1; i++) {
            var optionItem = document.createElement("option");
            optionItem.value = requestTypes[i];
            optionItem.textContent = requestTypes[i];
            dropdownControl.appendChild(optionItem);
        }
    }
});


