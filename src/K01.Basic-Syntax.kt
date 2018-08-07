package basic

fun main(args: Array<String>) {
  if (args.size < 2) {
    println("No number supplied")
  }
  else {
    val x = parseInt(args[0])
    val y = parseInt(args[1])

    // We cannot say 'x * y' now because they may hold nulls
    if (x != null && y != null) {
      print(x * y)
    }
    else {
      println("One of the arguments is null")
    }
  }
}

fun max(a: Int, b: Int) = if (a > b) a else b
// return null if str does not hold a number
fun parseInt(str: String): Int? {
  try {
    return str.toInt()
  }
  catch (e: NumberFormatException) {
    println("One of the arguments isn't Int")
  }
  return null
}