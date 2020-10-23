package com.example.testjetpack.base;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.testjetpack.R;
import com.example.testjetpack.bean.Student;

public class BaseActivity extends AppCompatActivity {
    private String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }

    @Override
    protected void onStart() {
        Log.e(TAG,"onStart");

        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG,"onResume");

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG,"onPause");

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.e(TAG,"onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.e(TAG,"onRetainCustomNonConfigurationInstance");

        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");

    }
}