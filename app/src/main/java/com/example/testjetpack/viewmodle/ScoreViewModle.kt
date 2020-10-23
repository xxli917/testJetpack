package com.example.testjetpack.viewmodle

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModle: ViewModel() {
     var aScore: Int = 0
        get() = field
        set(value) {
            field = value
        }
     var bScore: Int = 0
        get() = field
        set(value) {
            field = value
        }

    override fun onCleared() {
        super.onCleared()
        Log.e("ScoreViewModle","onCleared")
    }

}