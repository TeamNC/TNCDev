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
import com.example.faustin_12.ncdev.model.Element;

import java.util.List;

/**
 * Created by FAUSTIN-12 on 12/05/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.mViewHolder> {
    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private List<Element> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapter (Context context, List<Element> data){
        this.mData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType=0;
        if(position==0) viewType=1;
        return viewType;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        switch (viewType)
        {
            case 0:
                View view = mInflater.inflate(R.layout.list_item, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout.particular_row, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder" +position);
        Element currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }

    public String getTitle (int position){
        return mData.get(position).getTitle();
    }
    public String getDescription (int position){
        return mData.get(position).getDescription();
    }
    public int getIcon (int position){
        return mData.get(position).getImageID();
    }
    public String getTime (int position) {return mData.get(position).getTime();}
    public int getNbreCom (int position) {return mData.get(position).getNbreCom();}


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(Element item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        TextView title, description, time, nbreCom;
        ImageView imgRow, imgDelete;
        Element current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.item_title);
            description = (TextView)itemView.findViewById(R.id.item_description);
            imgRow = (ImageView)itemView.findViewById(R.id.imgRow);
            imgDelete= (ImageView)itemView.findViewById(R.id.imgRow_delete);
            time = (TextView) itemView.findViewById(R.id.time);
            nbreCom = (TextView) itemView.findViewById(R.id.nbreCom);
        }

        public void setData(Element current, int position) {
            this.title.setText(current.getTitle());
            this.description.setText(current.getDescription());
            this.imgRow.setImageResource(current.getImageID());
            this.time.setText(current.getTime());
            this.nbreCom.setText(""+current.getNbreCom());
            this.current=current;
            this.position=position;
        }
    }
}
