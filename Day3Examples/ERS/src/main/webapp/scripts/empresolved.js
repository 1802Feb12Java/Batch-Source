/**
 * 
 */
function loadPending(array){
    var listOfReqs = array;
    var theTable = document.getElementById("resolved");
    

    for(let i = 0; i < array.length; i++){
    	console.log("in for loop for adding table row");
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "request" + i);
        document.getElementById("resolved").appendChild(newrow);
        var col1 = document.createElement("td");
        var col2 = document.createElement("td");
        var col3 = document.createElement("td");
        //var col4 = document.createElement("img");
        var col5 = document.createElement("td");
        var col6 = document.createElement("td");
        var col7 = document.createElement("td");
        var col8 = document.createElement("td");
        var col9 = document.createElement("td");
        var col10 = document.createElement("td");
        
        col1.textContent = array[i].r_id;
        col2.textContent = array[i].r_amount;
        col3.textContent = array[i].r_description;
        if (array[i].r_receipt== "null") {
        	var col4 = document.createElement("td");
            col4.textContent = "No receipt";
        } else {
        	var col4 = document.createElement("img");
        	col4.src = "data:img/png;base64,"+ array[i].r_receipt;
        	col4.width = "150";
        	col4.height = "200";
        }
       // col4.textContent = array[i].r_receipt;
        col5.textContent = array[i].r_submitted;
        col6.textContent = array[i].r_resolved;
        col7.textContent = array[i].u_id_author;
        col8.textContent = array[i].u_id_resolver;
        col9.textContent = array[i].rt_type;
        col10.textContent = array[i].rt_status;
       
        document.getElementById("request" + i).appendChild(col1);
        document.getElementById("request" + i).appendChild(col2);
        document.getElementById("request" + i).appendChild(col3);
        document.getElementById("request" + i).appendChild(col4);
        document.getElementById("request" + i).appendChild(col5);
        document.getElementById("request" + i).appendChild(col6);
        document.getElementById("request" + i).appendChild(col7);
        document.getElementById("request" + i).appendChild(col8);
        document.getElementById("request" + i).appendChild(col9);
        document.getElementById("request" + i).appendChild(col10);
        


    }

}
function getPendingArray(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
       if(xhr.readyState==4 && xhr.status== 200){
    	   console.log(xhr.responseText);
           var list = JSON.parse(xhr.responseText);
           loadPending(list);
       }
   }
    
   xhr.open("GET","http://localhost:8080/ERS/empResolved", true);
   xhr.send();
}
window.onload = function(){
    getPendingArray();
}