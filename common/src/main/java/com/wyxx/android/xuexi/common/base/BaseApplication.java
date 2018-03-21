package com.wyxx.android.xuexi.common.base;

import android.app.Application;
import android.content.Context;

import com.okhttplib.OkHttpUtil;
import com.wyxx.android.xuexi.common.utils.SPUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/3/17 17:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/3/17$
 * @updateDes ${TODO}
 */

public class BaseApplication extends Application {
    private static BaseApplication INSTACE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTACE = this;

        OkHttpUtil.init(this);
        SPUtils.init(getApplicationContext());
    }

    public static Context getAppContext() {
        return INSTACE.getApplicationContext();
    }

    public static Application getApplication() {
        return INSTACE;
    }
}
