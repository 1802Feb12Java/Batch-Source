/**
 * 
 */

function viewEmps(array){
    var theTable = document.getElementById("employees");
    
    for(let i = 0; i < array.length; i++){
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "request" + i);
        document.getElementById("employees").appendChild(newrow);
        var col1 = document.createElement("td");
        var col2 = document.createElement("td");
        var col3 = document.createElement("td");
        var col4 = document.createElement("td");
        var col5 = document.createElement("td");

        var col6 = document.createElement("td");

        col1.textContent = array[i].u_id;
        col2.textContent = array[i].u_username;
        col3.textContent = array[i].u_firstname;
        col4.textContent = array[i].u_lastname;
        col5.textContent = array[i].u_email;

        col6.innerHTML = "<form action='viewEmps' method='post'><input type='hidden' name='uid' value='" + array[i].u_id + "'><button class='btn btn-custom btn-full-width' type='submit'>Select</button></form>";
       
        document.getElementById("request" + i).appendChild(col1);
        document.getElementById("request" + i).appendChild(col2);
        document.getElementById("request" + i).appendChild(col3);
        document.getElementById("request" + i).appendChild(col4);
        document.getElementById("request" + i).appendChild(col5);

        document.getElementById("request" + i).appendChild(col6);

    }

}
function getEmpArray(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
    	console.log("xhr.readyState: " + xhr.readyState);
    	console.log("xhr.status: " + xhr.status);
       if(xhr.readyState==4 && xhr.status== 200){
    	   console.log(xhr.responseText);
           var list = JSON.parse(xhr.responseText);
           viewEmps(list);
       }
   }
    
   xhr.open("GET","http://localhost:8080/ERS/viewEmps", true);
   xhr.send();
}
window.onload = function(){
    getEmpArray();
}


