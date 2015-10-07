package com.znshadows.bigdigjobtest;

/**
 * Created by MisterY on 17.09.2015.
 */

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.net.URL;

public class PictureLoader extends AsyncTask<Void, Void, String> {


    private String resultJson = "";
    private String dataSource = "";
    private ClipData output = null; //Here will be stored downloaded picture

    static private Activity ctx = null;


    public static void setContext(Activity context) {
        ctx = context;
    }


    public PictureLoader(ClipData output, String source)
    {
        this.output = output;
        dataSource = source;
    }


    @Override
    protected String doInBackground(Void... params) {
        // получаем данные с внешнего ресурса


            try {
                URL newurl = new URL(dataSource);
                output.setPicture(BitmapFactory.decodeStream(newurl.openConnection().getInputStream()));//loading picture directly to the object
                Log.d("test", "" + output.getPicture());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultJson;
    }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            try {
                MainActivity.adapter.notifyDataSetChanged(); //updating ListView, when image is downloaded
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
