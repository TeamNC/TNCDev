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
import com.example.faustin_12.ncdev.model.ElementDetailsLive;

import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 09/04/2017.
 */
public class RecyclerAdapterDetailsLive extends RecyclerView.Adapter <RecyclerAdapterDetailsLive.mViewHolder> {
    private static final String TAG = RecyclerAdapterDetailsLive.class.getSimpleName();
    private List<ElementDetailsLive> mLData;
    private LayoutInflater mInflater;
    private RecyclerAdapterDetailsLive.ClickListener clickListener;

    public RecyclerAdapterDetailsLive (Context context, List<ElementDetailsLive> data){
        this.mLData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType=0;
        if(position==0) viewType=1;
        return viewType;
    }

    @Override
    public RecyclerAdapterDetailsLive.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        switch (viewType)
        {
            case 0:
                View view = mInflater.inflate(R.layout.particular_row_live_details5, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout.particular_row_live_details2, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterDetailsLive.mViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder" +position);
        ElementDetailsLive currentObj = mLData.get(position);
        holder.setData(currentObj, position);

    }
    public void setClickListener(RecyclerAdapterDetailsLive.ClickListener clickListener) {
        this.clickListener = clickListener;
    }



    public String getDescription_Live_Details (int position){
        return mLData.get(position).getDescription();
    }
    public int getDLIcon (int position){
        return mLData.get(position).getIconID();
    }
    public String getSince (int position) {return mLData.get(position).getSince();}
    public int getNbreLove (int position) {return mLData.get(position).getNbreLove();}
    public int getImageDL (int position){return mLData.get(position).getImageID();}
    @Override
    public int getItemCount() {
        return mLData.size();
    }

    public void addInfo(ElementDetailsLive item) {
        mLData.add(item);
        notifyItemInserted(mLData.size());
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView  since, description_live_details,nbreLove;
        ImageView imgDL,DLicon;
        ElementDetailsLive current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
           since = (TextView)itemView.findViewById(R.id.since);
            description_live_details = (TextView)itemView.findViewById(R.id.description_live_details);
           imgDL =(ImageView)itemView.findViewById(R.id.picture_live);
            //DLicon = (ImageView)itemView.findViewById(R.id.profile2_image);
           // nbreLove = (TextView) itemView.findViewById(R.id.nbrelove);
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementDetailsLive current, int position) {
            this.since.setText(current.getSince());
            this.description_live_details.setText(current.getDescription());
         //   this.DLicon.setImageResource(current.getIconID());
           this.imgDL.setImageResource(current.getImageID());
           // this.nbreLove.setText(""+current.getNbreLove());
            this.current=current;
            this.position=position;
        }
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
