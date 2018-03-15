/* Create an array with the values of all the select options in a column */
$.fn.dataTable.ext.order['dom-select'] = function (settings, col) {
	return this.api().column(col, { order: 'index' }).nodes().map(function (td, i) {
		return $('select', td).val();
	});
}
var data = {
	parameter: 6
};
var response = Post(data, function (result) {

	$.each(result, function (index, bean) {

		var username = bean.U_ID_AUTHOR;
		var first = bean.First;
		var last = bean.Last
		var amount = bean.R_AMOUNT;
		var description = bean.R_DESCRIPTION;
		var type = bean.RT_TYPE;
		var status = bean.RT_STATUS;
		var submit = bean.R_SUBMITTED;
		var resolved = bean.R_RESOLVED;
		var r_id = bean.R_ID;
		var r_first = bean.Resolver_First;
		var r_last = bean.Resolver_Last;
		var receipt=bean.R_RECEIPT;

		var body = document.getElementById("body");
		var tr = document.createElement("tr");

		var td = document.createElement("td");
		td.innerHTML = r_id;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = username;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = first;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = last;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = amount;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = description;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = type;
		tr.appendChild(td);

		var td=document.createElement("td");
		var link=document.createElement("a");
		link.innerHTML = first+"'s Receipt";
		link.href = receipt;
		td.appendChild(link);
		tr.appendChild(td);

		var td = document.createElement("td");
		td.appendChild(dropdown_builder(r_id, status));
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = submit;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = resolved;
		tr.appendChild(td);
		
		var td = document.createElement("td");
		td.innerHTML = r_first+" "+r_last;
		tr.appendChild(td);

		body.appendChild(tr)
	})

	$(document).ready(function () {
		$('#example').DataTable({
			"columns": [
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				{ "orderDataType": "dom-select" },
				null,
				null,
				null
			],
			"bDestroy": true
		});
	});

	$("select").change(function () {

		//make sure it's async!
		console.log(this.value+":"+this.id);
		var data = {
		        parameter: 11,
		        r_id: this.id,
		        status: this.value,
		        resolver: localStorage.getItem("username")
		    };
		    var response=Post_async(data,function(result)
		    {
		        console.log("Updated");
		    });
		
	});
});

function dropdown_builder(r_id, status) {
	var select = document.createElement("select");
	select.setAttribute("id", r_id);
	select.setAttribute("size", 1);
	//TODO: refactor so that this is an async ajax call that's called once on page load to get all statuses from the database
	var status_types = ["Pending", "Approved", "Denied"]
	for (var i = 0; i < status_types.length; i++) {
		var option = document.createElement("option");
		option.innerHTML = status_types[i];
		option.value = status_types[i]
		select.appendChild(option);
	}

	select.value = status;
	return select;
}

