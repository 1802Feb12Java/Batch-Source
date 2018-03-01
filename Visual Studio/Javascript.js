//alert("Well dern");
var a=10;
var b,c,d,e,f,g,h,i;

//Loosely typed

b="10";
c=true;
d={};       //object
e=null;
//f= ;
g=(0/0);
h=[];       //array
i=function(){};

function incrementA(){
    a+=1;
    // return //<--semicolon injection
    // a;
    return a;
}

// == performs type coersion 
// === does not perform type coersion, checks if type and value are equal
console.log("5===5");
console.log(5===5);
console.log(5=="5");        //compares them by making one of them switch to the other's type
console.log(5==="5");
console.log("Tests");
console.log("false == 1");
console.log(false == 1);            //false
console.log("false == 0");          
console.log(false == 0);            //true
//console.log(false === 0);                 //false
//console.log(true == 1);           //true
//console.loge(true === 1);                 //false
console.log("Tricky");
console.log(true == 1100000);       //false
console.log(true == -1000000);              //false
console.log(5!="5");                //false
console.log(5!=="5")                        //true
console.log(false == 11001000);     //false

console.log("More fun!");
var divideByZero = function(at, least, three){
    console.log("Hey kitty, kitty");
    console.log(at);
    console.log(least);
    console.log(three);
    console.log(arguments[3]);          //prints out some of the extra variables if they are passed in
    console.log(arguments[4]);
    console.log(arguments[5]);
    console.log(arguments[6]);
    console.log(arguments[7]);

    if(typeof(at)=='string'){console.log("Your first param is a string");}
    else{console.log("It is not a string")}
    if(three){console.log("three param function");}
    return (at + three + least)/0;
}

console.log(7+'7'+7);

var add = (function () {
    var counter = 0;                        //makes the variable private so it can't be manipulated outside the method
    return function () {return counter += 1;}         //returns an anonymous closure function
 })();  //these extra () at the end make it self invoking, so it only calls counter = 0 once
 
 console.log(add());
 console.log(add());
 console.log(add());      //returns 3

