var myObject = {}
//1
myObject.getUSA = function()
{
    var span = document.getElementsByTagName("span");
    for(var i = 0; i < span.length; i++)
    {
        if(span[i].textContent.value == "USA")
        document.write(span[i]);
    }
    document.write("<br>");     
}
//2
myObject.getPeopleInSales = function()
{
    var sales = document.getElementsByClassName("empName");
    for(var i = 0; i < sales.length; i++)
    {
        document.write(sales[i].textContent);
    }
    document.write("<br>");  
}
//3
myObject.getAnchorChildren = function()
{
    var anchor = document.getElementsByTagName("a");
    for(var i = 0; i < anchor.length; i++)
    {
        if(anchor[i].childNodes == "span")
        {
            for(var j = 0; j < anchor[i].childNodes.length; j++)
            {
                document.write(anchor[i].childNodes[j].textContent)
            }
        }
    }
    document.write("<br>");
}
//4
myObject.getSkills = function()
{
    var skills = document.getElementsByName("skills");
    for (var i; i< skills.length; i++)       
    {
          for(let i = 0; i < skills.childNodes.length; i++)
          {
            document.write(skills.childNodes[i].value + "<br>");
            document.write(skills.childNodes[i].textContent);
          }
          
    }
    document.write("<br>");
}
//5
myObject.getCustomAttribute = function()
{
    document.write("<br>"); 
}
//6
var xSum = document.createElement("input");
var ySum = document.createElement("input");
var zSum = document.createElement("h3");
xSum.setAttribute('id',"num1")
xSum.setAttribute('class',"nums")
xSum.setAttribute('type', "text")
xSum.setAttribute('onchange', "myObject.sumeEvent()")
ySum.setAttribute('id',"num2")
ySum.setAttribute('class',"nums")
ySum.setAttribute('type', "text")
ySum.setAttribute('onchange', "myObject.sumeEvent()")
document.body.appendChild(xSum);
document.body.appendChild(ySum);
document.body.appendChild(zSum);
myObject.sumeEvent = function()
{
   
    if(xSum.value != "" && ySum.value != "")
    {
        var inNum = xSum.value
        var inNum2 = ySum.value
        var result = Number(inNum) + Number(inNum2);
        zSum.innerHTML = result.toString();
    }
    else
    alert("Cannot Add")
    
}
//7
var xSkills = document.createElement("select");
var ySkills = document.createElement("option")
var ySkills1 = document.createElement("option")
xSkills.setAttribute('onchange', "myObject.skillsEvent()");
ySkills.setAttribute('id', "CSS");
ySkills.setAttribute('value', "CSS");
ySkills1.setAttribute('id', "Javascript");
ySkills1.setAttribute('value', "Javascript");
var textNode = document.createTextNode("CSS");
var textNode1 = document.createTextNode("Javascript")
ySkills.appendChild(textNode);
ySkills1.appendChild(textNode1);
xSkills.appendChild(ySkills);
xSkills.appendChild(ySkills1);
document.body.appendChild(xSkills);
myObject.skillsEvent = function()
{
   alert('Are you sure CSS is one of your skills?')
}
//8
var xFavColor = document.createElement("input");
xFavColor.setAttribute("type", "radio");
xFavColor.setAttribute("id", "blue");
xFavColor.setAttribute("value", "blue");
xFavColor.setAttribute("name", "FavColor");
xFavColor.setAttribute("onclick", "valueChanged()");
var yFavColor = document.createElement("input");
yFavColor.setAttribute("type", "radio");
yFavColor.setAttribute("id", "green");
yFavColor.setAttribute("value", "green");
yFavColor.setAttribute("name", "FavColor");
yFavColor.setAttribute("onclick", "valueChanged()");
document.body.appendChild(xFavColor);
document.body.appendChild(yFavColor);
function valueChanged() 
{
    if (document.getElementById("blue").checked == true) {
        document.getElementById("green").checked == false
        document.getElementById("blue").value = 1;
        document.getElementById("green").value = 0;
        
        document.body.style.backgroundColor = "blue";
        
    } else {
        document.getElementById("blue").checked == false
        document.getElementById("blue").value = 0;
        document.getElementById("green").value = 1;
        alert('So you like green more than blue now?')
        document.body.style.backgroundColor = "green";
    
    }
} 
//9
var xShowHide = document.createElement("p");
xShowHide.setAttribute("id", "employee");
xShowHide.setAttribute("name" , "employee");
xShowHide.setAttribute("onmouseover" , "xShowHide.innerHTML = 'John Smith'")
xShowHide.setAttribute("onmouseout" , "xShowHide.innerHTML = 'Hover mouse over for Employee name'")
document.body.appendChild(xShowHide);
//10
myObject.currentTime = function()
{
    var x = document.createElement("h5");
    x.setAttribute("id", "currentTime");
    document.body.appendChild(x);
    var currentTime = new Date();
    console.log(currentTime.toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', second: 'numeric', hour12: true }));
}
//11
var xDelay = document.createElement("p");
var t = document.createTextNode("Hello, World!")
xDelay.setAttribute("id", "helloWord");
xDelay.setAttribute("onclick" , "setTimeout(function() { randomColors(); }, 3000);")
xDelay.appendChild(t);
document.body.appendChild(xDelay);
function randomColors() 
{
    xDelay.style.color = '#' + Math.floor(Math.random() * 16777215).toString(16);
    return
}
//12
myObject.walkTheDOM = function(node)
{
    if(node == null)
    {
        return
    }
    myObject.walkTheDOM(node);
    
   
}

//function declarations\\
myObject.getUSA();
myObject.getPeopleInSales();
myObject.getAnchorChildren();
myObject.getSkills();
myObject.getCustomAttribute();
myObject.currentTime();
var node = document.body.rootNode;
myObject.walkTheDOM(node);





