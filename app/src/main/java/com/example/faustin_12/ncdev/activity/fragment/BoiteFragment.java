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
import com.example.faustin_12.ncdev.RequestInterface;
import com.example.faustin_12.ncdev.adapter.DataAdapter;
import com.example.faustin_12.ncdev.model.Feed;
import com.example.faustin_12.ncdev.model.FeedItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class BoiteFragment extends Fragment {
    RecyclerView recyclerView;
    private DataAdapter adapter;
    FragmentManager mFragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v=inflater.inflate(R.layout.recyclerview_layout, container, false);
        FloatingActionButton addButton = (FloatingActionButton) v.findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRSS();
            }
        }
        );

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return v;
    }

    private void loadRSS(){
        Retrofit  retrofit = new Retrofit .Builder()
                .baseUrl("http://www.eurosport.fr") //http://www.footmercato.net http://www.eurosport.fr http://www.ka-news.de
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<Feed> call = request.getRSS();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Toast.makeText(getActivity(), "Download Succeed", Toast.LENGTH_LONG).show();

                Feed feedItems = response.body();
                List<FeedItem> mItems = new ArrayList<>();
                mItems = feedItems.getChannel().getItemList();
                adapter = new DataAdapter(getActivity(), mItems);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Toast.makeText(getActivity(), "Error Downloading Data",Toast.LENGTH_LONG).show();
            }
        });
    }
    //@Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getActivity(), "Tu as sélectionné :" + adapter.getTitle(position), Toast.LENGTH_SHORT).show();
        DetailFragment temps = new DetailFragment();
        temps.setTitle("Détail de :" + adapter.getDescription(position));
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, temps).addToBackStack(null).commit();
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
}
