    /* 1. Return the nth fibonacci number   */
	var homework = {};  
	homework.fibonacci = function(n, a=1, b=0) {  
		if (n === 0) {
			return b;
		} else {
			// recursion
			return homework.fibonacci(n-1, a+b, a);
		}
	};
	console.log("1. Return the nth fibonacci number:");
	var x = homework.fibonacci(10);
	console.log(x);
	console.log("");

	/* 2. Sort the given array */ 
	var swap = false;
	var temp = 0;
	var i = 0;
	homework.sort = function(array) {  
		do {
			swap = false;
			for(i=0; i<array.length-1; i++) {
				if(array[i] > array[i+1]) {
					temp = 0;
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					swap = true;
				}
			}
		} while(swap);
		return array;
	}; 
	console.log("2. Sort the given array:");
	var x = homework.sort([3,2,4,1,3,1]);
	console.log(x);
	console.log("");

	/* 3. Return n factorial */
	homework.factorial = function(n) {
		if (n === 0) {
			return 1;
			}
		// recursion
		return n * homework.factorial(n - 1);
	}
	console.log("3. Return n factorial");
	var x = homework.factorial(5);
	console.log(x);
	console.log("");

	/* 4. Given an array, rotate left n times and return array */
	homework.rotateLeft = function(array, n) {  
		var returnArray = [];
		var temp;
		var temp2;
		for(var i=0; i<n; i++) {
			for(var j=0; j<array.length; j++) {
				if(j==0) {
					temp = array[array.length-1];
					array[array.length-1] = array[0];
				} else {
					temp2 = temp;
					temp = array[array.length-(j+1)];
					array[array.length-(j+1)] = temp2;
				}
			}
		}
		return array;
	};
	console.log("4. Given an array, rotate left n times and return array")
	var x = homework.rotateLeft([1,2,3,4,5], 37);
	console.log(x);
	console.log("");

	/* 5. Balanced brackets. Return true if balanced, false if not (,[,{ */
	homework.balancedBrackets = function(bracketsString) {  
		
		var i, val, expectedBracket;
		var temp = [];
		var split = bracketsString.split(',');
		var openingBrackets = ['[', '{', '('];
		var closingBrackets = [']', '}', ')'];

		for(i=0; i<bracketsString.length; i++) {

			val = split[i];

			if (openingBrackets.indexOf(val) > -1) {
				temp.push(val);
			} else if (closingBrackets.indexOf(val) > -1) {

				expectedBracket = openingBrackets[closingBrackets.indexOf(val)];
				if (temp.length === 0 || (temp.pop() !== expectedBracket)) {
					return false;
				}
		    }
		}
		return (temp.length === 0);
	};
    console.log("5. Balanced brackets");
    var x = homework.balancedBrackets('{,},{,(,[,(,),],),},{,},[,]');
    console.log(x);
    console.log("");