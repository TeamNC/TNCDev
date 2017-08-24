package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementOffresSpeciales;
import com.example.faustin_12.ncdev.notification.DisplayCustomNotification;
import com.example.faustin_12.ncdev.notification.DisplayNotification;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 14/12/2016.
 */
public class RecyclerAdapterOffresSpeciales extends RecyclerView.Adapter <RecyclerAdapterOffresSpeciales.mViewHolder> {
    private static final String TAG = RecyclerAdapterBoiteSnack.class.getSimpleName();
    private List<ElementOffresSpeciales> mData;
    private ClickListener clickListener;
    private LayoutInflater mInflater;
    Handler mHandler = new Handler();
    Context mContext;
    DisplayCustomNotification displayCustomNotification;
    DisplayNotification displayNotification;

    public RecyclerAdapterOffresSpeciales(Context context, List<ElementOffresSpeciales> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
        displayCustomNotification = new DisplayCustomNotification(context, "NCDev", " ", " ", " ", " ");
        //displayNotification = new DisplayNotification(context, "NCDev", " ", " ", " ", " ");
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        if (position == 0) viewType = 1;
        return viewType;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        switch (viewType) {
            case 0:
                View view = mInflater.inflate(R.layout.particular_row_offres_speciales, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout. particular_row_offres_speciales, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder" + position);
        ElementOffresSpeciales currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }

    public ElementOffresSpeciales getItem(int position) {
        return mData.get(position);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementOffresSpeciales item) {
        mData.add(item);
        notifyItemInserted(mData.size());
        displayCustomNotification.setnDescription("Offre Spéciale : " + item.getTitleOffresSpeciales());
        displayCustomNotification.setnTickerM("Offre Spéciale : " + item.getTitleOffresSpeciales());
        displayCustomNotification.setnTime(""+(new SimpleDateFormat("HH:MM").format(Calendar.getInstance().getTime())));
        mHandler.post(displayCustomNotification);
    }

    public void setData (List<ElementOffresSpeciales> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    public List<ElementOffresSpeciales> getData(){
        return this.mData;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleOffresSpeciales,price,delais;
        ImageView imgRow;
        ElementOffresSpeciales current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleOffresSpeciales = (TextView) itemView.findViewById(R.id.titleOffresSpeciales);
            delais = (TextView) itemView.findViewById(R.id.delaisOffresSpeciales);
            imgRow = (ImageView) itemView.findViewById(R.id.thumbnailOffresSpeciales);
            price = (TextView) itemView.findViewById(R.id.price);
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementOffresSpeciales current, int position) {
            this.titleOffresSpeciales.setText(current.getTitleOffresSpeciales());
            this.imgRow.setImageResource(current.getImageIDOffresSpeciales());
            this.delais.setText("" + current.getDelais()+"jours");
            this.current = current;
            this.position = position;
        }

    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}



