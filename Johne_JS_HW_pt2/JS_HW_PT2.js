
/*
----------------------------------------------------------------------------------

PART II


Part II will focus on Javascript's ability to manipulate the DOM.

Use the provided HTML page.

Put your Javascript in the provided <script> element at the bottom of the page.

Please put the question itself as a comment above each answer.


-----------------------------------------------------------------------------------
*/


// 1. USA
// Define function getUSA()

// Find the html element that contains "USA".

// Print that element's contents.

function getUSA() {

    var usa = document.body.childNodes[1].childNodes[3].childNodes[1].childNodes[3].innerHTML;
    return usa;
}
console.log(getUSA());
// 2. Sales

// Define function getPeopleInSales()

// Print the names of all the people in the sales department.
function getPeopleInSales() {
    var thirdDiv = document.getElementsByTagName('div')[5]; //div with name and departments
    var tableTag = thirdDiv.getElementsByTagName('table')[0]; //table with tbody and tr
    var tbodyTag = tableTag.getElementsByTagName('tbody')[0]; //list of tr
    
    var trRecord = tbodyTag.getElementsByTagName('tr')[1];    //the record of tr
    var john = trRecord.getElementsByTagName('td')[0].innerHTML; //department name
    
    var trRecord = tbodyTag.getElementsByTagName('tr')[3];    //the record of tr
    var austin = trRecord.getElementsByTagName('td')[0].innerHTML; //department name
    
    var trRecord = tbodyTag.getElementsByTagName('tr')[5];    //the record of tr
    var courtney = trRecord.getElementsByTagName('td')[0].innerHTML; //department name

    var trRecord = tbodyTag.getElementsByTagName('tr')[6];    //the record of tr
    var scout = trRecord.getElementsByTagName('td')[0].innerHTML; //department name

    var salesName = [john, austin, courtney, scout];
    
    return salesName;
}
console.log('Name of all people in sales department: ' + getPeopleInSales());

// 3. Click Here

// Define function getAnchorChildren()

// Find all anchor elements with a <span> child.

// Print the contents of <span>

//johne - there might be four
function getAnchorChildren() {
    var anchorTag1 = document.getElementsByTagName('a')[6];
    var here1 = anchorTag1.getElementsByTagName('span')[0].innerHTML;  //here1

    var formTag = document.getElementsByTagName('form')[0];
    var here2 = formTag.getElementsByTagName('a')[0].getElementsByTagName('span')[0].innerHTML;  //here2

    var Here = formTag.getElementsByTagName('a')[1].getElementsByTagName('span')[0].innerHTML;  //Here

    var here3 = document.getElementsByTagName('body')[0].childNodes[5].getElementsByTagName('span')[0].innerHTML;

    var arrResult = [];
    arrResult.push(here1, here2, Here, here3);

    return arrResult;
}
console.log('Contents of anchor elements with span child: ' + getAnchorChildren());
// 4. Hobbies

// Define function getSkills()

// Find all checked options in the 'skills' select element.

// Print the value and the contents.
function getSkills() {
    var formTag = document.getElementsByTagName('form')[0].childNodes[55];
    var javaSkill = formTag.getElementsByTagName('option')[0].innerHTML;    //java
    var netSkill = formTag.getElementsByTagName('option')[1].innerHTML;    //.NET
    var domSkill = formTag.getElementsByTagName('option')[2].innerHTML;    //DOM
    var htmlSkill = formTag.getElementsByTagName('option')[3].innerHTML;    //HTML
    var cssSkill = formTag.getElementsByTagName('option')[4].innerHTML;    //CSS
    var javascriptSkill = formTag.getElementsByTagName('option')[5].innerHTML;    //JavaScript
    var arrSkill = [javaSkill, netSkill, domSkill, htmlSkill, cssSkill, javascriptSkill];

    return arrSkill;
}
console.log('All checked options in SKills: ' + getSkills());

// 5. Custom Attribute

// Define function getCustomAttribute()

// Find all elements with "data-customAttr" attribute

// Print the value of the attribute.

// Print the element that has the attribute.


function getCustomAttribute() {
    var dataUSA = document.body.getElementsByTagName('div')[2].getElementsByTagName('span')[0].getAttribute('data-customAttr');  //value of attribute
    var usaElement = document.body.getElementsByTagName('div')[2].getElementsByTagName('span')[0]; 

    var dataCourt = document.body.getElementsByTagName('div')[5].childNodes[1].getElementsByTagName('tr')[5].childNodes[1].getAttribute('data-customAttr');
    var courtElement = document.body.getElementsByTagName('div')[5].childNodes[1].getElementsByTagName('tr')[5].childNodes[1];

    var data7 = document.body.childNodes[1].childNodes[9].childNodes[3].getAttribute('data-customAttr');
    var element7 = document.body.childNodes[1].childNodes[9].childNodes[3];

    var data24 = document.body.childNodes[1].childNodes[9].childNodes[6].getAttribute('data-customAttr');
    var element24 = document.body.childNodes[1].childNodes[9].childNodes[6];

    var data500 = document.body.childNodes[5].childNodes[1].getAttribute('data-customAttr');
    var element500 = document.body.childNodes[5].childNodes[1];

    return 'CustomAttr = ' + dataUSA + '\nElement with Attrbute: ' + usaElement + '\n\n' +
    'CustomAttr = ' + dataCourt + '\nElement with Attrbute: ' + courtElement + '\n\n' +
    'CustomAttr = ' + data7 + '\nElement with Attrbute: ' + element7 + '\n\n' +
    'CustomAttr = ' + data24 + '\nElement with Attrbute: ' + element24 + '\n\n' +
    'CustomAttr = ' + data500 + '\nElement with Attrbute: ' + element500 + '\n\n';
}
console.log(getCustomAttribute());

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

/*
    assign a variable for the nums class for child nodes or input
    then find the sum of the two inputs
    if sum is NaN, display cannot add, otherwise display the sum
*/
var nums = document.getElementsByClassName('nums');
for(var i = 0; i < nums.length; i++) {
        nums[i].onchange = function() {
        var sum = document.getElementById('sum').innerHTML = parseInt(nums[0].value) + parseInt(nums[1].value);
        if(isNaN(sum)) {
            document.getElementById('sum').innerHTML = 'Cannot Add';
        } else {
            return sum;
        }
    }
}



// 7. Skills Event

// NOTE: Write unobtrusive Javascript

// When user selects a skill, create an alert with a message similar to:
	
// "Are you sure CSS is one of your skills?"

// NOTE: no alert should appear when user deselects a skill.

function skillsFunct() {
    if(this.value === 'java') {
        alert("Are you sure Java is one of your skills?");
    }
    if(this.value === 'net') {
        alert("Are you sure .NET is one of your skills?");
    }
    if(this.value === 'dom') {
        alert("Are you sure DOM is one of your skills?");
    }
    if(this.value === 'html') {
        alert("Are you sure HTML is one of your skills?");
    }
    if(this.value === 'css') {
        alert("Are you sure CSS is one of your skills?");
    }
    if (this.value === 'javascript') {
        alert("Are you sure JavaScript is one of your skills?");
    }
}

document.querySelector('select[name=skills]').addEventListener('change',skillsFunct);


// 8. Favorite Color Event

// NOTE: Write unobtrusive Javascript

// NOTE: This is regarding the favoriteColor radio buttons.

// When a user selects a color, create an alert with a message similar to:
	
// "So you like green more than blue now?"

// In this example, green is the new value and blue is the old value.

// Make the background color (of all favoriteColor radio buttons) 
// the newly selected favoriteColor

/*
    PARTIALLY COMPLETED
*/
function favoriteColor() {
    if(this.value === 'blue'){
        alert("So you like blue more than __ now?");
    }
    if(this.value === 'red'){
        alert("So you like red more than __ now?");
    }
    if(this.value === 'green'){
        alert("So you like green more than __ now?");
    }
    if(this.value === 'orange'){
        alert("So you like orange more than __ now?");
    }

}
document.querySelector('input[value=blue]').addEventListener('click', favoriteColor);
document.querySelector('input[value=red]').addEventListener('click', favoriteColor);
document.querySelector('input[value=green]').addEventListener('click', favoriteColor);
document.querySelector('input[value=orange]').addEventListener('click', favoriteColor);
//document.querySelector('input[name=favoriteColor]').value;
//console.log(document.querySelector('input[name=favoriteColor]'));

// 9. Show/Hide Event

// NOTE: Write unobtrusive Javascript

// When user hovers over an employees name:
	
// Hide the name if shown.
// 	Show the name if hidden.

/*
    loop through names, check if names are shown or hidden, NOT COMPLETED
*/

var empNames = document.getElementsByClassName('empName');
// empNames[0].style.visibility = 'hidden';
for(var i = 0; i < empNames.length; i++) {
    empNames[i].onmouseover = function() {
        
        if(empNames[i]) {
            empNames[i] = 'hidden';
        } else {
            empNames[i] = 'visible';
        }
    }
}


// 10. Current Time

// Regarding this element:
// 	<h5 id="currentTime"></h5>

// Show the current time in this element in this format: 9:05:23 AM

// The time should be accurate to the second without having to reload the page.
  
function currentTime() {
    var currentTime = new Date();
    document.getElementById('currentTime').innerHTML = currentTime.toLocaleTimeString();
}
setInterval(currentTime, 1000);
// 11. Delay
// Regarding this element:
	
// <p id="helloWorld">Hello, World!</p>

// Three seconds after a user clicks on this element, change the text to a random color.

/*
    setTimeout will call a function and will change the text to a red color
*/
function changeColor() {
    setTimeout(function () {
        document.getElementById('helloWorld').style.color = 'red';
    }, 3000);
}
document.getElementById('helloWorld').addEventListener('click', changeColor);

// 12. Walk the DOM

// Define function walkTheDOM(node, func)

// This function should traverse every node in the DOM. 
// Use recursion.

// On each node, call func(node).

/*
    INCOMPLETE
*/



