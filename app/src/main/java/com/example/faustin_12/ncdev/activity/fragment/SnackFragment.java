package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class SnackFragment extends Fragment {
    // Array of integers points to images stored in /res/drawable/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View x = inflater.inflate(R.layout.fragment_fixe, null);
        TextView tv= (TextView)x.findViewById(R.id.textView1);
        tv.setText("Mes Snacks");
        tv.setTextSize(25);
        return x;
    }
}
