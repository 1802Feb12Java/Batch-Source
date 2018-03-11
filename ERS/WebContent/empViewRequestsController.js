
document.getElementById("viewAll").addEventListener("click", getAllRequests);
document.getElementById("viewPending").addEventListener("click", getPendingRequests);
document.getElementById("viewApproved").addEventListener("click", getApprovedRequests);
function getPendingRequests() {
  var results;
  var req = new XMLHttpRequest();
  req.open("GET", "http://localhost:8080/ERS/EmployeeRequestViewServlet?viewType='PENDING'", true);
  req.send(null);
  req.addEventListener("load", function () {
    if (req.status >= 200 && req.status < 300) {
      results = req.responseText;
      results = JSON.parse(results);
      //create table rows
      var tableBody = document.getElementById("empReqTableBody");
      tableBody.innerHTML = "";
      for (var i = 0; i < results.length; i++) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);
        
        //create holders for data
        var amount = document.createElement("td")
        var type = document.createElement("td");
        var description = document.createElement("td");
        var receipt = document.createElement("td");
        var status = document.createElement("td");
        var dateSubmit = document.createElement("td");
        var resolvedBy = document.createElement("td");
        var dateResolved = document.createElement("td");
        //append holders to table 
        tableRow.appendChild(amount);
        tableRow.appendChild(type);
        tableRow.appendChild(description);
        tableRow.appendChild(receipt);
        tableRow.appendChild(status);
        tableRow.appendChild(dateSubmit);
        tableRow.appendChild(resolvedBy);
        tableRow.appendChild(dateResolved);
        
        //parse data into table
        var requestObject = results[i];
        amount.textContent = requestObject.amount;
        type.textContent = requestObject.type;
        description.textContent = requestObject.description;
        var imageData = document.createElement("img"); 
        imageData.src = "data:image/png;base64," + requestObject.receipt;
        imageData.classList.add("receipt");
        receipt.appendChild(imageData);
        status.textContent = requestObject.status;
        dateSubmit.textContent = requestObject.timeStampRequest;
        resolvedBy.textContent = requestObject.resolver;
        dateResolved.textContent = requestObject.timeStampApprove;
      }
    }
  });
}

function getApprovedRequests() {
  var results;
  var req = new XMLHttpRequest();
  req.open("GET", "http://localhost:8080/ERS/EmployeeRequestViewServlet?viewType='APPROVED'", true);
  req.send(null);
  req.addEventListener("load", function () {
    if (req.status >= 200 && req.status < 300) {
      results = req.responseText;
      results = JSON.parse(results);
      //create table rows
      var tableBody = document.getElementById("empReqTableBody");
      tableBody.innerHTML = "";
      for (var i = 0; i < results.length; i++) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);
        
        //create holders for data
        var amount = document.createElement("td")
        var type = document.createElement("td");
        var description = document.createElement("td");
        var receipt = document.createElement("td");
        var status = document.createElement("td");
        var dateSubmit = document.createElement("td");
        var resolvedBy = document.createElement("td");
        var dateResolved = document.createElement("td");
        //append holders to table 
        tableRow.appendChild(amount);
        tableRow.appendChild(type);
        tableRow.appendChild(description);
        tableRow.appendChild(receipt);
        tableRow.appendChild(status);
        tableRow.appendChild(dateSubmit);
        tableRow.appendChild(resolvedBy);
        tableRow.appendChild(dateResolved);
        
        //parse data into table
        var requestObject = results[i];
        amount.textContent = requestObject.amount;
        type.textContent = requestObject.type;
        description.textContent = requestObject.description;
        var imageData = document.createElement("img"); 
        imageData.src = "data:image/png;base64," + requestObject.receipt;
        imageData.classList.add("receipt");
        receipt.appendChild(imageData);
        status.textContent = requestObject.status;
        dateSubmit.textContent = requestObject.timeStampRequest;
        resolvedBy.textContent = requestObject.resolver;
        dateResolved.textContent = requestObject.timeStampApprove;
      }
    }
  });
}

function getAllRequests() {
  var results;
  var req = new XMLHttpRequest();
  req.open("GET", "http://localhost:8080/ERS/EmployeeRequestViewServlet?viewType='ALL'", true);
  req.send(null);
  req.addEventListener("load", function () {
    if (req.status >= 200 && req.status < 300) {
      results = req.responseText;
      results = JSON.parse(results);
      //create table rows
      var tableBody = document.getElementById("empReqTableBody");
      tableBody.innerHTML = "";
      for (var i = 0; i < results.length; i++) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);
        
        //create holders for data
        var amount = document.createElement("td")
        var type = document.createElement("td");
        var description = document.createElement("td");
        var receipt = document.createElement("td");
        var status = document.createElement("td");
        var dateSubmit = document.createElement("td");
        var resolvedBy = document.createElement("td");
        var dateResolved = document.createElement("td");
        //append holders to table 
        tableRow.appendChild(amount);
        tableRow.appendChild(type);
        tableRow.appendChild(description);
        tableRow.appendChild(receipt);
        tableRow.appendChild(status);
        tableRow.appendChild(dateSubmit);
        tableRow.appendChild(resolvedBy);
        tableRow.appendChild(dateResolved);
        
        //parse data into table
        var requestObject = results[i];
        amount.textContent = requestObject.amount;
        type.textContent = requestObject.type;
        description.textContent = requestObject.description;
        var imageData = document.createElement("img"); 
        imageData.src = "data:image/png;base64," + requestObject.receipt;
        imageData.classList.add("receipt");
        receipt.appendChild(imageData);
        status.textContent = requestObject.status;
        dateSubmit.textContent = requestObject.timeStampRequest;
        resolvedBy.textContent = requestObject.resolver;
        dateResolved.textContent = requestObject.timeStampApprove;
      }
    }
  });
}


