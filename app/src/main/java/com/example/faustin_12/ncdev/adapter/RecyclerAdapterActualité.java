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
import com.example.faustin_12.ncdev.model.ElementActualite;

import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 18/09/2016.
 */
public class RecyclerAdapterActualité extends RecyclerView.Adapter <RecyclerAdapterActualité.mViewHolder> {
    private List<ElementActualite> mData;
    private LayoutInflater mInflater;
    private ClickListener clickListener;

    public RecyclerAdapterActualité (Context context, List<ElementActualite> data){
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
                View view = mInflater.inflate(R.layout.particular_row_actualite, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout.particular_row_actualite, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        ElementActualite currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public String getPrice (int position){
        return ""+mData.get(position).getPrice();
    }
    public String getTitle (int position){
        return mData.get(position).getTitle();
    }
    public String getDescription_Actualité (int position){
        return mData.get(position).getDescription();
    }
    public int getIcon (int position){
        return mData.get(position).getImage();
    }
    public String getDate (int position) {return ""+mData.get(position).getDate();}
    public int getNbreCom (int position) {return mData.get(position).getComment();}


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementActualite item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView price, date, title, description_actualite, localisation,categories, nbreCom,nbreLove;
        ImageView imgRow;
        ElementActualite current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            price = (TextView)itemView.findViewById(R.id.price);
            date = (TextView)itemView.findViewById(R.id.date);
            title=(TextView)itemView.findViewById(R.id.title_actualite);
            description_actualite = (TextView)itemView.findViewById(R.id.description_actualite);
            localisation= (TextView)itemView.findViewById(R.id.localisation);
            imgRow = (ImageView)itemView.findViewById(R.id.imgRow);

            categories= (TextView) itemView.findViewById(R.id.categories);
            nbreCom = (TextView) itemView.findViewById(R.id.nbreCom);
            nbreLove = (TextView) itemView.findViewById(R.id.nbrelove);
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementActualite current, int position) {
            this.price.setText(""+current.getPrice());
            this.date.setText(""+current.getDate());
            this.title.setText(current.getTitle());
            this.description_actualite.setText(current.getDescription());
            this.localisation.setText(current.getLocalisation());
            this.imgRow.setImageResource(current.getImage());
            this.categories.setText(current.getCategorie());
            this.nbreCom.setText(""+current.getComment());
            this.nbreLove.setText(""+current.getLike());
            this.current=current;
            this.position=position;
        }
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
