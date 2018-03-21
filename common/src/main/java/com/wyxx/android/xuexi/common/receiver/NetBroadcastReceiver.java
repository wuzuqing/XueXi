package com.wyxx.android.xuexi.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.wyxx.android.xuexi.common.baserx.RxManagerUtil;
import com.wyxx.android.xuexi.common.utils.NetWorkUtils;


/**
 * 监听网络状态发生变化广播
 * Created by Administrator on 2017/3/1.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            // 接口回调传过去状态的类型
            RxManagerUtil.post("netWorkState", NetWorkUtils.isNetConnected(context));
        }
    }

}