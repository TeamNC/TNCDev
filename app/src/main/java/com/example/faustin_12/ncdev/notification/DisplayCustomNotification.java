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
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;

import static java.security.AccessController.getContext;

/**
 * Created by FAUSTIN-12 on 07/04/2017.
 */

public class DisplayCustomNotification implements Runnable {

    Context mContext;
    NotificationManager mNotificationManager;
    int NOTIFICATION_ID = 002;
    int counter = 0;
    String nTitle="Notification Title";
    String nDescription ="Sample Notification Content";
    String nInfo = "Sample Notification info";
    String nTime = "Sample Notification time";
    String nTickerM = "Sample Notification Ticker Message";
    Bitmap nIcon ;


    public DisplayCustomNotification(Context mContext, String nTitle, String nDescription,
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

        Notification n;
        this.counter+=1;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            n = buildNotification(mContext).build();
        } else {
            n = buildNotification(mContext).getNotification();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            n.bigContentView = getComplexNotificationView(mContext);
        }
        mNotificationManager.notify(NOTIFICATION_ID, n);
    }

    protected NotificationCompat.Builder buildNotification(Context context) {
        // Open NotificationView.java Activity
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );

        PendingIntent pIntent = PendingIntent.getActivity(context,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(nTickerM)
                .setAutoCancel(true)
                .setContentIntent(pIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // build a complex notification, with buttons and such
            //
            builder = builder.setContent(getComplexNotificationView(context));
        } else {
            // Build a simpler notification, without buttons
            //
            builder = builder.setContentTitle(nTitle)
                    .setContentText(nDescription)
                    .setSmallIcon(R.mipmap.ic_launcher);
        }
        return builder;
    }

    private RemoteViews getComplexNotificationView(Context context) {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews notificationView = new RemoteViews(
                context.getPackageName(),
                R.layout.notification_layout
                );
        // Locate and set the Image into notification_layout.xml ImageViews
        notificationView.setImageViewResource(
                R.id.imagenotileft,
                R.mipmap.ic_launcher);

        // Locate and set the Text into notification_layout.xml TextViews
        notificationView.setTextViewText(R.id.notiTitle, nTitle);
        notificationView.setTextViewText(R.id.notiDescription, nDescription);
        notificationView.setTextViewText(R.id.notiInfo, counter + " Events");
        notificationView.setTextViewText(R.id.notiTime, nTime);

        return notificationView;
    }

    public void setnDescription (String description){
        if(this.nDescription.compareTo("Sample Notification Content") == 0){
            this.nDescription = description;
        }else {
            this.nDescription= description + "\n" + this.nDescription;
        }
    }

    public void setnTickerM (String tickerM){
        this.nTickerM=tickerM;
    }

    public void setnInfo (String info){
        //this.nInfo=info;
    }

    public void setnTime (String time){
        this.nTime=time;
    }
}
