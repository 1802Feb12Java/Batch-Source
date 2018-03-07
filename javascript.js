// //alert("roll tide");
// var a = 10;
// var b,c,d,e,f,g,h,e;

// //Loosely typed
// b="10"
// c=true;
// d={};//empty object
// e=null;
// //f= ;
// g=(0/0);
// h=[];//empty array
// i=function(){};

// function incrementA(){
//     a+=1;
//     return  a
// }

// //== performs a type coersion
// //=== does not perform type coersion
// // console.log("5==5")
// // console.log(5==5);
// // console.log("5===5");
// // console.log(5===5);
// // console.log("5=='5'");
// // console.log(5=="5");
// // console.log("5==='5'");
// // console.log(5==="5");
// console.log("======TESTS======")
// console.log("false==1")
// console.log(false==1);
// console.log("false==0")
// console.log(false==0);
// console.log("false===0")
// console.log(false===0);
// console.log("true==1")
// console.log(true==1);
// console.log("true==0")
// console.log(true==0);
// console.log("true===1")
// console.log(true===1);
// console.log("TRICKY ONES");
// console.log(true==-1000000);
// console.log(true==10000000);
// console.log(5!="5");
// console.log(5!=="5");

// console.log("more fun: \n")
// var divideByZero = function(kitty, cat){
//     console.log("HEY KITTY KITTY")
//     return (kitty+cat)/0;
// }
//======================================another day another js===================================================

//we want to build a reusable counter. we want the count variable to be accessible to other objects.

//var counter = 0;

// function add(){
//     counter+=1;
// }

// add();
// add();
// add();
// //counter is 3
// counter ="poop";

// //part 2
// function add2(){
//     var counter = 0; //counter reset to 0 everytime
//     counter+=1;
// }

//part3
// function add3(){
//     var counter;
//     if(count==Nan||count==undefined){
//         count=0;
//     }
//     function plus(){
//         counter+=1;
//     }
//     plus();
//     return count;
// }

var add=(function(){
    var count=0;
    return function(){ //IIFE
        count+=1;
        return count;
    }
}());