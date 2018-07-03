interface Expr
class Num(val value: Int): Expr
class Sum(val left: Expr, val right: Expr): Expr

fun main(args: Array<String>) {
  val x = 2
  val n = x as Number // explicit cast
  println(n)
  println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
  println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))
}

// expression body
fun eval(e: Expr): Int =
  when (e) {
    // check the type of the when argument value.
    is Num -> e.value                       // smart cast: check and cast then use
    is Sum -> eval(e.left) + eval(e.right)  // smart cast: check and cast then use
    else -> throw IllegalArgumentException("Unknown expression")
  }

// expression body
fun evalWithLogging(e: Expr): Int =
  when (e) {
    // check the type of the when argument value.
    is Num -> {
      println("num: ${e.value}")
      e.value
    }                       // smart cast: check and cast then use
    is Sum -> {
      val left = evalWithLogging(e.left)
      val right = evalWithLogging(e.right)
      println("sum: $left + $right")
      left + right
    }  // smart cast: check and cast then use
    else -> throw IllegalArgumentException("Unknown expression")
  }
