package com.wyxx.android.xuexi.common.base;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wyxx.android.xuexi.common.R;
import com.wyxx.android.xuexi.common.baserx.RxManager;
import com.wyxx.android.xuexi.common.ui.dialog.LoadingDialog;
import com.wyxx.android.xuexi.common.utils.AppManager;
import com.wyxx.android.xuexi.common.utils.EmptyUtils;
import com.wyxx.android.xuexi.common.utils.TUtil;
import com.wyxx.android.xuexi.common.utils.ToastUitl;

import butterknife.ButterKnife;


/**
 * 基类
 */

/***************
 * 使用例子
 *********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements
// NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements BaseView
//        BGASwipeBackHelper.Delegate
{
    //Presenter 可以为空
    public T mPresenter;
    //Model 可以为空
    public E mModel;
    //当前Context对象
    public Context mContext;
    //RxBus管理器
    public RxManager mRxManager;
    private String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        //初始化RxBus管理器
        mRxManager = new RxManager();
        //设置layout前配置
        doBeforeSetcontentView();
        setContentView(getLayoutId());

     ButterKnife.bind(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            initBundle(getIntent().getExtras());
        }
        mContext = this;
        //获取当前的Presenter
        mPresenter = TUtil.getT(this, 0);
//        获取当前的Model
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        //初始化Presenter
        this.initPresenter();
        //初始化控件
        this.initView();
//        PushAgent.getInstance(this).onAppStart();
        TAG = this.getClass().getSimpleName();
    }

    protected void initBundle(Bundle extras) {

    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 设置layout前配置
     */
    protected void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        //设置主题
        if (isSupportSwipeBack()) {
            setTheme(R.style.JK_SwipeBack_Transparent_Theme);
        } else {
            setTheme(getThemeId());
        }
    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    protected int getThemeId() {
        return R.style.AppTheme;
    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initView();


    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
//        SetStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

//    /**
//     * 着色状态栏（4.4以上系统有效）
//     */
//    protected void SetStatusBarColor(int color) {
//
//        StatusBarCompat.setStatusBarColor(this, color);
//    }
//
//    /**
//     * 沉浸状态栏（4.4以上系统有效）
//     */
//    protected void SetTranslanteBar() {
//        StatusBarCompat.translucentStatusBar(this);
//    }
//

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //debug版本不统计crash
//        if (!BuildConfig.LOG_DEBUG) {
//            //友盟统计
//        }
        isPause = false;
//        MobclickAgent.onResume(this);
        mRxManager.post("onResume", TAG);
    }

    protected boolean isPause;

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
//        MobclickAgent.onPause(this);
    }

    public Bundle getBundle() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getExtras();
        }
        return null;
    }

    public Parcelable getParcelable(String key) {
        Bundle bundle = getBundle();
        if (bundle != null) return bundle.getParcelable(key);
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
        mRxManager.clear();
        AppManager.getAppManager().finishActivity(this);
    }

    public int getIntentExtra(Intent intent, String key, int defValue) {
        if (intent == null) {
            return defValue;
        }
        return getIntent().getIntExtra(key, defValue);
    }

    public String getIntentExtra(Intent intent, String key) {
        if (intent == null) {
            return "1";
        }
        return getIntent().getStringExtra(key);
    }

    public void initFragment(Fragment fragment) {
        initFragment(fragment, null);
    }

    public void initFragment(Fragment fragment, Bundle arguments) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (EmptyUtils.isNotEmpty(arguments)) {
            fragment.setArguments(arguments);
        }
//        ft.replace(R.id.frameLayout, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

    public Bundle getBundle(Intent intent) {
        if (intent != null) {
            return intent.getExtras();
        }
        return null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showShort(msg);
    }

    @Override
    public void showEmpty() {

    }
//
//    public void initCanTouchListener(ViewPager viewPager) {
//        if (viewPager != null) {
//            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//                @Override
//                public void onPageSelected(int position) {
//                    mSwipeBackHelper.setSwipeBackEnable(position == 0);
//                }
//            });
//        }
//    }
//
//    protected BGASwipeBackHelper mSwipeBackHelper;
//
//    /**
//     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
//     */
//    private void initSwipeBackFinish() {
//        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
//
//        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
//        // 下面几项可以不配置，这里只是为了讲述接口用法。
//
//        // 设置滑动返回是否可用。默认值为 true
//        mSwipeBackHelper.setSwipeBackEnable(true);
//        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
//        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
//        // 设置是否是微信滑动返回样式。默认值为 true
//        mSwipeBackHelper.setIsWeChatStyle(true);
//        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
//        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
//        // 设置是否显示滑动返回的阴影效果。默认值为 true
//        mSwipeBackHelper.setIsNeedShowShadow(true);
//        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
//        mSwipeBackHelper.setIsShadowAlphaGradient(true);
//        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
//        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
//    }
//
//    /**
//     * 正在滑动返回
//     *
//     * @param slideOffset 从 0 到 1
//     */
//    @Override
//    public void onSwipeBackLayoutSlide(float slideOffset) {
//    }
//
//    /**
//     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
//     */
//    @Override
//    public void onSwipeBackLayoutCancel() {
//    }
//
//    /**
//     * 滑动返回执行完毕，销毁当前 Activity
//     */
//    @Override
//    public void onSwipeBackLayoutExecuted() {
//        if (isSupportSwipeBack()) {
//            mSwipeBackHelper.swipeBackward();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        // 正在滑动返回的时候取消返回按钮事件
//        if (isSupportSwipeBack() && mSwipeBackHelper.isSliding()) {
//            return;
//        }
//        super.onBackPressed();
////        mSwipeBackHelper.backward();
//    }
}
