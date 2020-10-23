package com.example.testjetpack.test

class DemoKT(private val mType: String,private val mA:Int): BaseDemo(mType){
    fun add(): String{
        return mType+"-"+mA

    }
}