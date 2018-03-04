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
console.log();

//Q4 Find all checked options in the 'skills' select element
let getSkills = function(){
    let skeelz = document.getElementsByName("skills");
    
    console.log(skeelz)
   
};

console.log("");
getSkills();