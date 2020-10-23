package com.example.testjetpack.livedata

import androidx.lifecycle.LiveData
import java.math.BigDecimal

class StockLiveData(symbol: String) : LiveData<BigDecimal>() {
   // private val stockManager = StockManager(symbol)

    private val listener = {price: BigDecimal ->
        value = price
    }

    //具有活跃观察者，也就是生命周期处于started 和 resumed
    override fun onActive() {
       // stockManager.requestPriceUpdates(listener)
    }

    override fun onInactive() {
       // stockManager.removeUpdates(listener)
    }
}