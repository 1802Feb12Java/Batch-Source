// NN 

var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
    if(n == 1 || n == 2) {
            return 1;
    }
    else if(n == 0){ return 0;}
    else {
        return  homework.fibonacci(n-2) + homework.fibonacci(n-1);//use recursive
    }
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) {
    for (var i = 0; i < array.length ; i++){
        for (var j = 0; j < array.length - 1; j++){
            if (array[i] < array[j]) //swap elements
            {
                var temp = array[i];
                array[i] = array[j];
                array[j] = temp; 
            }
        }
    }
    return array;

};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
    if(n == 1 || n == 0 ) {
        return 1;
    }
    else 
    {
        return n* homework.factorial(n -1);//use recursive
    }
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
  
    var temp;
    //do this n times
    while(n--){
        temp = array.shift();//get first element and remove it from the array
        array.push(temp);//insert it at the end of the array
    }
    return array;
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

    var stack = [];
    var open = { '{': '}', '[': ']', '(': ')' }; //key pair values
    var closed = { '}': true, ']': true, ')': true };
    
    for (var i = 0; i < bracketsString.length; i ++) {
      var char = bracketsString[i];//parse char into a variable
      if (open[char]) {
        stack.push(char);//add char into stack array if key pair matches
      } else if (closed[char]) { //if closed bracket appears, 
        if (open[stack.pop()] !== char) return false;
      }
    }
    
  return stack.length === 0;
};
