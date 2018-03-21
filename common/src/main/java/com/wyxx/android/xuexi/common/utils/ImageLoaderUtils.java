package com.wyxx.android.xuexi.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.wyxx.android.xuexi.common.module.CustomImageViewTarget;
import com.wyxx.android.xuexi.common.module.GlideApp;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/3/17 15:14
 * @des ${图片加载框架}
 * @updateAuthor $Author$
 * @updateDate 2018/3/17$
 * @updateDes ${TODO}
 */

public class ImageLoaderUtils {

    private static final int DEFAULT_PLACE_HOLDER_ID = 0;
    private static final int DEFAULT_ERROR_ID = 0;

    public static void displayCircle(ImageView iv, String url) {
        display(iv.getContext(), iv, url, DEFAULT_PLACE_HOLDER_ID, DEFAULT_ERROR_ID, false, true);
    }

    public static void display(Context context, ImageView iv, String url) {
        display(context, iv, url, DEFAULT_PLACE_HOLDER_ID, DEFAULT_ERROR_ID, false);
    }

    public static void display(Context context, ImageView iv, String url, int placeholderId) {
        display(context, iv, url, placeholderId, placeholderId, false);
    }

    public static void displaySkipMemoryCache(Context context, ImageView iv, String url) {
        display(context, iv, url, DEFAULT_PLACE_HOLDER_ID, DEFAULT_ERROR_ID, true);
    }

    public static void display(Context context, ImageView iv, String url, int placeholderId, int errorId, boolean skipMemoryCache) {
        GlideApp.with(context).load(url).dontAnimate().placeholder(placeholderId).error(errorId).skipMemoryCache(skipMemoryCache).
                into(iv);
    }

    public static void display(Context context, ImageView iv, String url, int placeholderId, int errorId, boolean skipMemoryCache, boolean isCircle) {
        GlideApp.with(context).load(url).dontAnimate().placeholder(placeholderId).error(errorId).skipMemoryCache(skipMemoryCache).circleCrop().
                into(iv);
    }

    public static void displayTarget(Context context, ImageView iv, String url, int placeholderId, int errorId, int width, int height) {
        GlideApp.with(context).asBitmap().load(url).dontAnimate().placeholder(placeholderId).error(errorId).
                into(new CustomImageViewTarget(iv, width, height));
    }


    public static void clearMemory(Context context) {
        GlideApp.get(context).clearMemory();
    }


}
