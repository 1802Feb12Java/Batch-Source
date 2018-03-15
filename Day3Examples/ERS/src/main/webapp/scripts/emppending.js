/**
 * 
 */

function loadPending(array){
    var listOfReqs = array;
    var theTable = document.getElementById("pending");
    
    
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
        //col4.textContent = array[i].r_receipt;
        col5.textContent = array[i].r_submitted;
        col6.textContent = array[i].rt_type;
        
        col7.innerHTML = "<form action='empPending' method='post' enctype='multipart/form-data'><input type='hidden' name='rid' value='" + array[i].r_id + "'><input type='file' name='pic' id='image'/><button class='btn btn-custom btn-full-width' type='submit'>Submit</button>";
  
        document.getElementById("request" + i).appendChild(col1);
        document.getElementById("request" + i).appendChild(col2);
        document.getElementById("request" + i).appendChild(col3);
        document.getElementById("request" + i).appendChild(col4);
        document.getElementById("request" + i).appendChild(col5);
        document.getElementById("request" + i).appendChild(col6);  
        
        document.getElementById("request" + i).appendChild(col7);
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
    
   xhr.open("GET","http://localhost:8080/ERS/empPending", true);
   xhr.send();
}



window.onload = function(){
    getPendingArray();
}


function imageUpload(){
	console.log('imageTest()');
	var fileInput = document.getElementById('image');
	console.log(fileInput);
	var file = fileInput.files[0];
	var img = new FormData();
	img.append('img', file);
	img.append('image', reimNum);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('msgAlert').innerHTML = xhr.responseText;
			viewRequests();
		}
	}
	xhr.open("POST", "empPending", true); 
    xhr.send(img); 
}
