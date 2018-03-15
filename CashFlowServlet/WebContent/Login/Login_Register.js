document.getElementById("login").onclick=function()
{
	var fn=document.getElementById("fn").value;
	var ln=document.getElementById("ln").value;
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    var email=document.getElementById("email").value;
    var redirect_code=authenticate(username,password,email,fn,ln);
}

function authenticate(username,password,email,fn,ln)
{
    if( !(username && password && email && ln && fn) )
    {
        incorrect_blank_field();
    }
    else
    	{
    		var data = {
            parameter: 2,
            username: username,
            password: password,
            email: email,
            fn: fn,
            ln: ln
        };
        var response=Post(data,function(result)
        {
            if(result.status_message=="taken")
            	incorrect();
        });
    	}
}

function incorrect()
{
    document.getElementById("incorrect").innerHTML="Error, username is already taken";
    document.getElementById("incorrect").style.display="block";
}

function incorrect_blank_field()
{
    document.getElementById("incorrect").innerHTML="Error, fields cannot be blank";
    document.getElementById("incorrect").style.display="block";
}

//Extra behavior

//Hit enter only on password will login

document.getElementById("password").addEventListener("keyup", function(event) 
{
    event.preventDefault();
    if (event.keyCode === 13) 
    {
        document.getElementById("login").click();
    }
});







