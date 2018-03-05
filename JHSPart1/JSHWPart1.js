
var homework = {};

homework.fibonacci = function(n){

    if(n == 0)
        return 0;
    else if(n == 1)
      return 1;
    else
    return homework.fibonacci(n - 1) + homework.fibonacci(n - 2);
}

homework.sort = function(array){
    var temp;

    for(i = 0; i < array.length - 1 ; i++){
        console.log("i " + i);
        for(j = 0; j < array.length - i - 1; j++){
            if(array[j] > array[j + 1]){
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
}

homework.factorial = function(n){

    if(n == 1){
        return 1;
    }

    return n * homework.factorial(n - 1);
}

homework.rotateLeft = function(array, rotateAmount){
    var temp;
    var element;
    var newArray = [];
    arrayLength = array.length;

    for(i = 0; i < rotateAmount; i++){
        for(j = 0; j < arrayLength + 1; j++){
            if(j == 0 ){
                temp = array.shift();
            }
            else if(j != arrayLength && j != 0){
                newArray[j - 1] = array.shift();
            }
            else{
                newArray[j - 1] = temp;
            }
        }
        array = newArray;
        newArray = [];
    }
    console.log(array.toString());
}

homework.balancedBrackets = function(bracketsString){
    if(bracketsString.length % 2 != 0){
        return false;
    }
    else{
        for(i = 0; i < bracketsString.length / 2; i++){
            switch(bracketsString.substr(i, 1)){
                case "(":
                    if(bracketsString.substr(bracketsString.length - i - 1,1) != ")"){
                        return false;
                    }
                    break;
                case "[":
                     if(bracketsString.substr(bracketsString.length - i - 1,1) != "]"){
                         return false;
                      }
                      break;
                default:
                    return false;
            }
        }
    }
    console.log("These brackets are balanced");
    return true;
}


myArray = [5,4,3,2,1];

x = homework.fibonacci(9);

console.log(x);

console.log(myArray.toString());

homework.sort(myArray);
console.log(myArray.toString());

console.log("The factorial of 10 is:");
console.log(homework.factorial(10));

homework.rotateLeft(myArray, 3);


if(!homework.balancedBrackets("([))")){
    console.log("These brackets are not balanced.");
}

if(!homework.balancedBrackets("()]")){
    console.log("These brackets are not balanced.");
}

if(!homework.balancedBrackets("()")){
    console.log("These brackets are not balanced.");
}
