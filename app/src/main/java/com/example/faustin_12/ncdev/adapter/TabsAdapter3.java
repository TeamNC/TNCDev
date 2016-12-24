package com.example.faustin_12.ncdev.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.faustin_12.ncdev.activity.fragment.ActualiteFragment;
import com.example.faustin_12.ncdev.activity.fragment.EvenementFragment;
import com.example.faustin_12.ncdev.activity.fragment.FavorisFragment2;
import com.example.faustin_12.ncdev.activity.fragment.LiveFragment;
import com.example.faustin_12.ncdev.activity.fragment.OffreSpecialeFragment;

/**
 * Created by LIONEL KOUEMENI on 23/12/2016.
 */
public class TabsAdapter3 extends FragmentStatePagerAdapter {
    public static int int_items = 2 ;

    public TabsAdapter3(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new OffreSpecialeFragment();
            case 1 : return new FavorisFragment2();
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
                return "OffresSpeciales";
            case 1 :
                return "Favoris";
        }
        return null;
    }
}
