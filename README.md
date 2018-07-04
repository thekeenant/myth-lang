# myth-lang

A programming language for the JVM.

After my PL & Compilers class, I was inspired to create my own language, but one
which would run on the JVM.

Clone the generator branch to write your own `.myth` files.

Here is all it does so far:

```javascript
// imports
import java.util.*

// public class
class First {
  // static method
  static def main(args: String[]) {
    var scanner = Scanner(System::in) // construct scanner

    System::out.print("x: ") // print
    var x = scanner.nextInt() // method call

    System::out.print("y: ")
    var y = scanner.nextInt()

    System::out.print(x)
    System::out.print(" vs ")
    System::out.println(y)

    // branching
    if (x > y) System::out.println("TRUE")
    else System::out.println("FALSE")
  }
}
```

References:
* https://cs.au.dk/~mis/dOvs/jvmspec/ref-Java.html
