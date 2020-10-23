package com.example.testjetpack.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  不要让 ViewModel 和 Presenter 接触到 Android 框架中的类
 *  避免在 ViewModel 中持有 View 的引用
 *  在 ViewModel 和 View 中通信的建议方式是观察者模式，使用 LiveData 或者其他类库中的可观察对象。
 *  让 UI 观察数据的变化，而不是把数据推送给 UI
 *
 *  https://www.cnblogs.com/bingxinshuo/p/11717178.html


 */

//请确保用于更新界面的 LiveData 对象存储在 ViewModel 对象中，而不是将其存储在 Activity 或 Fragment 中，原因如下：
//避免 Activity 和 Fragment 过于庞大。现在，这些界面控制器负责显示数据，但不负责存储数据状态。
//将 LiveData 实例与特定的 Activity 或 Fragment 实例分离开，并使 LiveData 对象在配置更改后继续存在。
//LiveData 对象通常存储在 ViewModel 对象中，并可通过 getter 方法进行访问，如以下示例中所示：
class NameViewModel : ViewModel(){
    val currentName: MutableLiveData<String> by lazy{//延迟初始化
        MutableLiveData<String>()
    }

}

/**
 *  public class NameViewModel extends ViewModel {

// Create a LiveData with a String
private MutableLiveData<String> currentName;

public MutableLiveData<String> getCurrentName() {
if (currentName == null) {
currentName = new MutableLiveData<String>();
}
return currentName;
}

// Rest of the ViewModel...
}
 */