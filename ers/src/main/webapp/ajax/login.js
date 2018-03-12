name=document.getElementById("uname");
pass=document.getElementById("password");
let person = {
        username : name,
        password : pass
    }
console.log("normal:"+ person);
console.log("JSON:"+JSON.stringify(person));
