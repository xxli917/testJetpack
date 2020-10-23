package com.example.testjetpack.bean

import androidx.lifecycle.ViewModel

//https://blog.csdn.net/weixin_33719619/article/details/88006679
/**
 * ViewModel 类旨在以一种能够感知生命周期的方式来保存和管理与UI相关的数据，这使得数据能够在configuration changes（如屏幕旋转）的时候不会丢失。
 *  从你第一次请求ViewModel（通常在onCreate Activity中）开始到Activity最终被销毁，ViewModel会一直存在。
 *  onCreate可能会在Activity的生命周期中多次调用，
 *  例如设备的屏幕发生旋转，但ViewModel还是同一个ViewModel。
 */
class StudentViewModel : ViewModel() {
    var age = 21
    var name = "王一博"
    fun add(){
        age++
        name = "王甜甜"
    }

}