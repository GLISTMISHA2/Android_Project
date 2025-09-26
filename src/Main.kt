import kotlin.concurrent.thread

fun main(){
    val people = arrayOf(
        Human("Petrov P.P.", 14, 2.0),
        Human("Ivanov P.P.", 1, 2.0),
        Human("Sidorov P.P.", 17, 2.0),
        Human("Sinitsa P.P.", 18, 20.0),
        Human("Demin P.P.", 19, 2.0),
        Human("Krivolapov P.P.", 18, 3.0),
        Human("Kytenkov P.P.", 19, 1.0),
        Human("Shternshis P.P.", 20, 2.5),
        Human("Shalamov P.P.", 88, 2.0),
        Human("Statsenko P.P.", 18, 3.9),
        Human("Dobromilov P.P.", 8, 0.1),
        Human("Petrov2 P.P.", 18, 2.0),
        Driver("Voditel P.P.", 35, 10.0, Math.PI / 4)
    )

    println("============================================")

    val time_steps=10
    for (step in 1..time_steps) {
        println("\nСекунда $step:")
        println("_____________________________________________")

        val threads = mutableListOf<Thread>()

        for (person in people){
            val thread=thread{
                person.move()
            }
            threads.add(thread)
        }
        threads.forEach { it.join() }
    }
}