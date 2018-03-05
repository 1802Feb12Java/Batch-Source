// Week 3 assignment 1

// JS HW, part 1:

// Fill in the functions and submit them to your branch in a file called JSHWPart1.js
//gp
var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
    var x = 0;
    var y = 1;
    var temp;
    while (n > 0){
        temp = y;
        y += x;
        x = temp;
        n--;
    }

    return x;
};

console.log('homework.fibonacci(10)');
console.log(homework.fibonacci(10));
/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) {
    var temp;
    for (x = 0; x < array.length - 1; x++){
        for (y = 0; y < array.length - 1; y++){
            if (array[y] > array[y+1]){
                temp = array[y];
                array[y]=array[y+1];
                array[y+1]=temp;
            }
        }
    }
    return array;
};

console.log('homework.sort([2,4,5,1,3,1])');
console.log(homework.sort([2,4,5,1,3,1]));

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
    var totsmaggot = 1;

    for ( x = 1; x <= n; x++){
        totsmaggot = totsmaggot * x;
    }

    return totsmaggot;
};

console.log('homework.factorial(3)');
console.log(homework.factorial(3));

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
    var temp;
    for (y = 0; y < n; y++){
        temp = array[0];
    for (x = 0; x < array.length - 1; x++){
        array[x] = array[x+1];
    }
    array[array.length-1] = temp;
}
return array;
};

console.log('homework.rotateLeft([1,2,3,4,5],3)');
console.log(homework.rotateLeft([1,2,3,4,5],3));
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
    var bool = false;
    var stack = [];
    if(bracketsString.length % 2 == 1){
        bool = false;
        return bool;
    }
    for ( x = 0; x < bracketsString.length; x++){
        if (bracketsString.charAt(x) == "("){
            stack.push("(");
        }
        else if (bracketsString.charAt(x) == "{"){
            stack.push("{");
        }
        else if (bracketsString.charAt(x) == "["){
            stack.push("[");
        }
        else if (bracketsString.charAt(x) == ")"){
            if (stack[stack.length-1] == "("){
                stack.pop();
            } else {
                bool = false;
            }
        }
        else if (bracketsString.charAt(x) == "}"){
            if (stack[stack.length-1] == "{"){
                stack.pop();
            } else {
                bool = false;
            }
        }
        else if (bracketsString.charAt(x) == "]"){
            if (stack[stack.length-1] == "["){
                stack.pop();
            } else {
               bool = false;
            }
        }
    }

    if(stack.length != 0) {
       bool = false;
    } else {
        bool = true;
    }
    return bool;
}

console.log('homework.balancedBrackets("({[]})")');
console.log(homework.balancedBrackets("({[]})"));

console.log('homework.balancedBrackets("(((]")');
console.log(homework.balancedBrackets("(((]"));