package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterBoiteSnack;
import com.example.faustin_12.ncdev.model.ElementBoite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class  BoiteFragment extends Fragment implements RecyclerAdapterBoiteSnack.ClickListener {
    // Array of strings storing country names
    int index=0;
    String[] countries = new String[] {"India", "Pakistan", "Sri Lanka", "China", "Bangladesh", "Nepal", "Afghanistan", "North Korea", "South Korea", "Japan"
    };
    int[] flags = new int[]{R.drawable.images3,
            R.drawable.images6,
            R.drawable.images3,
            R.drawable.images6,
            R.drawable.images3,
            R.drawable.images6,
            R.drawable.images3,
            R.drawable.images6,
            R.drawable.images3,
            R.drawable.images6,
    };
    // Array of strings to store currencies
    String[] currency = new String[]{"Indian Rupee", "Pakistani Rupee", "Sri Lankan Rupee", "Renminbi", "Bangladeshi Taka", "Nepalese Rupee", "Afghani", "North Korean Won", "South Korean Won", "Japanese Yen"
    };
    RecyclerView recyclerView;
    RecyclerAdapterBoiteSnack mAdapter;
    FragmentManager mFragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v=inflater.inflate(R.layout.boite_layout, container, false);
        FloatingActionButton addButton = (FloatingActionButton) v.findViewById(R.id.boutonBoite);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DateFormat df = new SimpleDateFormat("HH:mm");
                if (index > 9) index = 0;
                ElementBoite item = new ElementBoite(index, countries[index], "", index, "",
                        "Localisation #" +index, "", flags[index], index*1000);
                if(index==0) item.setImageID(R.drawable.particular_row);
                addInfo(item);
                index++;
            }
        }
        );

        recyclerView = (RecyclerView) v.findViewById(R.id.BoiteList);
        mAdapter = new RecyclerAdapterBoiteSnack(getContext(), new ArrayList<ElementBoite>());
        mAdapter.setClickListener(this);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    public void addInfo (ElementBoite item){
        mAdapter.addInfo(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.boites_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getActivity(), "Tu as sélectionné :" + mAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
        DetailFragment temps = new DetailFragment();
        temps.setTitle("Détail de :" + mAdapter.getItem(position).getName());
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView0, temps).addToBackStack(null).commit();
    }

}
