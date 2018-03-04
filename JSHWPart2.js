"use strict";
//1
function getElementbyInnerHTML(target,root) 
{
    var root = root || document.body;
    let children = root.children;
    for(let i = 0; i < children.length; i++) 
    {
        if(children[i].hasChildNodes())
            getElementbyInnerHTML(target,children[i]);
        if(children[i].innerHTML==target)
        console.log(children[i]);
    }
}

getElementbyInnerHTML("USA");

//2
function getPeopleInSales()
{

    var x = document.querySelectorAll(".empName");
    for(let i=0;i<x.length;i++)
    {
        var element=x[i].nextElementSibling;
        if(element.innerHTML=="Sales")
        console.log(x[i].innerHTML);
    }

}

getPeopleInSales();

//3
var x=document.body.querySelectorAll('a');
console.log(x[0].chi);

function getAnchorChildren()
{
    var anchor=document.body.querySelectorAll('a');
    for(var i=0;i<anchor.length;i++)
    {
        if(anchor[i].hasChildNodes())
        {
            var anchor_children=anchor[i].children;
            for(var j=0;j<anchor_children.length;j++)
            {
                if(anchor_children[j].tagName=="SPAN")
                console.log(anchor_children[j].innerHTML)
            }
        }
    }

}

getAnchorChildren();

//4
function getSkills()
{
    var elements = document.querySelectorAll('[name="skills"]');
    for(var i=0;i<elements.length;i++)
    {
        var children=elements[i].children;
        for(var j=0;j<children.length;j++)
        {
            if(children[j].getAttribute("selected")=="selected")
            console.log(children[j]);
        }
    }
}

getSkills();

//5
function getCustomAttribute(root) 
{
    var root = root || document.body;
    let children = root.children;
    for(let i = 0; i < children.length; i++) 
    {
        if(children[i].hasChildNodes())
            getCustomAttribute(children[i]);
        if(children[i].hasAttribute("data-customAttr"))
        console.log(children[i]);
    }
}

getCustomAttribute();

//6
function compute_sum()
{
    var num1=document.getElementById("num1").value || 0;
    var num2=document.getElementById("num2").value || 0;
    if( isNaN(num1) || isNaN(num2))
        document.getElementById("sum").innerHTML="Cannot Add";
    else
        document.getElementById("sum").innerHTML=Number(num1)+Number(num2);
}
document.getElementById("num1").onchange=function(){compute_sum()};
document.getElementById("num2").onchange=function(){compute_sum()};

//7
var savevalue=document.getElementsByName("skills")[0].value;
document.getElementsByName("skills")[0].onchange=function()
{
    if(savevalue!=this.value)
    {
        if(confirm("Are you sure "+this.value+" is one of your skills idiot?"))
            savevalue=this.value;
        else
            this.value=savevalue;
    }

};

//8
var first_time=true;
var savevalue2;
var x=document.getElementsByName("favoriteColor")
x.forEach(function(y)
{
    y.onchange=function()
    {
        console.log(savevalue2+" "+this.value);
        if(savevalue2!=undefined)
        {
            if(savevalue2!=this.value)
            {
                if(confirm("So you like "+this.value+" more than "+savevalue2+"?"))
                    savevalue2=this.value;
                else
                    this.value=savevalue2;
            }
        }
        else
        {
            savevalue2=this.value;
        }
    };
}
)

//9 //TODO
//document.getElementsByClassName("empName")[0].addEventListener("mouseover",function(event){event.target.innerHTML="yeah"});
var emp_Elements=document.getElementsByClassName("empName");
var original=emp_Elements;
for(var i=0;i<emp_Elements.length;i++)
{
    emp_Elements[i].addEventListener("mouseover",function(event)
        {
           if(event.target.innerHTML=="     ")
                event.target.innerHTML=original[i];
            else
                event.target.innerHTML="     ";
        }
    )
}

//10
function checkTime(i) {
    if (i < 10) {
      i = "0" + i;
    }
    return i;
  }
  
  function startTime() {
    var today = new Date();
    var h = today.getHours();
    var ampmbool=true;
    var ampm;
    if(h >= 12)
    {
        ampmbool=!ampmbool;
        if(h!=12)
            h=h-12
    }
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    if(ampmbool)
        ampm="AM";
    else
        ampm="PM";
    document.getElementById('currentTime').innerHTML = h + ":" + m + ":" + s + " "+ampm;
    setTimeout(function() {
      startTime()
    }, 1000);
  }
  startTime();

  //11
  document.getElementById("helloWorld").onclick=function()
  {
    setTimeout(function(color){
        document.getElementById("helloWorld").style.color='rgb(' + (Math.floor(Math.random() * 256)) + ',' + (Math.floor(Math.random() * 256)) + ',' + (Math.floor(Math.random() * 256)) + ')';
    },3000);
  }

  //12
  function walkTheDOM(root,func) 
{
    var root = root || document.body;
    let children = root.children;
    for(let i = 0; i < children.length; i++) 
    {
        if(children[i].hasChildNodes())
            walkTheDOM(children[i],func);
       func(children[i]);
    }
}

function func(x)
{
    console.log(x);
}
walkTheDOM(document.body,func);

/*----------------------------------------------------------------------------------
PART II
Part II will focus on Javascript's ability to manipulate the DOM.
Use the provided HTML page.
Put your Javascript in the provided <script> element at the bottom of the page.
Please put the question itself as a comment above each answer.
-----------------------------------------------------------------------------------

/* 9. Show/Hide Event
* NOTE: Write unobtrusive Javascript
* When user hovers over an employees name:
* Hide the name if shown.
* Show the name if hidden.
*/
