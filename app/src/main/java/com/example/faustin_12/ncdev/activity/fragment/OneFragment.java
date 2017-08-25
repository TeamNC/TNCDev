package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.InterfaceEvenements;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterActualite;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterEvenement;
import com.example.faustin_12.ncdev.model.ElementActualite;
import com.example.faustin_12.ncdev.model.ResponseEvenement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LIONEL KOUEMENI on 04/10/2016.
 */
public class OneFragment extends Fragment implements RecyclerAdapterActualite.ClickListener{
    public OneFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    RecyclerAdapterActualite mAdapter;
    List<ElementActualite> mData = new ArrayList<>();
    Handler mHandler = new Handler();
    FragmentManager mFragmentManager;
    String categorie;
    int date;
    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate fragment_fixe and setup Views.
         */
        View v=inflater.inflate(R.layout.mdb_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerMDB);
        mAdapter = new RecyclerAdapterActualite(getContext(), new ArrayList<ElementActualite>());
        mAdapter.setClickListener(this);
        mFragmentManager = getActivity().getSupportFragmentManager();

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        categorie=getArguments().getString("categorie");
        date = Integer.parseInt(getArguments().getString("date"));

        download(categorie, date);

        return v;
    }

    public void download (String categorie, int date){
        // Trailing slash is needed
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(((MainActivity) getActivity()).getServerT()) //url host/ncdev/db_get_all_document.php
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceEvenements apiService = retrofit.create(InterfaceEvenements.class);
        Call<ResponseEvenement> call = apiService.getJSON("/nca/db_get_document_cdp.php?categorie="
                +categorie+"&date="+date);
        call.enqueue(new Callback<ResponseEvenement>() {
            @Override
            public void onResponse(Call<ResponseEvenement> call, Response<ResponseEvenement> response) {
                ResponseEvenement responseEvenement = response.body();
                mData = responseEvenement.getEnevements();

                try{
                    if (mData.size()>0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
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
            }

            @Override
            public void onFailure(Call<ResponseEvenement> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error",t.getMessage());
            }
        });
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

    //@Override
    public void itemClicked(View view, int position) {
        DetailFragment temps = new DetailFragment();
        temps.setTitle(mAdapter.getItem(position).getTitle());
        temps.setDate(""+mAdapter.getItem(position).getDate());
        temps.setPrice("" + mAdapter.getItem(position).getDescription());
        ImageView icon = (ImageView) view.findViewById(R.id.imgRow);
        temps.setMyImageView(icon);
        Toast.makeText(getContext(),"Clicked : "+ mAdapter.getItem(position).getTitle(),Toast.LENGTH_LONG).show();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView0, temps).addToBackStack(null).commit();
    }
}
