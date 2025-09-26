class Driver:Human {
    var angle:Double = 0.0
        get()=field
        set(value){field=value%(2*Math.PI)}

    constructor(_fio:String, _age:Int, _speed: Double, _direction: Double):
            super(_fio, _age, _speed) {
        angle=_direction
        println("Создан водитель: $fio. Направление: ${"%.2f".format(Math.toDegrees(angle))}°")
    }

    override fun printInfo(){
        println("Водитель: $fio, возраст: $age, скорость: $curSpeed, направление: ${"%.2f".format(Math.toDegrees(angle))}°")
    }

    override fun move() {
        x+=curSpeed*Math.cos(angle)
        y+=curSpeed*Math.sin(angle)
        println("$fio переехал на (${"%.1f".format(x)}, ${"%.1f".format(y)})")
    }
}