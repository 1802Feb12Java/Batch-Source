// alert("Roll Tide");

var a=10;
var b,c,d,e,g,h;

b="10";
c=true;
d={}; //object
e=null;
f= e;
g=(0/0);
h=[]; //arrays

i = function(){};

function increment(){
    a+=11;
    return a;
}

//double and triple equlas
console.log("5==5: " + (5==5));
console.log("5==5: " + (5==5));
console.log("5===5: " + (5===5));
console.log("\"5\"===\"5\": " + ("5"==="5"));
console.log("5===\"5\":" + (5==="5"));
console.log("true==1 " + (true == 1));

divideByZero = function(at,least,three){
    console.log(at);
    console.log(least);
    console.log(three);
    console.log(arguments[3]);
    console.log(arguments[4]);
    console.log(arguments[5]);
    console.log(arguments[6]);
    console.log(arguments[7]);
    console.log(arguments[8]);
    console.log(arguments[9]);
    console.log(arguments[10]);
    if(typeof(at) == 'string'){console.log("your first arg");}
    else{console.log("not str");}
    return (at + " "  + least + " " + three)/0;
};

/*
var x;
var a = false;
var b = true;
var c = true;
var d = false;
x = a || (b ? ((c && d) ? 1 : 2 ): 3); //true?
console.log(x);
*/
console.log(false||"hello");

var d = {};
console.log(d.hello + " " + d.nothing);
d.hello = "World";
d.nothing = 0;
console.log(d.hello + " " + d.nothing);
