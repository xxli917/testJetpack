package com.example.testjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testjetpack.bean.StudentViewModel
import com.example.testjetpack.databinding.ActivityViewModelBinding

/*
保证屏幕切换 数据不被销毁
以注重生命周期的方式管理界面相关的数据
 */
class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding =  DataBindingUtil.setContentView<ActivityViewModelBinding>(this,R.layout.activity_view_model)
        var student = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        //初始话ui布局
        binding.student = student

        //改变数值，切换屏幕数据不丢失
        binding.button.setOnClickListener {
            student.add()
            binding.age.text = student.age.toString()
            binding.name.text = student.name
        }

    }
}
