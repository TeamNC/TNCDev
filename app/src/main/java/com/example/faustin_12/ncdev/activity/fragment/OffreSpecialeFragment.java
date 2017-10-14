package com.example.faustin_12.ncdev.activity.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterOffresSpeciales;
import com.example.faustin_12.ncdev.model.ElementOffresSpeciales;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by LIONEL KOUEMENI on 12/09/2016.
 */
public class OffreSpecialeFragment extends Fragment implements RecyclerAdapterOffresSpeciales.ClickListener{
    // Array of strings storing country names
    int index=0;
    String[] countries = new String[] {"India", "Pakistan", "Sri Lanka", "China", "Bangladesh", "Nepal", "Afghanistan", "North Korea", "South Korea", "Japan"
    };
    int[] flags = new int[]{R.drawable.images6,
            R.drawable.images3,
            R.drawable.images6,
            R.drawable.images3,
            R.drawable.images6,
            R.drawable.images3,
            R.drawable.images6,
            R.drawable.images3,
            R.drawable.audrey,
            R.drawable.images3
    };
    // Array of strings to store currencies
    String[] currency = new String[]{"Indian Rupee", "Pakistani Rupee", "Sri Lankan Rupee", "Renminbi", "Bangladeshi Taka", "Nepalese Rupee", "Afghani", "North Korean Won", "South Korean Won", "Japanese Yen"
    };
    RecyclerView recyclerView;
    RecyclerAdapterOffresSpeciales mAdapter;
    FragmentManager mFragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View v = inflater.inflate(R.layout.recyclerview_layout, container, false);
        FloatingActionButton addButton = (FloatingActionButton) v.findViewById(R.id.button_add);
        /*addButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Calendar c = Calendar.getInstance();
                                             DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
                                             if (index > 9) index = 0;
                                             ElementOffresSpeciales item = new ElementOffresSpeciales();
                                             item.setImageIDOffresSpeciales(flags[index]);
                                             item.setName(countries[index]);
                                             //if(index==0) item.setImageID(R.drawable.particular_row);
                                             addInfo(item);
                                             index++;
                                         }
                                     }
        );*/

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerList);
        mAdapter = new RecyclerAdapterOffresSpeciales(getContext(), new ArrayList<ElementOffresSpeciales>());


        // LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        //mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(mLinearLayoutManager);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);


        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFragmentManager=getActivity().getSupportFragmentManager();
        return v;
    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



    public void addInfo (ElementOffresSpeciales item){
        mAdapter.addInfo(item);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {



        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.evenement_menu, menu);
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

    // @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getActivity(), "Tu as sélectionné :" + mAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
        CategoriesFragment temps = new CategoriesFragment();
        //temps.setName("Détail de :" + mAdapter.getName(position));
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, temps).addToBackStack(null).commit();
    }

}



