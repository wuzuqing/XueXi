package com.wyxx.android.xuexi.common.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.wyxx.android.xuexi.common.R;
import com.wyxx.android.xuexi.common.utils.DisplayUtil;


/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/3/14 11:34
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/3/14$
 * @updateDes ${TODO}
 */

public class RoundImageView extends AppCompatImageView {

    private float radius,stokeWidth;
    private float topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;
    private int borderColor = Color.WHITE;
    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        int defaultValue = DisplayUtil.dip2px(getResources(), 6);
        if (array.hasValue(R.styleable.RoundImageView_riv_radius)) {
            radius = topLeftRadius = topRightRadius = bottomLeftRadius = bottomRightRadius = array.getDimension(R.styleable.RoundImageView_riv_radius, defaultValue);
        }
       if (array.hasValue(R.styleable.RoundImageView_riv_top_left_radius)){
            topLeftRadius = array.getDimension(R.styleable.RoundImageView_riv_top_left_radius,defaultValue);
       }
       if (array.hasValue(R.styleable.RoundImageView_riv_top_right_radius)){
            topRightRadius = array.getDimension(R.styleable.RoundImageView_riv_top_right_radius,defaultValue);
       }
       if (array.hasValue(R.styleable.RoundImageView_riv_bottom_left_radius)){
            bottomLeftRadius = array.getDimension(R.styleable.RoundImageView_riv_bottom_left_radius,defaultValue);
       }
       if (array.hasValue(R.styleable.RoundImageView_riv_bottom_right_radius)){
            bottomRightRadius = array.getDimension(R.styleable.RoundImageView_riv_bottom_right_radius,defaultValue);
       }
       if (array.hasValue(R.styleable.RoundImageView_riv_width)){
           stokeWidth = array.getDimension(R.styleable.RoundImageView_riv_width,0);
       }
       if (array.hasValue(R.styleable.RoundImageView_riv_border_color)){
            borderColor = array.getColor(R.styleable.RoundImageView_riv_border_color, borderColor);
       }
        array.recycle();
        mRoundRectF = new RectF();
        mRoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRoundPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    private RectF mRoundRectF;
    private Paint mRoundPaint;
    private Path mPath;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (check()) {
            mRoundRectF.right = w;
            mRoundRectF.bottom = h;
        }
    }

    private boolean check() {
        return radius > 0 || topLeftRadius > 0 || topRightRadius > 0 || bottomLeftRadius > 0 || bottomRightRadius > 0;
    }

    private float[] getBorder(float topLeftRadiu, float topRightRadiu, float bottomLeftRadiu, float bottomRightRadiu) {
        return new float[]{topLeftRadiu, topLeftRadiu, topRightRadiu, topRightRadiu, bottomLeftRadiu, bottomLeftRadiu, bottomRightRadiu, bottomRightRadiu};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (check()) {
            mRoundPaint.setStrokeWidth(stokeWidth);
            mRoundPaint.setColor(borderColor);
            mPath.reset();
            canvas.drawRect(mRoundRectF,mRoundPaint);
            mPath.addRoundRect(mRoundRectF, getBorder(topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius), Path.Direction.CCW);
            canvas.drawPath(mPath, mRoundPaint);
        }
    }

    public void setBorderColor(int borderColor) {
        if (this.borderColor == borderColor) return;
        this.borderColor = borderColor;
        invalidate();
    }

    public void setRadius(float radius) {
        if (this.radius == radius) return;
        this.radius = radius;
        invalidate();
    }

    public void setTopLeftRadius(float topLeftRadius) {
        if (this.topLeftRadius == topLeftRadius) return;
        this.topLeftRadius = topLeftRadius;
        invalidate();
    }

    public void setTopRightRadius(float topRightRadius) {
        if (this.topRightRadius == topRightRadius) return;
        this.topRightRadius = topRightRadius;
        invalidate();
    }

    public void setBottomLeftRadius(float bottomLeftRadius) {
        if (this.bottomLeftRadius == bottomLeftRadius) return;
        this.bottomLeftRadius = bottomLeftRadius;
        invalidate();
    }

    public void setBottomRightRadius(float bottomRightRadius) {
        if (this.bottomRightRadius == bottomRightRadius) return;
        this.bottomRightRadius = bottomRightRadius;
        invalidate();
    }
}
