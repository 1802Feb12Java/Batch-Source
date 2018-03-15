document.getElementById("login").onclick=function()
{
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    var redirect_code=authenticate(username,password);
}

function authenticate(username,password)
{
	//send the username and password to login using parameter 1
	//once in login, check if there is one (first parameter sent back here). if there is, get the role, and direct to the appropriate page
	var data = {
	        parameter: 1,
	        username: username,
	        password: password
	    };
	    var response=Post(data,function(result)
	    {
	        if(result.verified==1)
	        	{
	        		if(result.role=="Employee")
	        		{
	        			open_employee_page(username);
	        		}
	        		else
	        		{
	        			open_manager_page(username);
	        		}
	        	}
	        else
	        	reload();
	    });
}

function reload()
{
    //TODO: should say logging in while it authenticates. should only load a new page if it is found
    var header=document.getElementById("incorrect");
    header.style.display="block";
}

function open_employee_page(username)
{
    localStorage.setItem("username",username);
    localStorage.setItem("permission",2);
	window.location.replace("Employee/Employee_Form.html");
}

function open_manager_page(username)
{
    localStorage.setItem("username",username);
    localStorage.setItem("permission",1);
    window.location.replace("Manager/Manager_Requests.html");
}

//Hit enter only on password will login

document.getElementById("password").addEventListener("keyup", function(event) 
{
    event.preventDefault();
    if (event.keyCode === 13) 
    {
        document.getElementById("login").click();
    }
});


