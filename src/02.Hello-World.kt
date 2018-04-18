fun main(args: Array<String>) {
  // value initializer and type inference
  val name = if (args.isNotEmpty()) args[0] else "Kotlin"
  // string templates & escape character
  println("Hello \$name $name!")
  println("Hello ${args[0]}")
  println("Hello ${if (args.size > 1) args[1] else "someone"}")

  // property syntax
  println(member.name)
  println(member.isMarried)

  println(rectangle.isSquare)
}

// expression body and return type inference
fun max(a: Int, b: Int) = if (a > b) a else b

// type inference
// String
val question = "The Ultimate Question of Life, the Universe, and Everything"
// Int
val answer = 42
// Double
val yearsToCompute = 7.5e6

// class
class Member(
  // private field
  val name: String,       // getter
  var isMarried: Boolean  // getter, setter
)

val member = Member("Bob", true)

class Rectangle(private val height: Int, private val width: Int) {
  val isSquare: Boolean get() = height == width
}

val rectangle = Rectangle(41, 43)
