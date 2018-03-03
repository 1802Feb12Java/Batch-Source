// ----------------------------------------------------------------------------------

// PART II


// Part II will focus on Javascript's ability to manipulate the DOM.

// Use the provided HTML page.

// Put your Javascript in the provided <script> element at the bottom of the page.

// Please put the question itself as a comment above each answer.


// -----------------------------------------------------------------------------------

// 1. USA
// Define function getUSA()

// Find the html element that contains "USA".
var allTags = document.getElementsByTagName("*");
var result;
for(var i=0; i<allTags.length; i++){
    if(allTags[i].innerHTML == "USA"){
        result = allTags[i];
    }
}

// Print that element's contents.
console.log(result.textContent);
console.log("\n");
// 2. Sales

// Define function getPeopleInSales()
function getPeopleInSales(){
    var allTableRows = document.getElementsByTagName("tr");
    for(var i=0; i<allTableRows.length; i++){
        if(allTableRows[i].children[1].textContent == "Sales"){
            console.log(allTableRows[i].children[0].textContent);
        }
    }
}
// Print the names of all the people in the sales department.
getPeopleInSales(); 
console.log("\n");

// 3. Click Here

// Define function getAnchorChildren()
function getAnchorChildren(){
    return document.getElementsByTagName("a");
}
// Find all anchor elements with a <span> child.
var allAnchors = getAnchorChildren();
var anchorSpanChildren = [];
for(var i=0; i<allAnchors.length; i++){
    var anchorChildren = allAnchors[i].children;
    for(var j=0; j<anchorChildren.length; j++){
        if(anchorChildren[j].nodeName == "SPAN"){    //span is 1 apparently
            anchorSpanChildren.push(allAnchors[i]);
        }
    }
}
// Print the contents of <span>
for(var i=0; i<anchorSpanChildren.length; i++){
    var oneSpanChildAnchor = anchorSpanChildren[i].children;
    for(var j=0; j<oneSpanChildAnchor.length; j++){
        if(oneSpanChildAnchor[j].nodeName == "SPAN")
            console.log(oneSpanChildAnchor[j].textContent);
    }
}
console.log("\n");
  

// 4. Hobbies

// Define function getSkills()
function getSkills(){
    var allSelects = document.getElementsByTagName("select");
    for(var i=0; i<allSelects.length; i++){
        if(allSelects[i].name == "skills"){
            return allSelects[i];
        }
    }
}
// Find all checked options in the 'skills' select element.
var allSkills = getSkills();
// Print the value and the contents.
for(var i=0; i<allSkills.length; i++){
    if(allSkills[i].selected == true){
        console.log(allSkills[i].value);
    }
}
console.log("\n");
  

// 5. Custom Attribute

// Define function getCustomAttribute()
function getCustomAttribute(){
    var elementsWithCustom = [];
    for(var i=0; i<allTags.length; i++){
        if(allTags[i].hasAttribute("data-customAttr")){
            elementsWithCustom.push(allTags[i]);
        }
    }
    return elementsWithCustom;
}
// Find all elements with "data-customAttr" attribute
var elementsWithCustom = getCustomAttribute();
// Print the value of the attribute.
// Print the element that has the attribute.
for(var i=0; i<elementsWithCustom.length; i++){
    console.log(elementsWithCustom[i].getAttribute("data-customAttr"));
    //console.log(elementsWithCustom[i]); 
    //would print element, but looks gross so I'm not leaving it
}
console.log("\n");


// 6. Sum Event

// NOTE: Write unobtrusive Javascript

// Regarding these elements:
	
// <input id="num1" class="nums" type="text"/>
// <input id="num2" class="nums" type="text"/>
// <h3>Sum: span id="sum"></span></h3>

// Define onchange event handler.
var tagsReferred = [];
tagsReferred.push(document.getElementById("num1"));
tagsReferred.push(document.getElementById("num2"));
tagsReferred.push(document.getElementById("sum"));

for(var i=0; i<tagsReferred.length; i++){
    tagsReferred[i].onchange = function(){
        var num1 = tagsReferred[0].value;
        var num2 = tagsReferred[1].value;
        var sum = num1+num2;
        if(sum != undefined || sum != NaN){
            tagsReferred[2].textContent = sum;
            //updates where it says Sum at the top of the page
            //when putting stuff into the last 2 input boxes
        }
        else{
            tagsReferred[2].textContent = "Cannot add";
        }
    };
}
// Add <input> element values.
// Put the sum in the <span> element.
// If values cannot be added, put "Cannot add" in the <span> element


// 7. Skills Event

// NOTE: Write unobtrusive Javascript
// When user selects a skill, create an alert with a message similar to:
// "Are you sure CSS is one of your skills?"
// NOTE: no alert should appear when user deselects a skill.
var allSkills = getSkills();
for(var i=0; i<allSkills.length; i++){
    allSkills.onchange = function(){
        for(var i=0; i<allSkills.length; i++){
            if(allSkills[i].selected == true){
                var skillChosen = allSkills[i].value;
                alert("Are you sure " + skillChosen + " is one of your skills?");
            }
        }
    };
}


// 8. Favorite Color Event

// NOTE: Write unobtrusive Javascript
// NOTE: This is regarding the favoriteColor radio buttons.
// When a user selects a color, create an alert with a message similar to:
// "So you like green more than blue now?"
// In this example, green is the new value and blue is the old value.
// Make the background color (of all favoriteColor radio buttons) 
// the newly selected favoriteColor
var favColorList = document.getElementsByName("favoriteColor");
var lastChoice;
var newChoice;
for(var i=0; i<favColorList.length; i++){
    favColorList[i].onmousedown = function() {
        for(var j=0; j<favColorList.length; j++){
            if(favColorList[j].checked == true){
                lastChoice = favColorList[j].value;
            }
        }
    }
    favColorList[i].onchange = function() {
        for(var j=0; j<favColorList.length; j++){
            if(favColorList[j].checked == true){
                newChoice = favColorList[j].value;
            }
        }
        this.style.backgroundColor = newChoice;
        this.style.color = newChoice;
        this.style.background = newChoice;
        this.style.fill = newChoice;
        //trust me, none of these do diddly squat
        this.parentNode.style.backgroundColor = newChoice;
        //so I said flip it, I'm just gunna change the whole form's background color
        //cuz at least that actually works
        //also it technically does change the background color of all the favoriteColor buttons
        //...just... a few more things, too
        if(lastChoice != null)  //only alert if there was a choice before
           alert("So you like " + newChoice + " more than " + lastChoice + " now?");
    }
}


// 9. Show/Hide Event

// NOTE: Write unobtrusive Javascript
// When user hovers over an employees name:
// Hide the name if shown.
// Show the name if hidden.
var allEmps = document.getElementsByClassName("empName");
for(var i=0; i<allEmps.length; i++){
    allEmps[i].onmouseover = function(){
        if(this.style.color == "black" || this.style.color == ""){
            this.style.color = "white";
        }
        else if(this.style.color == "white"){
            this.style.color = "black";
        } 
    }
}


// 10. Current Time

// Regarding this element:
// 	<h5 id="currentTime"></h5>
// Show the current time in this element in this format: 9:05:23 AM
// The time should be accurate to the second without having to reload the page.
function updateTime(){
    var currTimeElement = document.getElementById("currentTime");
    var timestamp = new Date();
    var hours = timestamp.getHours();
    var AMPM;
    if(hours >= 12){
        AMPM = "PM";
        if(hours != 12){
            hours -= 12;
        }
    }
    else{
        AMPM = "AM";
    }
    var minutes = timestamp.getMinutes();
    var seconds = timestamp.getSeconds();
    if(minutes<10){
        minutes="0"+minutes;
    }
    if(seconds<10){
        seconds="0"+seconds;
    }
    currTimeElement.innerHTML = hours+":"+minutes+":"+seconds+" "+AMPM;
    setTimeout(updateTime, 1000); //update it every second
}
updateTime();


// 11. Delay
// Regarding this element:
// <p id="helloWorld">Hello, World!</p>
// Three seconds after a user clicks on this element, change the text to a random color.
var helloWorldElement = document.getElementById("helloWorld");
helloWorldElement.onclick = function(){
    setTimeout(function(){
        helloWorldElement.style.color = "#"+Math.floor(Math.random()*16777215).toString(16);
    }, 3000);   //do it after 3000 ms
}


// 12. Walk the DOM

// Define function walkTheDOM(node, func)
// This function should traverse every node in the DOM. 
// Use recursion.
// On each node, call func(node).
function walkTheDOM(node, func){
    for(var i=0; i<node.length; i++){
        walkTheDOM(node[i], func);
    }
    if(node.length == undefined){   
        //if the node isn't an array anymore (called on recursion)
        func(node);
    }
}
function printTextContents(x){
    console.log(x.textContent);
}
var x = document.getElementsByTagName("a");
walkTheDOM(x, printTextContents);