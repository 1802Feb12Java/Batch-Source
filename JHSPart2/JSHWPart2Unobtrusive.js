        
        //1. Define function getUSA()
        // Find the html element that contains "USA".
        // Print that element's contents.

        function getUSA(){
            //grabbing all tags
            var usa;

            var tags = document.getElementsByTagName("*");
            console.log(tags.length);
            //loop until finding innerHTML containing "USA"
            for(i = 0; i < tags.length; i++){
                if(tags[i].innerHTML == "USA"){
                    usa = tags[i];
                }
            }
            //print out the text
            console.log(usa.innerHTML);
        }

        getUSA();

        console.log("===========================================");

        // 2. Sales
        // Define function getPeopleInSales()
        // Print the names of all the people in the sales department.
  
        function getPeopleInSales(){
            var employees = document.getElementsByClassName("empName");

            for(i = 0; i < employees.length; i++){
                //nextElementSibling will get the next node that shares the same parent
                if(employees[i].nextElementSibling.innerHTML == "Sales"){
                    console.log(employees[i].innerHTML);
                }
            }
        }

        getPeopleInSales();
        console.log("===========================================");
        // 3. Click Here
        // Define function getAnchorChildren()
        // Find all anchor elements with a <span> child.
        // Print the contents of <span>

        function getAnchorChildren(){
            var tags = document.getElementsByTagName("*");        
            //loops through all tags
           for(i = 0; i < tags.length; i++){
               var children = [];
               children = tags[i].children;
               for(j = 0; j < children.length; j++){
                   if(children[j].nodeName == "SPAN"){
                      console.log(children[j].innerHTML);
                   }
               }
            }
        }

        getAnchorChildren();

        console.log("===========================================");

        // 4. Hobbies
        // Define function getSkills()
        // Find all checked options in the 'skills' select element.
        // Print the value and the contents.

        function getSkills(){
            var skills = document.getElementsByName("skills");
            var skillOptions = skills[0].children;

            for(i = 0; i < skillOptions.length; i++){
                console.log(skillOptions[i].innerHTML);
            }
            
        }

        getSkills();

        console.log("===========================================");


        // 5. Custom Attribute
        // Define function getCustomAttribute()
        // Find all elements with "data-customAttr" attribute
        // Print the value of the attribute.
        // Print the element that has the attribute.
        
        function getCustomAttribute(){
            //create an array of anything with the custom attribute 'data-customAttr'
            var customs = document.querySelectorAll('[data-customAttr]');
            for(i = 0; i < customs.length; i++){
                //return the values of the custom attributes
                console.log(customs[i].getAttributeNode("data-customAttr").value);
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

var num1 = document.getElementById("num1");
var num2 = document.getElementById("num2");
var sum = document.getElementById("sum");

//if num1 is changed
num1.onchange = function(){
    console.log(isNaN(parseInt(num2.value)));
    if(!isNaN(parseInt(num2.value))){
        sum.innerHTML = parseInt(num1.value) + parseInt(num2.value);
    }
    else{
        sum.innerHTML = parseInt(num1.value);
    }
}

//if num2 is changed
num2.onchange = function(){
    if(!isNaN(parseInt(num1.value))){
        sum.innerHTML = parseInt(num1.value) + parseInt(num2.value);
    }
    else{
        sum.innerHTML = parseInt(num2.value);
    }
}

// 7. Skills Event
// NOTE: Write unobtrusive Javascript
// When user selects a skill, create an alert with a message similar to:	
// "Are you sure CSS is one of your skills?"
// NOTE: no alert should appear when user deselects a skill.

var skillsForm = document.getElementsByName("skills");
var skills = skillsForm[0].children;

skillsForm[0].onchange = function(){
    for(i = 0; i < skills.length; i++){
        if(skills[i].selected){
            alert("Are you sure " + skills[i].value + " is one of your skills?");
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

var colorRadios = document.getElementsByName("favoriteColor");
var lastColor = "";
console.log(colorRadios[0].checked);

//it sees that i can check all of the values like this (putting it in a for loop)
for(i = 0; i < colorRadios.length; i++){
    colorRadios[i].onchange = function(){
        for(i = 0; i < colorRadios.length; i++){
            if(colorRadios[i].checked == true){
                if(lastColor == ""){
                    alert(colorRadios[i].value + " is a nice color!");
                    lastColor = colorRadios[i].value;
                }
                else{
                    alert("So you like " + colorRadios[i].value + " more than " + lastColor + " now?");
                    lastColor = colorRadios[i].value;
                }
            }
        }
    }
}

// 9. Show/Hide Event
// NOTE: Write unobtrusive Javascript
// When user hovers over an employees name:	
// Hide the name if shown.
// Show the name if hidden.

var employees = document.getElementsByClassName("empName");

for(i = 0; i < employees.length; i++){
    employees[i].onmouseover = function(){
        if(this.style.color == "white"){
            this.style.color = "black";
        }
        else{
            this.style.color = "white";
        }
    }
}

// 10. Current Time
// Regarding this element:
// <h5 id="currentTime"></h5>
// Show the current time in this element in this format: 9:05:23 AM
// The time should be accurate to the second without having to reload the page.

var currentTime = document.getElementById("currentTime");

function time(){
    var date = new Date();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    var meridies = "";
    
    //reset innerHTML (because we are appending)
    //so fast no one will see
    currentTime.innerHTML = "";

    if(hours >= 12){ 
        meridies = "PM";
        if(hours == 12){
            currentTime.innerHTML += hours;
        }
        else{
            currentTime.innerHTML += hours - 12;
        }
    }
    else{
        meridies = "AM";
        currentTime.innerHTML += hours;
    }

    currentTime.innerHTML += ":" + minutes;
    currentTime.innerHTML += ":" + seconds + meridies;

    //run this function once every second
    setTimeout(time, 1000);
}

time();

// 11. Delay
// Regarding this element:	
// <p id="helloWorld">Hello, World!</p>
// Three seconds after a user clicks on this element, change the text to a random color.

var helloWorld = document.getElementById("helloWorld");

helloWorld.onclick = function(){
    setTimeout(function(){
        helloWorld.style.color = '#' + Math.floor(Math.random()*16777215).toString(16); 
    }, 3000);
}

// 12. Walk the DOM
// Define function walkTheDOM(node, func)
// This function should traverse every node in the DOM. 
// Use recursion.
// On each node, call func(node).

var allElements = document.getElementsByTagName("*");

//gott finish this
function walkTheDOM(node, func){
    // for(i = 0; i < node.length; i++){
    //     func(node);
    //     walkTheDOM(node[i], func);
    // }
}

// function output(content){
//     console.log(content);
// }

//walkTheDOM(allElements, output);
