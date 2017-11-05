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


import java.util.ArrayList;

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
        //adapter.setDate(date);
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

        return x;
    }




    public void setTabId(int tabid){
        this.tabId=tabid;
    }
    public void setCategorie(String categorie){this.categorie = categorie;}
}

   /*  public class CategoriesFragment extends Fragment implements RecyclerAdapterEvenement.ClickListener{

         // Array of strings storing country names
         int index=0;
         String[] Days = new String[] {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"}

         ;

         RecyclerView recyclerView;
         RecyclerAdapterEvenement mAdapter;
         FragmentManager mFragmentManager;

         public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
             setHasOptionsMenu(true);
             /**
              *Inflate fragment_fixe and setup Views.
              */

/*
             View v = inflater.inflate(R.layout.recyclerview_layout_filter_date, container, false);
             FloatingActionButton addButton = (FloatingActionButton) v.findViewById(R.id.button_add);
             addButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                //  Calendar c = Calendar.getInstance();
                                                  //DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
                                                  if (index > 6) index = 0;
                                                  ElementCatégorie item = new ElementCatégorie(Days[index]);
                                                  // if(index==0) item.setImageID(R.drawable.particular_row);
                                                  item.setTitle(index);
                                                  addInfo(item);
                                                  index++;
                                              }
                                          }
             );

             recyclerView = (RecyclerView) v.findViewById(R.id.recyclerList2);
             mAdapter = new RecyclerAdapterEvenement(getContext(), new ArrayList<ElementCatégorie>());
             recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
             mFragmentManager=getActivity().getSupportFragmentManager();

             LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
             mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
             recyclerView.setLayoutManager(mLinearLayoutManager);

             recyclerView.setItemAnimator(new DefaultItemAnimator());

             mAdapter.setClickListener(this);
             recyclerView.setAdapter(mAdapter);





             return v;
         }

         public void addInfo (ElementCatégorie item){
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
             Toast.makeText(getActivity(), "Tu as sélectionné :" + mAdapter.getDate(position), Toast.LENGTH_SHORT).show();
             DetailFragment temps = new DetailFragment();
           //  temps.setTitle(mAdapter.getTitle(position));
             temps.setDate(mAdapter.getDate(position));
             FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.containerView, temps).addToBackStack(null).commit();
         }
     }
*/