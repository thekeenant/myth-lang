# myth-lang

A programming language for the JVM.

After my PL & Compilers class, I was inspired to create my own language, but one
which would run on the JVM.

Clone the generator branch to write your own `.myth` files.

Here is all it does so far:

```dart
class First {
  static def main(args: String[]) {
    // declare variable to static field
    var out = System::out

    // make method calls on local variable
    out.print(1)
    out.print(2)
    out.print(3)
    out.println()

    // static field with method call
    System::out.println(0)
  }
}
```

References:
* https://cs.au.dk/~mis/dOvs/jvmspec/ref-Java.html
