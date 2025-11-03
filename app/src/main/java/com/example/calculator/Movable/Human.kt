package com.example.calculator
import kotlin.random.Random

open class Human:Movable{
    var fio: String = ""
        get() = field
        set(value){
            field = value
        }

    var age: Int = 0
        get()=field
        set(value){
            if (value>0){
                field=value
            }
            else {
                field=0
            }
        }

    override var curSpeed: Double = 0.0
    override var x: Double = 0.0
    override var y: Double = 0.0

    constructor(_fio: String, _age: Int, _speed: Double){
        fio= _fio
        age= _age
        curSpeed= _speed
        println("Создан: $fio")
    }

    open fun printInfo(){
        println("Человек: $fio, возраст: $age, скорость: $curSpeed")
    }

    override fun move() {
        val direction=Random.nextDouble(0.0, 2*Math.PI)
        x+= curSpeed*Math.cos(direction)
        y+= curSpeed*Math.sin(direction)
        println("$fio переместился в (${"%.1f".format(x)}, ${"%.1f".format(y)})")
    }
}