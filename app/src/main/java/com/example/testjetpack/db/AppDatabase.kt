package com.example.testjetpack.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.testjetpack.AppExecutors
import com.example.testjetpack.db.dao.CommentDao
import com.example.testjetpack.db.dao.ProductDao
import com.example.testjetpack.db.entity.CommentEntity
import com.example.testjetpack.db.entity.ProductEntity

abstract class  AppDatabase : RoomDatabase() {

    //通过dao,对数据进行增删改差
    abstract fun productDao(): ProductDao

    abstract fun commentDao(): CommentDao
    companion object {

        val DATABASE_NAME = "basic-sample-db"

        // For Singleton instantiation
        @Volatile
        private  var instance: AppDatabase? = null //"?"加在变量名后，系统在任何情况不会报它的空指针异常。


        fun getInstance(context: Context, appExecutors: AppExecutors?): AppDatabase {
            if(instance == null){
                instance = buildDatabase(context, appExecutors)
                instance?.updateDatabaseCreated(
                    context.applicationContext
                )            }

            return instance!!//"!!"加在变量名后，如果对象为null，那么系统一定会报异常！


        }

        private fun buildDatabase(
            appContext: Context,
            executors: AppExecutors?
        ): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    executors?.diskIO()?.execute {
                        //延迟4000
                        addDelay()
                        // Generate the data for pre-population
                        val database = getInstance(appContext, executors)
                        val products: List<ProductEntity> =
                            DataGenerator.generateProducts()
                        val comments: List<CommentEntity> =
                            DataGenerator.generateCommentsForProducts(products)

                        database.insertData(database,products,comments
                        )
                        // notify that the database was created and it's ready to be used
                        // notify that the database was created and it's ready to be used
                        database.setDatabaseCreated()
                    }

                }


            }).build()
        }



        fun addDelay() {
            Thread.sleep(4000)
        }








    }
    private fun insertData(
        database: AppDatabase,
        products: List<ProductEntity>,
        comments: List<CommentEntity>
    ) {
        database!!.runInTransaction {
            database.productDao().insertAll(products)
            database.commentDao().insertAll(comments)
        }

    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME)
                .exists()
        ) {
            setDatabaseCreated()
        }    }

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    private val mIsDatabaseCreated: MutableLiveData<Boolean> = MutableLiveData()


    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    open fun getDatabaseCreated(): LiveData<Boolean> {
        return mIsDatabaseCreated
    }
}