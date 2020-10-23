package com.example.testjetpack.viewmodle

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testjetpack.DataRepository
import com.example.testjetpack.JetPackApplication
import com.example.testjetpack.db.entity.CommentEntity
import com.example.testjetpack.db.entity.ProductEntity

class ProductViewModel : AndroidViewModel {
    constructor(application: Application,dataRepository: DataRepository,productId: Int):super(application){
        mProductId = productId

        mObservableComments = dataRepository.loadComments(mProductId)
        mObservableProduct = dataRepository.loadProduct(mProductId)
    }
    private  var  mObservableProduct:LiveData<ProductEntity>
    private  var mObservableComments:LiveData<List<CommentEntity>>
    private  var  mProductId =0


    open  var mProduct = ObservableField<ProductEntity>();

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    fun getComments(): LiveData<List<CommentEntity>> {
        return mObservableComments
    }

    fun getObservableProduct(): LiveData<ProductEntity> {
        return mObservableProduct
    }

    fun setProduct(product: ProductEntity?) {
        mProduct.set(product)
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     *
     *
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    companion object {
        class Factory(application: Application,productId: Int): ViewModelProvider.NewInstanceFactory(){
            val mApplication = application
            val mProductId = productId
            val mRepository = (application as JetPackApplication).getRepository()
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ProductViewModel(mApplication, mRepository, mProductId) as T

            }

        }
    }







}