function sendReimbursement()
{
        xhttp.onreadystatechange = function() 
    {
            if (this.readyState == 4 && this.status == 200) {
        // Typical action to be performed when the document is ready:
            
        var responseJSON = JSON.parse(xhttp.responseText)
        
        var infoTable = document.createElement("table")
        var row = infoTable.insertRow();
        var cell = row.insertCell()
        var cell1 = row.insertCell()
        cell.innerHTML = responseJSON.description
        cell1.innerHTML = responseJSON.status
        document.body.appendChild(infoTable);
        

        }
    }
    
    var xhttp = new XMLHttpRequest();
    var url = "/ERS/Reimbursement"
    var amount = document.getElementById("amount")
    var description = document.getElementById("description")
    var uploadBlob = document.getElementById("uploadBlob")
    var sendJSON = {amount: amount.value, description:description.value, 
        blob:uploadBlob.value, status:"pending"};
    var myJSONString = JSON.stringify(sendJSON)
    xhttp.open("POST", url , true);
    xhttp.send("x=" + myJSONString);
}