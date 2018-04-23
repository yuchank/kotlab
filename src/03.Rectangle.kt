package geometry.shapes

import java.util.Random

class Rectangle(private val height: Int, private val width: Int) {
  // custom accessor
  val isSquare: Boolean get() = height == width
}

fun createRandomRectangle(): Rectangle {
  val random = Random()
  return Rectangle(random.nextInt(), random.nextInt())
}