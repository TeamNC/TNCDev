package com.example.faustin_12.ncdev.activity.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.RecyclerViewAdapter;
import com.example.faustin_12.ncdev.model.Informations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class EvenementFragment extends Fragment implements RecyclerViewAdapter.ClickListener
        , SearchView.OnQueryTextListener {
    public FragmentManager mFragmentManager ;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private int[] icons = new int[]{
            R.drawable.india,
            R.drawable.pakistan,
            R.drawable.srilanka,
            R.drawable.china,
            R.drawable.bangladesh,
            R.drawable.nepal,
            R.drawable.afghanistan,
            R.drawable.nkorea,
            R.drawable.skorea,
            R.drawable.japan
    };
    private int iconIndex=0;
    private FloatingActionButton addButton;
    public ArrayList<Informations> data = new ArrayList<>();
    public ArrayList<Informations> mdata = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View x = inflater.inflate(R.layout.recyclerview_layout, null);
        recyclerView= (RecyclerView) x.findViewById(R.id.recyclerList);
        addButton = (FloatingActionButton) x.findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DateFormat df = new SimpleDateFormat("HH:mm");
                if (iconIndex > 9) iconIndex = 0;
                Informations item = new Informations(icons[iconIndex], "Titre #" + iconIndex, "This is my description #" + iconIndex, df.format(c.getTime()));
                item.setNbreCom(iconIndex);
                addInfo(item);
                iconIndex++;
            }
        });

        adapter = new RecyclerViewAdapter(getActivity(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFragmentManager = getActivity().getSupportFragmentManager();
        mdata=adapter.getData();
        return x;
    }

    public ArrayList<Informations> getData (){
        return adapter.getData();
    }

    public void setData (ArrayList<Informations> data){
        adapter.setData(data);
    }


    public void addInfo (Informations item){
        adapter.addInfo(item);
    }

    @Override
     public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.evenement_menu, menu);

        final MenuItem item = menu.findItem(R.id.eaction_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        adapter.setFilter(mdata);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });
    }

    @Override
    public boolean onQueryTextChange(String newText){
        final ArrayList<Informations> filteredModelList = filter(mdata, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    private ArrayList<Informations> filter (ArrayList<Informations> data, String query){
        query = query.toLowerCase();

        final ArrayList<Informations> filteredModelList = new ArrayList<>();
        for (Informations model : data){
            final String text = model.getTitre().toLowerCase();
            final String text2 = model.getDescription().toLowerCase();
            if (text.contains(query)||text2.contains(query))filteredModelList.add(model);
        }
        return filteredModelList;
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

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getActivity(), "Tu as sélectionné :" + adapter.getTitle(position), Toast.LENGTH_SHORT).show();
        DetailFragment temps = new DetailFragment();
        temps.setTitle("Détail de :" + adapter.getTitle(position));
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, temps).addToBackStack(null).commit();
    }

}
