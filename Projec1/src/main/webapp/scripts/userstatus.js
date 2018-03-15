//pull information from java servlet and display on the page
function displayInfo(){
    
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
                if (xhr.readyState == 4 && xhr.status == 200) {
                    console.log("goes through onreadystatchange");
                    console.log(xhr.responseText);
                    var view = JSON.parse(xhr.responseText); //convert json into js object
                        display(view);            
                }
              
            }
           
            xhr.open("get", "http://localhost:8080/Projec1/requestservlet" , true);
            xhr.send(null);
  
}
function display(jsoninfo){
   var col = [];
   for (var i = 0; i < Object.keys(jsoninfo).length; i++) {
       for (var key in jsoninfo[i]) {
           if (col.indexOf(key) === -1) {
               col.push(key);
               }
           }
       }
   

    // CREATE DYNAMIC TABLE.
    var table = document.createElement("table");

      // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

      var tr = table.insertRow(-1);                   // TABLE ROW.

      for (var i = 0; i < col.length; i++) {
          var th = document.createElement("th");      // TABLE HEADER.
          var head = ["Request ID","Amount","Description","Time Submitted","Time Resolved","Rsolver ID","Status","Type"];

          th.innerHTML = head[i];
          tr.appendChild(th);
      }
         // ADD JSON DATA TO THE TABLE AS ROWS.
         for (var i = 0; i <  Object.keys(jsoninfo).length; i++) {

            tr = table.insertRow(-1);
            for (var j = 0; j < col.length; j++) {
                var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = jsoninfo[i][col[j]];
              
            }
        }
        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("placeholder");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
        // styling the table
        var x = document.createElement("STYLE");
        var t = document.createTextNode("table,th,td{border: 2px solid black}");
        var t1 = document.createTextNode("th,td{border: padding: 15px}");
        var t2 = document.createTextNode("th{text-align: center}");
        var t3 = document.createTextNode("table{border-spacing: 10px;}");
        var t4 = document.createTextNode("image{max-width: 150px;}");
        var t5 = document.createTextNode("image{max-height: 150px;}");


        x.appendChild(t);
        x.appendChild(t1);
        x.appendChild(t2);
        x.appendChild(t3);
        x.appendChild(t4);
        x.appendChild(t5);

        document.head.appendChild(x);


}