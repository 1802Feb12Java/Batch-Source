//  1. return the nth fibonacci number

function fibonacci(n){
    var result = 0;

    if(n <= 2){
        return n-1;
    }

    result = fib(n-2)+fib(n-1);
    return result;
}


//  2. sort array of integers

function sort(a){
    var swap;
    do {
        swap = false;
        for (var i=0; i < a.length-1; i++) {
            if (a[i] > a[i+1]) {
                var temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
                swap = true;
            }
        }
    } while (swap);
}


//  3. return n factorial

function factorial(n){
    if(n == 0 || n == 1){
        return 1;
    }

    return n * fact(n-1);
}


//  4. rotate array left n times

function rotateLeft(n,i){
    var m = 0;
    var t = 0;
    var p;
    while(m < i){
        for(p = 0; p < n.length; p++){
            if(p == 0){
                t = n[p];
                n[p] = n[p+1];
                continue;
            }

            if(p == n.length-1){
                n[n.length-1] = t;
                continue;
            }

            n[p] = n[p+1];
        }
        m++;
    }

    return n;
}


//  5. balanced brackets

function balancedBrackets(s){
    var n = 0, m = 0, o = 0, i = 0;

    while(i < s.length){
        if(s[i] == '('){
            n++;
        }else if(s[i] == ')'){
            n--;
        }

        if(s[i] == '{'){
            m++;
        }else if(s[i] == '}'){
            m--;
        }

        if(s[i] == '['){
            o++;
        }else if(s[i] == ']'){
            o--;
        }

        i++;
    }

    if(n == 0 && m == 0 && o == 0){
        return true;
    }else
        return false;
}
}