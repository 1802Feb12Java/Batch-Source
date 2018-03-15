/**
 * THIS ONE I CAN CHANGE SO TABLE DATA ELEMENTS ARE CLICKABLE TO UPDATE
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
        col3.textContent = array[i].u_password;
        col4.textContent = array[i].u_firstname;
        col5.textContent = array[i].u_lastname;
        col6.textContent = array[i].u_email;
        
        document.getElementById("request" + i).appendChild(col1);
        document.getElementById("request" + i).appendChild(col2);
        document.getElementById("request" + i).appendChild(col3);
        document.getElementById("request" + i).appendChild(col4);
        document.getElementById("request" + i).appendChild(col5);
        document.getElementById("request" + i).appendChild(col6);

        document.getElementById("emp1").value=array[i].u_username;
        document.getElementById("emp2").value=array[i].u_password;
        document.getElementById("emp3").value=array[i].u_firstname;
        document.getElementById("emp4").value=array[i].u_lastname;
        document.getElementById("emp5").value=array[i].u_email;
    }

}
function getEmpArray(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
       if(xhr.readyState==4 && xhr.status== 200){
           var list = JSON.parse(xhr.responseText);
           viewEmps(list);
       }
   }
    
   xhr.open("GET","http://localhost:8080/ERS/empInfo", true);
   xhr.send();
}
window.onload = function(){
    getEmpArray();
}
/**
 * 
 */