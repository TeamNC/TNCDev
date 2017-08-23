package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.LiveSection;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterLive;
import com.example.faustin_12.ncdev.model.ElementDetailsLive;
import com.example.faustin_12.ncdev.model.ElementLive;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by LIONEL KOUEMENI on 12/09/2016.
 */
public class LiveFragment extends Fragment{
    public RecyclerView recyclerView;
    public SectionedRecyclerViewAdapter sectionAdapter;
    public LiveSection section1;
    public LiveSection section2;
    public LiveSection section3;
    public LiveSection section4;
    public FloatingActionButton addbuton;
    FragmentManager mFragmentManager;
    public int[] imgID =    {R.drawable.images3,
            R.drawable.images6,
            R.drawable.veste,
            R.drawable.audrey};
    public int[] colors = {R.color.categorieone, R.color.categorietwo, R.color.categoriethree, R.color.categoriefour};
    public int index = 0;
    public int liveNbr = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate) {
        setHasOptionsMenu(true);
        View layout=inflater.inflate(R.layout.live_layout,null);

        recyclerView= (RecyclerView) layout.findViewById(R.id.LiveList);
        sectionAdapter = new SectionedRecyclerViewAdapter();
        mFragmentManager=getActivity().getSupportFragmentManager();
        section1 = new LiveSection("Soirée", new ArrayList<ElementLive>(), getContext(), mFragmentManager, sectionAdapter);
        section2 = new LiveSection("Concert", new ArrayList<ElementLive>(), getContext(), mFragmentManager, sectionAdapter);
        section3 = new LiveSection("Animation", new ArrayList<ElementLive>(), getContext(), mFragmentManager, sectionAdapter);
        section4 = new LiveSection("Visite", new ArrayList<ElementLive>(), getContext(), mFragmentManager, sectionAdapter);
        sectionAdapter.addSection(section1);
        sectionAdapter.addSection(section2);
        sectionAdapter.addSection(section3);
        sectionAdapter.addSection(section4);
        recyclerView.setAdapter(sectionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*final Handler handler = new Handler();
        new Thread(new Runnable() {
            public int count = 0;
            @Override
            public void run() {
                int delay = 0; // delay for 0 sec.
                int period = 10000; // repeat every 10 sec.
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask()
                {
                    public void run()
                    {
                        //Call function
                        if(count < 25)
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                addInfo(new Random().nextInt(4)+1);
                                count++;
                            }
                        });
                    }
                }, delay, period);
            }
        }).start();*/

        return layout;
    }

    public void addInfo(int numLive){
        liveNbr++;
        Toast.makeText(getContext(), "Cat : " + numLive + ". Live : " + liveNbr, Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
        if (index>3) index=0;
        ElementLive item=new ElementLive();
        item.setIconIDLIVE(imgID[index]);
        item.setTitle("Live N° " + liveNbr);
        item.setDate(df.format(c.getTime()));
        item.setCategorie(colors[numLive]);
        switch(numLive){
            case 1:
                section1.addItem(item);
                break;
            case 2:
                section2.addItem(item);
                break;
            case 3:
                section3.addItem(item);
                break;
            case 4:
                section4.addItem(item);
                break;
            //section1.addItem(item);
        }
        sectionAdapter.notifyDataSetChanged();
        index++;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu,MenuInflater inflater){
        inflater.inflate(R.menu.actualite_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}




