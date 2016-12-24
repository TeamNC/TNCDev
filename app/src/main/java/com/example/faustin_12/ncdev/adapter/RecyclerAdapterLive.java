package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementEvenement;
import com.example.faustin_12.ncdev.model.ElementLive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 30/11/2016.
 */
public class RecyclerAdapterLive extends RecyclerView.Adapter<RecyclerAdapterLive.MyViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private ClickListener clickListener;
    private List<ElementLive> data;


    public RecyclerAdapterLive(Context Context, List<ElementLive> data){
        this.data=data;
        inflater=LayoutInflater.from(Context);
    }
    public int getItemViewType(int position) {
        int viewType = 0;
        if (position == 0) viewType = 1;
        if (position == 1) viewType = 2;
        return viewType;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = inflater.inflate(R.layout.particular_row_live, parent, false);
                return new MyViewHolder(view);
            case 1:
                View pview = inflater.inflate(R.layout.particular_row_live2, parent, false);
                return new MyViewHolder(pview);
            case 2:
                View sview = inflater.inflate(R.layout. particular_row_live3, parent, false);
                return new MyViewHolder(sview);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder" + position);
        ElementLive currentObj = data.get(position);
        holder.setData(currentObj, position);
    }
    public void addItemLive(ElementLive item) {
        data.add(item);
        notifyItemInserted(data.size());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView imgRow;
        ElementLive current;
        int position;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title= (TextView) itemView.findViewById(R.id.live_title);
            imgRow= (ImageView) itemView.findViewById(R.id.live_item);
        }

        @Override
        public void onClick(View v) {if (clickListener != null) {
            clickListener.itemClicked(v, getPosition());
        }

        }
        public void setData(ElementLive current, int position) {
            this.title.setText(current.getTitlelive());
            this.imgRow.setImageResource(current.getIconIDLIVE());
            this.current = current;
            this.position = position;
        }
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}

