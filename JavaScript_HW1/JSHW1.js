let homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
    let fibNum = [];
    fibNum[0] = 0;
    fibNum[1] = 1;

    //for n = 0 and n = 1 return 1
    if(n===0) {
        return 0;
    }

    else if(n===1) {
        return 1;
    }

    else {
        for (let index = 2; index <= n; index++) {
            fibNum[index] = fibNum[index-1] +fibNum[index-2];
        }
        return fibNum[n];
    }
};

console.log("1: The 10th Fibonacci is: " + homework.fibonacci(10))
console.log("");
/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
arr = [2,4,5,1,3,1];
console.log("2. Sort the following array: " + arr);
homework.sort = function(array) {
    let SwapOccurred = false;
    let temp = 0;
    let index = 0;
    do{
        swapOccurred = false;
        for(index = 0; index < array.length - 1; index++){
            if(array[index] > array[index + 1]) {
                temp = array[index];
                array[index] = array[index + 1];
                array[index + 1] = temp;
                swapOccurred = true;
            }
        }
    }while(swapOccurred===true);

    return array;
};

console.log(homework.sort(arr));
console.log("");
/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
    if(n===0 || n===1){
        return 1;
    }

    else {
        return n * homework.factorial(n-1);
    }
};
console.log("3. 5! = " +homework.factorial(5));
console.log("");
/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
let shiftArr = [1,2,3,4,5];
console.log("4. Rotate left")
console.log("Starting array: " + shiftArr);
homework.rotateLeft = function(array, n) {
    let temp;
    //loop counter for number of times to shift
    for(let counter = 1; counter <= n; counter++) {
        //assign the first variable of the array into a temporary variable
        temp = array[0];

        //shift the rest of the array one position to the left
        for(let index = 0; index < array.length - 1; index++) {
            array[index] = array[index + 1];
            
        }
        
        //put the first element from the temp into the last element
        array[array.length-1] = temp;
    }
    return array;
};
console.log("Array left shifted 3 positions: " + homework.rotateLeft(shiftArr, 3));
console.log("");
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
let bs = "({({([])})})";

homework.balancedBrackets = function(bracketsString){
    let stack = new Array();

    if (bracketsString.length % 2 != 0) {
        return false;
    }
    for (let index = 0; index < bracketsString.length; index++){
        //for opening braces, brackets, and parentheses, simply push them on to the stack
        if (bracketsString[index]==='(') {
            stack.push(bracketsString[index]);
        }

        if (bracketsString[index]==='{') {
            stack.push(bracketsString[index]);
        }

        if (bracketsString[index]==='[') {
            stack.push(bracketsString[index]);
        }

        //for closing symbols, check if the opposing pair is on top of the stack
        //JavaScript's stack method uses an array, and only has push and pop functionality
        //so 'peek' must be manually achieved by looking at the last element in the array
        if (bracketsString[index]===')') {
            //if the symbol on the stack is not the exact opposite of the closing symbol
            //the string is not balanced
            if (stack[stack.length - 1] != '(') {
                return false;
            }
            //if this pair of symbols is properly closed, pop the stack
            stack.pop();
        }

        if (bracketsString[index]==='}') {
            //if the symbol on the stack is not the exact opposite of the closing symbol
            //the string is not balanced
            if (stack[stack.length - 1] != '{') {
                return false;
            }
            //if this pair of symbols is properly closed, pop the stack
            stack.pop();
        }

        if (bracketsString[index]===']') {
            //if the symbol on the stack is not the exact opposite of the closing symbol
            //the string is not balanced
            if (stack[stack.length - 1] != '[') {
                return false;
            }
             //if this pair of symbols is properly closed, pop the stack
            stack.pop();
        }
    }
    //if the for loop is completed, then both the stack and the string are empty, return true.
    return true;
};

console.log("5. Closure check:")
console.log("String is ({({([])})})");
console.log("String closed: " + homework.balancedBrackets(bs));
bs = "([)]";
console.log("");
console.log("New string is ([)]")
console.log("String closed: " + homework.balancedBrackets(bs));

//YOUR SOLUTIONS, NOT STACKOVERFLOW’S  ;)

