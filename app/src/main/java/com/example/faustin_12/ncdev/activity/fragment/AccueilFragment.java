package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.TabsAdapter;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class AccueilFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public int tabId=0;
    public int check;
    public TabsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        adapter = new TabsAdapter(getChildFragmentManager());
        /**
         *Set an Apater for the View Pager
         */
        //mdata = evenementFragment.getData();

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(tabId);
        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;
    }

    /*public void onPause(){
        super.onPause();
        setRetainInstance(true);
        Toast.makeText(getContext(), "On Pause"+check, Toast.LENGTH_SHORT).show();
    }*/

    /*public void onResume(){
        super.onResume();
        Toast.makeText(getContext(), "On Resume"+ check, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "On Resume aft"+ check, Toast.LENGTH_SHORT).show();
    }*/

   /* public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int position = viewPager.getCurrentItem();
        outState.putInt("key", position );
        Toast.makeText(getContext(), "Sauvegarde" +outState.getInt("key", 0), Toast.LENGTH_SHORT).show();
    }*/

    public void setTabId(int tabid){
        this.tabId=tabid;
    }
}

