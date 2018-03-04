
//NOTE: All function calls are immediately after the function
//     The results of each function are appended directly to the HTML page in red

/*
1. USA
Define function getUSA()

Find the html element that contains "USA".

Print that element's contents.
*/
function getUSA() {
	var spanElements = document.getElementsByTagName("span");
	for (var i = 0; i < spanElements.length; i++) {
		if(spanElements[i].textContent == "USA") {
			var newSpanElement = document.createElement("span");
			var newBrElement = document.createElement("br");
			spanElements[i].appendChild(newBrElement);
			newSpanElement.textContent = spanElements[i].textContent + " <-- grabbed from USA Span";
			newSpanElement.classList.add("questionAnswer");
			spanElements[i].appendChild(newSpanElement);
		}
	}
}
getUSA();

/*
2. Sales

Define function getPeopleInSales()

Print the names of all the people in the sales department.
*/
function getPeopleInSales() {
	var empNameElements = document.getElementsByClassName("empName");
	var salesPeople = [];
	//get names of sales people
	for (var i = 0; i < empNameElements.length; i++) {
		if (empNameElements[i].nextElementSibling.textContent == "Sales") {
			salesPeople.push(empNameElements[i].textContent);
		}
	}
	//print names of sales people
	var outputParagraph = document.getElementById("salesPeopleOutput"); // label for the new list we're creating
	var listOfNames = document.createElement("ul");  //append sales people to this new element
	outputParagraph.appendChild(listOfNames);
	for (var i = 0; i < salesPeople.length; i++) {
		var listElement = document.createElement("li");
		listElement.textContent = salesPeople[i];
		listElement.classList.add("questionAnswer");
		listOfNames.appendChild(listElement);
	}
}

getPeopleInSales();


/*
3. Click Here

Define function getAnchorChildren()

Find all anchor elements with a <span> child.

Print the contents of <span>
*/

function getAnchorChildren() {
	var anchorSpanOutputArea = document.getElementById("anchorSpanOutput");
	var listNode = document.createElement("ul");
	anchorSpanOutputArea.appendChild(listNode);
	var anchors = document.getElementsByTagName("a");
	var spanElementText = [];

	//loop through list of <a> tags
	for (var i = 0; i < anchors.length; i++) {
		var anchorChildren = anchors[i].childNodes;
		//loop through children of <a> tags
		for (var j = 0; j < anchorChildren.length; j++) {
			if (anchorChildren[j].nodeName == "SPAN") {
				spanElementText.push(anchorChildren[j].textContent);
			}
		}
	}

	//append <a> tags to document
	for (var i = 0; i < spanElementText.length; i++) {
		var listElement = document.createElement("li");
		listElement.textContent = spanElementText[i];
		listNode.appendChild(listElement);
		listElement.classList.add("questionAnswer");
	}

}
getAnchorChildren();

/*
4. Hobbies

Define function getSkills()

Find all checked options in the 'skills' select element.

Print the value and the contents.
*/
function getSkills() {
	var skillOutputArea = document.getElementById("skillsOutputArea");
	var skillList = document.createElement("ul");
	skillOutputArea.appendChild(skillList);

	var skillSelectBox = document.getElementsByName("skills");
	var skills = skillSelectBox[0].children;
	for (var i = 0; i < skills.length; i++) {
		var skillListItem = document.createElement("li");
		skillListItem.textContent = skills[i].textContent;
		skillListItem.classList.add("questionAnswer");
		skillList.appendChild(skillListItem);
	}
}
getSkills();


/*
5. Custom Attribute

Define function getCustomAttribute()

Find all elements with "data-customAttr" attribute

Print the value of the attribute.

Print the element that has the attribute.
*/
function getCustomAttribute() {
	var listOfDataCustomAttr = document.querySelectorAll("[data-customAttr]");
	var outputArea = document.getElementById("customDataOutputArea");
	var listOfCustomAttr = document.createElement("ul");
	outputArea.appendChild(listOfCustomAttr);
	for (var i = 0; i < listOfDataCustomAttr.length; i++) {
		var listElement = document.createElement("li");
		listElement.textContent = listOfDataCustomAttr[i].nodeName + " with a value of: " + listOfDataCustomAttr[i].textContent;
		listElement.classList.add("questionAnswer");
		listOfCustomAttr.appendChild(listElement);
	}
}
getCustomAttribute();


/*
6. Sum Event

NOTE: Write unobtrusive Javascript

Regarding these elements:
	
<input id="num1" class="nums" type="text"/>
	
<input id="num2" class="nums" type="text"/>
	
<h3>Sum: span id="sum"></span></h3>


Define onchange event handler.

Add <input> element values.

Put the sum in the <span> element.

If values cannot be added, put "Cannot add" in the <span> element
*/

document.getElementById("num1").addEventListener("change", sumEvent);
document.getElementById("num2").addEventListener("change", sumEvent);

function sumEvent() {
	var sumSpan = document.getElementById("sum");
	var numList = document.getElementsByClassName("nums");
	var num1 = Number(numList[0].value);
	var num2 = Number(numList[1].value);
	if (isNaN(num1) || isNaN(num2)) {
		sumSpan.textContent = "Cannot Add"
	}
	else {
		var theSum = num1 + num2;
		sumSpan.textContent = theSum.toString();
	}
}


/*
7. Skills Event

NOTE: Write unobtrusive Javascript

When user selects a skill, create an alert with a message similar to:
	
"Are you sure CSS is one of your skills?"

NOTE: no alert should appear when user deselects a skill.
*/
	var skillsSelect = document.getElementsByName("skills");
	skillsSelect[0].addEventListener("change", skillSelected);

	function skillSelected() {
		alert("Are you sure " + skillsSelect[0].value + " is one of your skills?");
	}


/*
8. Favorite Color Event

NOTE: Write unobtrusive Javascript

NOTE: This is regarding the favoriteColor radio buttons.

When a user selects a color, create an alert with a message similar to:
	
"So you like green more than blue now?"

In this example, green is the new value and blue is the old value.

Make the background color (of all favoriteColor radio buttons) 
the newly selected favoriteColor
*/

oldSelectedColor = "NONE";
function addListenersToFavoriteColor() {
	var colorRadioButtons = document.getElementsByName("favoriteColor");
	for (var i = 0; i < colorRadioButtons.length; i++) {
		colorRadioButtons[i].addEventListener("change", favoriteColorSelected);
	}
}
addListenersToFavoriteColor();

function favoriteColorSelected() {
	var colorRadioButtons = document.getElementsByName("favoriteColor");
	var favoriteColor;
	for (var i = 0; i < colorRadioButtons.length; i++) {
		if(colorRadioButtons[i].checked) {
			favoriteColor = colorRadioButtons[i].value;
		}
	}
	if (oldSelectedColor === "NONE") {
		alert ("Your favorite color is: " + favoriteColor);
		oldSelectedColor = favoriteColor;
	}
	else {
		alert("So you like " + favoriteColor + " more than " + oldSelectedColor + " now, huh?");
		oldSelectedColor = favoriteColor;
	}
	
}

/*
9. Show/Hide Event

NOTE: Write unobtrusive Javascript

When user hovers over an employees name:
	
Hide the name if shown.
	Show the name if hidden.
*/

	var employeeNames = document.getElementsByClassName("empName");
	for (var i = 0; i < employeeNames.length; i++) {
		employeeNames[i].addEventListener("mouseover", hideEmpName);
	}

	//adds or removes mouseOver class to affect visibility via CSS
	function hideEmpName() {
		if(this.classList.contains("mouseOver")){
			this.classList.remove("mouseOver");
		}
		else {
			this.classList.add("mouseOver");
		}	
	}
/*
10. Current Time

Regarding this element:
	<h5 id="currentTime"></h5>

Show the current time in this element in this format: 9:05:23 AM

The time should be accurate to the second without having to reload the page.
*/
	function showTime() {
		var timeHeader = document.getElementById("currentTime");
		var date = new Date();
		var timeString = date.toLocaleTimeString();
		timeHeader.textContent = timeString
	}
	setInterval(showTime, 1000);
/*
11. Delay
Regarding this element:
	
<p id="helloWorld">Hello, World!</p>

Three seconds after a user clicks on this element, change the text to a random color.
*/
	var helloP = document.getElementById("helloWorld").addEventListener("click", setHelloWorldColor);
	

	function setHelloWorldColor() {
		setTimeout(function () {
			var helloP = document.getElementById("helloWorld");
			var newColor = "rgb(" + Math.floor(Math.random() * 255) + "," +
									Math.floor(Math.random() * 255) + "," +
									Math.floor(Math.random() * 255) + ")";
			helloP.style.color = newColor;
		}, 3000);

	}

/*
12. Walk the DOM

Define function walkTheDOM(node, func)

This function should traverse every node in the DOM. 
Use recursion.

On each node, call func(node).
*/

function walkTheDOM(node, func) {
	if (node) {
		console.log("Current node is: " + node.tagName);
		//check for child nodes
		if (node.children.length > 0) {
			for (var i = 0; i < node.children.length; i++) {
				walkTheDOM( node.children[i], walkTheDOM);
			}
		}

		//check for sibling nodes
		if (typeof node.nextElementSibling != "Undefined") {
			walkTheDOM(node.nextElementSibling, walkTheDOM);
		}
	}
}

var nodeList = document.getElementsByTagName("*");
walkTheDOM(nodeList[0], walkTheDOM);



