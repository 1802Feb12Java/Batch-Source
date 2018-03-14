function loadReqs(array){
    
    var listOfReqs = array;
    var theTable = document.getElementById("pendreqlist");

    for(let i = 0; i < array.length; i++){
        var newrow = document.createElement("tr");
        newrow.setAttribute("id", "request" + i);
        document.getElementById("pendreqlist").appendChild(newrow);
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
        col1.textContent = array[i].r_id;
        col2.textContent = array[i].r_amount;
        col3.textContent = array[i].r_description;
        // image things
        col4.innerHTML = '<button type="button" class="btn btn-custom" data-toggle="modal" data-target="#imgmodal'+i+'">'+
                        'Click for Receipt' +
                        '</button>' +
                        '<div class="modal fade" id="imgmodal'+ i +'" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                        '<div class="modal-dialog" role="document">' +
                        '<div class="modal-content">' +
                        '<div class="modal-header orange-title-custom">' +
                        '<h5 class="modal-title" id="exampleModalLabel">Receipt Image</h5>' +
                        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
                        '<span aria-hidden="true">&times;</span>' +
                        '</button>' +
                        '</div>' +
                        '<div class="modal-body">' +
                        '<img src="data:img/png;base64,' + array[i].r_receipt +'">' +
                        '</div>' +
                        '<div class="modal-footer">' +
                        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
                        '</div></div></div></div>'

        col5.textContent = array[i].r_submitted;
        col6.textContent = array[i].u_author;
        col7.textContent = array[i].rt_type;
        col8.innerHTML = "<form action='getpendingservlet' method='post'><input type='hidden' name='reqid' value='" + array[i].r_id + "'><input type='hidden' name='newstatus' value='1'><button class='btn btn-custom btn-full-width' type='submit'>Approve</button></form>";
        col8.innerHTML += "<form action='getpendingservlet' method='post'><input type='hidden' name='reqid' value='" + array[i].r_id + "'><input type='hidden' name='newstatus' value='2'><button class='btn btn-custom btn-full-width' type='submit' style='margin-top: 5px'>Deny</button></form>";
        document.getElementById("request" + i).appendChild(col1);
        document.getElementById("request" + i).appendChild(col2);
        document.getElementById("request" + i).appendChild(col3);
        document.getElementById("request" + i).appendChild(col4);
        document.getElementById("request" + i).appendChild(col5);
        document.getElementById("request" + i).appendChild(col6);
        document.getElementById("request" + i).appendChild(col7);
        document.getElementById("request" + i).appendChild(col8);
    }//end for


    $(document).ready( function () {
        $('#ajaxTable').DataTable();
    } );
}

function getRequestArray(){
    var xhr = new XMLHttpRequest();
   
   xhr.onreadystatechange= function(){
       if(xhr.readyState==4 && xhr.status== 200){
           console.log(xhr.responseText);
           var list = JSON.parse(xhr.responseText);
           loadReqs(list);
       }
   }
 
   xhr.open("GET","http://localhost:8080/Project1ERS/getpendingservlet", true);
   xhr.send();
}

window.onload = function(){
    getRequestArray();
}