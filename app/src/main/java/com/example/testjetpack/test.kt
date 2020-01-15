package com.example.testjetpack

interface CanFly{
    fun fly()
}
class Bird(f:CanFly):CanFly by f // 只有接口可以委托哦！编译器会生成继承自CanFly接口的所有方法，并将调用转发给f对象

class AnimalWithWings :CanFly{
    override fun fly() {
        println("use wings to fly")
    }
}
class Bat:CanFly  by AnimalWithWings() //编译器会生成继承自CanFly接口的所有方法，并将调用转发给AnimalWithWings对象
fun main(){
    Bird(AnimalWithWings()).fly()
    Bat().fly()
}