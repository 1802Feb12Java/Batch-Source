var data = {
  parameter: 12
};
var response=Post(data,function(result)
{
    document.getElementById("label").value=result.label;
});

$("#submit").click(function(event)
{

  $( "#form1" ).submit();

  event.preventDefault();
  var amount_form=document.getElementById("exampleInputPassword1").value; 
  var type_form=document.getElementById("exampleSelect1").value; 
  var description_form=document.getElementById("exampleTextarea").value;
  var receipt_url="https://s3.us-east-2.amazonaws.com/zains-bucket/"+document.getElementById("label").value;
  console.log("Sending "+receipt_url+":"+amount_form+":"+type_form+":"+description_form+":"+localStorage.getItem("username"));

  var data = {
      parameter: 3,
      amount: amount_form,
      type: type_form,
      description: description_form,
      receipt: receipt_url,
      username : localStorage.getItem("username")
  };
  var response=Post_async(data,function(result)
  {
      console.log(result);
  });

});

function objectifyForm(formArray) 
{
	  var returnArray = {};
	  for (var i = 0; i < formArray.length; i++){
	    returnArray[formArray[i]['name']] = formArray[i]['value'];
	  }
	  return returnArray;
}