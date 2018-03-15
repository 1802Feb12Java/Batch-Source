//Populate table only
var data = {
    parameter: 4,
    username: localStorage.getItem("username")
};
var response=Post(data,function(result)
{
	
    $.each(result, function(index, bean) 
    {
		//Amount,Description,Type,Status,Submitted,Resolved

		var amount=bean.R_AMOUNT;
		var description=bean.R_DESCRIPTION;
		var type=bean.RT_TYPE;
		var status=bean.RT_STATUS;
		var submit=bean.R_SUBMITTED;
		var resolved=bean.R_RESOLVED;
		var receipt=bean.R_RECEIPT;

		var body=document.getElementById("body");
		var tr=document.createElement("tr");

		var td=document.createElement("td");
		td.innerHTML=amount;
		tr.appendChild(td);

		var td=document.createElement("td");
		td.innerHTML=description;
		tr.appendChild(td);

		var td=document.createElement("td");
		td.innerHTML=type;
		tr.appendChild(td);

		var td=document.createElement("td");
		var link=document.createElement("a");
		link.innerHTML = localStorage.getItem("username")+" Receipt";
		link.href = receipt;
		td.appendChild(link);
		tr.appendChild(td);

		var td=document.createElement("td");
		td.innerHTML=status;
		tr.appendChild(td);

		var td=document.createElement("td");
		td.innerHTML=submit;
		tr.appendChild(td);

		var td=document.createElement("td");
		td.innerHTML=resolved;
		tr.appendChild(td);

		
		body.appendChild(tr)
    		
    		
    })

});