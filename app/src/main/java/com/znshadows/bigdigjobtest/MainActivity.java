package com.znshadows.bigdigjobtest;

import android.app.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;


public class MainActivity extends Activity {
    public static CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_multiple_parallax);

        //Preparing Picture Loader
        PictureLoader.setContext(this);

        //Connects to the BigDig, gets data, downloading images etc.
        BigDigConectionService bdConection = new BigDigConectionService();
        bdConection.execute();

        //Creating ListView with parallax effect
        CustomListView listView = (CustomListView) findViewById(R.id.list_view);

        try {

            adapter = new CustomListAdapter(this, LayoutInflater.from(this), bdConection.getClips()); //ListView will get the information downloaded
            listView.setOnDetectScrollListener(new OnDetectScrollListener()); //for proper work of Parallax effect
            listView.setOnScrollListener(new CustomScrollListener()); //for proper work of Parallax effect
            listView.setAdapter(adapter);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
