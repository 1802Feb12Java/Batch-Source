var homework = {};
//1
homework.fibonacci = function(n = 25)
{
    var num = 0;
    var num2 = num + 1;
    for (var i = 0; i < 25; i++)
    {
       
        var fibonacci = num + num2;
        num = num2;
        num2 = fibonacci;
        try
        { 
             window.document.write(num + " ");
        }
        catch(e)
        {
            console.log(e);
        }
      
    }
    document.write("<br>");
}
//2
var sort = [2,4,5,1,3,1];
homework.sort = function()
{
    var swap;
    do {
        swap = false;
        for(var i = 0; i < 5; i++) 
        {
            if(sort[i] > sort[i+1]) 
            {
                var temp;
                temp = sort[i];
                sort[i] = sort[i+1];
                sort[i+1] = temp;
                swap = true;
            }
        }
    }while(swap);
    for(var i in sort)
    {
        document.write(sort[i]);
    }
    document.write("<br>");
}
//3
homework.factorial = function(u = 5)
{
        var x = u;
		if (u == 1)
            return 1;
        else
        {
            u = homework.factorial(u - 1);
            document.write(u + " ");
            u = u * x;
            return u; 
        }
    
}
//4

homework.rotateLeft = function(array,n = 1)
{
    var array = [1,2,3,4,5];
    for(var i = 0; i < n; i++)
    {
        var element = array.shift();
        array.push(element);
    }
    for(var i in array)
    {
        document.write(array[i]);
    }
    document.write("<br>");
}
//5
var bracketsString = "()}";

homework.balancedBrackets = function(bracketsString)
{
    var compare1 = "()"
    var compare2 = "()()"
    var compare3 = "(())"
    var compare4 = "({[]})"
    for(var i in bracketsString)
    {
        if((compare1 == i) || (compare2 == i) || (compare3 == i) || (compare4 == i))
        {
            return true;
        }

    }
    return false;
}
//Function Declarations\\
homework.fibonacci();
homework.sort();
var fac =homework.factorial();
document.write(fac + "<br>");
homework.rotateLeft();
var isBalanced = homework.balancedBrackets();
document.write(isBalanced.toString());
document.write("<br>");


