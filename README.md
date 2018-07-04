# myth-lang

A programming language for the JVM.

After my PL & Compilers class, I was inspired to create my own language, but one
which would run on the JVM.

Clone the generator branch to write your own `.myth` files.

Here is all it does so far:

```javascript
import java.util.*

class First {
  static def main(args: String[]) {
    System::out.print("Type away: ")
    var scanner = Scanner(System::in)
    var bool = scanner.nextBoolean()

    if (bool) {
      System::out.println("You said true!")
    }
    else {
      System::out.println("You said false :(")
    }
  }
}
```

References:
* https://cs.au.dk/~mis/dOvs/jvmspec/ref-Java.html
