package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementCategorie;

import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 12/11/2016.
 */
public class RecyclerAdapterFiltreDate extends RecyclerView.Adapter <RecyclerAdapterFiltreDate.mViewHolder> {
        private List<ElementCategorie> mData;
        private LayoutInflater mInflater;
        private ClickListener clickListener;

        public RecyclerAdapterFiltreDate(Context context, List<ElementCategorie> data){
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
            switch (viewType)
            {
                case 0:
                    View view = mInflater.inflate(R.layout.date_filter, parent, false);
                    return new mViewHolder(view);
                case 1:
                    View pview = mInflater.inflate(R.layout.date_filter, parent, false);
                    return new mViewHolder(pview);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(mViewHolder holder, int position) {
            ElementCategorie currentObj = mData.get(position);
            holder.setData(currentObj, position);

        }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public String getTitle (int position){
        return mData.get(position).getName();
    }

    public String getDate (int position) {return mData.get(position).getName();}


        public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementCategorie item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView  date,title;
        ElementCategorie current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            date = (TextView)itemView.findViewById(R.id.days);
            title=(TextView)itemView.findViewById(R.id.numbers);
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementCategorie current, int position) {

            this.date.setText(current.getName());
            this.title.setText(""+current.getName());
            this.current=current;
            this.position=position;
        }
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
