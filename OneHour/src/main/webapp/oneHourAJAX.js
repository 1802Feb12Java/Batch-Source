window.onload = function(){
    console.log("onload functions");
    document.getElementById("createSubmit").addEventListener("click", getInput, false);
    document.getElementById("fetchSubmit").addEventListener("click", getPersonByID, false);
}

function getInput(){
        var input_id = document.getElementById("ID").value;
        var input_name = document.getElementById("Name").value;
        var person = {input_id, input_name};

        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                console.log(xhr.responseText);
                var response = JSON.parse(xhr.responseText);
                dataSubmitAlert(response);
            }
        }

        xhr.open("PUT", "localhost:8080/userServlet/", true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send(JSON.stringify({id:input_id, name:input_name}));
        console.log(JSON.stringify(person));

    
}//end function getInput

function dataSubmitAlert(response){
    if(response == 1){
        alert("Your input has been successfully submitted!");
    } else if(response == 0){
        alert("There was an error in submitting your input.");
    } else {
        console.log("Something went wrong, the response was not 0 or 1.");
        console.log("Verify whether or not database has been updated.")
        alert("ERROR: please check the console log.");
    }
}//end function dataSubmitAlert


function getPersonByID(){
    var personID = document.getElementById("fetchID").value;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            console.log(xhr.responseText);
            var personObj = JSON.parse(xhr.responseText);
            loadThisPerson(personObj);
        }
    }

    xhr.open("GET", "localhost:8080/userServlet/",true)
    xhr.send();
}//end function getPersonById

function loadThisPerson(personObj){
    document.getElementById("fetchedData").innerHTML = personObj;
}//end function loadThisPerson

