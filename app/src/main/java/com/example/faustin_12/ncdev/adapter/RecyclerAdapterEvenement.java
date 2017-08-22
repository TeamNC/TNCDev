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
import com.example.faustin_12.ncdev.activity.fragment.EvenementFragment;
import com.example.faustin_12.ncdev.model.ElementEvenement;

import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 01/10/2016.
 */
public class RecyclerAdapterEvenement extends RecyclerView.Adapter <RecyclerAdapterEvenement.mViewHolder> {
    private List<ElementEvenement> mData;
    private ClickListener clickListener;
    private LayoutInflater mInflater;

    public RecyclerAdapterEvenement(Context context, List<ElementEvenement> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
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
                View view = mInflater.inflate(R.layout.row_evenement, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout. particular_row_evenement, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        ElementEvenement currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }

    public String getTitle(int position) {
        return mData.get(position).getTitle();
    }

    public int getImageID(int position) {
        return mData.get(position).getImageID();
    }

    public int getNbreEvents(int position) {
        return mData.get(position).getNbreEvents();
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementEvenement item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,nbreEvents;
        ImageView imgRow;
        ElementEvenement current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.title_pop);
            imgRow = (ImageView) itemView.findViewById(R.id.first_pict);
            nbreEvents = (TextView) itemView.findViewById(R.id.count);
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementEvenement current, int position) {
            this.title.setText(current.getTitle());
            this.imgRow.setImageResource(current.getImageID());
            this.nbreEvents.setText("" + current.getNbreEvents());
            this.current = current;
            this.position = position;
        }

    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}




