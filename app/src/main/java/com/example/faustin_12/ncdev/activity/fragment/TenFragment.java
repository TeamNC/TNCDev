package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.example.faustin_12.ncdev.InterfaceEvenements;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;
import com.example.faustin_12.ncdev.adapter.DataAdapter;
import com.example.faustin_12.ncdev.model.ElementEvenement;
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
public class TenFragment extends Fragment {
    public TenFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    DataAdapter mAdapter;
    List<ElementEvenement> mData = new ArrayList<>();
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
        mAdapter = new DataAdapter(getContext(), new ArrayList<ElementEvenement>());

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        Call<ResponseEvenement> call = apiService.getJSON("/ncdev/db_get_document_cdp.php?categorie="
                +categorie+"&date="+date);
        call.enqueue(new Callback<ResponseEvenement>() {
            @Override
            public void onResponse(Call<ResponseEvenement> call, Response<ResponseEvenement> response) {
                ResponseEvenement responseEvenement = response.body();
                mData = responseEvenement.getEnevements();
                mAdapter.setData(mData);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ResponseEvenement> call, Throwable t) {
                // Log error here since request failed
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
}
