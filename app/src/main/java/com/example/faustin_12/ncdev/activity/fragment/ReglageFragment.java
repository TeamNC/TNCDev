package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;

/**
 * Created by FAUSTIN-12 on 18/03/2016.
 */
public class ReglageFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View x = inflater.inflate(R.layout.fragment_fixe, null);
        TextView tv= (TextView)x.findViewById(R.id.textView1);
        tv.setText("Mes Reglages");
        final EditText server = (EditText)x.findViewById(R.id.edittext1);
        FloatingActionButton validate = (FloatingActionButton)x.findViewById(R.id.serverSet);
        server.setText(((MainActivity) getActivity()).getServerT());
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setServerT(server.getText().toString());
            }
        });
        tv.setTextSize(25);
        return x;
    }
}
