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
