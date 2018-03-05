// 1. USA
// Define function getUSA()

// Find the html element that contains "USA".

// Print that element's contents.

function getUSA(){
   var x = document.getElementsByTagName("*");
   var len = x.length;
   console.log("hello");
  for(var i = 0; i < len; i++){
    //console.log("hello");
      if(x[i].innerHTML == "USA"){
          console.log("I found " + x[i].textContent);
          return;
      }
  }
}
console.log("Question 1");
  getUSA();

// 2. Sales

// Define function getPeopleInSales()

// Print the names of all the people in the sales department.

function getPeopleInSales(){
    var emps = document.getElementsByTagName("td");
    for(var i = 0; i < emps.length; i++){
        if(emps[i].classList.contains("empName")){
            if(emps[i+1].textContent == "Sales"){
                console.log("We got a salesman: " + emps[i].textContent);
            }
        }
    }
}
console.log("Question 2");
getPeopleInSales();

// 3. Click Here

// Define function getAnchorChildren()

// Find all anchor elements with a <span> child.

// Print the contents of <span>

function getAnchorChildren(){
    var anchor = document.getElementsByTagName("a");    //if you switch "a" to "*" you can find all parents with a span child
    var len = anchor.length;
    for(var i = 0; i < len; i++){
        var chillen = anchor[i].children;               //holds the children so we can see if they're spans
        var len2 = chillen.length;
        for(var j = 0; j < len2; j++){
            if(chillen[j].nodeName == "SPAN"){
                console.log(anchor[i].innerText + " has a span child :)");
            }
        }
    }
}
console.log("Question 3");
getAnchorChildren();

// 4. Hobbies

// Define function getSkills()

// Find all checked options in the 'skills' select element.

// Print the value and the contents.

function getSkills(){
    var selects = document.getElementsByTagName("select");
    var len = selects.length;
    var skillz = HTMLSelectElement;
    for(var i = 0; i < len; i++){
        if(selects[i].name == "skills"){
            skillz = selects[i];
        }
    }
    var len2 = skillz.length;
    for(var j = 0; j < len2; j++){
        if(skillz[j].selected == true){
            console.log(skillz[j].textContent);
        }
    }

}
console.log("Question 4");
getSkills();

// 5. Custom Attribute

// Define function getCustomAttribute()

// Find all elements with "data-customAttr" attribute

// Print the value of the attribute.

// Print the element that has the attribute.

function getCustomAttribute(){
    var errthing = document.getElementsByTagName("*");
    var len = errthing.length;
    for(var i = 0; i < len; i++){
        if(errthing[i].hasAttribute("data-customAttr")){
            console.log(errthing[i].nodeName + " " + errthing[i].textContent + " has a custom attribute!");
        }
    }
}
getCustomAttribute();

// 6. Sum Event

// NOTE: Write unobtrusive Javascript

// Regarding these elements:
	
// <input id="num1" class="nums" type="text"/>
	
// <input id="num2" class="nums" type="text"/>
	
// <h3>Sum: span id="sum"></span></h3>


// Define onchange event handler.

// Add <input> element values.

// Put the sum in the <span> element.

// If values cannot be added, put "Cannot add" in the <span> element

var number1 = document.getElementById("num1");
var number2 = document.getElementById("num2");
var summer = document.getElementById("sum");
number1.onchange = function(){
    var uno = Number(number1.value);
    var dos = Number(number2.value);
    var sum = uno + dos;

    if(sum != undefined && sum == NaN){
        summer.textContent = sum;
    }else {
        summer.textContent = "Cannot add";
    }
}
number2.onchange = function(){
    var uno = Number(number1.value);
    var dos = Number(number2.value);
    var sum = uno + dos;

    if(sum != undefined && sum == NaN){         //When i had != NaN, it still printed NaN
        summer.textContent = sum;               //When I changed it to this it worked, idk why
    }else {
        summer.textContent = "Cannot add";
    }
}



// 7. Skills Event

// NOTE: Write unobtrusive Javascript

// When user selects a skill, create an alert with a message similar to:
	
// "Are you sure CSS is one of your skills?"

// NOTE: no alert should appear when user deselects a skill.

var selecters = document.getElementsByTagName("select");
var leng = selecters.length;
var skrillz = HTMLSelectElement;
for(var i = 0; i < leng; i++){
    if(selecters[i].name == "skills"){
            skrillz = selecters[i];
    }
}
for(var q = 0; q < skrillz.length; q++){
    skrillz.onchange = function(){
        for(var i = 0; i < skrillz.length; i++){
        if(skrillz[i].selected == true){
            alert("Really, you think you're proficient in " + skrillz[i].value + "?");
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


var colors = document.getElementsByName("favoriteColor");
for(var i = 0; i < colors.length; i++){
    var oldfav;
    colors[i].onchange = function(){
        for(var j = 0; j < colors.length; j++){
            if(colors[j].checked == true){
                if(oldfav != null){             //if oldfav is null, it's the first click
                alert("Oh, so you like " + colors[j].value + " more than " + oldfav + " now?");}
                oldfav = colors[j].value;
                colors[0].style.backgroundColor = oldfav;       //idk why these don't work but they should
                colors[1].style.backgroundColor = oldfav;
                colors[2].style.backgroundColor = oldfav;
                colors[3].style.backgroundColor = oldfav;
            }
        }
    }
}

// 9. Show/Hide Event

// NOTE: Write unobtrusive Javascript

// When user hovers over an employees name:
	
// Hide the name if shown.
// 	Show the name if hidden.

var workers = document.getElementsByClassName("empName");
for(var i = 0; i < workers.length; i++){
    workers[i].onmouseover = function(){        //makes it invisible when hovering over it
        this.style.visibility = "hidden"; 
    }

    workers[i].onmouseout = function(){
        this.style.visibility = "visible";      //brings it back when you leave it
    }
}


// 10. Current Time

// Regarding this element:
// 	<h5 id="currentTime"></h5>

// Show the current time in this element in this format: 9:05:23 AM

// The time should be accurate to the second without having to reload the page.

function timeDisplay(){
    var currentTime = new Date();               //creates a new date object with the current datetime values
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();
    var seconds = currentTime.getSeconds();
    var ampm;
    if(hours < 12){                         //if it's before 12 it's the morning
        ampm = "AM"
    } else if(hours == 24){                 //if its midnight, it's in the morning
        hours = hours - 12;
        ampm = "AM";
    } else{
        hours = hours - 12                  //if it's after 12 it's the afternoon
        ampm = "PM";
    }
    if(minutes < 10){
        minutes = "0" + minutes;            //makes it look pretty
    }
    if(seconds < 10){
        seconds = "0" + seconds;            //makes it look pretty
    }

    var clock = document.getElementById("currentTime");
    clock.textContent = hours + ":" + minutes + ":" + seconds + " " + ampm;
    clock.innerText = hours + ":" + minutes + ":" + seconds + " " + ampm;

    setTimeout("timeDisplay()", 1000);          //calls this function every 1000 miliseconds, aka every second

}
timeDisplay();

// 11. Delay
// Regarding this element:
	
// <p id="helloWorld">Hello, World!</p>

// Three seconds after a user clicks on this element, change the text to a random color.

var hw = document.getElementById("helloWorld");
hw.onclick = function(){
    setTimeout(function(){hw.style.color = randomColors()}, 3000);
}

function randomColors() {       //taken from Paul Irish's random color generator
    return '#' + Math.floor(Math.random() * 16777215).toString(16);     
  } //http://www.paulirish.com/2009/random-hex-color-code-snippets/

// 12. Walk the DOM

// Define function walkTheDOM(node, func)

// This function should traverse every node in the DOM. 
// Use recursion.

// On each node, call func(node).

function walkTheDom(node, func){
    for(var i = 0; i < node.length; i++){
        walkTheDom(node[i], func)
    }
    if(node.length == undefined){
        func(node);
    }
}

function printer(node){
    console.log(node.innerText);
}

var noodles = document.getElementsByTagName("*");
console.log("");
console.log("");
console.log("");
console.log("Walking the DOM");
console.log("Question 12");
walkTheDom(noodles, printer);
