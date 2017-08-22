package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementCategorie;

import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 01/10/2016.
 */
public class RecyclerAdapterCategorie extends RecyclerView.Adapter <RecyclerAdapterCategorie.mViewHolder> {
    private List<ElementCategorie> mData;
    private ClickListener clickListener;
    private LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapterCategorie(Context context, List<ElementCategorie> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        if (position == 0) viewType = 1;
        return viewType;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = mInflater.inflate(R.layout.row_categorie, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout.particular_row_categorie, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        ElementCategorie currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }

    public String getName(int position) {
        return mData.get(position).getName();
    }

    public String getImage(int position) {
        return mData.get(position).getImage();
    }

    public int getNbreEvents(int position) {
        return mData.get(position).getNombreevts();
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementCategorie item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    public void setData (List<ElementCategorie> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,nbreEvents;
        ImageView imgRow;
        ElementCategorie current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.title_pop);
            imgRow = (ImageView) itemView.findViewById(R.id.first_pict);
            nbreEvents = (TextView) itemView.findViewById(R.id.count);
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementCategorie current, int position) {
            this.name.setText(current.getName());
            Glide.with(context).load(current.getImage()).placeholder(R.drawable.placeholder).into(this.imgRow);
            this.nbreEvents.setText("" + current.getNombreevts());
            this.current = current;
            this.position = position;
        }

    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}




