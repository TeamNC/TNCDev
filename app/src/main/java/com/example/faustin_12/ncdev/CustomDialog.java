package com.example.faustin_12.ncdev;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.faustin_12.ncdev.notification.DisplayCustomNotification;
import com.example.faustin_12.ncdev.notification.DisplayNotification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by FAUSTIN-12 on 02/04/2017.
 */

public class CustomDialog extends Dialog implements View.OnClickListener {
    //Test For Notification
    String nTitle="Notification Title";
    String nDescription ="Sample Notification Content";
    String nInfo = "Sample Notification info";
    String nTime = "Sample Notification time";
    String nTickerM = "Sample Notification Ticker Message";
    int nbGallery=0;
    int nbPhoto=0;

    ImageButton gallery, photo, video, localisation;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        gallery = (ImageButton) findViewById(R.id.gallery_img_btn);
        photo = (ImageButton) findViewById(R.id.photo_img_btn);
        video = (ImageButton) findViewById(R.id.video_img_btn);
        localisation = (ImageButton) findViewById(R.id.localisation_img_btn);
        gallery.setOnClickListener(this);
        photo.setOnClickListener(this);
        video.setOnClickListener(this);
        localisation.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gallery_img_btn:
                Toast.makeText(getContext(), "Tu as sélectionné : Gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.photo_img_btn:
                Toast.makeText(getContext(), "Tu as sélectionné : Photo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_img_btn:
                Toast.makeText(getContext(), "Tu as sélectionné : Video", Toast.LENGTH_SHORT).show();
                break;
            case R.id.localisation_img_btn:
                Toast.makeText(getContext(), "Tu as sélectionné : Localisation", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        dismiss();
    }
}
