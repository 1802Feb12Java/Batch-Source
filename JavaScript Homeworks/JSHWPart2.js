// 1. USA
// Define function getUSA()

// Find the html element that contains "USA".

// Print that element's contents.
  
function getUSA(){
    var tags = document.getElementsByTagName("*"); //get all tags
    var searchTag;
    for(var i=0; i<tags.length; i++){
        if(tags[i].innerHTML == "USA"){ //search for tag with "USA"
        searchTag = tags[i];
    }
}
    //prints all usa tags
    console.log(searchTag.textContent);

}
getUSA(); //call function


/* 2. Sales
* Define function getPeopleInSales()
* Print the names of all the people in the sales department.
*/
function getPeopleInSales(){
    var getEmployee = document.getElementsByTagName("tr");
    for(var i=0; i<getEmployee.length; i++){
        if(getEmployee[i].children[1].textContent == "Sales"){
            console.log(getEmployee[i].children[0].textContent); //prints content
        }
}
}
getPeopleInSales();//call function

/* 3. Click Here
* Define function getAnchorChildren()
* Find all anchor elements with a <span> child.
* Print the contents of <span>
*/
function getAnchorChildren(){
    var getAtags = document.getElementsByTagName("a");//get <a> element since span is in <a> tag
    var getSpanChildren = [];
    var getOneSpan ;
    for(var i=0; i<getAtags.length; i++){
        var anchorChildren = getAtags[i].children;
    for(var j=0; j<anchorChildren.length; j++){
        if(anchorChildren[j].nodeName == "SPAN"){    //get node with span name
            getSpanChildren.push(getAtags[i]);//add element in array
        }
    }
}
//prints elements in span tag
for(var i=0; i<getSpanChildren.length; i++){
     getOneSpan = getSpanChildren[i].children;
    for(var j=0; j<getOneSpan.length; j++){
        if(getOneSpan[j].nodeName == "SPAN"){
            console.log(getOneSpan[j].textContent);
        }
    }
}
}
getAnchorChildren();//call function

/* 4. Hobbies
* Define function getSkills()
* Find all checked options in the 'skills' select element.
* Print the value and the contents.
*/
function getSkills(){
    var getSelect = document.getElementsByTagName("select");
    for(var i=0; i<getSelect.length; i++){
        if(getSelect[i].name == "skills"){
            return getSelect[i];//return array of skills;
        }
    }
  
}
var skills = getSkills();
// prints values if skill is selected
for(var i=0; i<skills.length; i++){
    if(skills[i].selected == true){
        console.log(skills[i].value);
    }
}

/* 5. Custom Attribute
* Define function getCustomAttribute()
* Find all elements with "data-customAttr" attribute
* Print the value of the attribute.
* Print the element that has the attribute.
*/
function getCustomAttribute(){
    var getTags = document.getElementsByTagName("*");
    var data = [];// empty array
    for (var i = 0; i < getTags.length; i++){
        if(getTags[i].hasAttribute("data-customAttr")){
            data.push(getTags[i]);//add tag customer to data array;
        }
    }
    //prints elements
    for(var i = 0; i < data.length; i++){
        console.log(data[i].getAttribute("data-customAttr"));
    }
}
getCustomAttribute();

/* 6. Sum Event
* NOTE: Write unobtrusive Javascript
* Regarding these elements:
* <input id="num1" class="nums" type="text"/>
* <input id="num2" class="nums" type="text"/>  
* <h3>Sum: span id="sum"></span></h3>
* Define onchange event handler.
* Add <input> element values.
* Put the sum in the <span> element.
* If values cannot be added, put "Cannot add" in the <span> element
*/
//gets numbers
var tags = [];
tags.push(document.getElementById("num1"));
tags.push(document.getElementById("num2"));
tags.push(document.getElementById("sum"));
for (var i = 0; i < tags.length; i++){
    tags.onchange = function(){
        //get numbers
        var num1 = tags[0].value;
        var num2 = tags[1].value;
        var sum = num1 + num2;
        if(sum != NaN){
            tags[2].textContent = sum; //displays sum
        }
        else
        {
            tags[2].textContent = "Cannot add";
        }
    };
}


/* 7. Skills Event
* NOTE: Write unobtrusive Javascript
* When user selects a skill, create an alert with a message similar to:
* "Are you sure CSS is one of your skills?"
* NOTE: no alert should appear when user deselects a skill.
*/
var skill = getSkills(); //gets all skills
for(var i = 0; i<skill.length; i++){
    skill.onchange = function(){
        for(var i = 0; i < skill.length; i++){
            if(skill[i].selected == true){
                var choseSkill = skill[i].value;
                alert("Are you sure " + choseSkill + " is one of your skills?"); //creates alert
            }
        }
    };
}


/* 8. Favorite Color Event
* NOTE: Write unobtrusive Javascript
* NOTE: This is regarding the favoriteColor radio buttons.
* When a user selects a color, create an alert with a message similar to:
* "So you like green more than blue now?"
* In this example, green is the new value and blue is the old value.
* Make the background color (of all favoriteColor radio buttons)
* the newly selected favoriteColor
*/

var spanList = document.getElementsByClassName("favoriteColor"); //get color span list
var favColor = document.getElementsByName("favoriteColor");
var oldChoice;
var newChoice;
for(var i=0; i<favColor.length; i++){
    favColor[i].onmousedown = function() {
        for(var j = 0; j < favColor.length; j++){
            if(favColor[j].checked == true){
                oldChoice = favColor[j].value; //gets old color choice
            }
        }
    }
    favColor[i].onchange = function() {
        for(var j=  0; j < favColor.length; j++){
            if(favColor[j].checked == true){
                newChoice = favColor[j].value; //gets new color choice
            }
        }
        for(var j = 0; j < spanList.length; j++){
            spanList[j].style.backgroundColor = newChoice;
        }
        if(oldChoice != null)  //creates alert if new choice is chosen
           alert("So you like " + newChoice + " more than " + oldChoice + " now?");
    }
}


/* 9. Show/Hide Event
* NOTE: Write unobtrusive Javascript
* When user hovers over an employees name:
* Hide the name if shown.
* Show the name if hidden.
*/
var getName = document.getElementsByClassName("empName");//get employees' names
for(var i = 0; i < getName.length; i++){
    getName[i].onmouseover = function(){
        if(this.style.color == "black" || this.style.color == ""){
            this.style.color = "white";//change color so name can be hidden
        }
        else if(this.style.color == "white"){
            this.style.color = "black";//change color so name ca be read
        } 
    }
}

/* 10. Current Time
* Regarding this element:
* <h5 id="currentTime"></h5>
* Show the current time in this element in this format: 9:05:23 AM
* The time should be accurate to the second without having to reload the page.
*/
function getTime(){
    var time = new Date();
    var displayTime = document.getElementById("currentTime");
    
    //display time through header5
    displayTime.textContent = time.toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', second: 'numeric', hour12: true }) ;
    setTimeout(getTime, 1000); //update it every second
}
getTime();

/* 11. Delay
* Regarding this element:
* <p id="helloWorld">Hello, World!</p>
* Three seconds after a user clicks on this element, change the text to a random color.
*/
var changedColor = document.getElementById("helloWorld"); 
changedColor.onclick = function(){
    setTimeout(function(){
        changedColor.style.color = "#"+Math.floor(Math.random()*16777215).toString(16);//change to a random color
    }, 3000);   //timer for 3000ms
}

/* 12. Walk the DOM
* Define function walkTheDOM(node, func)
* This function should traverse every node in the DOM.
* Use recursion.
* On each node, call func(node).
*/
function walkTheDOM(node, func){
    for(var i = 0; i <node.length; i++){
        walkTheDOM(node[i], func); 
    }
    if(node.length == undefined){   
        //use call func when node's length is null
        func(node);
    }
}
