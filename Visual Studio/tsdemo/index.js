var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
function hello(someone) {
    return "Hello, " + someone;
}
var person = "Gunslinger";
document.body.innerHTML = hello(person);
//3 basic types
var isDone = false;
var num = 42;
var myName = "Josh";
//when type is unknown
var notSure = 4;
notSure = "Maybe a string";
notSure = false;
//collections
var list = [1, 2, 3];
var genlist = [1, 3, 5, 7, 9];
var color;
(function (color) {
    color[color["Black"] = 0] = "Black";
    color[color["white"] = 1] = "white";
    color[color["blue"] = 2] = "blue";
    color[color["red"] = 3] = "red";
})(color || (color = {}));
;
var c = color.white;
//function that returns nothing/ void
function bigAlert() {
    alert("Big Alert!");
}
//lambdas
var f1 = function (i) {
    return i + i;
};
var f2 = function (i) { return i + i; };
var a = { species: "Bear", eat: function () { console.log("nom nom"); } };
//classes
var Point = /** @class */ (function () {
    function Point(x, y) {
        this.x = x;
        this.y = y;
    }
    Point.prototype.mag = function () {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    };
    return Point;
}());
//inheritance
var Point3D = /** @class */ (function (_super) {
    __extends(Point3D, _super);
    function Point3D(x, y, z) {
        var _this = _super.call(this, x, y) || this;
        _this.z = z;
        return _this;
    }
    //override methods
    Point3D.prototype.mag = function () {
        var m = _super.prototype.mag.call(this);
        return Math.sqrt(m * m + this.z * this.z);
    };
    return Point3D;
}(Point));
//Modules: "." operator can seperate modules and submodules
var Geometry;
(function (Geometry) {
    var Square = /** @class */ (function () {
        function Square(sideLength) {
            if (sideLength === void 0) { sideLength = 0; }
            this.sideLength = sideLength;
        }
        ;
        Square.prototype.area = function () {
            return Math.pow(this.sideLength, 2);
        };
        return Square;
    }());
    Geometry.Square = Square;
})(Geometry || (Geometry = {}));
var square1 = new Geometry.Square(5);
//Generics
var Tuple = /** @class */ (function () {
    function Tuple(item1, item2) {
        this.item1 = item1;
        this.item2 = item2;
    }
    ;
    return Tuple;
}());
//Template strings
var newName = "Gunslinger\n\n"; //back ticks, not '
var greeting = "Hello " + newName + ", how are you?";
document.body.innerText = greeting;
