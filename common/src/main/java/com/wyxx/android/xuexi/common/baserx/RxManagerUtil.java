package com.wyxx.android.xuexi.common.baserx;


/**
 * Created by Administrator on 2017/3/22.
 */

public class RxManagerUtil {
    private static RxManager rxManager = new RxManager();

    public static void post(Object tag, Object content) {
        rxManager.post(tag, content);
    }

    public static void clear() {
        rxManager.clear();
    }
}
