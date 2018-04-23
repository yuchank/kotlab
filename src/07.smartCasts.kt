interface Expr
class Num(val value: Int): Expr
class Sum(val left: Expr, val right: Expr): Expr

fun main(args: Array<String>) {
  val x = 2
  val n = x as Number // explicit cat
  println(n)
  println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

// expression body
fun eval(e: Expr): Int =
  when (e) {
    // check the type of the when argument value.
    is Num -> e.value                       // smart cast
    is Sum -> eval(e.left) + eval(e.right)  // smart cast
    else -> throw IllegalArgumentException("Unknown expression")
  }
