var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
        if( n== 0){
            return 0;
        } else if (n==1){
            return 1;
        } else {
            return homework.fibonacci(n-1) + homework.fibonacci(n-2);
        }
};
console.log("Fibonacci Function:")
x = homework.fibonacci(0);
console.log(x);
x = homework.fibonacci(1);
console.log(x);
x = homework.fibonacci(10);
console.log(x);


/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) {
    var s = array.length;
    for(i = 0; i < s-1; i++ ){
        for( j = i+1; j < s; j++){
            if(array[j] < array[i]){
                var temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }
    return array;
};
console.log("Array Sort: ");
x = homework.sort([2,4,5,1,3,1]);
console.log(x)

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
    if(n==0){ return 1;}
    var num = 1;
    for(i = 1; i <= n; i++){
        num = num*i;
    }
    return num;
};

console.log("Factorial Function:");
x = homework.factorial(0);
console.log(x);
x = homework.factorial(1);
console.log(x);
x = homework.factorial(3);
console.log(x);

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
    if(n==0){ return array;}
    for(i = 1; i <= n; i++){
        var num = array[0];
        array.shift();
        array.push(num);
    }
    return array;
};
console.log("Rotate Array Elements Left: ");
x = homework.rotateLeft([1,2,3,4,5], 1);
console.log(x);
x = homework.rotateLeft([1,2,3,4,5], 6);
console.log(x);
x = homework.rotateLeft([1,2,3,4,5], 3);
console.log(x);

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
    var stringLength = bracketsString.length;
    var stack = new Array();
    if(stringLength % 2 != 0){ bool = false;}
    
    for(i = 0; i < stringLength; i++){
        var nextbrack = bracketsString.substr(i,1);
        if(nextbrack == "("){
            stack.push("(");
        } else if(nextbrack == "{"){
            stack.push("{");
        } else if(nextbrack == "["){
            stack.push("[");
        } else if(nextbrack == ")"){
            if(stack[stack.length-1] == "("){
                stack.pop();
            } else {return false;}
        } else if(nextbrack == "}"){
            if(stack[stack.length-1] == "{"){
                stack.pop();
            } else {return false;}
        } else if(nextbrack == "]"){
            if(stack[stack.length-1] == "["){
                stack.pop();
            } else {return false;}
        }
    }

    if(stack.length != 0){return false;}
    else{return true;}
};

console.log("Balanced Brackets: ")
console.log("Input: ({[]})");
x = homework.balancedBrackets("({[]})");
console.log(x);
console.log("Input: ({}[])");
x = homework.balancedBrackets("({}[])");
console.log(x);
console.log("Input: ({([]})){]");
x = homework.balancedBrackets("({([]})){]");
console.log(x);