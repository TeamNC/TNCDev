package com.example.faustin_12.ncdev.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faustin_12.ncdev.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BlankActivity extends AppCompatActivity {

    private String transitionName = "dialogimage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        //supportPostponeEnterTransition();

        ImageView imageView = (ImageView) findViewById(R.id.dialogimage);
        ImageView room = (ImageView) findViewById(R.id.room);

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(transitionName);
        }

        Picasso.with(this)
                .load("https://cdn.pixabay.com/photo/2016/03/31/19/51/acceptation-1295324_960_720.png")
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                        Toast.makeText(getApplicationContext(), "Faillure", Toast.LENGTH_SHORT).show();
                    }
                });*/
        Glide.with(getApplicationContext())
                .load("https://cdn.pixabay.com/photo/2016/03/31/19/51/acceptation-1295324_960_720.pngaa")
                .placeholder(R.drawable.placeholder).centerCrop().into(imageView);
    }

    public void setTransitionName (String tName){this.transitionName = tName;}


}
