package com.example.testjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.testjetpack.viewmodle.ScoreViewModle
import kotlinx.android.synthetic.main.activity_simple_view_model.*

/**
 * https://blog.csdn.net/weixin_33719619/article/details/88006679
 * https://blog.csdn.net/weixin_33854644/article/details/87953135
 */
//直接写屏幕旋转会destory 重新走onCreate() 所有分数归0
class SimpleViewModelActivity : AppCompatActivity(){
    private lateinit var scopeViewMode: ScoreViewModle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("----","onCreate")

        setContentView(R.layout.activity_simple_view_model)
        scopeViewMode = ViewModelProvider(this).get(ScoreViewModle::class.java)
        ThreeForTeamA.setOnClickListener  { addAPoint(3) }
        ThreeForTeamB.setOnClickListener {  addBPoint(3) }
        TwoForTeamA.setOnClickListener { addAPoint(2) }
        TwoForTeamB.setOnClickListener { addBPoint(2) }
        OneForTeamA.setOnClickListener { addAPoint(1) }
        OneForTeamB.setOnClickListener { addBPoint(1) }
        //scropViewMode不会被清除，用它的值设置，保证屏幕切换后值不变
        displayA()
        displayB()

    }



    fun addAPoint(point: Int){
        scopeViewMode.aScore = scopeViewMode.aScore+point
        displayA()



    }
    fun addBPoint(point: Int){
        scopeViewMode.bScore = scopeViewMode.bScore+point
        displayB()
    }
    fun displayB(){
        team_b_score.text = (scopeViewMode.bScore).toString()
    }
    fun displayA(){
        team_a_score.text = (scopeViewMode.aScore).toString()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("----","ondestory")
    }
}