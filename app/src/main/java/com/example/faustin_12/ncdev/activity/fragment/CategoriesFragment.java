package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;

import com.example.faustin_12.ncdev.adapter.TabsAdapter2;


/**
 * Created by LIONEL KOUEMENI on 04/10/2016.
 */

public class CategoriesFragment extends Fragment {    public static TabLayout tabLayout2;
    public static ViewPager viewPager;
    public int tabId=0;
    public String[] date = {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a" };
    public String categorie;
    public TabsAdapter2 adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate tab_layout and setup Views.
         */

        View x = inflater.inflate(R.layout.tab_layout2, null);
        tabLayout2 = (TabLayout) x.findViewById(R.id.tabs2);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager2);
        adapter = new TabsAdapter2(getContext(), getChildFragmentManager());
        /**
         *Set an Apater for the View Pager
         */
       // mdata = evenementFragment.getData();

        viewPager.setAdapter(adapter);

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout2.setupWithViewPager(viewPager);

        /**
         * Iterate over all the tabs and set the custom view
         */

        for (int i =0 ;i<tabLayout2.getTabCount();i++)
        {
            TabLayout.Tab tab =tabLayout2.getTabAt(i);
            tab.setCustomView(TabsAdapter2.getTabView(i));
            View v = TabsAdapter2.getTabView(i);
            TextView tv1=(TextView)v.findViewById(R.id.mydate);
            date[i]= tv1.getText().toString();
        }

        adapter.setCategorie(categorie);
        adapter.setDate(date);
        TabLayout.Tab tab = tabLayout2.getTabAt(10);
        tab.select();

        Toolbar toolbarC= (Toolbar) x.findViewById(R.id.toolbarC);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarC);
        toolbarC.setNavigationIcon(R.drawable.ic_action_important);
        toolbarC.setNavigationIcon(R.drawable.ic_action_back);
        toolbarC.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Retour", Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) getActivity()).onBackPressed();
            }
        });

        Toast.makeText(getContext(), "Catégorie : " + categorie, Toast.LENGTH_SHORT).show();

        return x;
    }




    public void setTabId(int tabid){
        this.tabId=tabid;
    }
    public void setCategorie(String categorie){this.categorie = categorie;}
}