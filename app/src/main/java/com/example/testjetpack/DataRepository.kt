package com.example.testjetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.testjetpack.db.AppDatabase
import com.example.testjetpack.db.entity.CommentEntity
import com.example.testjetpack.db.entity.ProductEntity

class DataRepository private constructor(database: AppDatabase) {
    private var sInstance: DataRepository? = null

    private val mDatabase: AppDatabase = database
    private val mObservableProducts: MediatorLiveData<List<ProductEntity>> = MediatorLiveData()
    init {
        mObservableProducts.addSource(
            database.productDao().loadAllProducts()


        ) { productEntities ->
            if (mDatabase.getDatabaseCreated().value != null) {
                mObservableProducts.postValue(productEntities)
            }
        }

    }


    /**
     * Get the list of products from the database and get notified when the data changes.
     *
     */


    fun getProducts(): LiveData<List<ProductEntity>> {
        return mObservableProducts
    }

    fun loadProduct(productId: Int): LiveData<ProductEntity> {
        return mDatabase.productDao().loadProduct(productId)
    }

    fun loadComments(productId: Int): LiveData<List<CommentEntity>> {
        return mDatabase.commentDao().loadComments(productId)
    }

    companion object : SingletonHolder<DataRepository, AppDatabase>(::DataRepository)


}


