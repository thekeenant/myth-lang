# myth-lang

A programming language for the JVM.

After my PL & Compilers class, I was inspired to create my own language, but one
which would run on the JVM.

Here is what it looks like so far:

```text
// No types yet
var cool_int = 1;

// Explicit parameters
var function = (var param1, var param2) {

};

// Optional param declaration
var function2 = {
  var wow;

  var function3 = (var param1) {
    var wow_again = 1;
  };
};
```