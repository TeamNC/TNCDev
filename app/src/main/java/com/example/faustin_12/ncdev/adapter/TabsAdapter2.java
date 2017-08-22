package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.fragment.EightFragment;
import com.example.faustin_12.ncdev.activity.fragment.ElevenFragment;
import com.example.faustin_12.ncdev.activity.fragment.FiveFragment;
import com.example.faustin_12.ncdev.activity.fragment.FourFragment;
import com.example.faustin_12.ncdev.activity.fragment.FourteenFragment;
import com.example.faustin_12.ncdev.activity.fragment.NineFragment;
import com.example.faustin_12.ncdev.activity.fragment.OneFragment;
import com.example.faustin_12.ncdev.activity.fragment.SevenFragment;
import com.example.faustin_12.ncdev.activity.fragment.SixFragment;
import com.example.faustin_12.ncdev.activity.fragment.TenFragment;
import com.example.faustin_12.ncdev.activity.fragment.ThirteenFragment;
import com.example.faustin_12.ncdev.activity.fragment.ThreeFragment;
import com.example.faustin_12.ncdev.activity.fragment.TwelveFragment;
import com.example.faustin_12.ncdev.activity.fragment.TwoFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by LIONEL KOUEMENI on 04/10/2016.
 */
public class TabsAdapter2 extends FragmentStatePagerAdapter{
    public static int int_items = 14 ;
    private String categorie = "To be Init";
    private static Context context;
    private static String[] tabTitles=new String[]{"Dim", "Lun","Mar","Mer","Jeu","Ven","Sam"};
    private String tabSubTitles[]=new String[]{"1","2","3","4","5","6","7"};

    public TabsAdapter2(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public void setCategorie (String categorie){this.categorie=categorie;}

    /**
     * Return fragment with respect to Position .
     */

    public OneFragment getOneFragment(Bundle bundle){
        OneFragment oneFragment = new OneFragment();
        oneFragment.setArguments(bundle);
        return oneFragment;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("categorie",  categorie);
        switch (position){
            case 0 :
                return getOneFragment(bundle);
            case 1 :
                return getOneFragment(bundle);
            case 2 :
                return getOneFragment(bundle);
            case 3 :
                return getOneFragment(bundle);
            case 4 :
                return getOneFragment(bundle);
            case 5 :
                return getOneFragment(bundle);
            case 6 :
                return getOneFragment(bundle);
            case 7 :
                return getOneFragment(bundle);
            case 8 :
                return getOneFragment(bundle);
            case 9 :
                return getOneFragment(bundle);
            case 10 :
                return getOneFragment(bundle);
            case 11:
                return getOneFragment(bundle);
            case 12:
                return getOneFragment(bundle);
            case 13:
                return getOneFragment(bundle);
        }
        return null;
    }

    @Override
    public int getCount() {

        return int_items;
    }
    /**
     * This method returns the name of the tab according to the position.
     */


    //public  CharSequence getPageTitle(int position) {

      // return tabTitles[position];


    //}

            public static View getTabView(int position)
            {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, position - 10);
                SimpleDateFormat df = new SimpleDateFormat("EEE");
                View v = LayoutInflater.from(context).inflate(R.layout.date_filter,null);
                TextView tv1=(TextView)v.findViewById(R.id.days);

                int day = c.get(Calendar.DAY_OF_MONTH);
                int day2 = c.get(Calendar.DAY_OF_WEEK) -1;

                tv1.setText(tabTitles[day2]);
                TextView tv2=(TextView)v.findViewById(R.id.numbers);
                tv2.setText("" + day);

                return v;
            }

}


