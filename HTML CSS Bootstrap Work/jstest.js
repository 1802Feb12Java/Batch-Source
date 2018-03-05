//alert("roll tide");
//var a=10;
//var a,e,h,e;
var homework = {};

//loosely typed

homework.fibonachi = function(f)
{
    if(f == undefined)
    {
        return 1;
    }

    if(f<=1) //if n is equal to or less than 1
    {
        return f;
    }
    //the fibonachi formula is a time complexity formula
    //recursive function self calls the fibnoachi sequence avoiding the need for a while/for loop
    var t = fibonachi(f-1) + fibonachi(f-2); //returns a value with f-1 and f-2, which then calls itself to do it again, 
                                            //until the intended value is reached
    return t;
};


console.log("Fibonachu(0): " + fibonachi(0));
console.log("Fibonachu(1): " + fibonachi(1));
console.log("Fibonachu(10): " + fibonachi(10));

var arrayy = [2,4,5,1,3,1];
console.log("sorting array arr = [2,4,5,1,3,1]");
homework.selectionSort = function(arr)
{
    var arrIndex, temp, 
    arrLen = arr.length;
    for(var i = 0; i < arrLen; i++)
    {
        arrIndex = i;
        for(var j = (i+1); j<arrLen; j++)
        {
        if(arr[j]<arr[arrIndex])
        {
            arrIndex = j;
        }
    }
        temp = arr[i];
        arr[i] = arr[arrIndex];
        arr[arrIndex] = temp;
    }
    return arr;
};
console.log(selectionSort(arayy));

var selectionSortArray = [2,4,5,1,3,1];
//alert("selectionSort: 2,4,5,1,3,1 " + selectionSort(selectionSortArray));

homework.getFactorial = function(i)
{
    var returnInt = i;

    if (i === 1 || i === 0) 
        return 1; 

    while (i > 1)
    { 
        i--;
        returnInt *= i;
    }

    return returnInt;
  };

console.log("Factorials of 0,1,3 are: " + getFactorial(1) + " " + getFactorial(2) + " " + getFactorial(3));

homework.rotateArray = function(arr,j,pos)
{
    arr=arr.concat(arr.splice(0,arr.indexOf(j)+pos)); // just splice the array and reposition it to wherever you want
    return arr; //no sort function needed, splice does the work for us
};

let nums = [1,2,3,4,5];
homework.rotateLeft = function(arr, n)
{
    let temp;
    for(let counter = 1; counter <= n; counter++) 
    {
        temp = arr[0];
        for(let index = 0; index < arr.length - 1; index++)
        {
            arr[index] = arr[index + 1];
        }
        arr[arr.length-1] = temp;
    }
    return array;
};
console.log(homework.rotateLeft(nums, 3));
console.log(homework.sort(arr));
let br = "({({([])})})";
homework.balancedBrackets = function(bracketStr)
{
    var bracketArray = [];
    var bracketLength = bracketArray.length-1;

    for(i=0; i<bracketStr.length; i++)
    {
        if(bracketStr.charAt(i)=='(' || bracketStr.charAt(i)=='{' || bracketStr.charAt(i)=='[')
        {
            bracketArray.push(bracketStr.charAt(i));
        }
        else if(bracketStr.charAt(i)=='}')
        {
            if(bracketArray[bracketLength] == '{')
            {
                bracketArray.splice(-1,1);
            }
            else
                {return false;}
                
        }

        else if(bracketStr.charAt(i)==']')
        {
            if(bracketArray[bracketLength] == '[')
            {
                bracketArray.splice(-1,1);
            }
            else
                {return false;}
        }

        else if(bracketStr.charAt(i)==')')
        {
            if(bracketArray[bracketLength] == '(')
            {
                bracketArray.splice(-1,1);
            }
            else
                {return false;}
        }
    }
    if(bracketArray.length == 0)
    {
        return true;
    }
    else
    {
        return false;
    }
};
console.log("Balance brackets: " + homework.balancedBrackets(br));