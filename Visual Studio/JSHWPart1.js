/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
fibonacci = function(n){
    var x  = 0;
    var y = 1;
    var z = x;
    if(n == 0) return 0;
    if(n == 1) return 1;
    for(i = 1; i < n; i++){
        z = x + y
        if(x > y) {		
            y = z;
        }else {
            x = z;
        }
    }
    return z;
};

console.log(fibonacci(10));
/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
sort = function(array) {

		for(i = 0; i < array.length - 1; i++) {
			for(j = 0; j < array.length - i -1; j++) {		
				if(array[j] > array[j+1]) {				
					var num = array[j];				
					array[j] = array[j+1];			
					array[j+1] = num;
				}
			}
        }
        return array;
};
console.log(sort([2,4,5,1,3,1]));

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
factorial = function(n){
    var ans = n;							//placeholder for num so we can mess with it
		for(i = 1; i < n; i++) {			
			ans = ans*(n - i);
		}
		return ans;
};

console.log(factorial(5));

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
rotateLeft = function(array, n) {
    for(i = 0; i < n; i++) {
        var num = array[0];	
        for(j = 0; j < array.length - 1; j++){			
                array[j] = array[j+1];	
            }
        array[array.length - 1] = num;
    }
    return array;
};

console.log(rotateLeft([1,2,3,4,5], 1));
console.log(rotateLeft([1,2,3,4,5], 3));
console.log(rotateLeft([1,2,3,4,5], 6));
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
balancedBrackets = function(bracketsString){

    
    if(bracketsString.length%2 != 0){           //if it's not even, it's not balanced
        return false;
    }
    for(i = 0; i < bracketsString.length; i++){
        if(bracketsString.substr(i, 1) == '['){             //all these loops check to see the bracket is closed 
            for(j = i; j < bracketsString.length; j++){     //before another bracket is closed
                if(bracketsString.substr(j,1) == ']'){
                    break;
                }
                if(bracketsString.substr(j,1) == ')'){
                    return false;
                }
                if(bracketsString.substr(j,1) == '}'){
                    return false;
                }
            }
        }
        if(bracketsString.substr(i, 1) == '('){
            for(j = i; j < bracketsString.length; j++){
                if(bracketsString.substr(j,1) == ')'){
                    break;
                }
                if(bracketsString.substr(j,1) == ']'){
                    return false;
                }
                if(bracketsString.substr(j,1) == '}'){
                    return false;
                }
            }
        }
        if(bracketsString.substr(i, 1) == '{'){
            for(j = i; j < bracketsString.length; j++){
                if(bracketsString.substr(j,1) == '}'){
                    break;
                }
                if(bracketsString.substr(j,1) == ')'){
                    return false;
                }
                if(bracketsString.substr(j,1) == ']'){
                    return false;
                }
            }
        }
    }
    return true;
};

console.log(balancedBrackets("(()"));
console.log(balancedBrackets("(())"));
console.log(balancedBrackets("(({)})"));
console.log(balancedBrackets("(({})[)]"));
// YOUR SOLUTIONS, NOT STACKOVERFLOW’S  ;)

