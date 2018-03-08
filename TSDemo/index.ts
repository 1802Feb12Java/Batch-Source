function hello (someone){
    return "Hello, " + someone;
}

let person = "Kevin";

document.body.innerHTML = hello(person);

//3 basic types
let isDone: boolean = false;
let num: number = 42;
let myName: string = "Kevin";

//when type is unknown, any is the generic type
let notSure: any = 4;
notSure = 'Maybe a string now';
notSure = false;

//collections
let list: number[] = [1,2,3];
let genlist: Array<number> = [1, 2, 3];
enum Color {Black, White, Red, Blue};
let c: Color = Color.White;

//function that returns nothing/void
function bigAlert(): void{
    alert("Big Alert!");
}

//lambdas
let f1 = function(i: number): number{
    return i+i;
}

let f2 = (i: number) => i+i;

//interfaces
interface Animal {
    species: string;
    age?: number;  //optional property
    eat(): void;
}

let a: Animal = {species: "Bear", eat: () =>{}};

class Point{
    x: number;
    y: number;

    constructor(x: number, y: number){
        this.x = x;
        this.y = y;
    }

    mag(): number {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
}

class Point3D extends Point{
    z: number;
    constructor(x: number, y: number, z: number){
        super(x,y);
        this.z = z;
    }

    //overriding
    mag(): number {
        let m: number = super.mag();
        return Math.sqrt(m*m + this.z*this.z);
    }
}

//Modules: "." operator can separate submodules
//similar to namespace
module Geometry {
    export class Square{
        constructor(public sideLength: number = 0)
        {};

        area(): number {
            return Math.pow(this.sideLength, 2);
        }
    }
}

let square1 = new Geometry.Square(5);

//Generics
//Tuple can take tuples of anything
class Tuple<T1, T2>{
    //constructor can take type T1 and type T2, typing on instantiation
    constructor(public item1: T1, public item2: T2) {};
}

//This function only takes a single type
//extensible ( <T1, T2, T3...> )
interface Pair<T>{
    item1: T;
    item2: T;
}

//Template strings
// ` in this example is backtick on ~ key
//allows for an entire chunk of pure string data.  Anything in between the ticks
//becomes a string
let newName = `Kevin

Green`;
//Template literal allows for variables, as below
let greeting = `Hi ${newName}, how are you?`;