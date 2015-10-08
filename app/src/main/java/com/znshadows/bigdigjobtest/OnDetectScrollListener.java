package com.znshadows.bigdigjobtest;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by MisterY on 07.10.2015.
 */
public class OnDetectScrollListener implements CustomListView.OnDetectScrollListener {
    private float speed;
    long timer = 0;
    @Override
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    public float getSpeed() {
        if(timer <= 0) //start timer
        {
            timer = System.currentTimeMillis();
        }
        else //it must stop pictures, when user is not touching the screen anymore
        {


            long newTimer = System.currentTimeMillis();
            long deltaTime = timer - newTimer;

            if (speed > 0) { // if user was scrolling up  speed is positive

                float newSpeed = speed - speed * deltaTime*1000; // then we reduce speed

                if(newSpeed < 0 )//prevents from scrolling back down
                {
                    speed = 0;

                }
                else {
                    speed = newSpeed;
                }
            }
            else// if user was scrolling down speed will be negative
            {
                float newSpeed = speed + speed * deltaTime*1000; //then we increase speed

                if(newSpeed > 0 ) //prevents from scrolling back up
                {
                    speed = 0;

                }
                else {
                    speed = newSpeed;
                }
            }
            timer = newTimer;//rest timer
        }
        return speed;
    }

    @Override
    public void onScrolling(View view) {

        if(view != null) {
            LinearLayout l = (LinearLayout) view.findViewById(R.id.picture);
            l.setY(l.getY() - speed);//scrolls picture inside List Item

        }

    }

}
