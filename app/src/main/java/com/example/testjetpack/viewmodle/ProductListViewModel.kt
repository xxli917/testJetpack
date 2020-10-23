package com.example.testjetpack.viewmodle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.testjetpack.JetPackApplication
import com.example.testjetpack.db.entity.ProductEntity

/**
 * 保证在屏幕切换的时候数据保留
 */
class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    var mApplication  = application;
    //dataBinding 主要和xml交互 livedata和数据元交互，数据变化改变显示，但是要在控件中获取view
    // set
    //dataBinding 直接将数据写到xml中
    private val mObservableProducts = MediatorLiveData<List<ProductEntity>>()
    init {
        // set by default null, until we get data from the database.

        mObservableProducts.value = null
        val products: LiveData<List<ProductEntity>> =
            (mApplication as JetPackApplication).getRepository()
                .getProducts()

        // observe the changes of the products from the database and forward them
        //不为null才执行
        mObservableProducts.addSource(products,mObservableProducts::setValue)


    }


    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    fun getProducts(): LiveData<List<ProductEntity>> {
        return mObservableProducts
    }
}