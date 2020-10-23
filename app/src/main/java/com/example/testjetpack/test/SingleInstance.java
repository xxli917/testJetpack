package com.example.testjetpack.test;

import android.content.Context;

import java.lang.ref.WeakReference;

public class SingleInstance {
    private volatile static SingleInstance instance;
    private SingleInstance(Context context){
        //弱饮用解决内存泄漏问题
        WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        //contextWeakReference.get();

    }
    public static SingleInstance getInstance(Context context){
        if(instance == null){
            synchronized (SingleInstance.class){
                if(instance == null){
                    instance = new SingleInstance(context);

                }
            }
        }
        return instance;
    }
}
