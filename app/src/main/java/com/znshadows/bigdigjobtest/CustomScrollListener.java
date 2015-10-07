package com.znshadows.bigdigjobtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.LinearLayout;

/**
 * Created by MisterY on 07.10.2015.
 */
public class CustomScrollListener implements AbsListView.OnScrollListener {

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //scale little bit all the pictures,  so soner we could scroll them
        for (int i = 0; i < totalItemCount; i ++) {
            View child = view.getChildAt(i);
            if(child != null) {
                LinearLayout l = (LinearLayout)child.findViewById(R.id.picture);
                l.setScaleX(1.4f);//scale must be equal on X and Y
                l.setScaleY(1.4f);//but we will scroll only through Y

            }
        }
    }

}
