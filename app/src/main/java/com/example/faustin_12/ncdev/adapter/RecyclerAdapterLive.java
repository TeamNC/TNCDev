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
import com.example.faustin_12.ncdev.model.ElementDetailsLive;
import com.example.faustin_12.ncdev.model.ElementLive;
import com.example.faustin_12.ncdev.view.SquaredImageView;

import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 30/11/2016.
 */
public class RecyclerAdapterLive extends RecyclerView.Adapter<RecyclerAdapterLive.mViewHolder> {


    private List<ElementDetailsLive> mData;
    private ClickListener clickListener;
    private LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapterLive(Context context, List<ElementDetailsLive> data){
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
        switch (viewType)
        {
            case 0:
                View view = mInflater.inflate(R.layout.particular_row_live_details5, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout.particular_row_live_details5, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        ElementDetailsLive currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementDetailsLive item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgRow;
        SquaredImageView pictureLive;
        TextView  title, since, description;
        ElementDetailsLive current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            pictureLive = (SquaredImageView) itemView.findViewById(R.id.picture_live);
            since = (TextView)itemView.findViewById(R.id.since);
            title = (TextView)itemView.findViewById(R.id.title_live_details);
            description = (TextView)itemView.findViewById(R.id.description_live_details);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementDetailsLive current, int position) {
            this.since.setText(""+current.getSince());
            this.title.setText(""+current.getIconID());
            this.description.setText(""+current.getDescription());
            Glide.with(context).load(current.getImageID()).placeholder(R.drawable.placeholder).into(this.pictureLive);
            this.current=current;
            this.position=position;
        }
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}

