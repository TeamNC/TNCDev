package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.fragment.ActualiteFragment;
import com.example.faustin_12.ncdev.activity.fragment.CategoriesFragment;
import com.example.faustin_12.ncdev.activity.fragment.EightFragment;
import com.example.faustin_12.ncdev.activity.fragment.ElevenFragment;
import com.example.faustin_12.ncdev.activity.fragment.EvenementFragment;
import com.example.faustin_12.ncdev.activity.fragment.FifteenFragment;
import com.example.faustin_12.ncdev.activity.fragment.FiveFragment;
import com.example.faustin_12.ncdev.activity.fragment.FourFragment;
import com.example.faustin_12.ncdev.activity.fragment.FourteenFragment;
import com.example.faustin_12.ncdev.activity.fragment.LiveFragment;
import com.example.faustin_12.ncdev.activity.fragment.NineFragment;
import com.example.faustin_12.ncdev.activity.fragment.OneFragment;
import com.example.faustin_12.ncdev.activity.fragment.SevenFragment;
import com.example.faustin_12.ncdev.activity.fragment.SeventeenFragment;
import com.example.faustin_12.ncdev.activity.fragment.SixFragment;
import com.example.faustin_12.ncdev.activity.fragment.SixteenFragment;
import com.example.faustin_12.ncdev.activity.fragment.TenFragment;
import com.example.faustin_12.ncdev.activity.fragment.ThirteenFragment;
import com.example.faustin_12.ncdev.activity.fragment.ThreeFragment;
import com.example.faustin_12.ncdev.activity.fragment.TwelveFragment;
import com.example.faustin_12.ncdev.activity.fragment.TwoFragment;

/**
 * Created by LIONEL KOUEMENI on 04/10/2016.
 */
public class TabsAdapter2 extends FragmentStatePagerAdapter{
    public static int int_items = 30 ;
    private static Context context;
    private static String[] tabTitles=new String[]{"Lun","Mar","Mer","Jeu","Ven","Sam","Dim"};
    private String tabSubTitles[]=new String[]{"1","2","3","4","5","6","7"};
    public TabsAdapter2(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new OneFragment();
            case 1 : return new TwoFragment();
            case 2 : return new ThreeFragment();
            case 3 : return new FourFragment();
            case 4 : return new FiveFragment();
            case 5 : return new SixFragment();
            case 6 : return new SevenFragment();
            case 7 : return new EightFragment();
            case 8 : return new NineFragment();
            case 9 : return new TenFragment();
            case 10 : return new ElevenFragment();
            case 11: return new TwelveFragment();
            case 12: return new ThirteenFragment();
            case 13: return new FourteenFragment();
            case 14: return new FifteenFragment();
            case 15: return new SixteenFragment();
            case 16: return new SeventeenFragment();
            //case 3 : return new SnackFragment();
        }
        if (position>=17)
        {
            return new SeventeenFragment();
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


    //public  CharSequence getPageTitle(int position) {

      // return tabTitles[position];


    //}

            public static View getTabView(int position)
            {
                View v = LayoutInflater.from(context).inflate(R.layout.date_filter,null);
                TextView tv1=(TextView)v.findViewById(R.id.days);
                int day = position%7;
                tv1.setText(tabTitles[day]);
                TextView tv2=(TextView)v.findViewById(R.id.numbers);
                tv2.setText(""+position);

                return v;
            }

}


