import colors.Color
import colors.Color.*

fun getWarmth(color: Color) =
  when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
  }

fun main(args: Array<String>) {
  println(getWarmth(ORANGE))
  println(mix(BLUE, YELLOW))
  println(mixOptimized(BLUE, YELLOW))
}

// 'when' allows any objects.
// in this case, it creates too many objects(garbage).
fun mix(c1: Color, c2: Color) =
  when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
  }

// 'when' without an argument
// better performance, less readable
fun mixOptimized(c1: Color, c2: Color) =
  when {  // no argument for 'when', the branch condition is any Boolean expression.
    (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
    (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
    (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
    else -> throw Exception("Dirty color")
  }