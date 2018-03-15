//get user info from database

var data = {
    parameter: 10,
    username: localStorage.getItem("username"),
};
var response=Post(data,function(result)
{    
	$("#username").val(result.username);
	$("#first").val(result.firstname);
	$("#last").val(result.lastname);
	$("#email").val(result.email);
	$("#password").val(result.password);

	console.log(result);
});

var locked=true;

$("#edit").click(function(event)
{
    event.preventDefault();
    if(locked)
        $("#edit").html("Save");
    else
        $("#edit").html("Edit");

    locked=!locked;

    if(locked==false)
    {
        $("input").each(function()
        {
            $(this).attr('disabled',false)
        })
    }
    else
    {
        var data = {
            parameter: 5,
            username: localStorage.getItem("username"),
            new_username: $("#username").val(),
            new_first: $("#first").val(),
            new_last: $("#last").val(),
            new_email: $("#email").val(),
            new_password: $("#password").val()
        };
        var response=Post(data,function(result)
        {
            console.log(result);
        });
        
        localStorage.setItem("username",$("#username").val());

        $("input").each(function(){
            $(this).attr('disabled',true)
        })
    }

});

$("#logout").click(function(event)
{
    event.preventDefault();
	var data = {
            parameter: 9,
            message: "Hello from Employee_Info",
        };
        var response=Post(data,function(result)
        {
            //do stuff with result
            console.log(result);
        });
    //nullify session
    localStorage.setItem("username","null");
    localStorage.setItem("permission","null");
    window.location.replace("../Login.html");
});