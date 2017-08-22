package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterDetailsLive;
import com.example.faustin_12.ncdev.model.ElementDetailsLive;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by LIONEL KOUEMENI on 08/04/2017.
 */

public class DetailLiveFragment extends Fragment implements RecyclerAdapterDetailsLive.ClickListener{

    // Array of strings storing country names
    int index=0;
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
    String[] currency = new String[]{"Indian Rupee ", "Pakistani Rupee", "Sri Lankan Rupee", "Renminbi", "Bangladeshi Taka", "Nepalese Rupee", "Afghani", "North Korean Won", "South Korean Won", "Japanese Yen"
    };
    RecyclerView recyclerView;
    RecyclerAdapterDetailsLive mAdapter;
    public ImageView myImageView;
    FragmentManager mFragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v=inflater.inflate(R.layout.details_live_layout2, container, false);
        //ImageView imageView = (ImageView) v.findViewById(R.id.profile_image);
        //if (myImageView == null)
          //  myImageView=imageView;
        //imageView.setImageDrawable(myImageView.getDrawable());
        Toolbar toolbarDL = (Toolbar) v.findViewById(R.id.toolbarDL);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarDL);
        toolbarDL.setNavigationIcon(R.drawable.ic_action_back);
        toolbarDL.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Retour", Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) getActivity()).onBackPressed();
            }
        });
        FloatingActionButton addButton = (FloatingActionButton) v.findViewById(R.id.boutonBoite);

        addButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Calendar c = Calendar.getInstance();
                                             DateFormat df = new SimpleDateFormat("HH:mm");
                                             if (index > 9) index = 0;
                                             ElementDetailsLive item = new ElementDetailsLive( currency[index],df.format(c.getTime()),flags[index],flags[index])  ;
                                             if(index==0) item.setImageID(R.drawable.particular_row);
                                             addInfo(item);
                                             index++;
                                         }
                                     }
        );

        recyclerView = (RecyclerView) v.findViewById(R.id.DLList);
        mAdapter=new RecyclerAdapterDetailsLive(getContext(),new ArrayList<ElementDetailsLive>());
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFragmentManager=getActivity().getSupportFragmentManager();



        return v;
    }

    public void addInfo (ElementDetailsLive item){
        mAdapter.addInfo(item);
    }
    public void setMyImageView(ImageView imageView){
        this.myImageView=imageView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.details_live_menu, menu);
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
        Toast.makeText(getActivity(), "Tu as sélectionné :" + mAdapter.getDescription_Live_Details(position), Toast.LENGTH_SHORT).show();

    }

}
