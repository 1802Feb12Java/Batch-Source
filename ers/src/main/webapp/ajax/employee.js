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
    xhr.open('GET', 'index.html', true);

    //create the function to handle the request
    console.log(xhr);
    xhr.onreadystatechange = function(){
        console.log("status: " + this.status);
        if(this.readyState == 4 && this.status == 200){
            document.getElementById("page").innerHTML = this.responseText;
        };
    };
 
    //send the request
    console.log("Sending request");
    xhr.send();
    console.log("Sent");
};

let viewEPending = function(){
    console.log('View Employee Pending');
    console.log('And again');
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

            document.getElementById("employeeInfo").addEventListener("submit", function(e, employee){
                e.preventDefault();
            })
        }
    }

    xhr.send();
}

document.getElementById("submitReimbursement").addEventListener("click", submit);
document.getElementById("viewEPending").addEventListener("click", viewEPending);
document.getElementById("viewEResolved").addEventListener("click", viewEResolved);
document.getElementById("viewEInfo").addEventListener("click", viewEInfo);


