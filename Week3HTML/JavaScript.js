// // alert("Roll Tide");
// var a=10;
// var b,c,d,e,f,g,h,e;

// // Losely typed

// b="10";
// c=true;
// d={};   //empty object
// e=null;
// f= 0;
// g=(0/0);
// h=[];
// i=function(){};

// function incrementA(){
//     a+=1;
//     return a;  //semicolon injection
    
// }

// //== performs type coercion
// //=== doesn not perform type coercion
// console.log("5==5");
// console.log(5==5);
// console.log("5===5");
// console.log(5===5);
// console.log("5=='5'");
// console.log(5=="5");
// console.log("5==='5'");
// console.log(5==="5");
// console.log("\nTests")
// console.log("false == 1");
// console.log(false == 1);
// console.log("false == 0");
// console.log(false == 0);
// console.log("false === 0");
// console.log(false === 0);
// console.log("true == 1");
// console.log(true == 1);
// console.log("true === 1");
// console.log(true === 1);
// console.log("true == 0");
// console.log(true == 0);
// console.log("true === 0");
// console.log(true === 0);
// console.log("\nTricky");
// console.log("true == -1000000");
// console.log(true == -1000000);
// console.log("true == 1000000");
// console.log(true == 1000000);
// console.log("5 != '5''");
// console.log(5 != "5");
// console.log("5 !== '5'");
// console.log(5 !== "5");
// console.log("7+7+7");
// console.log(7+7+7);
// console.log("7+7+'7'");
// console.log(7+7+'7');
// console.log("'7'+7+7");
// console.log('7'+7+7);
// console.log("7+'7'+7");
// console.log(7+'7'+7);
// console.log("10+ +'10'");
// console.log(10+ +'10');
// console.log("10+ + + '10' + +'10'");
// console.log(10+ + + '10' + +'10');


// var divideByZero = function(kitty, cat){        //returns Infinity if given two numbers
//     console.log("Hey kitty, kitty");            //else returns NaN

//     return (kitty + cat) / 0;
// }

// divideByZero = function(at, least, three){
//     console.log(at);
//     console.log(least);
//     console.log(three);
//     console.log(arguments[3]);
//     console.log(arguments[4]);
//     console.log(arguments[5]);
//     console.log(arguments[6]);
//     console.log(arguments[7]);
//     console.log(arguments[8]);
//     console.log(arguments[9]);
//     console.log(arguments[10]);
    
//     if(typeof(at) == "string"){console.log("Your first parameter is a string")}
//     else{console.log("It is not a string")}
//     if(three){console.log("three param function")}
//     else{console.log("Hey, kitty kitty")}

//     return (at + least + three) / 0;
// }

    //We want to build a reusable counter. We want the count variable to be accessible to other objects.

// var counter = 0;

// function add() {
//     counter += 1;
// }
    
// add();
// add();
// add();

// the counter is now 3

//part 2
// function add() {
//     var counter = 0;
//     counter += 1;
// }

// add();
// add();
// add();
//counter will be reset every time

//try number 3:
// function add() {
//     if(count == undefined){
//         var count = 0; 
//     }
//     function plus(){
//         count += 1;
//     }
//     plus();
//     return count;
// }    //no workey

var add = function () {
    var count = 0;
    return function(){
        count+=1;
        return count;
    }
}();