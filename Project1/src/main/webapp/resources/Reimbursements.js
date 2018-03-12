function loadReqs(array){
    
    var listOfReqs = array;
    var theTable = document.getElementById("myTable");
    
    console.log(array);

    for(let i = 0; i < array.length; i++){
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "request" + i);
        theTable.appendChild(newrow);
        var col1 = document.createElement("td");
        var col2 = document.createElement("td");
        var col3 = document.createElement("td");
        var col4 = document.createElement("td");
        var col5 = document.createElement("td");
        var col6 = document.createElement("td");
        var col7 = document.createElement("td");
        var col8 = document.createElement("td");
        var col9 = document.createElement("td");
        var col10 = document.createElement("td");
        var col11 = document.createElement("td");
        col1.textContent = array[i].id;
        col2.textContent = array[i].amount;
        col3.textContent = array[i].description;
        col4.textContent = array[i].receipt;
        col5.textContent = array[i].submitted;
        col6.textContent = array[i].resolved;
        col7.textContent = array[i].authorId;
        col8.textContent = array[i].resolverId;
        col9.textContent = array[i].typeId;
        col10.textContent = array[i].statusId;
        col11.innerHTML = "<form action='reimbursements' method='post'><input type='hidden' name='reqid' value='" + array[i].id + "'><input type='hidden' name='newstatus' value='1'><button class='btn btn-custom btn-full-width' type='submit'>Approve</button></form>";
        col11.innerHTML += "<form action='reimbursements' method='post'><input type='hidden' name='reqid' value='" + array[i].id + "'><input type='hidden' name='newstatus' value='2'><button class='btn btn-custom btn-full-width' type='submit'>Deny</button></form>";
        newrow.appendChild(col1);
        newrow.appendChild(col2);
        newrow.appendChild(col3);
        newrow.appendChild(col4);
        newrow.appendChild(col5);
        newrow.appendChild(col6);
        newrow.appendChild(col7);
        newrow.appendChild(col8);
        newrow.appendChild(col9);
        newrow.appendChild(col10);
        newrow.appendChild(col11);
    }//end for

}

function getRequestArray(){
    var xhr = new XMLHttpRequest();
   
   xhr.onreadystatechange= function(){
       if(xhr.readyState==4 && xhr.status== 200){
           var list = JSON.parse(xhr.responseText);
           loadReqs(list);
       }
   }
 
   xhr.open("GET","http://localhost:8080/Project1/reimbursements", true);
   xhr.send();
}

window.onload = function(){
    getRequestArray();
}