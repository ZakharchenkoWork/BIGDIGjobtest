package com.znshadows.bigdigjobtest;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by MisterY on 07.10.2015.
 */
public class OnDetectScrollListener implements CustomListView.OnDetectScrollListener {
    @Override
    public void onUpScrolling(View view) {
        if(view != null) {
            LinearLayout l = (LinearLayout) view.findViewById(R.id.picture);
            l.setY(l.getY() - 0.4f);//scrolls picture inside List Item
        }

    }

    @Override
    public void onDownScrolling(View view) {
if(view != null) {
    LinearLayout l = (LinearLayout) view.findViewById(R.id.picture);
    l.setY(l.getY() + 0.4f);//scrolls picture inside List Item
}


    }
}
