package com.wyxx.android.xuexi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wyxx.android.xuexi.common.base.BaseBean;
import com.wyxx.android.xuexi.common.listener.NewRequestCallBack;
import com.wyxx.android.xuexi.common.utils.OkHttpClientManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        OkHttpClientManager.postAsync("", null, new NewRequestCallBack<BaseBean>(BaseBean.class) {
            @Override
            protected void onSuccess(BaseBean result) {

            }
        });

    }
}
