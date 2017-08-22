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
import com.example.faustin_12.ncdev.adapter.TabsAdapter4;

/**
 * Created by Arding Vaness on 28/06/2016.
 */
public class AccueilBoitesetsnacksFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public int tabId=0;
    public TabsAdapter4 adapter ;

    public static int int_items = 2 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        adapter = new TabsAdapter4(getChildFragmentManager());
        /**
         *Set an Apater for the View Pager
         */
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

    public void setTabId(int tabid){
        this.tabId=tabid;
    }

}
