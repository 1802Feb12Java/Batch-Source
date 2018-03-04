/*----------------------------------------------------------------------------------
PART II
Part II will focus on Javascript's ability to manipulate the DOM.
Use the provided HTML page.
Put your Javascript in the provided <script> element at the bottom of the page.
Please put the question itself as a comment above each answer.
-----------------------------------------------------------------------------------
*/

/* 1. USA
 * Define function getUSA()
 * Find the html element that contains "USA".
 * Print that element's contents.  
 */
function getUSA(){ 
    var list = document.getElementsByTagName("*");
    Array.prototype.forEach.call(list, element => {
        if(element.innerHTML == "USA")
            console.log(element.innerHTML);
        }
    );
}

/* 2. Sales
 * Define function getPeopleInSales()
 * Print the names of all the people in the sales department.
 */
function getPeopleInSales(){
    var list = document.getElementsByTagName('tr')
    
    // console.log(list[2].children[1].innerHTML);
    Array.prototype.forEach.call(list, element=>{
        if(element.children[1].innerHTML == "Sales")
            console.log(element.children[0].innerHTML);
    });
}

/* 3. Click Here
 * Define function getAnchorChildren()
 * Find all anchor elements with a <span> child.
 * Print the contents of <span>
 */
function getAnchorChildren(){
    var list = document.getElementsByTagName('a')

    Array.prototype.forEach.call(list,element=>{
        if(element.childElementCount > 0){
            if(element.children[0].tagName == 'SPAN')//idk why it's caps?
                console.log(element.children[0].innerHTML);
        }
    });
}

/* 4. Hobbies
 * Define function getSkills()
 * Find all checked options in the 'skills' select element.
 * Print the value and the contents.
 */
function getSkills(){
    var list = document.getElementsByName('skills');
    console.log("Value \t Content")
    Array.prototype.forEach.call(list[0], element=>{
        console.log(element.value + "\t" + element.innerHTML);
    });
}

/* 5. Custom Attribute
 * Define function getCustomAttribute()
 * Find all elements with "data-customAttr" attribute
 * Print the value of the attribute.
 * Print the element that has the attribute.
 */
function getCustomAttribute() {
    var list = document.querySelectorAll('[data-customAttr]');
    console.log("Attr Values \t Elements")
    Array.prototype.forEach.call(list, element=>{
        console.log(element.getAttribute('data-customAttr') + "\t\t " + element.innerHTML);
    });
}

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
//set value to sum
function loadSum(n){
    document.getElementById('sum').innerHTML = n;
}

//get values from input tags
function sumEvent(){
    var n1 = Number(document.getElementById('num1').value)
    var n2 = Number(document.getElementById('num2').value)
    console.log(n1 + "+" + n2);
    //console.log('in Sum Event');
    if(n1 != undefined && !isNaN(n1) && n2 != undefined && !isNaN(n2)){
        loadSum(n1+n2);
    }
        
}

//load triggers/event listener is at the buttom of the file

/* 7. Skills Event
 * NOTE: Write unobtrusive Javascript
 * When user selects a skill, create an alert with a message similar to:
 * "Are you sure CSS is one of your skills?"
 * NOTE: no alert should appear when user deselects a skill.
 */
//alert the user
function skillAlert(selected){
    alert("Are you sure " + selected + " is one of your skills?");
}

//get selected value from select tag
function selectSkill(){
    skill = document.getElementsByName('skills')[0];
    selected = skill.options[skill.selectedIndex].value;
    skillAlert(selected);
}

//load triggers/event listener is at the buttom of the file

/* 8. Favorite Color Event
 * NOTE: Write unobtrusive Javascript
 * NOTE: This is regarding the favoriteColor radio buttons.
 * When a user selects a color, create an alert with a message similar to:
 * "So you like green more than blue now?"
 * In this example, green is the new value and blue is the old value.
 * Make the background color (of all favoriteColor radio buttons)
 * the newly selected favoriteColor
 */
function colorAlert(newC, prevC){
    if(prevC === undefined)
        alert("Your new Favorite Color is " + newC);
    else
        alert("So you like " + newC + " more than " + prevC+" now?");
}

function setFavColor(color){
    document.getElementById('firstForm').style.backgroundColor = color;
}

function favoriteColor(color){
    curColor = color.value;
    if(firstColor){
        colorAlert(curColor);
        firstColor = false;
    }
    else{
        colorAlert(curColor, prevColor);
    }
    setFavColor(curColor);
    prevColor = curColor;
}

//load triggers/event listener is at the buttom of the file

/* 9. Show/Hide Event
 * NOTE: Write unobtrusive Javascript
 * When user hovers over an employees name:
 *      Hide the name if shown.
 *      Show the name if hidden. ???? can't show if hidden because can't hover over it 
 */
function toggleName(em){
    if (em.style.display === "none") {
        em.style.display = "block";
    } 
    else {
        em.style.display = "none";
    }
}


/* 10. Current Time
 * Regarding this element:
 * <h5 id="currentTime"></h5>
 * Show the current time in this element in this format: 9:05:23 AM
 * The time should be accurate to the second without having to reload the page.
 */
function currentTime(){
    var now = new Date();
    var hour = now.getHours(); 
    var min = now.getMinutes();
    var sec = now.getSeconds();
    var day_night = hour > 12 ? "PM" : "AM";
    document.getElementById('currentTime').innerHTML = hour + ":" + min + ":" + sec;
    t = setTimeout(()=> {
        currentTime();
    },1000);
}
currentTime();

/* 11. Delay
 * Regarding this element:
 * <p id="helloWorld">Hello, World!</p>
 * Three seconds after a user clicks on this element, change the text to a random color.
 */

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function delay(){
    setTimeout(() => {
        document.getElementById('helloWorld').style.color = getRandomColor();
    }, 3000);
}


/* 12. Walk the DOM
 * Define function walkTheDOM(node, func)
 * This function should traverse every node in the DOM.
 * Use recursion.
 * On each node, call func(node).
*/
function walkTheDOM(){
    let root = document.getElementsByTagName('html');
    DOMRecurse(root[0]);
}

function DOMRecurse(node){
    if(node.childElementCount == 0){
        return;
    }
    else{
        Array.prototype.forEach.call(node.children, child=>{
            DOMRecurse(child);
        });
    }
}

//Complied window.onloads so that every trigger works
window.onload = function(){
    console.log('loading event listeners');
    //Question 6 Sum Event
    console.log('Sum Event Listener');
    document.getElementById('num1').addEventListener('change', sumEvent,false);
    document.getElementById('num2').addEventListener('change', sumEvent,false);
    //Question 7
    console.log('Skills Event Listener');
    document.getElementsByName('skills')[0].addEventListener('change', selectSkill);
    //Question 8
    console.log('Colors Event Listener');
    colors = document.getElementsByName('favoriteColor');
    firstColor = true;
    Array.prototype.forEach.call(colors, color=>{
        color.addEventListener('change', function(){favoriteColor(color)},false);
    });
    //Question 9
    console.log('Toggle Employees Listener');
    emps = document.getElementsByClassName('empName');
    Array.prototype.forEach.call(emps, em=> {
        em.addEventListener('mouseover', function(){toggleName(em)},false);
    });
    //Question 11
    console.log('Hello World click listener')
    document.getElementById('helloWorld').addEventListener('click',delay,false);
}
