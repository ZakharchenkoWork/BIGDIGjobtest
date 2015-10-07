package com.znshadows.bigdigjobtest;

import android.graphics.Bitmap;

/**
 * Created by MisterY on 05.10.2015.
 */
public class ClipData {
    /*- картинка
    - название клипа
    - имя исполнителя
    - количество просмотров*/

    private Bitmap picture = null;
    private String clipName;
    private String singerName;
    private int viewCount;


    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getClipName() {
        return clipName;
    }

    public void setClipName(String clipName) {
        this.clipName = clipName;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }


}
