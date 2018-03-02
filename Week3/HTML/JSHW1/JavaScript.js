var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function (n) {
    if (n == 0) {
        return 0;
    } else if (n == 1) {
        return 1;
    }
    return homework.fibonacci(n - 2) + homework.fibonacci(n - 1);
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function (array) {
    sortThis = array;
    for (i = 0; i < sortThis.length; i++) {
        for (j = 0; j < sortThis.length - i - 1; j++) {
            if (sortThis[j] > sortThis[j + 1]) {
                temp = sortThis[j];
                sortThis[j] = sortThis[j + 1];
                sortThis[j + 1] = temp;
            }
        }
    }
    return sortThis;
};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function (n) {
    if (n == 0) {
        return 1;
    } else if (n == 1) {
        return 1;
    }
    return homework.factorial(n - 1) * n;
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function (array, n) {
    var moveThis = array;
    for (i = 0; i < n; i++) {
        moveThis[moveThis.length] = moveThis[0];
        moveThis.shift();
    }
    return moveThis;
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
homework.balancedBrackets = function (bracketsString) {
    bracketsString = bracketsString.split('');
    var stack = [];
    //odd length can't be balanced
    if (bracketsString.length % 2 != 0) {
        return false;
    }
    for (i = 0; i < bracketsString.length; i++) {
        //initial stack push
        if (i == 0) {
            stack.push(bracketsString[i]);
        } else {
            //check previous character
            if (bracketsString[i - 1] == '(' && bracketsString[i] == ')') {
                stack.pop();
            } else if (bracketsString[i - 1] == '[' && bracketsString[i] == ']') {
                stack.pop();
            } else if (bracketsString[i - 1] == '{' && bracketsString[i] == '}') {
                stack.pop();
            } else {
                //check stack
                if (stack[stack.length - 1] == '(' && bracketsString[i] == ')') {
                    stack.pop();
                } else if (stack[stack.length - 1] == '[' && bracketsString[i] == ']') {
                    stack.pop();
                } else if (stack[stack.length - 1] == '{' && bracketsString[i] == '}') {
                    stack.pop();
                } else {
                    stack.push(bracketsString[i]);
                }
            }
        }
    }
    if (stack.length == 0) {
        return true;
    } else {
        return false;
    }
};