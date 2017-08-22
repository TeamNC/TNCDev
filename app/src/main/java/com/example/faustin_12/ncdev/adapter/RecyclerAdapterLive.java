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
import com.example.faustin_12.ncdev.model.ElementLive;

import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 30/11/2016.
 */
public class RecyclerAdapterLive extends RecyclerView.Adapter<RecyclerAdapterLive.MyViewHolder> {

    private static final String TAG = RecyclerAdapterDetailsLive.class.getSimpleName();
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
    public void setClickListener(RecyclerAdapterLive.ClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public String getTitle (int position){
        return data.get(position).getTitle();
    }
    //public String getDescription_Actualité (int position){
      //  return data.get(position).getDescription();
    //}
    public int getIcon (int position){
        return data.get(position).getImageID();
    }
    public String getDate (int position) {return data.get(position).getDate();}
    public void addItemLive(ElementLive item) {
        data.add(item);
        notifyItemInserted(data.size());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //public TextView itemContent;
        TextView title,date;
        ImageView imgRow;
        ElementLive current;
        int position;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title= (TextView) itemView.findViewById(R.id.live_title);
            imgRow= (ImageView) itemView.findViewById(R.id.live_item);

          //  itemContent = (TextView)itemView.findViewById(R.id.item_content);
        }

        @Override
        public void onClick(View v) {if (clickListener != null) {
            clickListener.itemClicked(v, getPosition());
        }

        }
        public void setData(ElementLive current, int position) {
            this.title.setText(current.getTitle());
            this.imgRow.setImageResource(current.getImageID());
            this.date.setText(current.getDate());
            this.current = current;
            this.position = position;
        }
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}

