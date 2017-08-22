package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementBoite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FAUSTIN-12 on 12/05/2016.
 */
public class RecyclerAdapterBoiteSnack extends RecyclerView.Adapter <RecyclerAdapterBoiteSnack.mViewHolder> {
    private static final String TAG = RecyclerAdapterBoiteSnack.class.getSimpleName();
    private List<ElementBoite> mData;
    private ClickListener clickListener;
    private LayoutInflater mInflater;
    private Context context;


    public RecyclerAdapterBoiteSnack(Context context, List<ElementBoite> data){
        this.mData=data;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
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
                View view = mInflater.inflate(R.layout.particular_boite_et_snack2, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout.particular_boite_et_snack2, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder" +position);
        ElementBoite currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }

    public String getTitle (int position){
        return mData.get(position).getName();
    }
    public String getDescription (int position){
        return mData.get(position).getSpecificity();
    }
    public int getIcon (int position){
        return mData.get(position).getImageID();
    }
    public String getLocalisation (int position) {return mData.get(position).getLocation();}
    public int getPrices (int position) {return mData.get(position).getPrices();}


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData (List<ElementBoite> infos){
        mData = new ArrayList<>();
        mData.addAll(infos);
        notifyDataSetChanged();
    }

    public void addInfo (ElementBoite item){
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    public void removeItem (ElementBoite item){
        int position = mData.indexOf(item);
        if (position != -1){
            mData.remove(item);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        }
    }

    public void removeItem (int position){
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, description, localisation, nbreLove;
        ImageView imgRow;
        ElementBoite current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView)itemView.findViewById(R.id.boite_title);
            description = (TextView)itemView.findViewById(R.id.description_boite);
            imgRow = (ImageView)itemView.findViewById(R.id.boite_item);
            localisation = (TextView) itemView.findViewById(R.id.localisation);
            nbreLove = (TextView) itemView.findViewById(R.id.nbrelove);
        }

        @Override
        public void onClick(View v){
            if(clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementBoite current, int position) {
            this.title.setText(""+current.getName());
            this.description.setText(""+current.getSpecificity());
            this.localisation.setText(""+current.getLocation());
            this.nbreLove.setText(""+current.getPrices());
            Glide.with(context).load(current.getImageID()).placeholder(R.drawable.placeholder).into(this.imgRow);
            this.current=current;
            this.position=position;
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
