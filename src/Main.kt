import kotlin.random.Random

open class Human
{
    var fio: String = ""
        get() = field
        set(value){field =value}

    var age: Int = 0
        get() = field
        set(value){field =value}

    var curSpeed: Double = 0.0
        get() = field
        set(value){field =value}

    var x = 0.0
        get() = field
        set(value){field =value}

    var y = 0.0
        get() = field
        set(value){field =value}


    constructor(_fio: String, _age: Int, _speed: Double){
        fio = _fio
        age = _age
        curSpeed = _speed
        println("Создан: $fio")
    }
    fun move()
    {
        val direction = Random.nextDouble(0.0, 2 * Math.PI)
        val randomSpeed = curSpeed * Random.nextDouble(0.5, 1.5)

        x += randomSpeed * Math.cos(direction)
        y += randomSpeed * Math.sin(direction)

        println("$fio переместился в (${"%.1f".format(x)}, ${"%.1f".format(y)})")
    }
}

fun main(){
    val people = arrayOf(
        Human("Petrov P.P.",14,2.0),
        Human("Ivanov P.P.",1,2.0),
        Human("Sidorov P.P.",17,2.0),
        Human("Sinitsa P.P.",18,20.0),
        Human("Demin P.P.",19,2.0),
        Human("Krivolapov P.P.",18,3.0),
        Human("Kytenkov P.P.",19,1.0),
        Human("Shternshis P.P.",20,2.5),
        Human("Shalamov P.P.",88,2.0),
        Human("Statsenko P.P.",18,3.9),
        Human("Dobromilov P.P.",8,0.1),
        Human("Petrov2 P.P.",18,2.0),
        Human("Petrov3 P.P.",18,2.0),
        Human("Petrov4 P.P.",18,2.0),
        Human("Petrov5 P.P.",18,2.0),
        Human("Petrov6 P.P.",18,2.0),
        Human("Petrov7 P.P.",18,2.0),
        Human("Petrov8 P.P.",18,2.0),
        Human("Petrov9 P.P.",18,2.0),
        Human("Petrov10 P.P.",18,2.0),
        Human("Petrov11 P.P.",18,2.0),
        Human("Petrov12 P.P.",18,2.0),
        Human("Petrov13 P.P.",18,2.0),
        Human("Petrov14 P.P.",18,2.0),
        )

    println("============================================")

    val time_steps = 10
    for (step in 1..time_steps) {
        println("\nСекунда $step:")
        println("_____________________________________________")
        for (person in people) {
            person.move()
        }
    }
}