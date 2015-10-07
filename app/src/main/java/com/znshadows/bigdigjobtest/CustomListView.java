package com.znshadows.bigdigjobtest;

import android.content.Context;
import android.util.AttributeSet;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by MisterY on 07.10.2015.
 */
public class CustomListView extends ListView {


    private OnScrollListener onScrollListener;
    private OnDetectScrollListener onDetectScrollListener;


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
                //for every visible List Item

                for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount-1; i++) {
                    View view = absListView.getChildAt(i);

                    int top = (view == null) ? 0 : view.getTop();  //IDKWITBIW

                    if (firstVisibleItem == oldFirstVisibleItem) {
                        if (top > oldTop) {
                            onDetectScrollListener.onUpScrolling(view);
                        } else if (top < oldTop) {
                            onDetectScrollListener.onDownScrolling(view);
                        }
                    } else {
                        if (firstVisibleItem < oldFirstVisibleItem) {
                            onDetectScrollListener.onUpScrolling(view);
                        } else {
                            onDetectScrollListener.onDownScrolling(view);
                        }
                    }

                    oldTop = top;
                    oldFirstVisibleItem = firstVisibleItem;
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

        void onUpScrolling(View view);

        void onDownScrolling(View view);
    }

}