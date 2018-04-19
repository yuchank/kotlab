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
}

fun mix(c1: Color, c2: Color) =
  when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
  }