// JS HW, part 1:

//  1. Return the nth fibonacci number

//  f(0) = 0
//  f(1) = 1
//  f(10) = 55
document.getElementById("fibButton").addEventListener("click" , function () {
    var fibTextBox = document.getElementById("fibEntry");
    var theNumber = Number(fibTextBox.value);
    var theResult = 0;
    if (theNumber === 0) {
        fibTextBox.value = "f(" + theNumber + ") = 0";
    }
    else if (theNumber === 1 || theNumber == 2) {
        fibTextBox.value = "f(" + theNumber + ") = 1";
    }
    else if (isNaN(theNumber)) {
        fibTextBox.value = "This is not a valid number"
    }
    else {
        var num1 = 1;
        var num2 = 1;
        var i = 2;
        var theResult = 0;
        while (i < theNumber) {
            theResult = num1 + num2;
            num1 = num2;
            num2 = theResult;
            i++;
        }
        fibTextBox.value = "f(" + theNumber + ") = " + theResult;
    }
});

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.

 */
document.getElementById("sortButton").addEventListener("click", function() {
    var arrayTextBox = document.getElementById("sortEntry");
    var entryArray = arrayTextBox.value.split(",");
    
    var switchMade = false;
    do {
        switchMade = false;
        for (var i = 0; i < entryArray.length; i++) {
            if (entryArray[i] > entryArray[i+1]) {
                var temp = entryArray[i];
                entryArray[i] = entryArray[i + 1];
                entryArray[i + 1] = temp;
                switchMade = true;
            }
        }
    } while (switchMade);
    arrayTextBox.value = entryArray.join();
});


/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6

*/
document.getElementById("factorialButton").addEventListener("click", function() {
    var factorialTextBox = document.getElementById("factorialEntry");
    var theNumber = Number(factorialTextBox.value);
    var theResult = 1;
    for (var i = 1; i < theNumber+1; i++) {
        theResult = theResult * i;
    }

    factorialTextBox.value = theNumber + "! = " + theResult;

});
/*

 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]


*/
document.getElementById("rotateButton").addEventListener("click", function() {
    var numTimes = document.getElementById("rotateTimesEntry").value;
    var theArray = document.getElementById("rotateEntry").value.split(",");
    for (var i = 0; i < numTimes; i++) {
        var temp = theArray[0];
        for (var j = 0; j < theArray.length -1; j++) {
            theArray[j] = theArray[j + 1];
        }
        theArray[j] = temp;
    }
    document.getElementById("rotateEntry").value = theArray.join();
});


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

*/
document.getElementById("balanceCheckButton").addEventListener("click", function() {
    var bracketArray = document.getElementById("balanceCheckEntry").value.split("");
    var isBalanced = true;
    var outputString = "";

    if (bracketArray.length % 2 != 0) {
        isBalanced = false;
    }
    else {
        var inputArray = new Array();
        var openersArray = ['(', '[', '{'];

        for (var i = 0; i < bracketArray.length; i++) {
            if (openersArray.includes(bracketArray[i])) {
                inputArray.push(bracketArray[i]);
            }
            else{
                var poppedElement = inputArray.pop();
                switch (bracketArray[i]) {
                    case ')':
                        if(poppedElement != "(") {
                            isBalanced = false;
                        }
                        break;

                    case ']':
                    if(poppedElement != "[") {
                        isBalanced = false;
                    }
                        break;

                    case '}':
                    if(poppedElement != "{") {
                        isBalanced = false;
                    }
                        break;

                    default:
                        outputString = "Error only: (), {}, and [] are supported."
                        break;
                }
            }
        }
    }
    if (isBalanced) {
        outputString = "This IS balanced";
    }
    else {
        outputString = "This IS NOT balanced";
    }
    document.getElementById("balanceCheckResults").value = outputString;
});

