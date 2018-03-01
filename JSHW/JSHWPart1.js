// JS HW, part 1:

// Fill in the functions and submit them to your branch in a file called JSHWPart1.js
// gp
var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
    var theResult = 0;
    if (n === 0) {
        return 0;
    }
    else if (n === 1 || n == 2) {
        return 1;
    }
    else if (isNaN(n)) {
       return "This is not a valid number"
    }
    else {
        var num1 = 1;
        var num2 = 1;
        var i = 2;
        var theResult = 0;
        while (i < n) {
            theResult = num1 + num2;
            num1 = num2;
            num2 = theResult;
            i++;
        }
        return theResult;
    }
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame. <<-- you misspelled smart
*/
homework.sort = function(array) {
    var switchMade = false;
    do {
        switchMade = false;
        for (var i = 0; i < array.length; i++) {
            if (array[i] > array[i+1]) {
                var temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                switchMade = true;
            }
        }
    } while (switchMade);
    return array;
};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){

    var theResult = 1;
    for (var i = 1; i < n+1; i++) {
        theResult = theResult * i;
    }

    return theResult;
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
    for (var i = 0; i < n; i++) {
        var temp = array[0];
        for (var j = 0; j < array.length -1; j++) {
            array[j] = array[j + 1];
        }
        array[j] = temp;
    }
};

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
*/
homework.balancedBrackets = function(bracketsString){
    var isBalanced = true;
 

    if (bracketsString.length % 2 != 0) {
        isBalanced = false;
    }
    else {
        var inputArray = new Array();
        var openersArray = ['(', '[', '{'];

        for (var i = 0; i < bracketsString.length; i++) {
            if (openersArray.includes(bracketsString[i])) {
                inputArray.push(bracketsString[i]);
            }
            else{
                var poppedElement = inputArray.pop();
                switch (bracketsString[i]) {
                    case ')':
                        if(poppedElement != "(") {
                            isBalanced = false;
                        }
                        break;

                    case ']':
                    if(poppedElement != "[") {
                        isBalanced = false;
                    }
                        break;

                    case '}':
                    if(poppedElement != "{") {
                        isBalanced = false;
                    }
                        break;

                    default:
                        return "Error only: (), {}, and [] are supported."
                        break;
                }
            }
        }
    }

    return isBalanced;
};




