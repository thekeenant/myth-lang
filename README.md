# myth-lang

A programming language for the JVM.

After my PL & Compilers class, I was inspired to create my own language, but one
which would run on the JVM.

Clone the generator branch to write your own `.myth` files.

Here is what it looks like so far:

```javascript
// inferred variable types
var x = 0;    // Int
var y = true; // Bool

// explicit type
var z : Int;

// this is illegal, because variables need types
// var x;

// also illegal, "what did the boolean say to the integer? you're not my type"
// var x : Bool = 5;

class Animal {
  var living = true;
  var running = false;
  
  // abstract method, one default param
  var speak = (var toWhom = "Bob");
  
  // no params, returns Bool
  var toggleRun = () : Bool {
    running = true;
    return running;
  };
};

class Dog {
  var Dog = (var name : String) {
  
  };

  // inline function (no strings yet)
  var speak = (var toWhom = "Bob") -> print("Bark");
};

var dog = new Dog("Max");
```

References:
* https://cs.au.dk/~mis/dOvs/jvmspec/ref-Java.html
