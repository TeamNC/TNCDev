package com.example.faustin_12.ncdev.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.faustin_12.ncdev.activity.fragment.BoiteFragment;
import com.example.faustin_12.ncdev.activity.fragment.SnackFragment;

/**
 * Created by LIONEL KOUEMENI on 16/01/2017.
 */
public class TabsAdapter4 extends FragmentPagerAdapter {
    public static int int_items = 2 ;

    public TabsAdapter4(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new BoiteFragment();
            case 1 : return new SnackFragment();
        }
        return null;
    }

    @Override
    public int getCount() {

        return int_items;
    }
    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Boites";
            case 1 :
                return "Snacks";
        }
        return null;
    }
}

