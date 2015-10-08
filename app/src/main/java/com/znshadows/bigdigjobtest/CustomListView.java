package com.znshadows.bigdigjobtest;

import android.content.Context;
import android.util.AttributeSet;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by MisterY on 07.10.2015.
 */
public class CustomListView extends ListView {


    private OnScrollListener onScrollListener;
    private OnDetectScrollListener onDetectScrollListener;
    private float oldY;
    private long oldTime;

    public CustomListView(Context context) {
        super(context);
        onCreate(context, null, null);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate(context, attrs, null);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onCreate(context, attrs, defStyle);
    }
    //Please don't ask ;-)
    @SuppressWarnings("UnusedParameters")
    private void onCreate(Context context, AttributeSet attrs, Integer defStyle) {
        setListeners();
    }

    private void setListeners() {
        super.setOnScrollListener(new CustomScrollListener() {

            private int oldTop;
            private int oldFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(view, scrollState);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (onScrollListener != null) {
                    onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }

                if (onDetectScrollListener != null) {
                    onDetectedListScroll(view, firstVisibleItem, visibleItemCount);
                }
            }

            private void onDetectedListScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount) {

                for (int i = 0; i < visibleItemCount; i++) {

                    View view = absListView.getChildAt(i);//for every visible List Item
                    onDetectScrollListener.onScrolling(view);//we will handle scroll
                }

            }
        });
    }



    @Override
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setOnDetectScrollListener(OnDetectScrollListener onDetectScrollListener) {
        this.onDetectScrollListener = onDetectScrollListener;
    }


    public interface OnDetectScrollListener { //handles precise scroling

        void onScrolling(View view);

        void setSpeed(float newSpeed);

    }

    int lastAction = MotionEvent.ACTION_UP;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //With first touch it is saving Coordinates
        if(lastAction == MotionEvent.ACTION_UP && event.getAction() == MotionEvent.ACTION_DOWN) {

            oldTime = System.currentTimeMillis();
            oldY = event.getY();
            //start timer

        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {//when screen gets scrolled it takes speed


            long newTime = System.currentTimeMillis();
            float newY = event.getY();

            float speed = (newY-oldY) / (newTime - oldTime);
            oldY = newY;
            oldTime = newTime;
            onDetectScrollListener.setSpeed(speed/5);

        }
        lastAction = event.getAction(); //saving last Action
        return super.onTouchEvent(event);
    }
}