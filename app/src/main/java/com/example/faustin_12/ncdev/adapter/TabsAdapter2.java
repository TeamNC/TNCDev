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
    public String[] date = {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a" };


    public TabsAdapter2(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public void setCategorie (String categorie){this.categorie=categorie;}
    public void setDate(String[] date){this.date = date;}

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("categorie",  categorie);
        switch (position){
            case 0 :
                OneFragment oneFragment = new OneFragment();
                bundle.putString("date",  date[0]);
                oneFragment.setArguments(bundle);
                return oneFragment;
            case 1 :
                TwoFragment twoFragment = new TwoFragment();
                bundle.putString("date",  date[1]);
                twoFragment.setArguments(bundle);
                return twoFragment;
            case 2 :
                ThreeFragment treeFragment = new ThreeFragment();
                bundle.putString("date",  date[2]);
                treeFragment.setArguments(bundle);
                return treeFragment;
            case 3 :
                FourFragment fourFragment = new FourFragment();
                bundle.putString("date",  date[3]);
                fourFragment.setArguments(bundle);
                return fourFragment;
            case 4 :
                FiveFragment fiveFragment = new FiveFragment();
                bundle.putString("date",  date[4]);
                fiveFragment.setArguments(bundle);
                return fiveFragment;
            case 5 :
                SixFragment sixFragment = new SixFragment();
                bundle.putString("date",  date[5]);
                sixFragment.setArguments(bundle);
                return sixFragment;
            case 6 :
                SevenFragment sevenFragment = new SevenFragment();
                bundle.putString("date",  date[6]);
                sevenFragment.setArguments(bundle);
                return sevenFragment;
            case 7 :
                EightFragment eightFragment = new EightFragment();
                bundle.putString("date",  date[7]);
                eightFragment.setArguments(bundle);
                return eightFragment;
            case 8 :
                NineFragment nineFragment = new NineFragment();
                bundle.putString("date",  date[8]);
                nineFragment.setArguments(bundle);
                return nineFragment;
            case 9 :
                TenFragment tenFragment = new TenFragment();
                bundle.putString("date",  date[9]);
                tenFragment.setArguments(bundle);
                return tenFragment;
            case 10 :
                ElevenFragment elevenFragment = new ElevenFragment();
                bundle.putString("date",  date[10]);
                elevenFragment.setArguments(bundle);
                return elevenFragment;
            case 11:
                TwelveFragment twelveFragment = new TwelveFragment();
                bundle.putString("date",  date[11]);
                twelveFragment.setArguments(bundle);
                return twelveFragment;
            case 12:
                ThirteenFragment thirteenFragment = new ThirteenFragment();
                bundle.putString("date",  date[12]);
                thirteenFragment.setArguments(bundle);
                return thirteenFragment;
            case 13:
                FourteenFragment fourteenFragment = new FourteenFragment();
                bundle.putString("date",  date[13]);
                fourteenFragment.setArguments(bundle);
                return fourteenFragment;
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
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, position - 10);
                SimpleDateFormat df = new SimpleDateFormat("yyyMMdd");
                View v = LayoutInflater.from(context).inflate(R.layout.date_filter,null);
                TextView tv1=(TextView)v.findViewById(R.id.days);
                TextView tv2=(TextView)v.findViewById(R.id.numbers);
                TextView tv3=(TextView)v.findViewById(R.id.mydate);

                int day = c.get(Calendar.DAY_OF_MONTH);
                String day1 = df.format(c.getTime());
                int day2 = c.get(Calendar.DAY_OF_WEEK) -1;

                tv1.setText(tabTitles[day2]);
                tv2.setText("" + day);
                tv3.setText("" + day1);


                return v;
            }

}


