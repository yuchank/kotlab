// data class
data class Person(val name: String, val age: Int? = null)   // nullable type Int?

fun main(args: Array<String>) {
    val persons = listOf(Person("Alice"), Person("Bob", age = 29))  // named argument
    val oldest = persons.maxBy { it.age ?: 0 }  // lambda expression. elvis operator
    println("The oldest is: $oldest")       // string template
}

// The oldest is: Person(name=Bob, age=29)  // autogenerated toString
