package com.example.testjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.testjetpack.databinding.ActivityLiveDataBinding
import com.example.testjetpack.livedata.NameViewModel
import kotlinx.android.synthetic.main.activity_live_data.*
import java.util.jar.Attributes

class LiveDataActivity : AppCompatActivity() {
    private val model: NameViewModel by viewModels() //用于实现委托
   /* private NameViewModel model;
    model = new ViewModelProvider(this).get(NameViewModel.class);
    final Observer<String> nameObserver = new Observer<String>(){
        @Override
        public void onChanged(@Nullable final String newName){
            nameTextView.setText(newName);
        }
    };
    model.getCurrentName().observe(this,nameObserver);*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
       // var binding = DataBindingUtil.setContentView<ActivityLiveDataBinding>(this,R.layout.activity_live_data)
        //被观察者改变告诉观察者
        val nameObserver = Observer<String>{
            newName ->name.text = newName
        }
        //观察livedata对象
        model.currentName.observe(this,nameObserver)
        //本例中调用 setValue(T) 导致观察者使用值 司凤 调用其 onChanged() 方法
        //您必须调用 setValue(T) 方法以从主线程更新 LiveData 对象。
        // 如果在 worker 线程中执行代码，则您可以改用 postValue(T) 方法来更新 LiveData 对象。
        button.setOnClickListener { v-> model.currentName.value = "司凤" }
    }



}
