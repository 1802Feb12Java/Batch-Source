
var fibonacci = function(n) {

    if(n == 0){
        return 0;
    } else {
        return n + fibonacci(n-1);
    }
}

var sortArray = function(array){

    var boo = true;
    var p1 = 0;
    var p2 = 0;
    //console.log(array.length);
    //console.log("hello");
    while (boo) {
        if(p1 >= array.length){
            p2++;
            p1 = 0;
            if (p2==array.length){
                console.log(array);
                return;
            }
        }
        if(array[p1] > array[p1+1]){
            temp = array[p1+1];
            array[p1+1] = array[p1];
            array[p1] = temp;
        }
        //console.log(p1);
        p1++;
    }
}

var factorial = function(n){
    if (n == 1){
        return 1;
    }
    return n * factorial(n-1);
}

var rotateLeft = function(array, left){

    var arrayReturn = [];
    for(var i = 0; i<array.length-1; i++){
        arrayReturn[i] = array[i+1];
    }
    arrayReturn[array.length-1] = array[0];
    left = left -1

    if (left > 0){
        arrayReturn = rotateLeft(arrayReturn, left);
    }

    return arrayReturn;


}

var balanceBrackets = function(inputStr){
    
    if(inputStr.length % 2 != 0){
        return false;
    }
    
    var temp;
    var stack = [];

    for (var i = 0; i < inputStr.length; i++){
        if (inputStr[i] == '(' || inputStr[i] == '[' || inputStr[i] == '{'){
            stack.push(inputStr[i]);
        } else {
            temp = stack.pop();
            if ((inputStr[i] == ')' && temp == '(') ||
            (inputStr[i] == ']' && temp == '[') ||
            (inputStr[i] == '}' && temp == '{')){
            } else {
                return false;
            }
        }
    }

    if(stack.length == 0){
        return true;
    }
    return false;
}





