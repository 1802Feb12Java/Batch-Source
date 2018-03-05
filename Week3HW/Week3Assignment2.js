// 1. USA
// Find the html element that contains "USA".
// Print that element's contents.
function getUSA(){
    var span1 = document.getElementsByTagName("*");
    var found;
for (i = 0; i < span1.length ; i++){
    if ( span1[i].textContent == "USA"){
    found = span1[i];
    break;
    }
}
return found;
}

// 2. Sales
// Print the names of all the people in the sales department.
function getPeopleInSales(){
    var td2 = document.getElementsByTagName("td");
    for (var i = 0; i<td2.length; i++) {
        if (td2[i].innerHTML== "Sales") {
          console.log(td2[i-1].innerHTML);
        }
    }
}

// 3. Click here
// Find all anchor elements with a <span> child.
// Print the contents of <span>
function getAnchorChildren(){
    var anchor = document.getElementsByTagName("a");
    for (var x = 0; x < anchor.length;x++){
        var count = anchor[x].childElementCount;
        for (y = 0; y < count; y++){
        if(anchor[x].children[y].nodeName == "SPAN"){
            console.log(anchor[x].children[y].textContent)
        }}
    }
}

// 4. Hobbies
// Find all checked options in the 'skills' select element.
// Print the value and the contents.
function getSkills(){
var skill = document.getElementsByName("skills");
var skilloption = skill[0];
for (x = 0; x < skilloption.length; x++) {
    if (skilloption[x].selected){
        console.log(skilloption[x].value);
    }}
}

// 5. Custom Attribute
// Find all elements with "data-customAttr" attribute
// Print the value of the attribute.
// Print the element that has the attribute.

function getCustomAttribute(){
var all5 = document.getElementsByTagName("*");
var element5;
for (x = 0; x < all5.length;x++){
    if (all5[x].hasAttribute("data-customAttr")){
        console.log("Element with the attribute: "+all5[x].nodeName);
        console.log("data-customAttr value: " +all5[x].getAttribute("data-customAttr"));
    }
}
}

// 6. Sum Event

// Define onchange event handler.
// Add <input> element values.
// Put the sum in the <span> element.
// If values cannot be added, put "Cannot add" in the <span> element

var nums6 = document.getElementsByClassName("nums");
var total = document.getElementById("sum");
for(x = 0; x < nums6.length; x++){
    nums6[x].onchange = function() {
        var num1 = nums6[0].value;
        var num2 = nums6[1].value;
        var sum = parseInt(num1)+parseInt(num2);
        if(sum != undefined || sum != NaN) {
            total.textContent = sum;
        } else {
            total.textContent = "Cannot add";
        }
    }
}

// 7. Skills Event
// When user selects a skill, create an alert with a message similar to:
// "Are you sure CSS is one of your skills?"
// NOTE: no alert should appear when user deselects a skill.

var skill7 = document.getElementsByName("skills");
var skilloption = skill7[0];

for (x = 0; x < skilloption.length; x++){
    skilloption.onchange = function() {
        for (y = 0; y < skilloption.length; y++){
            if (skilloption[y].selected == true){
                var chosen = skilloption[y].value;
                alert("Do you know "+chosen+" well for it be to your skill");   
            }
        }
    }
}

// 8. Favorite Color Event
// NOTE: Write unobtrusive Javascript
// NOTE: This is regarding the favoriteColor radio buttons.
// When a user selects a color, create an alert with a message similar to:	
// "So you like green more than blue now?"
// In this example, green is the new value and blue is the old value.
// Make the background color (of all favoriteColor radio buttons) 
// the newly selected favoriteColor
var color8 = document.getElementsByName("favoriteColor");
var colorlist = [];
var prev;
var cur;
var back = document.getElementsByName("chosenColor");

for (x = 0; x < color8.length; x++){
    color8[x].onchange = function(){
        for (y = 0; y < color8.length; y++) {
            if (color8[y].checked) {
                cur = color8[y].value;
                for (a = 0; a < color8.length; a++){
                back[a].style.background = cur;
                }
                colorlist.push(cur);
                if (colorlist.length >= 2) {
                    prev = colorlist[colorlist.length-2];
                    alert("So you like " + cur + " more than " + prev);
                }
            }
        }
    }
}

// 9. Show/Hide Event
// NOTE: Write unobtrusive Javascript
// When user hovers over an employees name:
// Hide the name if shown.
// 	Show the name if hidden.

var emp9 = document.getElementsByClassName("empName");

for (x = 0; x < emp9.length; x++){
    emp9[x].onmouseover = function() {
            // tried visibility first but once its hidden, it doesnt turn visible
            // if (this.style.display == "none"){
            // this.style.visibility="visible";
            // } else {
            // this.style.visibility="hidden";    
            // }
            if (this.style.color == "white"){
                this.style.color = "black";
            } else {
                this.style.color = "white";
            }
        }
}

// 10. Current Time
// Regarding this element:
// 	<h5 id="currentTime"></h5>
// Show the current time in this element in this format: 9:05:23 AM
// The time should be accurate to the second without having to reload the page.
function preten(i) {
    if (i < 10) {
      i = "0" + i;
    }
    return i;
  }

  function startTime() {
    var day = new Date();
    var h = day.getHours();
    var m = day.getMinutes();
    var s = day.getSeconds();
    var hr;
    var end;
    if (h > 12){
        hr = h - 12;
        end = "PM";
    } else {
        hr = h;
        end = "AM";
    }
    m = preten(m);
    s = preten(s);
    document.getElementById('currentTime').innerHTML = hr + ":" + m + ":" + s + " " + end;
    t = setTimeout(function() {
      startTime()
    }, 500);
}
startTime();

/* 11. Delay
* Regarding this element:
* <p id="helloWorld">Hello, World!</p>
* Three seconds after a user clicks on this element, change the text to a random color.
*/

var del11 = document.getElementById("helloWorld");

function rand() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (x = 0; x < 6; x++){
        color += letters[Math.floor(Math.random()*16)];
    }
    return color;
}

del11.onclick = function() {
    setTimeout(function(){
    del11.style.color = rand();
},3000)};

/* 12. Walk the DOM
* Define function walkTheDOM(node, func)
* This function should traverse every node in the DOM.
* Use recursion.
* On each node, call func(node).
*/

function walkTheDOM(node, func) {
    func(node);
    node = node.firstChild;
    while (node) {
        walkTheDOM(node, func);
        node = node.nextSibling;
    }
}

walkTheDOM(document, function (node) {
    if (node.nodeType === 1) { 
        console.log(node.nodeName);
    }
});
