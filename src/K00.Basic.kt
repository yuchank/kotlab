/**
 * We declare a package-level function main which returns Unit and takes
 * an Array of strings as a parameter. Note that semicolons are optional.
 */

fun main(args: Array<String>) {
  if (args.isEmpty()) {
    println("Please provide a name as a command-line argument")
    return
  }

  // string templates and array access
  println("Hello, ${args[0]}")

  // concatenate strings using the + operator with values of other type, as long as the first element in the expression is a string.
  val s = args[0] + 1 + "!"

  // characters can be accessed by the indexing operation
  println(s[0])
  // string can be iterated over with a for loop
  for (c in s) {
    println(c)
  }
  // string template style concatenation
  println("$s !!!")

  // raw string
  val text = """
    |for (c in "foo")
    |  print(c)
    """.trimMargin()    // remove leading whitespace

  // by default, | is used as margin prefix, you can choose another character and pass is as a parameter
  val text2 = """
    >for (c in "foo")
    >  print(c)
    """.trimMargin(">")    // remove leading whitespace

  println(text)
  println(text2)

  // string template in a raw string
  val price = """
    |$text
    |${'$'}9.99
    """.trimMargin()
  println(price)

  // Array is a class
  val array1 = arrayOf(1, 2, 3)    // [1, 2, 3]
  for (c in array1)
    println(c)
  val array2 = arrayOfNulls<Int>(3)  // [null, null, null]
  for (c in array2)
    println(c)

  // creates an Array<String> with values ["0", "1", "4", "9", "16"]
  val asc = Array(5, { i -> (i * i).toString() })
  for (c in asc)
    println(c)

  // [] stands for calls to member functions get() and set()
  val x: IntArray = intArrayOf(1, 2, 3)
  x[0] = x[1] + x[2]
  println(x[0])

  // `val` denotes a declaration of a read-only local variable
  val language = if (args.isEmpty()) "EN" else args[0]
  println(when (language) {
    "EN" -> "Hello!"
    "FR" -> "Salut!"
    "IT" -> "Ciao"
    else -> "Sorry, I can't greet you in $language yet"
  })

  // the branch conditions may be combined with a comma
  val c = 1
  val t = "1"

  when (c) {
    0, 1 -> println("x == 0 or x == 1")
    else -> println("otherwise")
  }

  // arbitrary expressions as branch conditions
  when (c) {
    t.toInt() -> println("t encodes c")
    else -> println("t does not encode c")
  }

  val validNumbers = Array(5, { i -> (i * i) })

  // for being in or !in a range or a collection
  when (c) {
    in 1..10 -> println("c is in the range")
    in validNumbers -> println("c is valid")
    !in 10..20 -> println("x is outside the range")
    else -> println("none of the above")
  }

  println(hasPrefix("prefix"))
  println(hasPrefix("pre"))
  println(hasPrefix(1))

  val xx = Value(1)
  // if no arguments is supplied, the branch conditions are simply boolean expressions
  when {
    xx.isOdd() -> println("x is odd")
    xx.isEven() -> println("x is even")
    else -> println("x is funny")
  }

  Greeter(args[0]).greet()
  InitOrder(args[0])
  Constructor(3)

  DerivedOrder("John", "Wick")
}

fun hasPrefix(x: Any) = when (x) {
  is String -> x.startsWith("prefix")   // smart casts
  else -> false
}

// classes
class Value(private val x: Int) {
  fun isOdd(): Boolean {
    return x % 2 == 1
  }

  fun isEven(): Boolean {
    return x % 2 == 0
  }
}

class Greeter(private val name: String) {
  private val customerKey = name.toUpperCase()

  fun greet() {
    println("Hello, $name. key is $customerKey")
  }
}

// class body can be omitted
class Empty

// primary constructor
class InitOrder(name: String) {
  private val firstProperty = "First property: $name".also(::println)

  init {
    println("First initializer block that prints $name, $firstProperty")
  }

  private val secondProperty = "Second property: ${name.length}".also(::println)

  init {
    println("Second initializer block that prints ${name.length}, $secondProperty")
  }
}

// multiple parameters
class People(val firstName: String, val lastName: String, var age: Int)

// constructor keyword is required
class Customer public /* @Inject */ constructor(name: String)

// secondary constructor
class Parent(val name: String) {
  // delegation to the primary constructor
  constructor(name: String, parent: Parent) : this(name)
}

class Constructor {
  // init blocks are part of the primary constructor
  init {
    println("Init Block")
  }

  // secondary constructor
  constructor(i: Int) {
    // <-- delegation to the primary constructor happens as the first statement of a secondary constructor
    println("Constructor")
  }
}

// private empty primary constructor
class DontCreateMe private constructor()

// default value
class Sample(val name: String = "")

// the default superclass is Any
class Example   // implicitly inherits from Any

// declare an explicit supertype
open class Base(p: Int) {  // open is the opposite of java's final. by default, all classes in kotlin are final.
  open fun v() {}
  fun nv() {}
}

// In a final class, open members are prohibited.
class Derived(p: Int) : Base(p) {
  override fun v() {}
}

// In a open class, override is itself open. if you want to prohibit re-overriding, use final
open class AnotherDerived(p: Int) : Base(p) {
  final override fun v() {}
}

// choose constructor
open class View {
  constructor(ctx: Int)
  constructor(ctx: Int, attrs: Int)
}
class MyView : View {
  constructor(ctx: Int) : super(ctx)
  constructor(ctx: Int, attrs: Int) : super(ctx, attrs)
}

// overriding properties
open class Foo {
  open val x: Int get() { return 0 }  // custom getter
}

class Bar : Foo() {
  override val x: Int = 1
}

// you can override a val property with a var property.
interface iFoo {
  val count: Int
}
class Bar1(override val count: Int): iFoo
class Bar2: iFoo {
  override var count: Int = 0
}

// initialization order
open class BaseOrder(val name: String) {
  init { println("Initializing Base") }   // # 2
  open val size: Int = name.length.also { println("Initializing size in Base: $it") }   // # 3
}

class DerivedOrder(name: String, val lastName: String) : BaseOrder(name.capitalize().also { println("Argument for Base: $it") }) {  // # 1
  init { println("Initializing Derived") }    // # 4
  override val size: Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }   // # 5
}

// calling the superclass implementation
open class Foo2 {
  open fun f() { println("Foo.f()") }
  open val x: Int get() = 1
}

class Bar3: Foo2() {
  override fun f() {
    super.f()
    println("Bar.f()")
  }

  override val x: Int get() = super.x + 1
}

class Bar4: Foo2() {
  override fun f() { /*...*/ }
  override val x: Int get() = 0

  inner class Baz {
    fun g() {
      super@Bar4.f()        // calls Foo2's implementation of f()
      println(super@Bar4.x) // Uses Foo's implementation of x's getter
    }
  }
}

// overriding rules
open class A {
  open fun f() { print("A") }
  fun a() { print("a") }
}

interface B {
  fun f() { print("B") }  // interface members are 'open' by default
  fun b() { print("b") }
}

class C(): A(), B {
  // The compiler requires f() to be overriden:
  override fun f() {
    super<A>.f()  // call to A.f()
    super<B>.f()  // call to B.f()
  }
}

// don't need to annotate class or function with open
abstract class AA : A() {
  abstract override fun f()
}

// companion object
class Foo3 {
  companion object {
    const val BAR = "bar"
    fun baz() {}
  }
}