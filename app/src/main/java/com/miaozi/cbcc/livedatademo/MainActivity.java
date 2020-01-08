package com.miaozi.cbcc.livedatademo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private MutableLiveData<MyLiveData> liveData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liveData.observe(this, new Observer<MyLiveData>() {
            @Override
            public void onChanged(@Nullable MyLiveData myLiveData) {
                Log.d("TAG","数据变化了ssssssss: "+myLiveData.getId()+"");
            }
        });
        MyLiveData myLiveData = new MyLiveData();
        myLiveData.setId(1000);
        liveData.setValue(myLiveData);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    MyLiveData myLiveData1 = new MyLiveData();
                    myLiveData1.setId(30000);
                    liveData.postValue(myLiveData1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
