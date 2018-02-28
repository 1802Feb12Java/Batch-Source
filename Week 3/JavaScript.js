//alert("Roll Tide");
var a=10;
var b,c,d,e,f,g,h,e;

//Loosely typed

b="10";
c=true;
d={};
e= null;
//f= ;
g=(0/0);
h=[];
i= function(){};

function incrementA(){
    a+=1;
    return a; //<--semicolon injection
   
}

//== performs type coersion
//=== does not perform type coersion
// console.log("5==5");
// console.log(5==5);
// console.log("5===5");
// console.log(5===5);
// console.log(5=="5");
// console.log(5==="5");
console.log("Tests");
console.log("false == 1");
console.log(false == 1);
console.log(false==0);
console.log(false===0);
console.log(true==1);
console.log(true===1);
console.log(true==0);
console.log("Tricky");
console.log(false==-1000000);
console.log(true==1000000);
console.log(5!="5");
console.log(5!=="5");

console.log("more fun:");
var divideByZero = function(kitty, cat){
	
	console.log("Hey kitty, kitty");
	
	return (kitty + cat)/0;
	
}
divideByZero = function(at, least, three){
	
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
	
	if(typeof(at)=='string'){console.log("Your first param is a string")}
	else{console.log("It is not a string")}
	if(three){console.log("three param function");}
	else{console.log("Hey, kitty kitty")}
	
	return (at + least + three)/0;
	
}
