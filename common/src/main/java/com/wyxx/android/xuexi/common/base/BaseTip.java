package com.wyxx.android.xuexi.common.base;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/13 13:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/13$
 * @updateDes ${TODO}
 */

public interface BaseTip {
    /**
     * 开启加载进度条
     *
     * @param msg
     */
     void startProgressDialog(String msg);

    /**
     * 停止加载进度条
     */
    public void stopProgressDialog();


    /**
     * 短暂显示Toast提示(来自String)
     **/
     void showShortToast(String text);


    /**
     * 短暂显示Toast提示(id)
     **/
     void showShortToast(int resId);


}
