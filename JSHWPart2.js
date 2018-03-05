/* 
   1. USA 
   Define function getUSA()  
   Find the html element that contains "USA".  
   Print that element's contents.
*/
console.log('1. USA');
function getUSA() {
   var tags = document.getElementsByTagName('*');
   var searchText = 'USA';
   for(var i=0; i<tags.length; i++) {
      if(tags[i].textContent == searchText) {
         console.log(tags[i]);
         break;
      }
   }
}
getUSA();
console.log('');

/* 
   2. Sales  
   Define function getPeopleInSales()  
   Print the names of all the people in the sales department.
*/
console.log('2. Sales');
function getPeopleInSales() {
 var tags = document.getElementsByTagName('td');
 for(var i=0; i<tags.length; i++) {
    if(tags[i].classList.contains('empName')) {
      if(tags[i+1].textContent == 'Sales') {
        console.log(tags[i].textContent);
      }
    }
  }
}
getPeopleInSales();
console.log('');

/* 
   3. Click Here  
   Define function getAnchorChildren()  
   Find all anchor elements with a <span> child.  
   Print the contents of <span>
*/
console.log('3. Click Here');
function getAnchorChildren() {
   var parent, i;
   var tags = document.getElementsByTagName('span');
   for(i=0; i<tags.length; i++) {
      parent = tags[i].parentNode;
      if(parent.tagName == 'A') {
         console.log(tags[i].textContent);
      }
   }
}
getAnchorChildren();
console.log('');

/*
   4. Skills
   Define function getSkills()
   Find all checked options in the 'skills' select element.  
   Print the value and the contents.
*/
console.log('4. Skills');
function getSkills() {
   var e = document.getElementsByName('skills')[0];
   for(var i=0; i<e.length; i++) {
      if(e[i].hasAttribute('selected')) {
         console.log(e[i].text);
      }
   }
}
getSkills();
console.log('');

/*
   5. Custom Attribute  
   Define function getCustomAttribute()  
   Find all elements with "data-customAttr" attribute  
   Print the value of the attribute.  
   Print the element that has the attribute.
*/
console.log('5. Custom Attribute  ')
function getCustomAttribute() {
   var e = document.getElementsByTagName('*');
   for(var i=0; i<e.length; i++) {
      if(e[i].hasAttribute('data-customAttr')) {
         console.log(e[i].getAttribute('data-customAttr'));
         console.log(e[i]);
      }
   }
}
getCustomAttribute();
console.log('');

/*
   6. Sum Event  
   NOTE: Write unobtrusive Javascript   regarding these elements:    
   <input id="num1" class="nums" type="text"/>     
   <input id="num2" class="nums" type="text"/>     
   <h3>Sum: <span id="sum"></span></h3>   
   Define onchange event handler.  
   Add <input> element values.  
   Put the sum in the <span> element.  
   If values cannot be added, put "Cannot add" in the <span> element
*/
var num1 = document.getElementById('num1');
var num2 = document.getElementById('num2');
var span = document.getElementById('sum');
var value;
num1.onkeyup = function() {
   if(num2.value != '') {
      value = parseInt(num1.value) + parseInt(num2.value);
      if(isNaN(value)) {
         span.innerHTML = 'Cannot add';
      } else {
         span.innerHTML = value;
      }
   }
   if(num1.value == '') {
      span.innerHTML = '';
   }   
}
num2.onkeyup = function() {
   if(num1.value != '') {
      value = parseInt(num1.value) + parseInt(num2.value);
      if(isNaN(value)) {
         span.innerHTML = 'Cannot add';
      } else {
         span.innerHTML  = value;
      }
   }
   if(num2.value == '') {
      span.innerHTML = '';
   }
}

/*
    7. Skills Event  
   NOTE: Write unobtrusive Javascript  
   When user selects a skill, create an alert with a message similar to:  
   "Are you sure CSS is one of your skills?"  
   NOTE: no alert should appear when user deselects a skill.
*/
var option;
var skills = document.getElementsByName('skills')[0];
skills.onchange = function() {
   option = skills.options[skills.selectedIndex].text;
   alert('Are you sure ' + option + ' is one of your skills?');
};

/*
   8. Favorite Color Event  
   NOTE: Write unobtrusive Javascript
     NOTE: This is regarding the favoriteColor radio buttons.  
   When a user selects a color, create an alert with a message similar to:   "So you like green more than blue now?"  
   In this example, green is the new value and blue is the old value.
     Make the background color (of all favoriteColor radio buttons)  the newly selected favoriteColor
*/
var oldColor, newColor;
var colors = document.getElementsByName('colors')[0];
colors.addEventListener('click', function() {
   oldColor = colors.options[colors.selectedIndex].text;
   colors.onchange = function() {
      newColor = colors.options[colors.selectedIndex].text;
      alert('So you like ' + newColor + ' more than ' + oldColor + ' now?');
   }
});

/*
   9. Show/Hide Event  
   NOTE: Write unobtrusive Javascript  
   When user hovers over an employees name:  Hide the name if shown.  Show the name if hidden.
*/
var ename;
var names = document.getElementsByClassName('empName');
for(var i=0; i<names.length; i++) {
   ename = names[i];
   ename.onmouseover = function() {
      if(this.style.display == 'none') {
         this.style.display = 'block';
      } else {
         this.style.display = 'none';
      }
   }
}

/*
   10. Current Time  
   Regarding this element:  <h5 id="currentTime"></h5>  
   Show the current time in this element in this format: 9:05:23 AM  
   The time should be accurate to the second without having to reload the page.
*/
var ampm;
function checkTime(i) {
   if (i < 10) {
      i = "0" + i;
   }
   return i;
}
function startTime() {
   var today = new Date();
   var h = today.getHours();
   var m = today.getMinutes();
   var s = today.getSeconds();
   // am pm
   if(h > 12) {
      h = h - 12;
      ampm = 'PM';
   } else {
      ampm = 'AM'
   }
   // add a zero in front of numbers<10
   m = checkTime(m);
   s = checkTime(s);
   document.getElementById('currentTime').innerHTML = h + ':' + m + ':' + s + ' ' + ampm;
   t = setTimeout(function() {
      startTime()
   }, 500);
}
startTime();

/*
   11. Delay Regarding this element:  <p id="helloWorld">Hello, World!</p>  
   Three seconds after a user clicks on this element, change the text to a random color.
*/
function getRandomColor() {
  var letters = '0123456789ABCDEF';
  var color = '#';
  for (var i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}
var hw = document.getElementById('helloWorld');
hw.onclick = function() {
   setTimeout(function() {
      hw.style.color = getRandomColor();
   }, 3000);
};

/*
  12. Walk the DOM  
  Define function walkTheDOM(node, func)  
  This function should traverse every node in the DOM.  
  Use recursion.   On each node, call func(node).
*/
function walkTheDOM(node, func) {
  var children = node.childNodes;
  for(var i=0; i<children.length; i++) {
    walkTheDOM(children[i], func);
  }
  console.log(func(node));
}