
var homework={};


homework.fibonacci =function(n){
    f1=0;
    f2=1;
    fib=0;
    if(n==1){
        return f1;
    }else if(n==2){
        return f2;
    }else{
        for(i=1;i<n;i++){
            fib=f1+f2;
            f1=f2;
            f2=fib;
        }
        return fib;
    }
}


homework.sort = function(array){
    num=array.length;
    for(i=0;i<num-1;i++){
        for(j=0;j<num-i-1;j++){
            if(array[j]>array[j+1]){
                temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
            }
        }
    }
    return array;
}


homework.factorial = function(n){
    n_fac = 1;
    for(i=2;i<=n;i++){
        n_fac*=i;
    }
    return n_fac;
}



homework.rotateLeft = function(array,n){
    for(i=1;i<=n;i++){
        var left = array[0];
        array.shift();
        array.push(left);
    }
    return array;
}



homework.balanceBrakets = function(braketsString){
    num = braketsString.length;
    if(num%2 != 0){
        return false;
    }else{
        var openp=0;
        var openb=0;
        var openc=0;
        var closep=0;
        var closeb=0;
        var closec=0;
        for(var i =0;i<num;i++){
            if(braketsString[i] == "("){
                openp+=1;
            }else if(braketsString[i] == "["){
                openb+=1;
            }if(braketsString[i] == "{"){
                openp+=1;
            }else if(braketsString[i] == "{"){
                openc+=1;
            }else if(braketsString[i] == ")"){
                closep+=1;
            }else if(braketsString[i] == "]"){
                closeb+=1;
            }else if(braketsString[i] == "}"){
                closec+=1;
            }
        }
        if(openp != closep){
            return false;
        }else if(openb!=closeb){
            return false;
        }else if(openc!=closec){
            return false;
        }else{
            return true;
        }
    }
}