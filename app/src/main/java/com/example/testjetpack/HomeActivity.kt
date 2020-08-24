package com.example.testjetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testjetpack.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    //https://developer.android.google.cn/samples?language=kotlin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this,R.layout.activity_home)
        binding.sunflower.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.dataBinding.setOnClickListener {
            var intent = Intent(this,DataBindingActivity::class.java)
            startActivity(intent)
        }

        binding.viewmodle.setOnClickListener {
            var intent = Intent(this,ViewModelActivity::class.java)
            startActivity(intent)
        }

        binding.livedata.setOnClickListener {
            var intent = Intent(this,LiveDataActivity::class.java)
            startActivity(intent)
        }
        
    }

}
