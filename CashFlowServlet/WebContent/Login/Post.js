function Post(data,callback)
{
    let message="no response";

    $.ajax({

        type: "POST",
        url: "http://localhost:8080/CashFlowServlet/CFservlet",
        contentType: "application/json", // NOT dataType!
        data: JSON.stringify(data),
        success: function(response) 
        {
            if(!response)
            response="no response";
            callback(response);
        },
    async:false
    }); 
}

function Post_async(data,callback)
{
    let message="no response";

    $.ajax({

        type: "POST",
        url: "http://localhost:8080/CashFlowServlet/CFservlet",
        contentType: "application/json", // NOT dataType!
        data: JSON.stringify(data),
        success: function(response) 
        {
            if(!response)
            response="no response";
            callback(response);
        },
    }); 
}