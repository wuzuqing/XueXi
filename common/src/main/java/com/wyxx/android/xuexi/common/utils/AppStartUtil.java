package com.wyxx.android.xuexi.common.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     启动第三方APP的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class AppStartUtil {
    /**
     * 启动APP
     *
     * @param context  上下文对象
     * @param pageName 需要启动APP的包名
     * @param cls      需要启动的Activity
     */
    public static void startApp(Context context, String pageName, String cls) {
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName(pageName, cls);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        context.startActivity(intent);//启动

    }

    /**
     * 启动微信APP
     *
     * @param context 上下文对象
     */
    public static void startWechat(Context context) {
        startApp(context, "com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
    }


    public static void startSettings(Context context, String action) {
        Intent intent = new Intent(action);
        context.startActivity(intent);
    }

    public static void startWifiSettings(Context context) {
        startSettings(context, Settings.ACTION_WIFI_SETTINGS);
    }

    public static void startSettings(Context context) {
        startSettings(context, Settings.ACTION_SETTINGS);
    }




    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        String[] paths = { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su",
                "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }

    private static boolean checkRootMethod3() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }
}
