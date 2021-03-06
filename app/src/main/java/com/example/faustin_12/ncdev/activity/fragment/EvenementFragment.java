package com.example.faustin_12.ncdev.activity.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.faustin_12.ncdev.InterfaceCategories;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterCategorie;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterEvenement;
import com.example.faustin_12.ncdev.model.ElementCategorie;
import com.example.faustin_12.ncdev.model.ResponseCategorie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class EvenementFragment extends Fragment implements RecyclerAdapterCategorie.ClickListener{
    RecyclerView recyclerView;
    RecyclerAdapterCategorie mAdapter;
    FragmentManager mFragmentManager;
    GridLayoutManager mLayoutManager;
    List<ElementCategorie> mData = new ArrayList<>();
    private SwipeRefreshLayout swipeContainer;
    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int currentPage=1;
    Handler mHandler = new Handler();
    String server;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View v = inflater.inflate(R.layout.recyclerview_layout, container, false);

        mFragmentManager=getActivity().getSupportFragmentManager();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerList);
        mAdapter = new RecyclerAdapterCategorie(getContext(), new ArrayList<ElementCategorie>(), mFragmentManager);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainerC);

        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position){
                switch (mAdapter.getItemViewType(position)){
                    case 0:
                        return 1;
                    case 1:
                        return 2;
                    default:return 0;
                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        //Me

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            if(mAdapter.getItemCount()>0) {
                                loading = true;
                                loadMore(mAdapter.getData().get(mAdapter.getItemCount() - 1).getId_cat());
                            }
                        }
                    }
                }
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // mAdapter.addInfo(0, new ElementCategorie(0,"Test "+ currentPage, 10, "", 0));
                //currentPage++;
                swipeContainer.setRefreshing(false);
                /*if(mAdapter.getItemCount()>0)
                    refresh(mAdapter.getData().get(0).getId_cat());
                else
                    download();*/
                download();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return v;
    }

    public void refresh (int id_cat){
        server=((MainActivity) getActivity()).getServerT();
        // Trailing slash is needed
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server) //url host/ncdev/db_get_all_document.php
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceCategories apiService = retrofit.create(InterfaceCategories.class);
        Call<ResponseCategorie> call = apiService.getJSON("/page_up/"+id_cat);
        call.enqueue(new Callback<ResponseCategorie>() {
            @Override
            public void onResponse(Call<ResponseCategorie> call, Response<ResponseCategorie> response) {
                ResponseCategorie responseCategorie = response.body();
                mData = responseCategorie.getCategories();

                try{
                    if (mData.size()>0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i=0; i<mData.size(); i++){
                                            mAdapter.addInfo(0, mData.get(i));
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                swipeContainer.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<ResponseCategorie> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(),"Error : "+ t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());

                swipeContainer.setRefreshing(false);
            }
        });
    }

    public void loadMore (int id_cat){
        server=((MainActivity) getActivity()).getServerT();
        // Trailing slash is needed
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server) //url host/ncdev/db_get_all_document.php
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceCategories apiService = retrofit.create(InterfaceCategories.class);
        Call<ResponseCategorie> call = apiService.getJSON("/page_dwn/"+id_cat);
        call.enqueue(new Callback<ResponseCategorie>() {
            @Override
            public void onResponse(Call<ResponseCategorie> call, Response<ResponseCategorie> response) {
                ResponseCategorie responseCategorie = response.body();
                mData = responseCategorie.getCategories();

                try{
                    if (mData.size()>0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i=0; i<mData.size(); i++){
                                            mAdapter.addInfo(mAdapter.getItemCount(), mData.get(i));
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                swipeContainer.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<ResponseCategorie> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(),"Error : "+ t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());

                swipeContainer.setRefreshing(false);
            }
        });
    }


    public void download (){
        mAdapter.addInfo(0, new ElementCategorie(0,"Test", 10, "", 0));
        server=((MainActivity) getActivity()).getServerT();
        // Trailing slash is needed
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server) //url host/ncdev/db_get_all_document.php
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceCategories apiService = retrofit.create(InterfaceCategories.class);
        Call<ResponseCategorie> call = apiService.getJSON("/categories");
        call.enqueue(new Callback<ResponseCategorie>() {
            @Override
            public void onResponse(Call<ResponseCategorie> call, Response<ResponseCategorie> response) {
                ResponseCategorie responseCategorie = response.body();
                mData = responseCategorie.getCategories();

                try{
                    if (mData.size()>0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //Thales Moi Même ++ ++
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAdapter.setData(mData);
                                        recyclerView.setAdapter(mAdapter);
                                    }
                                });
                            }
                        }).start();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                swipeContainer.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<ResponseCategorie> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(),"Error : "+ t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());

                swipeContainer.setRefreshing(false);
            }
        });
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
        Toast.makeText(getContext(), "Ah, don't touch me!", Toast.LENGTH_SHORT).show();


        /*CategoriesFragment temps = new CategoriesFragment();
        temps.setCategorie(""+mAdapter.getItem(position).getId_cat());
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView0, temps).addToBackStack(null).commit();*/
    }

}
