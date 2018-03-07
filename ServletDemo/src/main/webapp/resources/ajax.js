var myPerson;

function getJSON(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
		if(xhr.readyState == 4 && xhr.status == 200){
			
			var person = JSON.parse(xhr.responseText);
			
			myPerson = person;
			
			document.getElementById("stuff").innerHTML = person.name;
			
		}
		
	}
	
	xhr.open("GET", "myfirstservlet", true);
	xhr.send();
	
}

function postPerson(){
	
	var xhr = new XMLHttpRequest();
	
	var stuff2 = document.getElementById("stuff2");
	
	var schaun = {};
	schaun.name = "Schaun";
	
	xhr.onreadystatechange = function(){
		
		switch(xhr.readyState) {
		
		case 0:
				
			stuff2.innerHTML = "Request not initialized";
			break;
		
		case 1: 
			stuff2.innerHTML = "initialized connection";
			break;
			
		case 2: 
			stuff2.innerHTML = "Request Recieved";
			break;
			
		case 3:
			stuff2.innerHTML = "Processing";
			break;
			
		case 4:
			if(xhr.status == 200){
				
				stuff2.innerHTML = "person Posted!!!!!";
				
			}
			
			else{
				
				stuff2.innerHTML = "EROOR with request, status code: " + xhr.status;
				
			}
			break;
		
		}
		
	}
	
	xhr.open("POST", "myfirstservlet", true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	var data = "person="+JSON.stringify(schaun);
	xhr.send(data);
	
}

console.log("in onload");
document.getElementById("myBtn").addEventListener("click", getJSON, false);
document.getElementById("myBtn2").addEventListener("click", postPerson, false);