package com.znshadows.bigdigjobtest;

/**
 * Created by MisterY on 17.09.2015.
 */

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BigDigConectionService extends AsyncTask<Void, Void, String> {

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";
    private final String dataSource =  "http://ellotv.bigdig.com.ua/api/home/video";   //link provided..
    private ArrayList<ClipData> clips = new ArrayList<ClipData>(10);
    /**
     *  Method for retriving information
     * @return Array of Objects with data downloaded
     * @throws Exception if ArrayList is empty
     */
    public ArrayList<ClipData> getClips() throws Exception{
        if(clips == null)
        {throw new Exception("Error: no data about clips");}

        return clips;
    }



    @Override
    protected String doInBackground(Void... params) {

        try {
            //Preparing URL
            URL url = new URL(dataSource);

            //connecting to Server
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            //reading JSON
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resultJson = buffer.toString();
            urlConnection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("test", resultJson);
        return resultJson;
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);
        // выводим целиком полученную json-строку
        //parcing JSON
        JSONObject mainJsonObj = null;
        String secondName = "";

        try {

            mainJsonObj = new JSONObject(strJson);
            JSONObject dataJsonObj = mainJsonObj.getJSONObject("data");
            JSONArray items = dataJsonObj.getJSONArray("items");

            for(int i = 0; i < items.length(); i++)
            {
                ClipData clipData = new ClipData();
                JSONObject info = items.getJSONObject(i);

                clipData.setClipName(info.getString("title"));
                clipData.setViewCount(info.getInt("view_count"));

                try {//One of the Artist don't have a name
                    JSONArray artistsArray = info.getJSONArray("artists");
                    JSONObject artistsObj = artistsArray.getJSONObject(0);
                    clipData.setSingerName(artistsObj.getString("name"));
                } catch (JSONException e) {//So it's a little bit cheating, but I don't have time to handle everything else
                    clipData.setSingerName("N/a");//And we just put Not Available, so f*** off ;-)
                }

                //Loading Picture
                PictureLoader loadPicture = new PictureLoader(clipData, info.getString("picture"));
                loadPicture.execute();

                //each object goes to the ArrayList
                clips.add(clipData);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}