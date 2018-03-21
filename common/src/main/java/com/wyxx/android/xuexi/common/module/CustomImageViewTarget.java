package com.wyxx.android.xuexi.common.module;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/3/17 15:39
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/3/17$
 * @updateDes ${TODO}
 */

public class CustomImageViewTarget extends ImageViewTarget<Bitmap> {

    private int width, height;

    public CustomImageViewTarget(ImageView view) {
        super(view);
    }

    public CustomImageViewTarget(ImageView view, int width, int height) {
        super(view);
        this.width = width;
        this.height = height;
    }

    @Override
    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        super.onResourceReady(bitmap,transition);
    }

    @Override
    protected void setResource(@Nullable Bitmap resource) {
        view.setImageBitmap(resource);
    }

    @Override
    public void getSize(SizeReadyCallback cb) {
        if (width > 0 && height > 0) {
            cb.onSizeReady(width, height);
            return;
        }
        super.getSize(cb);
    }
}
