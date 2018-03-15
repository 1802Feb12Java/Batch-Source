function approve()
{
    status = "resolved";
}
function deny()
{
    status = "pending";
}
function approveDenyEmployee()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() 
    {
        if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
               
             let JSONObj = JSON.parse(xhttp.responseText);
        }
        var url = "/ERS/approveDenyEmployee"
        let employee = document.getElementbyID("myInput")
        let parse = employee.value;
        let tokens = parse.split(" ");
        let firstname = tokens[0]
        let lastname = tokens[1]
        var sendJSON = {firstname: firstname, lastname:lastname,
            status: status
        };
        var myJSONString = JSON.stringify(sendJSON)
        xhttp.open("POST", url , true);
        xhttp.send("x=" + myJSONString);
    }
}
function displayEmployee()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() 
    {
        if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
               
             let JSONObj = JSON.parse(xhttp.responseText);
             var infoTable = document.createElement("table")
             infoTable.setAttribute('id', "infoTable")
             var row = infoTable.insertRow();
             var cell = row.insertCell()
             var cell1 = row.insertCell()
             var cell2 = row.insertCell()
             var cell3 = row.insertCell()
             
             cell.innerHTML = "Request resolver " + JSONObj.manager
             cell1.innerHTML = JSONObj.description
             cell2.innerHTML = JSONObj.amount
             cell3.innerHTML = JSONObj.status
             
             document.body.appendChild(infoTable);
              //still need to handle blob object
             let blob = document.createElement("img")
             blob.setAttribute('src', "JSONObj.blob")
             document.body.appendChild(blob)
             let approve = document.createElement("button")
             approve.setAttribute('id', "approve")
             approve.setAttribute('onclick',"approve()")
             document.body.appendChild(approve)
             let deny = document.createElement("button")
             deny.setAttribute('id', "deny")
             deny.setAttribute('onclick',"deny()")
             document.body.appendChild(deny)
         
             let submit = document.createElement("button")
             submit.setAttribute('id', "submit")
             submit.setAttribute('onclick', "approveDenyEmployee()")
             document.body.appendChild(submit)
        
    }
        var url = "/ERS/displayEmployee"
        let employee = document.getElementbyID("myInput")
        let parse = employee.value;
        let tokens = parse.split(" ");
        let firstname = tokens[0]
        let lastname = tokens[1]
        var sendJSON = {firstname: firstname, lastname:lastname};
        var myJSONString = JSON.stringify(sendJSON)
        xhttp.open("POST", url , true);
        xhttp.send("x=" + myJSONString);
    }
}
function getEmployees()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() 
    {
         if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
            //global    
            let employees = JSON.parse(xhttp.responseText);
            var empList = document.getElementById("myDropDown")
            for(let i = 0; i < Object.keys(employees).length; i++)
            {
                let empElement = document.createElement("a")
                empElement.setAttribute('onclick', "displayEmployee()")
                empElement.innerHTML = employees.firstname + " " 
                + employees.lastname;
                empList.appendChild(empElement)
            }
           
   
        }
    }
    var url = "/ERS/getEmployees"
    xhttp.open("POST", url , true);
    xhttp.send("x=" + "");
}
/* When the user clicks on the button,
            toggle between hiding and showing the dropdown content */
function btnDropDown() 
{
    getEmployees()
    document.getElementById("myDropdown").classList.toggle("show");
}
