var homework = {};
// #1: Fibonacci, n can be any valid integer
homework.fibonacci = function(n) {
    var fib2 = 0; // 0th
    var fib1 = 1; // 1st
    var fib;

    if(n < 0) {
        fib = this.fibonacci(-n);
        fib = ((n & 1) == 0) ? -fib : fib;
    } else if(n == 0) {
        fib = fib2;
    } else if(n == 1) {
        fib = fib1;
    } else {
        for(var i = 2; i <= n; i++, fib2 = fib1, fib1 = fib) {
            fib = fib1 + fib2;
        }
    }

    return fib;
};

// #2: Array sorting (merge sort);
// input array will be empty, the sorted array is returned
homework.sort = function(array) {
    var sorterObj = {};

    // special internal object used in order to avoid repeating the reassignment into
    // the input array done at the end of this function.
    sorterObj.mergeSort = function(ary) {
        var sorted_merge = function(a1, a2) {
            // Merge into a1.
            var newAry = []; // temporary
            while(a1.length > 0 || a2.length > 0) {
                if(a1.length == 0) {
                    // move all of a2 into newAry
                    while(a2.length > 0) {
                        newAry.push(a2.shift());
                    }
                } else if(a2.length == 0) {
                    // move all of a1 into newAry
                    while(a1.length > 0) {
                        newAry.push(a1.shift());
                    }
                } else if(a1[0] < a2[0]) {
                    // a1[0] is lesser value, add it to newAry
                    newAry.push(a1.shift());
                } else {
                    // a2[0] is lesser value, add it to newAry.
                    newAry.push(a2.shift());
                }
            } // end while
    
            return newAry;
        };
    
            
        if(ary.length != 1) {
            // Split into left & right arrays
            var leftAry, rightAry;
            leftAry = ary.splice(0, Math.trunc(ary.length/2));
            rightAry = ary;
            
            // Sort each sub-array
            leftAry = this.mergeSort(leftAry);
            rightAry = this.mergeSort(rightAry);
    
            // Merge back into a full array.
            ary = sorted_merge(leftAry, rightAry);
        } // else -> Array of size 1 is already sorted.
        
        return ary;
    };
    
    var sortedArray = sorterObj.mergeSort(array);
    
    // Push sorted array into original array.
    while(sortedArray.length > 0) {
        array.push(sortedArray.shift());
    }

    return array;
};

// #3: factorial
homework.factorial = function(n) {
    var val = 1;
    
    for(var i = 1; i <= n; i++) {
        val *= i;
    }
    
    return val;
};

// #4: Rotate left
homework.rotateLeft = function(array, n) {
    for(var i = 0; i < n; ++i) {
        array.push(array.shift());
    }
}

// #5: Balanced Brackets
homework.balancedBrackets = function(bracketsString) {
    var isBalanced = true;
    var bracketStack = [];

    // indices of opening and closing brackets must match.
    var lBrack = ['[', '{', '('];
    var rBrack = [']', '}', ')'];
    
    for(var i = 0; i < bracketsString.length; ++i) {
        var c = bracketsString.charAt(i);
        if(lBrack.includes(c)) { // open bracket
            // push open bracket into stack
            bracketStack.push(c);
        } else if(rBrack.includes(c)) { // closing bracket
            // get matching bracket index
            var matchBrackIndx = rBrack.indexOf(c);

            // check bracket type at top of stack with the matching opening bracket
            if(bracketStack[bracketStack.length-1] == lBrack[matchBrackIndx]) {
                // Match -> pop open bracket from stack.
                bracketStack.pop();
            } else {
                // Found mis-match
                isBalanced = false;
                break;
            }
        } // else -> ignore text.
    }

    // stack length check is to confirm that all brackets are matched.
    // without the check, unbalanced brackets such as '[{}()' may return true.
    return isBalanced && (bracketStack.length == 0);
};
