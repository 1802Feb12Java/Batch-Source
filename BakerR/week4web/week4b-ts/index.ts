function hello(someone) {
    return "Hello, " + someone;
}

let person = "Wamuu";


document.body.innerHTML = hello(person);


// 3 basic types
let isDone: boolean = false;
let num: number = 42;
let myName: string = "Wamuu";

// when type is unknown...
let notSure: any = 4;
notSure = 'Maybe a string now';
notSure = false;

// collections
let list: number[] = [1,2,3];
let genlist: Array<number> = [1,2,3];
enum Color {Black, White, Red, Blue};
let c: Color = Color.White;

// function that returns nothing/void
function bigAlert(): void {
    alert("I'm a Lert *, I'm a lert!");
}

// lambdas
let f1 = function(i: number): number {
    return i + i;
}

let f2 = (i: number) => i + i;

// interfaces
interface Animal {
    species: string;
    age?: number; // optional property
    eat(): void;
}


let a: Animal = {
    species: "Bear",
    eat: () => {}
};


// Generics
class Tuple<T1, T2> {
    constructor(public iitem1: T1, public item2: T2) {};
}

interface Pair<T> {
    item1: T;
    item2: T;
}



// Template Strings
let newName = `ワムー`

let greeting = `Hi ${newName}, how are you?`;
