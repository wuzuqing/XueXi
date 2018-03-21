package com.wyxx.android.xuexi.common.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wyxx.android.xuexi.common.R;
import com.wyxx.android.xuexi.common.utils.AppManager;
import com.wyxx.android.xuexi.common.utils.KeyBordUtil;


public class CommonTitleBar extends RelativeLayout {

    private LinearLayout mRightContainer;
    private ImageView ivRight, ivRight2, ivRight3;
    private TextView ivBack, tvTitle, tvRight;
    private RelativeLayout rlCommonTitle;
    private View verLine;
    private int imgWidth;
    private int backStyle;
    private int defPadding;
    private int textRightColor = Color.parseColor("#666666");
    private float textRightSize = 16f;

    public CommonTitleBar(Context context) {
        this(context, null);
    }

    public CommonTitleBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.group_view_common_title_bar, this, true);
        ivBack = (TextView) findViewById(R.id.tv_back);
        verLine = findViewById(R.id.verLine);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mRightContainer = (LinearLayout) findViewById(R.id.rightContainer);

        rlCommonTitle = (RelativeLayout) findViewById(R.id.common_title);

        defPadding =  getResources().getDimensionPixelSize(R.dimen.ntb_padding);
        imgWidth =  getResources().getDimensionPixelSize(R.dimen.ntb_img_width);
//        defPadding = (int) TypedValue.applyDimension(1, 12, getResources().getDisplayMetrics());
//        imgWidth = (int) TypedValue.applyDimension(1, 48, getResources().getDisplayMetrics());


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar);
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_bg)) {
            rlCommonTitle.setBackgroundColor(array.getColor(R.styleable.CommonTitleBar_ctb_bg, Color.TRANSPARENT));
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_img_padding)) {
            defPadding = array.getDimensionPixelSize(R.styleable.CommonTitleBar_ctb_img_padding,defPadding);
//            rlCommonTitle.setBackgroundColor(array.getColor(R.styleable.CommonTitleBar_ctb_bg,Color.TRANSPARENT));
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_title_text)) {
            tvTitle.setText(array.getString(R.styleable.CommonTitleBar_ctb_title_text));
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_tv_left)) {
            int style = array.getInteger(R.styleable.CommonTitleBar_ctb_tv_left, 0);
            switch (style) {
                case 0:
                    setTvLeftVisiable(true, false);
                    break;
                case 1:
                    setTvLeftVisiable(true, true);
                    break;
            }
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_right_text_color)) {
            textRightColor = array.getColor(R.styleable.CommonTitleBar_ctb_right_text_color, textRightColor);
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_right_text_size)) {
            textRightSize = array.getFloat(R.styleable.CommonTitleBar_ctb_right_text_size, 14f);
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_right_text)) {
            createRightText();
            String rightText = array.getString(R.styleable.CommonTitleBar_ctb_right_text);
            tvRight.setText(rightText);
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_right_img_size)) {
            imgWidth = array.getDimensionPixelSize(R.styleable.CommonTitleBar_ctb_right_img_size, (int) TypedValue.applyDimension(1, 48,
                    getResources().getDisplayMetrics()));
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_right_img1)) {
            ivRight = createRightImage(array.getDrawable(R.styleable.CommonTitleBar_ctb_right_img1), R.id.id_right_img1);
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_right_img2)) {
            ivRight2 = createRightImage(array.getDrawable(R.styleable.CommonTitleBar_ctb_right_img2), R.id.id_right_img2);
        }
        if (array.hasValue(R.styleable.CommonTitleBar_ctb_right_img3)) {
            ivRight3 = createRightImage(array.getDrawable(R.styleable.CommonTitleBar_ctb_right_img3), R.id.id_right_img3);
        }

        array.recycle();
    }


    private void createRightText() {
        if (tvRight != null) return;
        tvRight = new TextView(getContext());
        tvRight.setId(R.id.tv_right);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        tvRight.setGravity(Gravity.CENTER);
        tvRight.setPadding(defPadding, 0, defPadding, 0);
        tvRight.setTextColor(textRightColor);
        tvRight.setTextSize(textRightSize);
        tvRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_tran_gray));
        mRightContainer.addView(tvRight, params);
    }


    private ImageView createRightImage(Drawable drawable, int id) {
        ImageView iv = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        if (drawable != null) {
            iv.setImageDrawable(drawable);
        }
        iv.setId(id);
        iv.setPadding(defPadding, defPadding, defPadding, defPadding);
        iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_tran_gray));
        mRightContainer.addView(iv, params);
        return iv;
    }

    public void setBackGroundColor() {
        setBackGroundColor(R.color.titleBar_bg);
    }

    /**
     * 管理返回按钮
     * 设置标题栏左侧字符串
     *
     * @param visiable 是否显示
     */
    public void setTvLeftVisiable(boolean visiable) {
        if (visiable) {
            ivBack.setVisibility(View.VISIBLE);
            verLine.setVisibility(VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
            verLine.setVisibility(INVISIBLE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param visiable 是否显示
     */
    public void setTvLeftVisiable(boolean visiable, boolean isFinish) {
        setTvLeftVisiable(visiable);
        if (isFinish) {
            ivBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    KeyBordUtil.hideSoftKeyboard(v);
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).finish();
                    } else {
                        AppManager.getAppManager().currentActivity().finish();
                    }
                }
            });
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText) {
        ivBack.setVisibility(VISIBLE);
        ivBack.setText(tvLeftText);
        ivBack.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }


    /**
     * 管理标题
     */
    public void setTitleVisibility(boolean visible) {
        if (visible) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.INVISIBLE);
        }
    }

    public void setTitleText(String string) {
        tvTitle.setText(string);
    }

    public void setTitleIcon(int rightIcon) {
        setTitleIcon(rightIcon, 8);
    }

    public void setTitleIcon(int rightIcon, int drawablePadding) {
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
        tvTitle.setCompoundDrawablePadding(drawablePadding);
    }

    public void setTitleText(int string) {
        tvTitle.setText(string);
    }

    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 右图标
     */
    public void setRightImagVisibility(boolean visible) {
        if (ivRight != null) ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 右图标2
     */
    public void setRightImag2Visibility(boolean visible) {
        if (ivRight2 != null) ivRight2.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 右图标2
     */
    public void setRightImag3Visibility(int visible) {
        if (ivRight3 != null) ivRight2.setVisibility(visible);
    }

    public void setRightImagSrc(int id) {
        if (ivRight == null) {
            ivRight = createRightImage(getResources().getDrawable(id), R.id.id_right_img1);
        }
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(id);
    }

    public void setRightImag2Src(int id) {
        if (ivRight2 == null) {
            ivRight2 = createRightImage(getResources().getDrawable(id), R.id.id_right_img2);
        }
        ivRight2.setVisibility(View.VISIBLE);
        ivRight2.setImageResource(id);
    }

    public ImageView setRightImag3Src(int resId) {
        if (ivRight3 == null) {
            ivRight3 = createRightImage(getResources().getDrawable(resId), R.id.id_right_img3);
        } else {
            ivRight3.setImageResource(resId);
        }
        ivRight3.setVisibility(View.VISIBLE);
        return ivRight3;
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage() {
        return ivRight;
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public String getRightText() {
        if (tvRight != null) {

            return tvRight.getText().toString();
        }
        return "";
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public TextView getRightTextView() {
        return tvRight;
    }

    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImagSrc(int id) {
        ivBack.setVisibility(VISIBLE);
        ivBack.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0);

    }

    /**
     * 左文字
     *
     * @param str
     */
    public void setLeftTitle(String str) {
        ivBack.setText(str);
        setLeftImagSrc(0);
    }

    /**
     * 右标题
     */
    public void setRightTitleVisibility(boolean visible) {
        if (tvRight == null) {
            createRightText();
        }
        tvRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    public TextView setRightTitle(String text) {
        if (tvRight == null) {
            createRightText();
        }
        tvRight.setText(text);
        setRightTitleVisibility(true);
        return tvRight;
    }

    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    public void setOnRightImagListener(OnClickListener listener) {
        if (ivRight == null) return;
        ivRight.setOnClickListener(listener);
    }

    public void setOnRightImag2Listener(OnClickListener listener) {
        if (ivRight2 == null) return;
        ivRight2.setOnClickListener(listener);
    }

    public void setOnRightImag3Listener(OnClickListener listener) {
        if (ivRight3 == null) return;
        ivRight3.setOnClickListener(listener);
    }

    public void setOnRightTextListener(OnClickListener listener) {
        if (tvRight == null) return;
        tvRight.setOnClickListener(listener);
    }

    /**
     * 标题背景颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        rlCommonTitle.setBackgroundColor(getResources().getColor(color));
    }

    public Drawable getBackGroundDrawable() {
        return rlCommonTitle.getBackground();
    }

    public void setTvRightTextColor(int color) {
        tvRight.setTextColor(color);
    }

//    public void setCenterView(View view) {
//        setCenterView(view, view.getResources().getDimensionPixelSize(R.dimen.tablayout_width_160));
//    }

    public void setCenterView(View view, int width) {
        tvTitle.setVisibility(GONE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(view, params);
    }

    public void setLeftView(View view) {
//        ivBack.setVisibility(GONE);
//        int width = getResources().getDimensionPixelSize(R.dimen.titlebar_play);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, width);
//        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
//        addView(view, params);
    }

    public void setTitleOnClickListener(OnClickListener l) {
        if (l == null) return;
        tvTitle.setOnClickListener(l);
    }

    public void setRightToggle() {
        String rightText = tvRight.getText().toString();
        if ("编辑".equals(rightText)) {
            tvRight.setText("取消");
        } else if ("取消".equals(rightText)) {
            tvRight.setText("编辑");
        }
    }


    public void setStyle(int style) {
        switch (style) {
            case 2:
                setBackGroundColor(R.color.transparent);
                tvTitle.setTextColor(getResources().getColor(R.color.white));
                tvRight.setTextColor(getResources().getColor(R.color.white));
                setLeftImagSrc(R.mipmap.back_white);
                break;

        }
    }

    public ImageView getIvRight(){
        return ivRight;
    }
    public ImageView getIvRight2(){
        return ivRight2;
    }
    public ImageView getIvRight3(){
        return ivRight3;
    }
}
