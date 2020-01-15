package com.example.testjetpack.bean

import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    var age = 21
    var name = "王一博"
    fun add(){
        age++
        name = "王甜甜"
    }


}