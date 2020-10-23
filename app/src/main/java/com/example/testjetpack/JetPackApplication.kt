package com.example.testjetpack

import android.app.Application
import com.example.testjetpack.db.AppDatabase

class JetPackApplication : Application() {
    private var mAppExecutors: AppExecutors? = null

    override fun onCreate() {
        super.onCreate()
        mAppExecutors = AppExecutors()
    }

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this, mAppExecutors)
    }

    fun getRepository(): DataRepository {
        return DataRepository.getInstance(getDatabase())
    }
}