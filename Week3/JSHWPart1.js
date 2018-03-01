// JS HW, part 1:

// Fill in the functions and submit them to your branch in a file called JSHWPart1.js

var homework = {};

/* 1. Return the nth fibonacci number
 * f(0) = 0 
 * f(1) = 1 
 * f(10) = 55
*/
homework.fibonacci = function(n){
    if(n == 1) return 1;
    if(n == 0) return 0;
    return homework.fibonacci(n-1) + homework.fibonacci(n-2);
};

/* 2. Sort array of integers
 * f([2,4,5,1,3,1]) = [1,1,2,3,4,5]
 * Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) {
    for(i = 0; i < array.length;i++){
        for(j = 0; j < array.length; j++){
            if(j < array.length && array[j] > array[j+1]){
                var tempt = array[i];
                array[i] = array[j];
                array[j] = tempt;
            }
        }
    }
    return array
};

/* 3. Return the factorial of n
 * f(0) = 1
 * f(1) = 1
 * f(3) = 6
*/
homework.factorial = function(n){
    if(n == 1) return 1;
    return n * homework.factorial(n-1);
};

/* 4. Rotate left
 * Given array, rotate left n times and return array
 * f([1,2,3,4,5], 1) = [2,3,4,5,1]
 * f([1,2,3,4,5], 6) = [2,3,4,5,1]
 * f([1,2,3,4,5], 3) = [4,5,1,2,3]
*/
homework.rotateLeft = function(array, n) {
    normal_n = n % array.length;
    console.log(normal_n);
    if(normal_n != 0){
        for(i = 0; i < normal_n; i++){
            array.splice(array.length,0,array[i])
        }
        array.splice(0,i);
    }
    return array;
};

/* 5. Balanced Brackets
 * A bracket is any one of the following: (, ), {, }, [, or ]
 * The following are balanced brackets:
 *  ()
 *  ()()
 *  (())
 *  ({[]})
 * 
 * The following are NOT balanced brackets:
 *  (
 *  )
 *  (()
 *  ([)]
 * Return true if balanced
 * Return false if not balanced
*/
homework.balancedBrackets = function(bracketsString){
    stack = [];
    for(i=0; i < bracketsString.length; ++i){
        if(bracketsString.charAt(i) == '('){//push
            stack.push('(');
        }
        else if(bracketsString.charAt(i) == ')'){//pop
            if(stack[stack.length-1] == '('){
                stack.splice(-1,1);
            }
            else return false;
        }   
        else if(bracketsString.charAt(i) == '{'){//push
            stack.push('{');
        }   
        else if(bracketsString.charAt(i) == '}'){//pop
            if(stack[stack.length-1] == '{'){
                stack.splice(-1,1);
            }
            else return false;
        }   
        else if(bracketsString.charAt(i) == '['){//push
            stack.push('[');
        }   
        else if(bracketsString.charAt(i) == ']'){//pop
            if(stack[stack.length-1] == '['){
                stack.splice(-1,1);
            }
            else return false;
        }
    }
    if(stack.length != 0)
        return false;
    return true;
};

