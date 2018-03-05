
/* 1. USA
* Define function getUSA()
* Find the html element that contains "USA".
* Print that element's contents. 
*/


function getUSA(){
   
    var all = document.getElementsByTagName("*");
    var docLen = all.length;
    //console.log("length "+docLen);
    for (var i=0;i < docLen; i++){
        if(all[i].innerHTML == "USA"){
            console.log("Found USA: " + all[i].textContent);
            return;
        }
    }

}

/* 2. Sales
* Define function getPeopleInSales()
* Print the names of all the people in the sales department.
*/


function getPeopleInSales(){

    var names = document.getElementsByClassName("empName");
    for(var i = 0; i < names.length; i++){
        var parent = names[i].parentElement;
        if(parent.children[1].textContent == "Sales"){
            console.log(parent.children[0].innerHTML);
        }
    }
    return;
}

/* 3. Click Here
* Define function getAnchorChildren()
* Find all anchor elements with a <span> child.
* Print the contents of <span>
*/


function getAnchorChildren(){

    var anchorChild = document.getElementsByTagName("span");
    var aLen =  anchorChild.length;

    for (var i = 0; i < aLen; i++){
        //console.log(anchorChild[i].parentElement);
        if(anchorChild[i].parentElement.tagName == "A"){
            console.log(anchorChild[i].textContent);
        }
    }

}

/* 4. Hobbies
* Define function getSkills()
* Find all checked options in the 'skills' select element.
* Print the value and the contents.
*/


function getSkills(){

    var skill = document.getElementsByName('skills')[0];
    console.log(skill.options[skill.selectedIndex].value);

}

/* 5. Custom Attribute
* Define function getCustomAttribute()
* Find all elements with "data-customAttr" attribute
* Print the value of the attribute.
* Print the element that has the attribute.
*/


function getCustomAttribute(){

    var cAttr = document.querySelectorAll('[data-customAttr]');
    
    for(var i = 0; i<cAttr.length;i++){
        console.log(cAttr[i].getAttribute("data-customAttr"));

    }

}

/* 6. Sum Event
* NOTE: Write unobtrusive Javascript
* Regarding these elements:
* <input id="num1" class="nums" type="text"/>
* <input id="num2" class="nums" type="text"/>  
* <h3>Sum: span id="sum"></span></h3>
* Define onchange event handler.
* Add <input> element values.
* Put the sum in the <span> element.
* If values cannot be added, put "Cannot add" in the <span> element
*/

var num1 = document.getElementById("num1");
var num2 = document.getElementById("num2");
var sum = document.getElementById("sum");
// num1.addEventListener("change", summation());
// num2.addEventListener("change", console.log("num2 changed"));
// sum.addEventListener("change", summation());

num1.onchange = function() {summation();};
num2.onchange = summation;

function summation(){
    console.log("Changed");
    var sumN = Number(num1.value) + Number(num2.value);
    if(isNaN(sumN) || sumN==undefined){
        console.log("cant add those");
    } else {
        sum.innerHTML = sumN;
    }

}

/* 7. Skills Event
* NOTE: Write unobtrusive Javascript
* When user selects a skill, create an alert with a message similar to:
* "Are you sure CSS is one of your skills?"
* NOTE: no alert should appear when user deselects a skill.
*/

//NOTE - question asks to NOT print on deselect but idk how you deselect?
//if there is a way to deselect my code *probably* doesnt work but am unsure.
var buttons = document.getElementsByName("skills");
var bLen = buttons.length;
//console.log(buttons[0].value);
buttons[0].onchange = function(){console.log("Are you sure " + buttons[0].value + " is one of you skills?")};


/* 8. Favorite Color Event
* NOTE: Write unobtrusive Javascript
* NOTE: This is regarding the favoriteColor radio buttons.
* When a user selects a color, create an alert with a message similar to:
* "So you like green more than blue now?"
* In this example, green is the new value and blue is the old value.
* Make the background color (of all favoriteColor radio buttons)
* the newly selected favoriteColor
*/

//NOTE: it asks to change the color of the radio button but they are 
//native to the browser and you can't change them unless you create
//custom images for them. Or something like that so i just changed background.
var colorButtons = document.getElementsByName("favoriteColor");
var cLen = colorButtons.length;
var oldFav = "white";
for(var i = 0; i < cLen; i++){
    colorButtons[i].onchange = function(){
        for (var ind = 0; ind<cLen; ind++){
            if(colorButtons[ind].checked){
                console.log("So you like " + colorButtons[ind].value + " more than " + oldFav + " now?");
                document.body.style.backgroundColor = colorButtons[ind].value;
                oldFav = colorButtons[ind].value;
                return;
            }
        }
    };
}


/* 9. Show/Hide Event
* NOTE: Write unobtrusive Javascript
* When user hovers over an employees name:
* Hide the name if shown.
* Show the name if hidden.
*/

//This would satisfy the homework requirement so show the item again BUT
// you cant mouse over a hidden div

var names = document.getElementsByClassName("empName");

var nLen = names.length;
var thisRef;
for (var i = 0; i<nLen; i++){
    names[i].onmouseenter = function(){
        //console.log(this.innerHTML);

        if(this.style.display == "block"){
            this.style.display = "none";
        } else {
            this.style.display = "block";
        }
        
    }

    //mouse over on the hidden objects sibling to make it reappear
    //couldnt get setTimeout to work becuase it would throw an error
    //i thinking wrapping it in a setTimeout changes this reference away
    //from the element sibling and to the function? not sure
    //so there will be massive flickering as your mouse triggers on mouseover
    //100 times a second.
    names[i].parentNode.children[1].onmouseover = function(){
        this.parentNode.children[0].style.display = 'block';
    };//, 1000);
}

/* 10. Current Time
* Regarding this element:
* <h5 id="currentTime"></h5>
* Show the current time in this element in this format: 9:05:23 AM
* The time should be accurate to the second without having to reload the page.
*/

var timeElement = document.getElementById("currentTime");

setInterval("currentTimeCalc()", 1000);
function currentTimeCalc(){
    var day = new Date();    
    var ampm = 'AM';
    var hours = day.getHours();
    if(hours > 12){
        hours = hours - 12;
        ampm = 'PM';
    }
    timeElement.innerHTML = "" + hours + ":" + day.getMinutes() + ":" + day.getSeconds()+" "+ampm;
}

/* 11. Delay
* Regarding this element:
* <p id="helloWorld">Hello, World!</p>
* Three seconds after a user clicks on this element, change the text to a random color.
*/

var delayHello = document.getElementById("helloWorld");
console.log(delayHello);
delayHello.onclick = function(){console.log("clicked hello world");
setTimeout("changeHelloColor()", 3000); };
function changeHelloColor(){
    delayHello.style.color = "" + getRandomColor();
}
//grabbed this function straight from stack overflow
//just picks 6 random characters from the object array letters
//letters represents the 16 characters of a hex number
//builds a random hex number
function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

/* 12. Walk the DOM
* Define function walkTheDOM(node, func)
* This function should traverse every node in the DOM.
* Use recursion.
* On each node, call func(node).
*/

var root = document.getElementsByTagName("html");

function runWTD(){
    walkTheDOM(document.body, function(node){ console.log(node.tagName)})
}

function walkTheDOM(node, func){
    var child = node.children;
    func(node);
    for (var i = 0; i < child.length; i++){
        walkTheDOM(child[i], func);
    }

}























