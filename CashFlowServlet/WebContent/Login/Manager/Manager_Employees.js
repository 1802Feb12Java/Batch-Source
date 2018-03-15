console.log("hi");
var data = {
	parameter: 7
};
var response = Post(data, function (result) {

	$.each(result, function (index, bean) {
		console.log("hi" + bean);
		//Amount,Description,Type,Status,Submitted,Resolved
		var uid = bean.U_ID;
		var username = bean.U_UserName;
		var first = bean.U_FirstName;
		var last = bean.U_LastName;
		var email = bean.U_Email;
		var role = bean.UR_Role;

		var body = document.getElementById("body");
		var tr = document.createElement("tr");

		var td = document.createElement("td");
		td.innerHTML = uid;
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
		td.innerHTML = email;
		tr.appendChild(td);

		var td = document.createElement("td");
		td.innerHTML = role;
		tr.appendChild(td);

		body.appendChild(tr)

	})

});