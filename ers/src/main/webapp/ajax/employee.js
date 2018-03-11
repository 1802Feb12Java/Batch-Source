
let submit = function(){
    //create the XHR object
    let xhr = new XMLHttpRequest();

    //Open the request with the parameters: (type, url/filename, async)

    //create the function to handle the request
    xhr.onload = function(){
        if(this.status == 200){
            console.log(this.responseText);
        }
    }

    //send the request
    xhr.send();
};

let viewEPending = function(){
    console.log('View Employee Pending');
};

let viewEResolved = function(){
    console.log('View Employee Resolved');
};

let viewEInfo = function(){
    console.log('View Employee info');
}

document.getElementById("submitReimbursement").addEventListener("click", submit);
document.getElementById("viewEPending").addEventListener("click", viewEPending);
document.getElementById("viewEResolved").addEventListener("click", viewEResolved);
document.getElementById("viewEInfo").addEventListener("click", viewEInfo);


