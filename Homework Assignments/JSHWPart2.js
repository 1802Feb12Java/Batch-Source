

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



  

// 4. Hobbies

// Define function getSkills()
function getSkills(){
    var skillsElements = document.getElementsByName("skills");
    // var optionElements = skillsElements.getElementsByTagName("option");
    var children = skillsElements.childNodes;
   
    while(firstChild.nextElementSibling != null){
        // Find all checked options in the 'skills' select element.
        if(optionElements.hasAttribute("selected")){
            // Print the value and the contents.
            console.log(optionElements[i].getAttribute("value"));
            console.log(optionElements[i].textContent);
        }
    }
}

getSkills();




  

// 5. Custom Attribute

// Define function getCustomAttribute()

// Find all elements with "data-customAttr" attribute

// Print the value of the attribute.

// Print the element that has the attribute.


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


// 7. Skills Event

// NOTE: Write unobtrusive Javascript

// When user selects a skill, create an alert with a message similar to:
	
// "Are you sure CSS is one of your skills?"

// NOTE: no alert should appear when user deselects a skill.


// 8. Favorite Color Event

// NOTE: Write unobtrusive Javascript

// NOTE: This is regarding the favoriteColor radio buttons.

// When a user selects a color, create an alert with a message similar to:
	
// "So you like green more than blue now?"

// In this example, green is the new value and blue is the old value.

// Make the background color (of all favoriteColor radio buttons) 
// the newly selected favoriteColor


// 9. Show/Hide Event

// NOTE: Write unobtrusive Javascript

// When user hovers over an employees name:
	
// Hide the name if shown.
// 	Show the name if hidden.


// 10. Current Time

// Regarding this element:
// 	<h5 id="currentTime"></h5>

// Show the current time in this element in this format: 9:05:23 AM

// The time should be accurate to the second without having to reload the page.


// 11. Delay
// Regarding this element:
	
// <p id="helloWorld">Hello, World!</p>

// Three seconds after a user clicks on this element, change the text to a random color.

// 12. Walk the DOM

// Define function walkTheDOM(node, func)

// This function should traverse every node in the DOM. 
// Use recursion.

// On each node, call func(node).
