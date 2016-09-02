package com.example.faustin_12.ncdev.activity.fragment;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public  class DetailFragment extends Fragment {
    public String title= "Detail";
    public FragmentManager mFragmentManager ;
    DrawerLayout mDrawerLayout;
    private Context context;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View x = inflater.inflate(R.layout.fragment_detail, null);
        TextView tv= (TextView)x.findViewById(R.id.textDetail);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) x.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("My Toolbar Tittle");
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));

        Toolbar toolbarD = (Toolbar) x.findViewById(R.id.toolbarD);
        toolbarD.setNavigationIcon(R.drawable.ic_action_back);
        toolbarD.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Retour", Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) getActivity()).onBackPressed();
            }
        });

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        title = tv.getText().toString();

        //tv.setText(title);
        //tv.setTextSize(25);
        mFragmentManager = getActivity().getSupportFragmentManager();

        return x;
    }

    public void setTitle (String title){
        this.title = title + "    " + this.title;
    }

}
