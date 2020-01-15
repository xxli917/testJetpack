package com.example.testjetpack

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.testjetpack.bean.Student
import com.example.testjetpack.databinding.ActivityDataBindingBinding

import kotlinx.android.synthetic.main.activity_data_binding.*

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this,R.layout.activity_data_binding)
        //一个纯净的student改变 ui并不会跟着改变，需要继承 BaseObservable 或者 参数需要是Observable类型
        var student = Student(ObservableField("王甜甜"), ObservableInt(21))
        binding.student = student
        binding.button.setOnClickListener {
            //一定要使用set方法，直接=无效
            student.name.set("王一博")
        }

    }

}
