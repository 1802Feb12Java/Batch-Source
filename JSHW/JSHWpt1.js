// 1) return the nth fibonacci number
 


// function fibbonacci(x){
//     var y = 0;
//     while (y<x)
//     {
//         y = x;
//         x = x+y;
//     }
//     return x;
// }
// console.log(x);

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
    var temp = y;
    while (n>0)
    {
        temp = y;
        y = y + x;
        x=temp;
        n=n-1;
    }
    return x;
};



/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/

homework.sort = function(array) {
    {
        var swapped;
        do {
            swapped = false;
            for (var i=0; i < array.length-1; i++) {
                if (array[i] > array[i+1]) {
                    var temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
    console.log(array);
};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
    if(n==0){
        return 1;
    }
    else //TODO
    {
        return homework.factorial(n-1) * n;
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
    for(i=0; i<n; i++){
        for(j=0; j<array.length-1; j++){
            var temp = array[j+1];
            array[j+1] = array[j];
            array[j] = temp;
        }
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
    var openstack = [];
    for(i=0; i<bracketsString.length; i++){
        //add open brackets to the stack
        if(bracketsString.charAt(i)=='(' || bracketsString.charAt(i)=='{' || bracketsString.charAt(i)=='['){
            openstack.push(bracketsString.charAt(i));
        }
        //if closed brackets, check if the top of the stack is the correct open bracket
        else if(bracketsString.charAt(i)==')'){
            if(openstack[openstack.length-1] == '('){
                openstack.splice(-1,1);
            }
            else return false;
        }
        else if(bracketsString.charAt(i)=='}'){
            if(openstack[openstack.length-1] == '{'){
                openstack.splice(-1,1);
            }
            else return false;
        }
        else if(bracketsString.charAt(i)==']'){
            if(openstack[openstack.length-1] == '['){
                openstack.splice(-1,1);
            }
            else return false;
        }
    }
    if(openstack.length != 0){
        return false;
    }
    return true;
};