//Javascript homework
/*
JS HW, part 1:

Fill in the functions and submit them to your branch in a file called JSHWPart1.js
gp
*/
var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
  if (n===0){
    return 0
  }
  if(n===1){
    return 1;
  }
  var nums=[0,1];
  for(var i =2; i<n; i++){
    nums.push(nums[i-1] + nums[i-2]);
  }
  return nums[n-1]
};

console.log(homework.fibonacci(10));

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) {
  for(var i =0; i<array.length; i++){
    for(var j=i+1; j<array.length; j++){
      if (array[i] > array[j]){
        var k=array[i];
        var m=array[j];
        array[i]=m;
        array[j]=k;
      }
    }
  }
  return array;
};
console.log(homework.sort([2,4,5,1,3,1]));

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
  var total=1;
  for(var i=1; i<=n; i++){
    total*=i;
  }
  return total;
};
console.log(homework.factorial(4));
/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
  console.log(array);
  for(var i=0; i<n; i++){
    var a=array[0];
    array.shift(0);
    array.push(a);
  }
  return array;
};
console.log(homework.rotateLeft([2,4,5,1,3,1],2));
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
  var leftChar=[];
  for(var char=0; char< bracketsString.length; char++){
    switch (bracketsString[char]) {
      case ')':
        if (leftChar.length==0 || "(" !==leftChar.pop()) {
            return false;
        }
        break;
      case ']':
        if (leftChar.length==0 || "[" !==leftChar.pop()) {
            return false;
        }
        break;
      case '}':
        if (leftChar.length==0 || "{" !==leftChar.pop()) {
            return false;
        }
        break;
      case '(': case '[': case '{':
        leftChar.push(bracketsString[char]);
        break;
        }
  }
  return leftChar.length==0;
};

///YOUR SOLUTIONS, NOT STACKOVERFLOW’S  ;)


console.log(homework.balancedBrackets("(){}[{}]"));