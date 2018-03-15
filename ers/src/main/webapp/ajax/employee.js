/* Ready States:
0 - request not initialized
1 - server connection established
2 - request received by the server
3 - server is processing the request
4 - request finished and response is ready

HTTP Status:

200 - OK
403 - Forbidden
404 - Not found
*/

let submit = function(){
    //create the XHR object
    let xhr = new XMLHttpRequest();

    //Open the request with the parameters: (type, url/filename, async)
    xhr.open('GET', "/ers/requestSubmission.html", true);

    //create the function to handle the request
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            //get the request submission form and initialize the variables
            document.getElementById("page").innerHTML = this.responseText;
            let selectedButton = 5;
            let description = "";
            let amount = 0.00;

            //add the event listener to the form submit
            document.getElementById("requestForm").addEventListener('submit', function(e){
                //prevent the form from submitting via normal method
                e.preventDefault();
                console.log("Submit button");
                let radio = document.getElementsByName("rType");

                //locate the checked button and assign the value for backend usage
                for (let i = 0; i < 5; i++){
                    console.log("for: " + i);
                    if(radio[i].checked){
                        selectedButton = i + 1;
                        console.log("selected now: " + selectedButton);
                        break;
                    }
                }

                //get the remaining values from the form fields
                description = document.getElementsByName("description")[0].value;
                amount = document.getElementsByName("amountRequested")[0].value;

                //tediously formulate the JSON string by hand, follow up with several hours
                //of debugging before it works.
                request = '{"selected" : "' + selectedButton + '",' +
                            '"description" :' +'"' + description + '",' +
                            '"amount" : "' + amount + '"}';

                //pass the JSON-formatted string to the reimbursement request processor
                sendRequest(request);
            });
        };
    };
 
    //send the request
    xhr.send();
};

let sendRequest = function(req) {
    let xhr = new XMLHttpRequest();

    //open the requeset and set the header
    xhr.open('POST', '/ers/CreateReimbursement', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            //call the submission form again to clear out the values
            submit();
        }
    }

    xhr.send(req);
};

let viewEPending = function(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ers/GetPendingReimbursements', true);

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            let jArray = JSON.parse(this.responseText);
            viewTable(jArray);
        }       
    }
    
    xhr.send();
};

let viewEResolved = function(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ers/GetResolvedReimbursements', true);

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            let jArray = JSON.parse(this.responseText);
            viewTable(jArray);
        }       
    }
    
    xhr.send();
};

let viewEInfo = function(){
    //create the XHR object
    let xhr = new XMLHttpRequest();

    //get the employee values from the servlet
    xhr.open('GET', '/ers/ViewEmployeeInfo', true);

    //create the function to handle the request
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            //parse the string into a JSON object for convenience
            let employeeJSON = JSON.parse(this.responseText);

            //pass the JSON object to the employee view/change information page
            showCard(employeeJSON);
        };
    };
 
    //send the request
    xhr.send();
};

let viewTable = function(arr){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ers/table.html', true);
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log(arr);
            document.getElementById("page").innerHTML = this.responseText;
            table = document.getElementById("table");
            for(let i = 0; i < arr.length; i++){
                var row = table.insertRow(1);
                var cell1 = row.insertCell(0);
                cell1.innerHTML = arr[i] ["type"];
                var cell2 = row.insertCell(0);
                cell2.innerHTML = arr[i] ["status"];
                var cell3 = row.insertCell(0);
                cell3.innerHTML = arr[i] ["resolver"];
                var cell4 = row.insertCell(0);
                cell4.innerHTML = arr[i] ["author"];
                var cell5 = row.insertCell(0);
                cell5.innerHTML = arr[i] ["resolved"];
                var cell6 = row.insertCell(0);
                cell6.innerHTML = arr[i] ["submitted"];
                var cell7 = row.insertCell(0);
                cell7.innerHTML = arr[i] ["receipt"];
                var cell8 = row.insertCell(0);
                cell8.innerHTML = arr[i] ["description"];
                var cell9 = row.insertCell(0);
                cell9.innerHTML = arr[i] ["amount"];
                var cell10 = row.insertCell(0);
                cell10.innerHTML = arr[i] ["r_id"];
            }
        }
    }

    xhr.send();
}

let showCard = function(employee){
    let xhr = new XMLHttpRequest();

    xhr.open('GET', '/ers/employeeCard.html', true);
    xhr.onreadystatechange = function(){

        if(this.readyState == 4 && this.status == 200){
            //assign the values to the appropriate fields and placeholders
            document.getElementById("page").innerHTML = this.responseText;
            document.getElementById("employeeName").textContent = employee.fname + " " + employee.lname +
                    ", " + employee.role;
            document.getElementsByName("fname")[0].placeholder=employee.fname;
            document.getElementsByName("lname")[0].placeholder=employee.lname;
            document.getElementsByName("email")[0].placeholder=employee.email;

            //add event listeners to update the JSON object when input is detected
            document.getElementsByName("fname")[0].addEventListener("input", function(){
                employee.fname = document.getElementsByName("fname")[0].value;
            });

            document.getElementsByName("lname")[0].addEventListener("input", function(){
                employee.lname = document.getElementsByName("lname")[0].value;
            });
            document.getElementsByName("email")[0].addEventListener("input", function(){
                employee.email = document.getElementsByName("email")[0].value;
            });

            //add the custome event listener to the submit button, and pass the updated
            //employee values to finalize the changes when the form is submitted
            document.getElementById("employeeInfo").addEventListener("submit", function(e){
                e.preventDefault();
                submitChange(employee);
            });
        }
    }

    xhr.send();
}

let submitChange = function (emp){
    let xhr = new XMLHttpRequest();

    //stringify the employee values to prepare to send ot the servlet
    sendEmp = JSON.stringify(emp);

    //Post the form data to the servlet
    xhr.open('POST', '/ers/UpdateEmployeeInfo', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            //call the updated employee view with the new fields in place
            viewEInfo();
        }
    }

    xhr.send(sendEmp);
}

//add the event listeners for the nav bar items
document.getElementById("submitReimbursement").addEventListener("click", submit);
document.getElementById("viewEPending").addEventListener("click", viewEPending);
document.getElementById("viewEResolved").addEventListener("click", viewEResolved);
document.getElementById("viewEInfo").addEventListener("click", viewEInfo);


