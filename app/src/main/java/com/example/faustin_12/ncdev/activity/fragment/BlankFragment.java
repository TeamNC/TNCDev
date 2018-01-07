package com.example.faustin_12.ncdev.activity.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faustin_12.ncdev.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class BlankFragment extends Fragment {
    private String transitionName = "dialogimage";
    public ImageView imageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //postponeEnterTransition();
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            setSharedElementEnterTransition(TransitionInflater.from(getContext())
                    .inflateTransition(android.R.transition.move));
        }*/

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_blank, null);

        imageView = (ImageView) x.findViewById(R.id.dialogimage);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(transitionName);
        }
        //ViewCompat.setTransitionName(imageView, transitionName);


        Toast.makeText(getContext(), "End " + transitionName, Toast.LENGTH_SHORT).show();

        Picasso.with(getContext())
                .load("https://cdn.pixabay.com/photo/2016/03/31/19/51/acceptation-1295324_960_720.png")
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        startPostponedEnterTransition();
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        startPostponedEnterTransition();
                        Toast.makeText(getContext(), "Faillure", Toast.LENGTH_SHORT).show();
                    }
                });*/

        Glide.with(getContext())
                .load("https://cdn.pixabay.com/photo/2016/03/31/19/51/acceptation-1295324_960_720.png")
                .placeholder(R.drawable.placeholder).centerCrop().into(imageView);

        /*imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DialogImage dialogImage = new DialogImage();
                dialogImage.setTransitionName(transitionName);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialogimage");
                if(prev != null){
                    fragmentTransaction.remove(prev);
                }

                fragmentTransaction.addSharedElement(imageView, transitionName)
                        .addToBackStack(null);
                dialogImage.show(fragmentTransaction, "dialogimage");
            }
        });*/


        return x;
    }

    public void setTransitionName (String tName){this.transitionName = tName;}

}
