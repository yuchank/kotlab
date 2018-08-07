package nullstudy

fun main(args: Array<String>) {
  var a: String = "abc"
  // a = null  // error

  var b: String? = "abc"
  b = null
  println(b)

  // safe calls
  println(a?.length)  // The type of this expression is Int?
  println(b?.length)
}