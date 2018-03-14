
document.getElementById("viewAll").addEventListener("click", getAllRequests);
document.getElementById("viewPending").addEventListener("click", getPendingRequests);
document.getElementById("viewApproved").addEventListener("click", getApprovedRequests);

var newImage;
var imageModal = document.getElementById("imageModalBody");

function getPendingRequests() {
  var results;
  var req = new XMLHttpRequest();
  req.open("GET", "http://localhost:8080/ERS/EmployeeRequestViewServlet?viewType='PENDING'", true);
  req.send(null);
  req.addEventListener("load", function () {
    if (req.status >= 200 && req.status < 300) {
      results = req.responseText;
      results = JSON.parse(results);
      var tableBody = document.getElementById("empReqTableBody");
      tableBody.innerHTML = "";

      if (results.length == 0) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);
        var emptyRow = document.createElement("td");
        emptyRow.colSpan = 8;
        emptyRow.classList.add("text-center");
        emptyRow.textContent = "No Reimbursements Pending"
        tableRow.appendChild(emptyRow);
      }


      //create table rows
      
      
      for (var i = 0; i < results.length; i++) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);

        //create holders for data
        var imageCol = document.createElement("td");
        imageCol.hidden = true;
        var amount = document.createElement("td")
        var type = document.createElement("td");
        var description = document.createElement("td");
        var receipt = document.createElement("td");
        var status = document.createElement("td");
        var dateSubmit = document.createElement("td");
        var resolvedBy = document.createElement("td");
        var dateResolved = document.createElement("td");
        //append holders to table 
        tableRow.appendChild(imageCol);
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
        imageCol.textContent = "data:image/png;base64," + requestObject.receipt;

        var imageDataButton = document.createElement("button");
        imageDataButton.classList.add("btn", "btn-info");
        imageDataButton.innerText = "Click to View Receipt";
        imageDataButton.addEventListener("click", viewReceipt);
        receipt.appendChild(imageDataButton);
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

      if (results.length == 0) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);
        var emptyRow = document.createElement("td");
        emptyRow.colSpan = 8;
        emptyRow.classList.add("text-center");
        emptyRow.textContent = "No Reimbursement Data"
        tableRow.appendChild(emptyRow);
      }

      for (var i = 0; i < results.length; i++) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);

        //create holders for data
        var imageCol = document.createElement("td");
        imageCol.hidden = true;
        var amount = document.createElement("td")
        var type = document.createElement("td");
        var description = document.createElement("td");
        var receipt = document.createElement("td");
        var status = document.createElement("td");
        var dateSubmit = document.createElement("td");
        var resolvedBy = document.createElement("td");
        var dateResolved = document.createElement("td");
        //append holders to table 
        tableRow.appendChild(imageCol);
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
        imageCol.textContent = "data:image/png;base64," + requestObject.receipt;

        var imageDataButton = document.createElement("button");
        imageDataButton.classList.add("btn", "btn-info");
        imageDataButton.innerText = "Click to View Receipt";
        imageDataButton.addEventListener("click", viewReceipt);
        receipt.appendChild(imageDataButton);
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
   
      
      if (results.length == 0) {
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);
        var emptyRow = document.createElement("td");
        emptyRow.colSpan = 8;
        emptyRow.classList.add("text-center");
        emptyRow.textContent = "No Reimbursement Data"
        tableRow.appendChild(emptyRow);
      }
      for (var i = 0; i < results.length; i++) {
        //create holders for data
        var tableRow = document.createElement("tr");
        tableBody.appendChild(tableRow);
        var imageCol = document.createElement("td");
        imageCol.hidden = true;
        var amount = document.createElement("td")
        var type = document.createElement("td");
        var description = document.createElement("td");
        var receipt = document.createElement("td");
        var status = document.createElement("td");
        var dateSubmit = document.createElement("td");
        var resolvedBy = document.createElement("td");
        var dateResolved = document.createElement("td");
        //append holders to table 
        tableRow.appendChild(imageCol);
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
        imageCol.textContent = "data:image/png;base64," + requestObject.receipt;

        var imageDataButton = document.createElement("button");
        imageDataButton.classList.add("btn", "btn-info");
        imageDataButton.innerText = "Click to View Receipt";
        imageDataButton.addEventListener("click", viewReceipt);
        receipt.appendChild(imageDataButton);
        status.textContent = requestObject.status;
        dateSubmit.textContent = requestObject.timeStampRequest;
        resolvedBy.textContent = requestObject.resolver;
        dateResolved.textContent = requestObject.timeStampApprove;
      }
    }
  });
}

function viewReceipt() {
  var data = [];
  data = $(this).parent().siblings();
  var imageString = data[0].textContent;
  newImage = document.createElement("img");
  newImage.src = imageString;
  newImage.classList.add("receipt");

  if (imageModal.hasChildNodes) {
    imageModal.replaceChild(newImage, imageModal.childNodes[0]);
  }
  else {
    imageModal.appendChild(newImage);
  }

  $('#imageModal').modal('show');
}




