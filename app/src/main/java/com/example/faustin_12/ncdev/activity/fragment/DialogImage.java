package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.faustin_12.ncdev.R;

/**
 * Created by FAUSTIN-12 on 03/12/2017.
 */

public class DialogImage extends DialogFragment {
    private String transitionName = "dialogimage";
    public ImageView imageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            setSharedElementEnterTransition(TransitionInflater.from(getContext())
                    .inflateTransition(android.R.transition.move));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.dialog_image, null);

        imageView = (ImageView) x.findViewById(R.id.dialogimage);
        ViewCompat.setTransitionName(imageView, transitionName);



        return x;
    }
    public void setTransitionName (String tName){this.transitionName = tName;}
}
