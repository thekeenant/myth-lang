# myth-lang

A programming language for the JVM.

After my PL & Compilers class, I was inspired to create my own language, but one
which would run on the JVM.

Clone the generator branch to write your own `.myth` files.

Here is all it does so far:

```dart
class First {
  static def main(args: java.lang.String[]) {
    var x : java.io.PrintStream
    x = java.lang.System::out
    x.println(5)
  }
}
```

References:
* https://cs.au.dk/~mis/dOvs/jvmspec/ref-Java.html
