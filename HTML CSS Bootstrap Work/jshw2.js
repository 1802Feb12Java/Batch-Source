//hw02
let getUSA = function()
{
    let usaArray = document.querySelectorAll("*");
    for(let index = 0; index < usaArray.length; index++){
        if(usaArray[index].textContent === "USA") {
           console.log(usaArray[index].textContent);
        };
    };
};

console.log("Q1:");
getUSA();
console.log("");

//Q2
let getPeopleInEating = function()
{
    let dept = document.querySelectorAll("td");
    let employees = document.querySelectorAll("tr");

    for (let index = 0; index < dept.length; index++){
      //find the person that loves food
        if(dept[index].innerHTML == "Eating")
		{
            console.log(dept[index - 1].innerHTML);
        };
    };
};

console.log("Q2:");
getPeopleInEating();
console.log("");

//Q3
let getAnchorChildren = function()
{
    let anchorChild = document.querySelectorAll("span");
    for (let index = 0; index < anchorChild.length; index++)
	{
        console.log(anchorChild[index].innerHTML);
    };
};

console.log("Q3:");
getAnchorChildren();



//Q5
let getCustomAttribute = function(){
    let custAtt = document.querySelectorAll("[data-customAttr]");
    for (let index = 0; index < custAtt.length; index++){
        console.log();
        console.log(custAtt[index].tagName + " contains custom attribute: " + custAtt[index].getAttribute("data-customAttr"));
    };
};

console.log("Q5");
getCustomAttribute();
console.log("");

//Q6
document.getElementById("num1").addEventListener("change", addSum);
document.getElementById("num2").addEventListener("change", addSum);

function addSum() 
{
    let num1 = Number(document.getElementById("num1").value);
    let num2 = Number(document.getElementById("num2").value);

    let numSum = document.getElementById("sum");
    let summary = num1 + num2;

    if(isNaN(summary)){
        numSum.textContent = "Cant add";
    }
    else {
        numSum.textContent = summary;
    };
};

//Q7
let arraySkills = document.getElementsByName("favHobby");
arraySkills[0].addEventListener("change", youSure);

function youSure()
{
    let msg = "Are you sure " + arraySkills[0].value + " is a value?";
    alert(msg);
};

//Q8
var favColor = document.getElementsByName("favColor");
for(let index = 0; index < favColor.length; index++){
    favColor[index].addEventListener("click", changeColor);
}

var prevColor = "Red";

function changeColor(){
    for (let index = 0; index < favColor.length; index++)
	{
        if(favColor[index].checked){
            alert(favColor[index]);
			alert("Now you change your fav color?");
            prevColor = favColor[index].value;
            favColor[index].onchange = function(){
                console.log("beforeColor")
                document.body.style.backgroundColor = prevColor;
                console.log("afterColor")
            }
        }
    }
}

// 10 gettime
var options = { hour: 'numeric', minute: 'numeric', second: 'numeric' };
var day = new Date();
var formattedToday = day.toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', second: 'numeric', hour12: true });
console.log(formattedToday)
var timeElem = document.getElementById("currentTime").innerHTML = formattedToday;

//11
var hi = document.getElementById("Click3");
hi.onclick = function () 
{
    console.log("Clicked: " + hi)
    var delayInMilliseconds = 5000;
    setTimeout(function ()
	{
        //generate rand color
        let str = 'asdfjkl0987654321'
        let randomColor = '#';
        for (var i = 0; i < 6; i++) 
		{
            randomColor += str[Math.floor(Math.random() * 16)];
        }
        hi.style.color = randomColor;
		console.log(randomColor + " is the random color value");
    }, delayInMilliseconds);

}


//12
function walkTheDOM(node, func)
{
    if(node == undefined || node.children.length == 0){
        printStuff(node);
    }
    for(let i = 0; i<node.children.length; i++)
    {
        console.log(node.children[i])
        walkTheDOM(node.children[i], printNodeName)
    }
}
function printStuff(node)
{
    console.log("Node: " + node.nodeName)
}

 var rootElem = document.getRootNode();