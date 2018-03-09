function hello(someone) {
    return "Hello, " + someone;
}
var person = "Wamuu";
document.body.innerHTML = hello(person);
// 3 basic types
var isDone = false;
var num = 42;
var myName = "Wamuu";
// when type is unknown...
var notSure = 4;
notSure = 'Maybe a string now';
notSure = false;
// collections
var list = [1, 2, 3];
var genlist = [1, 2, 3];
var Color;
(function (Color) {
    Color[Color["Black"] = 0] = "Black";
    Color[Color["White"] = 1] = "White";
    Color[Color["Red"] = 2] = "Red";
    Color[Color["Blue"] = 3] = "Blue";
})(Color || (Color = {}));
;
var c = Color.White;
// function that returns nothing/void
function bigAlert() {
    alert("I'm a Lert *, I'm a lert!");
}
// lambdas
var f1 = function (i) {
    return i + i;
};
var f2 = function (i) { return i + i; };
var a = {
    species: "Bear",
    eat: function () { }
};
// Generics
var Tuple = /** @class */ (function () {
    function Tuple(iitem1, item2) {
        this.iitem1 = iitem1;
        this.item2 = item2;
    }
    ;
    return Tuple;
}());
// Template Strings
var newName = "\u30EF\u30E0\u30FC";
var greeting = "Hi " + newName + ", how are you?";
