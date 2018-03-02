//1
function fibonacci(n) 
{
    var current=0;
    var previous=0;
    var sum=0;
    for(var i=0;i<=n;i++)
    {
        if(i == 1)
        current=i;
        if(i==2)
        console.log(1);
        else
        {
            sum=current+previous;
            previous=current;
            current=sum;
        }
    }

    return sum;
}

//2
function bubblesort(array)
{
    for(var i=0;i < array.length; i++)
    {
        for(var j=0;j < array.length; j++)
        {
            if(array[i] > array[j])
            {
                var temp=array[i];
                array[i]=array[j];
                array[j]=temp;
            }
        }
    }

    return array;
}

//3
function factorial(n)
{
    var product=1;
    for(var x=1;x<=n;x++)
    {
        product=product*x;
    }

    return product
}

//4
function rotate(array,times)
{
    var new_array=[];
    var new_indice=0;
    if(times >= array.length)
        times=times%array.length;
    for(var x=0;x<array.length;x++)
    {
        new_indice=x-times;
        if(new_indice<0)
        {
            new_indice=array.length+new_indice;
        }
        new_array[new_indice]=array[x];

        console.log(new_indice);
    }

    return new_array
}

//5
function brackets(str)
{
    var stack=[];
    for(var x=0;x < str.length;x++)
    {
        if(stack.length==0)
            stack.push(str[x]);
        else if(equivalent(stack[stack.length-1],str[x]))
            stack.pop();
        else
            stack.push(str[x]);

        console.log(stack);
    }

    if(stack.length==0)
        return true;
    else
        return false;
}

function equivalent(opening,closing)
{
    if( (opening =='{' && closing == '}')  ||   (opening =='(' && closing == ')') || (opening =='[' && closing == ']') )
    return true;
    else
    return false;
}
