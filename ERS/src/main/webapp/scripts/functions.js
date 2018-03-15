/**
 * 
 */

function loadPending(array){    
    for(let i = 0; i < array.length; i++){
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "request" + i);
        document.getElementById("pending").appendChild(newrow);
        var col1 = document.createElement("td");
        var col2 = document.createElement("td");
        var col3 = document.createElement("td");
        //var col4 = document.createElement("td");
        var col5 = document.createElement("td");
        var col6 = document.createElement("td");
        var col7 = document.createElement("td");
        
        var col8 = document.createElement("td");
        var col9 = document.createElement("td");
        
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
        col5.textContent = array[i].r_submitted;
        col6.textContent = array[i].u_id_author;
        col7.textContent = array[i].rt_type;
        
        col8.innerHTML = "<form action='viewPending' method='post'><input type='hidden' name='rid' value='" + array[i].r_id + "'><input type='hidden' name='new_rs' value='1'><button class='btn btn-custom btn-full-width' type='submit'>Approve</button></form>";
        col9.innerHTML = "<form action='viewPending' method='post'><input type='hidden' name='rid' value='" + array[i].r_id + "'><input type='hidden' name='new_rs' value='2'><button class='btn btn-custom btn-full-width' type='submit'>Deny</button></form>";
       
        document.getElementById("request" + i).appendChild(col1);
        document.getElementById("request" + i).appendChild(col2);
        document.getElementById("request" + i).appendChild(col3);
        document.getElementById("request" + i).appendChild(col4);
        document.getElementById("request" + i).appendChild(col5);
        document.getElementById("request" + i).appendChild(col6);
        document.getElementById("request" + i).appendChild(col7);
        document.getElementById("request" + i).appendChild(col8);
        document.getElementById("request" + i).appendChild(col9);

    }

}
function getPendingArray(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function(){
    	console.log("xhr.readyState: " + xhr.readyState);
    	console.log("xhr.status: " + xhr.status);
       if(xhr.readyState==4 && xhr.status== 200){
    	   console.log(xhr.responseText);
           var list = JSON.parse(xhr.responseText);
           loadPending(list);
       }
   }
    
   xhr.open("GET","http://localhost:8080/ERS/viewPending", true);
   xhr.send();
}



window.onload = function(){
    getPendingArray();
}

