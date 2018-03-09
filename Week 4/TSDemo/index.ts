function hello (someone) {
  return "Hello, " + someone;
}

let person = "Mehrab";

document.body.innerHTML = hello(person);

//3 basic types 
let isDone: boolean = false;
let num: number = 42;
let myName: string = "Mehrab";

//when type is unknown
let notSure: any = 4;
notSure = 'Maybe a string now';
notSure = false;

//collections
let list: number[] = [1, 2, 3];
let genlist: Array<number> = [1, 2, 3];
enum Color {Black, White, Red, Blue};
let c: Color = Color.White;

//function that returns nothing/void
function bigAlert(): void {
  alert("Big Alert!");
}

//lambdas
let f1 = function(i: number): number {
  return i + i;
}

let f2 = (i: number) => i + i;

//interfaces
interface Animal {
  species: string;
  age?: number; //optional property (?)
  eat(): void;
}

let a: Animal = {species: "Bear", eat: ()=> {}};

//classes
class Point {
  x: number;
  y: number;

  constructor(x: number, y: number) {
    this.x = x;
    this.y = y;
  }

  mag(): number {
    return Math.sqrt(this.x*this.x + this.y*this.y);
  }
}

//inheritance
class Point3D extends Point {
  z: number;

  constructor(x: number, y: number, z: number) {
    super(x, y);
    this.z = z;
  }

  //overwriting
  mag(): number {
    let m: number = super.mag();
    return Math.sqrt(m*m + this.z*this.z);
  }
}

//Modules: "." operator can separate submodules
module Geometry {
  export class Square {
    constructor(public sideLength: number = 0) {};

    area(): number {
      return Math.pow(this.sideLength, 2);
    }
  }
}
let square1 = new Geometry.Square(5);

//Generics
class Tuple<T1, T2> {
  constructor(public item1: T1, public item2: T2) {};
}

interface Pair<T> {
  item1: T;
  item2: T;
}

//Template strings
let newName = `Mehrab Rahman`;
let greeting = `Hi ${newName}, how are you?`;