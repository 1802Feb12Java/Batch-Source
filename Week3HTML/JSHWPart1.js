var homework = {};

homework.fibonacci = function(n) {
    var numFibs=0;

    return function fib(n) {
        if(arguments[1] == undefined){
            var first = 0;
        }
        else{
            var first = arguments[1];
        }
        if(arguments[2] == undefined){
            var second = 1;
        }
        else{
            var second = arguments[2];
        }
        var next = second + first;	//next number will be the first plus the second

        if(n==0){
            return 0;
        }
        else if(n==1){
            return 1;
        }
        else if(numFibs == n-2){
            numFibs = 0;
            return next;
        }
        else if(numFibs <= n) {	//if not done it 25 (or whatever specified amount of repeats is) times yet
            numFibs++;
            return fib(n, second, next);	//call recursively to add the second and the next number and print
        }
    }
}();

homework.sort = function(array) {
    var numsWereSwapped = true;
	while(numsWereSwapped == true) {
		numsWereSwapped = false;
		for(var i=0; i < array.length-1; i++) {
			if(array[i] > array[i+1]) {
				numsWereSwapped = true;
				var temp = array[i+1];
				array[i+1] = array[i];
				array[i] = temp;
			}
		}
	}
    return array;
};

homework.factorial = function(n){
    if(n==0) {
        return 1;
    }
    var nFactorial = n;		//this will be the final thing returned
    var numToMultiplyBy = n-1;	//this is the next number to multiply nFactorial by in each step
    while(numToMultiplyBy > 1) {	//go until you hit 1, at which point multiplying by 1 will do nothing
        nFactorial *= numToMultiplyBy;	//multiply the current factorial value by the next number down in the list
        numToMultiplyBy--;	//decrement the next number in the list
    }
    return nFactorial;
}

homework.rotateLeft = function(array, n) {
    for(i=0; i<n; i++){
        for(j=0; j<array.length-1; j++){
            var temp = array[j+1];
            array[j+1] = array[j];
            array[j] = temp;
        }
    }
    return array;
};

homework.balancedBrackets = function(bracketsString){
    var openstack = [];
    for(i=0; i<bracketsString.length; i++){
        //add open brackets to the stack
        if(bracketsString.charAt(i)=='(' || bracketsString.charAt(i)=='{' || bracketsString.charAt(i)=='['){
            openstack.push(bracketsString.charAt(i));
        }
        //if closed brackets, see if the top of the stack is the corresponding open bracket
        else if(bracketsString.charAt(i)==')'){
            if(openstack[openstack.length-1] == '('){
                openstack.splice(-1,1);
            }
            else return false;
        }
        else if(bracketsString.charAt(i)=='}'){
            if(openstack[openstack.length-1] == '{'){
                openstack.splice(-1,1);
            }
            else return false;
        }
        else if(bracketsString.charAt(i)==']'){
            if(openstack[openstack.length-1] == '['){
                openstack.splice(-1,1);
            }
            else return false;
        }
    }
    if(openstack.length != 0){
        return false;
    }
    return true;
};