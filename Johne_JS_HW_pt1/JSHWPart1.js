/*

Johne Vang

JS HW, part 1:
Fill in the functions and submit them to your branch in a file called JSHWPart1.js
gp
var homework = {};
*/

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55


*/
var homework = {};

homework.fibonacci = function(n){
    var count = 0;
    var result = 0;
    
    if(n === 0) {
        return result;
    }

    while (count <= n) {
        if (n === 1) {
            result = 1;
        } else {
            result += count;
        }
        count++;
    }
    return result;
};
console.log('fibonacci function: ' + homework.fibonacci(10));

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.

 loop through array, compare the first element with the element to the right of it, 
 if the current element is bigger than the element to the right of it, then
 swap their positions. 
*/
homework.sort = function(array) {
    var temp;
    for(var i = 0; i < array.length - 1; i++) {
        for(var j = 0; j < array.length - i - 1; j++) {
            if(array[j] > array[j + 1]) {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
  return array;
};
console.log('sort array: \t\t' + homework.sort([2,4,5,1,3,1]));

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6

 if n is 0 or 1, the result will be 1
 otherwise the factorial function will multiply n with the
 recursive call  
*/
homework.factorial = function(n){
    var result = 1;

    if(n === 0 || n === 1) {
        return result;
    } else {
        result = n * homework.factorial(n - 1);
    }
    return result;
};
console.log('factorial: \t\t\t' + homework.factorial(5));

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

 loop through array n times, shift the first element, and push that element to the array
*/
homework.rotateLeft = function(array, n) {
    var firstElement;
    for(var i = 0; i < n; i++) {
        firstElement = array.shift(array[0]);
        array.push(firstElement);
    }
    return array;
};
console.log('rotate left: \t\t' + homework.rotateLeft([1,2,3,4], 2));

/*
 5. Balanced Brackets

 A bracket is any one of the following: (, ), {, }, [, or ]

 The following are balanced brackets:
    ()
    ()()
    (())
    ({[]})

 The following are NOT balanced brackets:
 (
 )
 (()
 ([)]

 Return true if balanced
 Return false if not balanced

 scan the string from left to right,
 everytime a left parenthesis occur, push it to the tempArr.
 Everytime a right parenthesis occur, a matching left parenthesis is popped 
 off of the tempArr array. If the left matching parenthesis is not on
 top of the tempArr, the string is unbalanced. 

 Once there is no more symbols in the tempArr, the result will evaluate to true. 

*/
homework.balancedBrackets = function(bracketsString){

    var left_normal = '(';
    var right_normal = ')';
    var left_curly = '{';
    var right_curly = '}';
    var left_square = '[';
    var right_square = ']';

    var tempArr = [];
    var failed = false;
    for (var i = 0; (i < bracketsString.length); i++) {
       switch(bracketsString.charAt(i)) {
           case left_curly : 
           case left_normal :
           case left_square :
                tempArr.push(bracketsString.charAt(i));
                break;
            case right_curly :
                if(tempArr.length === 0 || (tempArr.pop() !== left_curly)) {
                    failed = true;
                }
                break;
            case right_normal:
                if(tempArr.length === 0 || (tempArr.pop() !== left_normal)) {
                    failed = true;
                }
                break;
            case right_square :
                if(tempArr.length === 0 || (tempArr.pop() != left_square)) {
                    failed = true;
            }
                break;
        }
    }
    
    return (tempArr.length === 0 && !failed);
};
console.log('balanced brackets: \t' + homework.balancedBrackets('(()'));