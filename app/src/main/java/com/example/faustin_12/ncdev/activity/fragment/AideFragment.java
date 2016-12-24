package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;

/**
 * Created by FAUSTIN-12 on 18/03/2016.
 */
public class AideFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View x = inflater.inflate(R.layout.fragment_fixe, null);
        TextView tv= (TextView)x.findViewById(R.id.textView1);
        tv.setText("Accès à l'aide");
        tv.setTextSize(25);
        return x;
    }
}
