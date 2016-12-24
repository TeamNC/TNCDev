package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterLive;
import com.example.faustin_12.ncdev.model.ElementLive;

import java.util.ArrayList;

/**
 * Created by LIONEL KOUEMENI on 12/09/2016.
 */
public class LiveFragment extends Fragment {
   /** public LiveFragment() {
        // Required empty public constructor
    }

    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate fragment_fixe and setup Views.
         */
     /**   View x = inflater.inflate(R.layout.fragment_fixe, null);
        TextView tv= (TextView)x.findViewById(R.id.textView1);
        tv.setText("Mon Live");
        tv.setTextSize(25);
        return x;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actualite_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}*/
     private RecyclerView recyclerView;
    private RecyclerAdapterLive adapter;
    public  int[] icons={R.drawable.images6,R.drawable.images6,R.drawable.images6,R.drawable.images6};
    public String[] titles={"victoire en copa del rey du FCB","lionel messi à IBIZA","Apero street de DINGUE la!!","mancherster numéro un des Nullard"};
    public FloatingActionButton addbuton;
    public int index=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate) {
        setHasOptionsMenu(true);
        View layout=inflater.inflate(R.layout.live_layout,null);
        addbuton= (FloatingActionButton)layout.findViewById(R.id.boutondel);
        addbuton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (index>4) index=0;
                ElementLive item=new ElementLive(
                icons[index], titles[index]);
                addItemLive(item);
                index++;
            }
        });
        recyclerView= (RecyclerView) layout.findViewById(R.id.LiveList);
        adapter=new RecyclerAdapterLive(getContext(),new ArrayList<ElementLive>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return layout;
    }

    public void addItemLive (ElementLive item){adapter.addItemLive(item);
    }

    @Override
    public void onCreateOptionsMenu (Menu menu,MenuInflater inflater){
        inflater.inflate(R.menu.actualite_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}



