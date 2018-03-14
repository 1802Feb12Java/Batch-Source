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
            let description = "";
            let amount = 0.00;
            let radio = document.getElementsByName("rType");

            document.getElementById("requestForm").addEventListener('submit', function(e){
                e.preventDefault();
                for (let i = 1; i <= 5; i++){
                    if(radio[i].checked){
                        selectedButton = i;
                        break;
                    }
                }
                
                description = document.getElementsByName("description")[0].value;
                amount = document.getElementsByName("amountRequested")[0].value;

                let request = '{"selection" : '+ selectedButton +', "description" : "' + description +
                        '", "amount" : ' + amount + '}';

                sendRequest(request);
            });
        };
    };
 
    //send the request
    xhr.send();
};

let sendRequest = function(req) {
    console.log(req);
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
    xhr.open("POST", "/ers/UpdateEmployeeInfo", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log("readychange")
            viewEInfo();
        }
    }

    xhr.send(sendEmp);
}

document.getElementById("submitReimbursement").addEventListener("click", submit);
document.getElementById("viewEPending").addEventListener("click", viewEPending);
document.getElementById("viewEResolved").addEventListener("click", viewEResolved);
document.getElementById("viewEInfo").addEventListener("click", viewEInfo);


