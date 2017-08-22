package com.example.faustin_12.ncdev.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;

import static java.security.AccessController.getContext;

/**
 * Created by FAUSTIN-12 on 07/04/2017.
 */

public class DisplayNotification implements Runnable {
    Context mContext;
    NotificationManager mNotificationManager;
    int NOTIFICATION_ID = 001;
    String nTitle="Notification Title";
    String nDescription ="Sample Notification Content";
    String nInfo = "Sample Notification info";
    String nTime = "Sample Notification time";
    String nTickerM = "Sample Notification Ticker Message";
    Bitmap nIcon ;

    public DisplayNotification(Context mContext, String nTitle, String nDescription,
                               String nInfo, String nTime, String nTickerM) {
        this.mContext = mContext;
        this.nTitle=nTitle;
        this.nDescription=nDescription;
        this.nInfo=nInfo;
        this.nTime=nTime;
        this.nTickerM=nTickerM;
        mNotificationManager = (NotificationManager)
                mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.images3);
    }

    @Override
    public void run() {
        makeNotification(mContext);
    }

    private void makeNotification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(nTitle)
                .setContentText(nInfo)
                .setTicker(nTickerM)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(nDescription));
        Notification n;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            n = builder.build();
        } else {
            n = builder.getNotification();
        }

        mNotificationManager.notify(NOTIFICATION_ID, n);
    }

    public void setnDescription (String description){
        if(this.nDescription.compareTo("Sample Notification Content") == 0){
            this.nDescription = description;
        }else {
            this.nDescription+= "\n" +description;
        }
    }

    public void setnTickerM (String tickerM){
        this.nTickerM=tickerM;
    }

    public void setnInfo (String info){
        this.nInfo=info;
    }

    public void setnTime (String time){
        this.nTime=time;
    }
}
