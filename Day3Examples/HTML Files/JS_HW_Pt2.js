//JS homework part 2

function getUSA(){
    var tags = document.getElementsByTagName("*");
    var text = "USA";
    var found;
    for(var i =0; i<tags.length; i++){
        if(tags[i].textContent == text){
            found = tags[i];
            break;
        }
    }
    return found;
}


function getPeopleInSales(){
    var tablerows = document.getElementsByTagName("tr");
    var text = "Sales";
    var salesEmp=[];

    for(var i=0; i<tablerows.length; i++){
        if(tablerows[i].children[1].textContent == text){
            salesEmp.push(tablerows[i].children[0].textContent);
        }
    }
    return salesEmp;
}


function getAnchorChildren(){
    var anchors = document.getElementsByTagName("a");
    var anchorsWithSpan = [];
    for(var i=0; i<anchors.length; i++){
        var anchorChildren = allAnchors[i].children;
        for(var j=0; j<anchorChildren.length; j++){
            if(anchorChildren[j].nodeName == "SPAN"){   
                anchorsWithSpan.push(anchors[i]);
            }
        }
    }
    for(var i=0; i<anchorsWithSpan.length; i++){
        var singleSpan = anchorsWithSpan[i].children;
        for(var j=0; j<singleSpan.length; j++){
            if(singleSpan[j].nodeName == "SPAN")
                console.log(singleSpan[j].textContent);
        }
    }
}

function getSkills(){
    var selects = document.getElementsByTagName("select");
    var skills;
    for(var i =0; i<selects.length;i++){
        if(selects[i].name == "skills"){
            skills=selects[i];
        }
    }
    for(var j = 0;j<skills.length;j++){
        if(skills[j].selected == true){
            return skills[j].value;
        }
    }
}

function getCustomAttribute(){
    var allTags = document.getElementsByTagName("*");
    var dcAttribute;
    for(var i =0; i<allTags.length;i++){
        if(allTags[i].hasAttribute("data-customAttr")){
            console.log(allTags[i]);
        }
    }
}

function sumEvent(){
    var reqs =[];
    reqs.push(document.getElementById("num1"));
    reqs.push(document.getElementById("num2"));
    reqs.push(document.getElementById("sum"));
    for(var i =0; i<reqs.length;i++){
        reqs[i].onchange = function(){
            var n1 = parseInt(reqs[0].value);
            var n2 = parseInt(reqs[1].value);
            var add = n1+n2;
            if(!isNaN(add)){
                reqs[2].textContent = add;
            }else{
                reqs[2].textContent = "Cannot add";
            }
        }
    }
}window.onload = function(){
    document.getElementById("num1").addEventListener("change", sumEvent, false);  
    document.getElementById("num2").addEventListener("change", sumEvent, false);      
}


//Skill Event
var selects = document.getElementsByTagName("select");
var skills2 = []; //had to name it something else cuz of previous code
for(var i =0; i<selects.length;i++){
    if(selects[i].name == "skills"){
        skills2=selects[i];
    }
}
for(var i = 0;i<skills2.length;i++){
    skills2.onchange = function(){
        for(var i =0; i<skills2.length;i++){
            if(skills2[i].selected == true){
                var sel = skills2[i].value;
                alert("Are you sure " + sel + " is one of your skills?")
            }
        }
    }
}


//Favorite Color Event
//=======taking the L on this one========
 



//Show/Hide Event
var employees = document.getElementsByClassName("empName"); //doesnt fully work
for(var i =0;i<employees.lenthe;i++){
    employees[i].onmouseover = function(){
        var emp = employees[i];
        emp.onmouseenter = function(){
            this.style.display = "none";
        }
        emp.onmouseleave = function(){
            this.style.display = "block";
        }
    }
}


//Current Time
function check(i) {
    if (i < 10) {
      i = "0" + i;
    }
    return i;
}
function getTime() {
    var today = new Date();
    var amORpm = "AM";
    var h = today.getHours();
    if(h>12){
        h=h-12;
        amORpm = "PM";
    }
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = check(m);
    s = check(s);
    console.log(h + ":" + m + ":" + s + " " + amORpm);
}

 


//Delay
var hi = document.getElementById("helloWorld");
hi.onclick = function(){
    setTimeout(function(){
        hi.style.color = "#"+((1<<24)*Math.random()|0).toString(16); //random color generator
    },3000);
}



function walkTheDOM(node, func){
    for(var i=0; i<node.length; i++){
        walkTheDOM(node[i], func);
    } if(node.length == undefined){   
        func(node);
    }
}
function printStuff(stuff){
    console.log(stuff.textContent);
}
var tagz = document.getElementsByTagName("*");
walkTheDOM(tagz, printStuff);




