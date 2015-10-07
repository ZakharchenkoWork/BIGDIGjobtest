package com.znshadows.bigdigjobtest;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private final ArrayList<ClipData> objects;
    private Activity activity;

    public CustomListAdapter(Activity activity, LayoutInflater inflater,  ArrayList<ClipData> objects) {
        this.inflater = inflater;
        this.objects = objects;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item, parent, false);
        }

        ClipData data = getProduct(position); //get appropriate data for each list item

    if(data != null)
    {
        TextView clipName = (TextView) view.findViewById(R.id.clipName);
        TextView singerName = (TextView) view.findViewById(R.id.singerName);
        TextView viewCount = (TextView) view.findViewById(R.id.viewCount);
        LinearLayout picture = (LinearLayout) view.findViewById(R.id.picture);

        //setting fonts
        Typeface robotoBold = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Bold.ttf");
        clipName.setTypeface(robotoBold);
        Typeface robotoLight = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        singerName.setTypeface(robotoLight);
        Typeface robotoRegular = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");
        clipName.setTypeface(robotoRegular);

        //setting picture
        picture.setBackground(new BitmapDrawable(view.getResources(), data.getPicture()));

        //setting text data
        clipName.setText("Title: " + data.getClipName());
        singerName.setText("Author: " + data.getSingerName());
        viewCount.setText("View: " + data.getViewCount());

    }
        return view;
    }

    ClipData getProduct(int position) {
        return ((ClipData) getItem(position));
    }

}