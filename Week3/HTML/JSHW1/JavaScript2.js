window.onload = function () {
    document.getElementById('num1').addEventListener('change', sumNumbers, false);
    document.getElementById('num2').addEventListener('change', sumNumbers, false);
    document.getElementsByName('skills')[0].addEventListener('change', alertSkill, false);
    document.getElementsByName('favoriteColor')[0].addEventListener('change', changeColor, false);
    document.getElementsByName('favoriteColor')[1].addEventListener('change', changeColor, false);
    document.getElementsByName('favoriteColor')[2].addEventListener('change', changeColor, false);
    document.getElementsByName('favoriteColor')[3].addEventListener('change', changeColor, false);
    document.getElementById('helloWorld').addEventListener('click', delayedChange, false);
    document.getElementsByClassName('empName')[0].addEventListener('mouseover', hideUnhide, false);
    document.getElementsByClassName('empName')[1].addEventListener('mouseover', hideUnhide, false);
    document.getElementsByClassName('empName')[2].addEventListener('mouseover', hideUnhide, false);
    document.getElementsByClassName('empName')[3].addEventListener('mouseover', hideUnhide, false);
    document.getElementsByClassName('empName')[4].addEventListener('mouseover', hideUnhide, false);
    document.getElementsByClassName('empName')[5].addEventListener('mouseover', hideUnhide, false);
    setInterval(displayTime, 100);
};

//1. print contents of elements containing USA
function getUSA() {
    var allElements = document.getElementsByTagName('*');
    for (let i = 0; i < allElements.length; i++) {
        var attributes = allElements[i].attributes;
        for (let j = 0; j < attributes.length; j++) {
            if (attributes[j].textContent.includes('USA')) {
                return allElements[i].textContent;
            }
        }
    }
}

//2. print names of everyone in sales department
function getPeopleInSales() {
    var employeeTable = document.getElementsByTagName('table')[0];
    var e_tr = employeeTable.getElementsByTagName('tr');
    for (let i = 0; i < e_tr.length; i++) {
        var e_tr_td = e_tr[i].getElementsByTagName('td');
        for (let j = 0; j < e_tr_td.length; j++) {
            if (e_tr_td[j].textContent.includes('Sales')) {
                console.log(e_tr[i].getElementsByClassName('empName')[0].textContent);
            }
        }
    }
}

//3. print contents of spans within anchor tag
function getAnchorChildren() {
    var allAnchors = document.getElementsByTagName('a');
    for (let i = 0; i < allAnchors.length; i++) {
        if (allAnchors[i].hasChildNodes) {
            var spansInAnchors = allAnchors[i].getElementsByTagName('span');
            for (let j = 0; j < spansInAnchors.length; j++) {
                console.log(spansInAnchors[j].innerText);
            }
        }
    }
}

//4. print value and contents of "selected" skills
function getSkills() {
    var selects = document.getElementsByTagName('select');
    for (let i = 0; i < selects.length; i++) {
        if (selects[i].name == 'skills') {
            var skillOptions = selects[i].getElementsByTagName('option');
            for (let j = 0; j < skillOptions.length; j++) {
                if (skillOptions[j].getAttribute('selected') == 'selected') {
                    console.log(skillOptions[j]);
                }
            }
        }
    }
}

//5. print elements with "data-customAttr" attribute
function getCustomAttribute() {
    var allElements = document.getElementsByTagName('*');
    for (let i = 0; i < allElements.length; i++) {
        var attributes = allElements[i].attributes;
        for (let j = 0; j < attributes.length; j++) {
            if (attributes[j].nodeName == 'data-customattr') {
                console.log(allElements[i]);
            }
        }
    }
}

//6. sum num1 and num2 into sum field
function sumNumbers() {
    var sum = parseInt(document.getElementById('num1').value) + parseInt(document.getElementById('num2').value);
    if (isNaN(sum)) {
        sum = 'Cannot add';
    }
    document.getElementById('sum').textContent = sum;
}

//7. alert when skill is selected
function alertSkill() {
    var skillsLength = document.getElementsByName('skills')[0].length;
    var skill = document.getElementsByName('skills')[0];
    for (let i = 0; i < skillsLength; i++) {
        if (skill[i].selected) {
            alert('Are you sure ' + skill[i].textContent + ' is one of your skills?');
        }
    }
}

//8. change form background to selected color
function changeColor() {
    var form1 = document.getElementById('firstForm');
    var currentColor = form1.style.backgroundColor;
    var colors = document.getElementsByName('favoriteColor');
    for (let i = 0; i < colors.length; i++) {
        if (colors[i].checked) {
            form1.style.backgroundColor = colors[i].value;
            if (currentColor != '') {
                alert('So you like ' + colors[i].value + ' more than ' + currentColor + ' now?');
            }
        }
    }
}

//9. hide if unhidden, unhide if hidden
function hideUnhide() {
    if (this.style.color == 'black') {
        this.style.color = 'white';
    } else {
        this.style.color = 'black'
    }
}

//10. display time
function displayTime() {
    var d = new Date();
    var h = d.getHours();
    var m = d.getMinutes();
    var s = d.getSeconds();
    var ampm = 'AM';
    if (d.getHours() > 12) {
        h = d.getHours() - 12;
        if (h < 10) {
            h = '0' + h;
        }
        ampm = 'PM';
    }
    if (d.getHours() < 10) {
        h = '0' + d.getHours();
    }
    if (d.getMinutes() < 10) {
        m = '0' + d.getMinutes();
    }
    if (d.getSeconds() < 10) {
        s = '0' + d.getSeconds();
    }

    var timeNow = h + ':' + m + ':' + s + ' ' + ampm;
    document.getElementById('currentTime').innerHTML = timeNow;
}

//11. change color after 3 seconds
function delayedChange() {
    setTimeout(changeHelloWorld, 3000);
}

function changeHelloWorld() {
    var changeThis = document.getElementById('helloWorld');
    var redColor = Math.floor(Math.random() * 255);
    var greenColor = Math.floor(Math.random() * 255);
    var blueColor = Math.floor(Math.random() * 255);
    changeThis.style.color = 'rgb(' + redColor + ',' + greenColor + ',' + blueColor + ')';
}

//12. recursively access every element in DOM
function printNode(printThis) {
    console.log(printThis);
}

var walktheDOM = function (node, func) {
    if (node == null) {
        var nodeList = document.querySelectorAll('*');
        node = nodeList[0];
    }
    func(node);
    node = node.firstChild;
    while (node) {
        walktheDOM(node, func);
        node = node.nextSibling;
    }
};