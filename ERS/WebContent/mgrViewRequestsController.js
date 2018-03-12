document.getElementById("viewPending").addEventListener("click", getPendingRequests);
document.getElementById("viewAll").addEventListener("click", getAllRequests);
document.getElementById("viewApproved").addEventListener("click", getApprovedRequests);
var currentView;
function getPendingRequests() {
    var results;
    currentView = getPendingRequests;
    var req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/ERS/ManagerRequestViewServlet?viewType='PENDING'", true);
    req.send(null);
    req.addEventListener("load", function () {
        var tableBody = document.getElementById("mgrRequestViewTableBody");
        tableBody.innerHTML = "";

        //show action buttons
        var actionElements = document.getElementsByClassName("action");
        for (var i = 0; i < actionElements.length; i++) {
            actionElements[i].hidden = false;
        }

        if (req.status >= 200 && req.status < 300) {
            results = req.responseText;
            results = JSON.parse(results);
            //create table rows
            var tableBody = document.getElementById("mgrRequestViewTableBody");
            tableBody.innerHTML = "";
            for (var i = 0; i < results.length; i++) {
                var tableRow = document.createElement("tr");
                tableBody.appendChild(tableRow);

                //create holders for data
                var id = document.createElement("td");
                id.hidden = true;
                var amount = document.createElement("td")
                var type = document.createElement("td");
                var description = document.createElement("td");
                var receipt = document.createElement("td");
                var status = document.createElement("td");
                var submitter = document.createElement("td");
                var dateSubmit = document.createElement("td");
                var resolvedBy = document.createElement("td");
                var dateResolved = document.createElement("td");
                var action = document.createElement("td");
                action.classList.add("action");

                //append holders to table 
                tableRow.appendChild(id);
                tableRow.appendChild(amount);
                tableRow.appendChild(type);
                tableRow.appendChild(description);
                tableRow.appendChild(receipt);
                tableRow.appendChild(status);
                tableRow.appendChild(submitter);
                tableRow.appendChild(dateSubmit);
                tableRow.appendChild(resolvedBy);
                tableRow.appendChild(dateResolved);
                tableRow.appendChild(action);

                //parse data into table
                var requestObject = results[i];
                id.textContent = requestObject.requestId;
                amount.textContent = requestObject.amount;
                type.textContent = requestObject.type;
                description.textContent = requestObject.description;
                var imageData = document.createElement("img");
                imageData.src = "data:image/png;base64," + requestObject.receipt;
                imageData.classList.add("receipt");
                receipt.appendChild(imageData);
                status.textContent = requestObject.status;
                submitter.textContent = requestObject.requester;
                dateSubmit.textContent = requestObject.timeStampRequest;
                resolvedBy.textContent = requestObject.resolver;
                dateResolved.textContent = requestObject.timeStampApprove;

                if (requestObject.status == "PENDING") {
                    //create buttons for action column
                    var approveButton = document.createElement("button");
                    approveButton.classList.add("btn-sm", "btn-success");
                    var denyButton = document.createElement("button");
                    denyButton.classList.add("btn-sm", "btn-danger");
                    approveButton.addEventListener("click", approveRequest);
                    approveButton.textContent = "Approve Request";


                    denyButton.addEventListener("click", denyRequest);
                    denyButton.textContent = "Deny Request";

                    //append buttons to column
                    action.appendChild(approveButton);
                    action.appendChild(denyButton);
                }
            }
        }
    });
}


function getAllRequests() {
    var results;
    currentView = getAllRequests;
    var req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/ERS/ManagerRequestViewServlet?viewType='ALL'", true);
    req.send(null);
    req.addEventListener("load", function () {
        var tableBody = document.getElementById("mgrRequestViewTableBody");
        tableBody.innerHTML = "";

        //show action buttons
        var actionElements = document.getElementsByClassName("action");
        for (var i = 0; i < actionElements.length; i++) {
            actionElements[i].hidden = false;
        }

        if (req.status >= 200 && req.status < 300) {
            results = req.responseText;
            results = JSON.parse(results);
            //create table rows
            var tableBody = document.getElementById("mgrRequestViewTableBody");
            tableBody.innerHTML = "";
            for (var i = 0; i < results.length; i++) {
                var tableRow = document.createElement("tr");
                tableBody.appendChild(tableRow);

                //create holders for data
                var id = document.createElement("td");
                id.hidden = true;
                var amount = document.createElement("td")
                var type = document.createElement("td");
                var description = document.createElement("td");
                var receipt = document.createElement("td");
                var status = document.createElement("td");
                var submitter = document.createElement("td");
                var dateSubmit = document.createElement("td");
                var resolvedBy = document.createElement("td");
                var dateResolved = document.createElement("td");
                var action = document.createElement("td");
                action.classList.add("action");

                //append holders to table 
                tableRow.appendChild(id);
                tableRow.appendChild(amount);
                tableRow.appendChild(type);
                tableRow.appendChild(description);
                tableRow.appendChild(receipt);
                tableRow.appendChild(status);
                tableRow.appendChild(submitter);
                tableRow.appendChild(dateSubmit);
                tableRow.appendChild(resolvedBy);
                tableRow.appendChild(dateResolved);
                tableRow.appendChild(action);

                //parse data into table
                var requestObject = results[i];
                id.textContent = requestObject.requestId;
                amount.textContent = requestObject.amount;
                type.textContent = requestObject.type;
                description.textContent = requestObject.description;
                var imageData = document.createElement("img");
                imageData.src = "data:image/png;base64," + requestObject.receipt;
                imageData.classList.add("receipt");
                receipt.appendChild(imageData);
                status.textContent = requestObject.status;
                submitter.textContent = requestObject.requester;
                dateSubmit.textContent = requestObject.timeStampRequest;
                resolvedBy.textContent = requestObject.resolver;
                dateResolved.textContent = requestObject.timeStampApprove;

                if (requestObject.status == "PENDING") {
                    //create buttons for action column
                    var approveButton = document.createElement("button");
                    var denyButton = document.createElement("button");
                    approveButton.classList.add("btn-sm", "btn-success");
                    approveButton.addEventListener("click", approveRequest);
                    approveButton.textContent = "Approve Request";


                    denyButton.addEventListener("click", denyRequest);
                    denyButton.classList.add("btn-sm", "btn-danger");
                    denyButton.textContent = "Deny Request";

                    //append buttons to column
                    action.appendChild(approveButton);
                    action.appendChild(denyButton);
                }
                else {
                    //action.hidden = true;
                    action.textContent = "No Actions Available"
                }
            }
        }
    });
}

function getApprovedRequests() {
    var results;
    currentView = getApprovedRequests;
    var req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/ERS/ManagerRequestViewServlet?viewType='APPROVED'", true);
    req.send(null);
    req.addEventListener("load", function () {
        var tableBody = document.getElementById("mgrRequestViewTableBody");
        tableBody.innerHTML = "";

        //hide action buttons
        var actionElements = document.getElementsByClassName("action");
        for (var i = 0; i < actionElements.length; i++) {
            actionElements[i].hidden = true;
        }

        if (req.status >= 200 && req.status < 300) {
            results = req.responseText;
            results = JSON.parse(results);
            //create table rows
            var tableBody = document.getElementById("mgrRequestViewTableBody");
            tableBody.innerHTML = "";
            for (var i = 0; i < results.length; i++) {
                var tableRow = document.createElement("tr");
                tableBody.appendChild(tableRow);

                //create holders for data
                var id = document.createElement("td");
                id.hidden = true;
                var amount = document.createElement("td")
                var type = document.createElement("td");
                var description = document.createElement("td");
                var receipt = document.createElement("td");
                var status = document.createElement("td");
                var submitter = document.createElement("td");
                var dateSubmit = document.createElement("td");
                var resolvedBy = document.createElement("td");
                var dateResolved = document.createElement("td");
                //var action = document.createElement("td");
                //action.classList.add("action");

                //append holders to table 
                tableRow.appendChild(id);
                tableRow.appendChild(amount);
                tableRow.appendChild(type);
                tableRow.appendChild(description);
                tableRow.appendChild(receipt);
                tableRow.appendChild(status);
                tableRow.appendChild(submitter);
                tableRow.appendChild(dateSubmit);
                tableRow.appendChild(resolvedBy);
                tableRow.appendChild(dateResolved);
                //tableRow.appendChild(action);

                //parse data into table
                var requestObject = results[i];
                id.textContent = requestObject.requestId;
                amount.textContent = requestObject.amount;
                type.textContent = requestObject.type;
                description.textContent = requestObject.description;
                var imageData = document.createElement("img");
                imageData.src = "data:image/png;base64," + requestObject.receipt;
                imageData.classList.add("receipt");
                receipt.appendChild(imageData);
                status.textContent = requestObject.status;
                submitter.textContent = requestObject.requester;
                dateSubmit.textContent = requestObject.timeStampRequest;
                resolvedBy.textContent = requestObject.resolver;
                dateResolved.textContent = requestObject.timeStampApprove;
            }
        }
    });
}

function approveRequest() {
    var data = [];
    data = $(this).parent().siblings();
    //data = data[0];
    var id = data[0].textContent;

    var req = new XMLHttpRequest();
    req.open("PUT", "http://localhost:8080/ERS/ManagerRequestViewServlet?type=APPROVE&id=" + id, true);
    req.send(null);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 300) {
            currentView();
        }

    });

}

function denyRequest() {
    var data = [];
    data = $(this).parent().siblings();
    //data = data[0];
    var id = data[0].textContent;

    var req = new XMLHttpRequest();
    req.open("PUT", "http://localhost:8080/ERS/ManagerRequestViewServlet?type=DENY&id=" + id, true);
    req.send(null);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 300) {
            currentView();
        }

    });
}