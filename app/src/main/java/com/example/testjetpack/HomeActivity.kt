package com.example.testjetpack

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testjetpack.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.reflect.Proxy

class HomeActivity : AppCompatActivity() {
    //https://developer.android.google.cn/samples?language=kotlin

    //切换屏幕，list数据会变为当前初始值
    val numbers = mutableListOf(1, 2, 3, 4)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this,R.layout.activity_home)
        //给xml数据 布局
        binding.activity = this
       // sunflower.setOn
        binding.sunflower.setOnClickListener(Test())
        /*binding.sunflower.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }*/
        binding.dataBinding.setOnClickListener {
            var intent = Intent(this,DataBindingActivity::class.java)
            startActivity(intent)
        }

        binding.viewmodle.setOnClickListener {
            var intent = Intent(this,ViewModelActivity::class.java)
            startActivity(intent)
        }
        binding.simpleViewMode.setOnClickListener {
            var intent = Intent(this,SimpleViewModelActivity::class.java)
            startActivity(intent)
        }

        binding.livedata.setOnClickListener {
            var intent = Intent(this,LiveDataActivity::class.java)
            startActivity(intent)
        }
        binding.simpleArchitecture.setOnClickListener {
            Log.e("----","点击了")
            var intent = Intent(this,SimpleArchitectureActivity::class.java)
            startActivity(intent)
        }
        var list = packageManager.getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES)
        Log.e("安装包个数","="+list.size)
        var i = 0;
        var j  = 0
        for(item in list){
            if(item.packageName.contains("com.huawei")){
                i++
            }
            if(item.packageName.contains("com.android")){
                j++
            }

            Log.e("包名",item.packageName+"  版本="+item.versionName)


        }
        Log.e("华为自带软件 = ","个数 = "+i)
        Log.e("android自带软件 = ","个数 = "+j)
        var packageInfo:PackageInfo
        //String()
        //getSystemService()
       /* var str = Class.forName("java.lang.String")
        var cons = str.declaredConstructors
        var s = cons[0].newInstance("") as String

        Proxy.newProxyInstance()

        ServiceManager.getService*/
    }
    inner class Test :View.OnClickListener{
        override fun onClick(v: View?) {
            numbers.forEach { Log.e("list",it.toString()) }
            when(v?.id ){
                R.id.sunflower-> numbers[0]=100

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

    public fun gotoRecycler(){
        var intent = Intent(this,RecyclerViewActivity::class.java)
        startActivity(intent)

    }

}
