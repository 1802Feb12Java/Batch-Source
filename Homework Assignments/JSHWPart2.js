

// 1. USA
// Define function getUSA()
function getUSA(){
    var allTags = document.getElementsByTagName("*");
    // Find the html element that contains "USA".
    for(var i = 0 ; i < allTags.length; i++){
        if(allTags[i].innerHTML == "USA"){
            var thisTag = allTags[i];
        }
    }
    // Print that element's contents.
    console.log(thisTag.textContent);
}

getUSA();

/*************************************************************************** */  
// 2. Sales

// Define function getPeopleInSales()
function getPeopleInSales(){
    var employeeNames = document.getElementsByClassName("empName");
    // Print the names of all the people in the sales department.
    for(var i = 0; i < employeeNames.length; i++){
        if(employeeNames[i].nextElementSibling.innerHTML == "Sales"){
            console.log(employeeNames[i].textContent);
        }
    }
}
getPeopleInSales();
console.log("");
/*************************************************************************** */  
// 3. Click Here

// Define function getAnchorChildren()
function getAnchorChildren(){
    // Find all anchor elements with a <span> child.
    var allTags = document.getElementsByTagName("a");
    for(var i = 0; i < allTags.length; i++){
       if(allTags[i].firstElementChild != null){
           var spans = allTags[i].getElementsByTagName("span");
           for(var j = 0; j < spans.length; j++){
               // Print the contents of <span>
               console.log(spans[j].textContent);
           }
        }
    }
}

getAnchorChildren();
  
/*************************************************************************** */  
// 4. Hobbies

// Define function getSkills()
function getSkills(){
    var skillsElements = document.getElementsByName("skills");
   var skills = skillsElements[0];
    for(var i = 0; i < skills.length; i++){
        // Find all checked options in the 'skills' select element.
        if(skills[i].hasAttribute("selected") == true){
            console.log("Value: " + skills[i].value + "\nText Content: " + skills[i].textContent);
        }
     
    }
}

getSkills();

/*************************************************************************** */    
// 5. Custom Attribute

// Define function getCustomAttribute()
function getCustomAttribute(){
    var custAttributes = document.getElementsByTagName("*");
    for(var i = 0; i < custAttributes.length; i++){
        // Find all elements with "data-customAttr" attribute
        if(custAttributes[i].hasAttribute("data-customAttr")){
            // Print the value of the attribute.
            // Print the element that has the attribute.
            console.log("Element: " + custAttributes[i] + "\nValue: " + custAttributes[i].getAttribute("data-customAttr"));
        }
    }
}
getCustomAttribute();

/*************************************************************************** */    
// 6. Sum Event

// NOTE: Write unobtrusive Javascript

// Regarding these elements:
	
// <input id="num1" class="nums" type="text"/>
	
// <input id="num2" class="nums" type="text"/>
	
// <h3>Sum: <span id="sum"></span></h3>

window.onload = function(){
    document.getElementById("num1").addEventListener("change", getAndSum, false);
    document.getElementById("num2").addEventListener("change", getAndSum, false);
    
}

function getAndSum(){
    var number1 = document.getElementById("num1").value;
    var number2 = document.getElementById("num2").value;
    var sum = parseInt(number1) + parseInt(number2);
   
    if(isNaN(sum)){
        document.getElementById("sum").innerHTML = "Cannot Add.";
    } else {
        document.getElementById("sum").textContent = sum;
    }
    
}

// Define onchange event handler.

// Add <input> element values.

// Put the sum in the <span> element.

// If values cannot be added, put "Cannot add" in the <span> element

/*************************************************************************** */    
// 7. Skills Event

// NOTE: Write unobtrusive Javascript

// When user selects a skill, create an alert with a message similar to:
	
// "Are you sure CSS is one of your skills?"

// NOTE: no alert should appear when user deselects a skill.

var skillsElements = document.getElementsByName("skills");
var skills = skillsElements[0];

for(var i = 0; i < skills.length; i++){
    skills.onchange = function(){
        for(var i = 0; i < skills.length; i++){
            if(skills[i].selected == true){
                var skillValue = skills[i].value;
                alert("Are you sure " + skillValue + " is one of your skills?");
            }
        }
        
    }
}



/*************************************************************************** */    
// 8. Favorite Color Event

// NOTE: Write unobtrusive Javascript

// NOTE: This is regarding the favoriteColor radio buttons.

// When a user selects a color, create an alert with a message similar to:
	
// "So you like green more than blue now?"

window.onload = function(){
    oldColor = getCheckedColor();
    // console.log("selected color on load: " + oldColor);
    var colorelements = document.getElementsByName("favoriteColor");
    for(var i = 0; i < colorelements.length; i++){
        colorelements[i].onmousedown = function(){
            oldColor = getCheckedColor();
        }
        colorelements[i].addEventListener("change", alertColorChange, false);
     }
}



function getCheckedColor(){
    var colorButtons = document.getElementsByName("favoriteColor");
    var checkedButtonValue = "none";
    for(var i = 0; i < colorButtons.length; i++){
        if(colorButtons[i].checked == true){
            checkedButtonValue = colorButtons[i].value;
        }
    }
    //console.log(checkedButtons);
    return checkedButtonValue;
}

function alertColorChange(){
    var oldColor2 = oldColor;
    var newColor = getCheckedColor();

    if(oldColor2 == "none"){
        alert("So you like the color " + newColor + ", huh?");
    } else {
        alert("So you like " + newColor + " better than " + oldColor2 + " now?");
    }

    var favColorSpans = document.getElementsByName("favColor");
    for(var i = 0; i < favColorSpans.length; i++){
        favColorSpans[i].setAttribute("style", "background-color:" + newColor)
    }

}

// In this example, green is the new value and blue is the old value.

// Make the background color (of all favoriteColor radio buttons) 
// the newly selected favoriteColor

/*************************************************************************** */    
// 9. Show/Hide Event

// NOTE: Write unobtrusive Javascript

// When user hovers over an employees name:
	
// Hide the name if shown.
// 	Show the name if hidden.

var employeeNames = document.getElementsByClassName("empName");
//console.log(employeeNames);
    for(var i = 0; i < employeeNames.length; i++){
        var emp = employeeNames[i];
        emp.onmouseenter = function(){
            this.style.display = "none";
        }
        emp.onmouseleave = function(){
            this.style.display = "block";
        }
        
       
    }


/*************************************************************************** */    
// 10. Current Time

// Regarding this element:
// 	<h5 id="currentTime"></h5>

// Show the current time in this element in this format: 9:05:23 AM

// The time should be accurate to the second without having to reload the page.

(function () {
    function checkTime(i) {
        return (i < 10) ? "0" + i : i;
    }

    function startTime() {
        var today = new Date(),
            h = checkTime(today.getHours()),
            m = checkTime(today.getMinutes()),
            s = checkTime(today.getSeconds());
        if(h > 12){
            var ampm = "PM";
            h = h-12;
        } else {
            ampm = "AM";
        }
        document.getElementById("currentTime").innerHTML = h + ":" + m + ":" + s + " " + ampm;
        t = setTimeout(function () {
            startTime()
        }, 500);
    }
    startTime();
})();

/*************************************************************************** */    
// 11. Delay
// Regarding this element:
	
// <p id="helloWorld">Hello, World!</p>

function changetextcolor(){
    document.getElementById("helloWorld").style.color = "#"+((1<<24)*Math.random()|0).toString(16);
}

document.getElementById("helloWorld").onclick = function (){
    t = setTimeout(function(){changetextcolor()}, 3000);
}

// Three seconds after a user clicks on this element, change the text to a random color.

/*************************************************************************** */    
// 12. Walk the DOM

// Define function walkTheDOM(node, func)

// This function should traverse every node in the DOM. 
// Use recursion.

// On each node, call func(node).

function walkTheDom(node, func){
    func(node.nodeName);
    var childs = node.childNodes;
    if(childs != null && childs != undefined){
        for(var i = 0; i < childs.length; i++){
            walkTheDom(childs[i], func);
        }
    } else {
        return;
    }
    
}

function printNode(node){
    console.log("Node Type: " + node);
}

var htmlnode = document.getElementsByTagName("html")["0"];
 walkTheDom(htmlnode, printNode);
//console.log(htmlnode.firstElementChild.nextElementSibling.firstElementChild);
