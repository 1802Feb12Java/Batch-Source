//Homework 2

//Q1: USA! USA! 
let findUSA = function(){
    //select all the elements
    let usaUSA = document.querySelectorAll("*");

    //loop through the elements, if the text 'USA' is found
    //in the text content, print it outer
    for(let index = 0; index < usaUSA.length; index++){
        if(usaUSA[index].textContent === "USA") {
           console.log(usaUSA[index].textContent);
        };
    };
};

console.log("Question 1 output:");
findUSA();
console.log("");

//Q2 Print all employees in sales department
let getPeopleInSales = function(){
    let department = document.querySelectorAll("td");
    let employees = document.querySelectorAll("tr");

    //loop through the table data
    for (let index = 0; index < department.length; index++){
        //if table data shows employee, print the previous index
        //containing the employee name
        if(department[index].innerHTML == "Sales"){
            console.log(department[index - 1].innerHTML);
        };
    };
};

console.log("Question 2 output:");
getPeopleInSales();
console.log("");

//Q3 Find all anchor elements with span child, print the content of the span
let getAnchorChildren = function(){
    //grab em
    let anchorChild = document.querySelectorAll("span");
    for (let index = 0; index < anchorChild.length; index++){
        //print em
        console.log(anchorChild[index].innerHTML);
    };
};

console.log("Question 3 output:");
getAnchorChildren();

//Q4 Find all checked options in the 'skills' select element
let getSkills = function(){
    //I've tried this a few different way, but it only shows
    //JavaScript selected as true, it's not picking up DOM
    //even though they look exactly the same.  I'll come 
    //back to this if I have time.
    let skeelz = document.getElementsByName("skills");
    let toPayTheBillz = skeelz[0].children;
    for(let index = 0; index < toPayTheBillz.length; index++){
        if(toPayTheBillz[index].selected){
            console.log(toPayTheBillz[index].textContent);
        };
    };   
};

console.log("");
console.log("Question 4 output: ")
getSkills();
console.log("");

//Q5 Custom attribute, find all elements with data-customAttr
//print the attribute and print the element that has the attribute
let getCustomAttribute = function(){
    let custAtt = document.querySelectorAll("[data-customAttr]");
    for (let index = 0; index < custAtt.length; index++){
        console.log();
        console.log(custAtt[index].tagName +
            " contains custom attribute: " +
            custAtt[index].getAttribute("data-customAttr"));
    };
};

console.log("");
console.log("Question 5 output:");
getCustomAttribute();
console.log("");

//Question 6:  Define onchange event handler to add the input elements
//num1 and num2.  Output the sum in the sum element, if the numbers
//cannot be added, put "Cannot add" in the sum span element

/* NOTE:  This method is unobtrusive insofar that the fields will
still exist and be in the same place whether JavaScript is working
or not, but without the JavaScript functionality, they will do 
nothing regardless of what is entered in them
*/

//add the eventListener to the number fields, setting it to listen for
//an onchange event in either field
document.getElementById("num1").addEventListener("change", handleSum);
document.getElementById("num2").addEventListener("change", handleSum);

function handleSum() {
    //get the value of the first and second fields, converting them
    //to Number.
    let firstNum = Number(document.getElementById("num1").value);
    let secondNum = Number(document.getElementById("num2").value);

    //get the "sum" element in order to be able to change the field
    let numSum = document.getElementById("sum");

    //add the numbers from num1 and num2
    let summed = firstNum + secondNum;

    //if the sum is not a number, print the cannot add message
    //blank fields are treated as the Number 0.
    if(isNaN(summed)){
        numSum.textContent = "Cannot add";
    }

    //otherwise, change the textContent of the element to the
    //calculated sum
    else {
        numSum.textContent = summed;
    };
};

//Question 7:  Create a message alert when a user selects a skill, 
//verifying that the user possesses that skill by producing an 
//alert.  Produce nothing if the user deselects

//get the skills node
let skeelzes = document.getElementsByName("skills");

//add the listener to the selection box
skeelzes[0].addEventListener("change", youSure);

function youSure(){
    //form the alert message and then pass it to the alert function
    let msg = "Are you sure " + skeelzes[0].value + " is one of your skills?";
    alert(msg);
};

//Question 8:  When a user selects a color ask "so you like <new color>
//more than <old color>? " in an alert and change the background of
//all favorite color radio buttons to the new color.

//add a listener to all favorite color radio buttons
var favColor = document.getElementsByName("favoriteColor");
for(let index = 0; index < favColor.length; index++){
    favColor[index].addEventListener("click", changeColor);
}

//assign a global variable to keep track of the previously
//selected color
var prevColor = "Pearlescent Subtle Eggshell White";

function changeColor(){
    for (let index = 0; index < favColor.length; index++){
        //when the checked option is found form the message and produce the alert
        if(favColor[index].checked){
            let msg = "Oh, so you like " + favColor[index].value +
            " better than " + prevColor + " now??";

            alert(msg);

            //set prevColor to the currently selected color for future use
            prevColor = favColor[index].value;
           

            //change the background color of the  favoriteColor radio button
            favColor[index].onchange = function(){
                //create a string to use with jQuery
                newWrap = "<span style='background-color:" + prevColor + "'></span>";

                //create the new span wrap dynamically around the radio buttons using jquery
                $('[name="favoriteColor"]').wrap( newWrap );
            };
        };
    };
};

//Question 9: When a user hovers over an employee name, hide the name if shown, 
//show the name if hidden.

//get the employee elements
let employeeNames = document.getElementsByClassName("empName");

//loop through the employee list, using an anonymous callback function for the mouseover behavior
for(let index = 0; index < employeeNames.length; index++){
    employeeNames[index].onmouseover = function(){
        //if the employee name is visible, change the text color to white to blend in with the background
        if(this.style.color == "black" || this.style.color == ""){
            this.style.color = "white";
        }

        //if the employee name isn't visible, change the color back to black
        else if(this.style.color == "white"){
            this.style.color = "black";
        }; 
    };
};

//Question 10:  Show current time in AM/PM format without updating
let yoWhatTimeItIs = function(){
    //get the element
    let timeElement = document.getElementById("currentTime");

    //create a date object to access time/date functions
    let time = new Date();

    //use the 12 hour format with a Blade Runner hat-tip
    let wakeUpTimeToDie = time.toLocaleTimeString();
    
    //write the formatted time to the element
    timeElement.textContent = wakeUpTimeToDie;
};

//print the updated clock every 1000ms
setInterval(yoWhatTimeItIs, 1000);

//Question 11:  When a user clicks on Hello world, wait three seconds, change the text to a random color
//get the element
let supPlanet = document.getElementById("helloWorld");

supPlanet.onclick = function(){
    //set a timeout function that waits for 3000ms and then generates the random color and sets the element
    //color to it
    setTimeout(function(){
        supPlanet.style.color = '#' + Math.floor(Math.random() * 16777215).toString(16);
    }, 3000);
};