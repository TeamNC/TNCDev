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
import android.widget.ImageView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterActualité;
import com.example.faustin_12.ncdev.model.ElementActualite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class ActualiteFragment extends Fragment implements RecyclerAdapterActualité.ClickListener{

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
            R.drawable.images6
    };
    // Array of strings to store currencies
    String[] currency = new String[]{"Indian Rupee", "Pakistani Rupee", "Sri Lankan Rupee", "Renminbi", "Bangladeshi Taka", "Nepalese Rupee", "Afghani", "North Korean Won", "South Korean Won", "Japanese Yen"
    };
    RecyclerView recyclerView;
    RecyclerAdapterActualité mAdapter;
    FragmentManager mFragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate fragment_fixe and setup Views.
         */


        View v = inflater.inflate(R.layout.recyclerview_layout, container, false);
        FloatingActionButton addButton = (FloatingActionButton) v.findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Calendar c = Calendar.getInstance();
                                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
                                             if (index > 9) index = 0;
                                             ElementActualite item = new ElementActualite(flags[index], countries[index],
                                                     "les mechants n'ont pas besoin de raison pour hair.leur mechanceté  suffit"+index, index, df.format(c.getTime()),index*1000, "Localisation#" + index,"#Catégories" + index, index );
                                            // if(index==0) item.setImageID(R.drawable.particular_row);
                                             addInfo(item);
                                             index++;
                                         }
                                     }
        );

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerList);
        mAdapter = new RecyclerAdapterActualité(getContext(), new ArrayList<ElementActualite>());


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFragmentManager=getActivity().getSupportFragmentManager();


        return v;
    }

    public void addInfo (ElementActualite item){
        mAdapter.addInfo(item);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {



        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.actualite_menu, menu);
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
        DetailFragment temps = new DetailFragment();
        temps.setTitle(mAdapter.getTitle(position));
        temps.setDate(mAdapter.getDate(position));
        temps.setPrice("" + mAdapter.getPrice(position));
        ImageView icon = (ImageView) view.findViewById(R.id.imgRow);
        temps.setMyImageView(icon);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView0, temps).addToBackStack(null).commit();
    }
}
