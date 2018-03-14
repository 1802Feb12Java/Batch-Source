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
            document.getElementById("page").innerHTML = this.responseText;
            let selectedButton = 5;
            console.log("selected: " + selectedButton);
            let description = "";
            let amount = 0.00;

            document.getElementById("requestForm").addEventListener('submit', function(e){
                e.preventDefault();
                console.log("Submit button");
                let radio = document.getElementsByName("rType");

                for (let i = 0; i < 5; i++){
                    console.log("for: " + i);
                    if(radio[i].checked){
                        selectedButton = i + 1;
                        console.log("selected now: " + selectedButton);
                        break;
                    }
                }
                console.log("For loop complete");

                description = document.getElementsByName("description")[0].value;
                amount = document.getElementsByName("amountRequested")[0].value;

                console.log("Desc: " + description + " amount: " + amount + "selected: " +selectedButton);

                request = '{"selected" : "' + selectedButton + '",' +
                            '"description" :' +'"' + description + '",' +
                            '"amount" : "' + amount + '"}';
                console.log("Request looks like: " + request);

                sendRequest(request);
            });
        };
    };
 
    //send the request
    xhr.send();
};

let sendRequest = function(req) {
    let xhr = new XMLHttpRequest();
    console.log("In send request with: " + req);
    xhr.open('POST', '/ers/CreateReimbursement', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    console.log("After set req header: " + xhr.readyState);
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log("Readystate change in sendRequest");
        }
    }

    xhr.send(req);
    console.log("After send: " + xhr.readyState);
};

let viewEPending = function(){
    console.log('View Employee Pending');
};

let viewEResolved = function(){
    console.log('View Employee Resolved');
};

let viewEInfo = function(){
    //create the XHR object
    let xhr = new XMLHttpRequest();

    //Open the request with the parameters: (type, url/filename, async)
    xhr.open('GET', '/ers/ViewEmployeeInfo', true);
    
    console.log("View: " + xhr);
    //create the function to handle the request
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            let employeeJSON = JSON.parse(this.responseText);
            showCard(employeeJSON);
        };
    };
 
    //send the request
    xhr.send();
};

let showCard = function(employee){
    let xhr = new XMLHttpRequest();

    xhr.open('GET', '/ers/employeeCard.html', true);
    xhr.onreadystatechange = function(){

        if(this.readyState == 4 && this.status == 200){
            document.getElementById("page").innerHTML = this.responseText;
            document.getElementById("employeeName").textContent = employee.fname + " " + employee.lname +
                    ", " + employee.role;
            document.getElementsByName("fname")[0].placeholder=employee.fname;
            document.getElementsByName("lname")[0].placeholder=employee.lname;
            document.getElementsByName("email")[0].placeholder=employee.email;

            document.getElementsByName("fname")[0].addEventListener("input", function(){
                employee.fname = document.getElementsByName("fname")[0].value;
            });

            document.getElementsByName("lname")[0].addEventListener("input", function(){
                employee.lname = document.getElementsByName("lname")[0].value;
            });
            document.getElementsByName("email")[0].addEventListener("input", function(){
                employee.email = document.getElementsByName("email")[0].value;
            });

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

    sendEmp = JSON.stringify(emp);
    xhr.open('POST', '/ers/UpdateEmployeeInfo', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            viewEInfo();
        }
    }

    xhr.send(sendEmp);
}

document.getElementById("submitReimbursement").addEventListener("click", submit);
document.getElementById("viewEPending").addEventListener("click", viewEPending);
document.getElementById("viewEResolved").addEventListener("click", viewEResolved);
document.getElementById("viewEInfo").addEventListener("click", viewEInfo);


