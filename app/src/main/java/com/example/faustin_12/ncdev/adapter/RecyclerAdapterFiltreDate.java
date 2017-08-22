package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementFiltreDate;
import com.example.faustin_12.ncdev.notification.DisplayCustomNotification;
import com.example.faustin_12.ncdev.notification.DisplayNotification;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 12/11/2016.
 */
public class RecyclerAdapterFiltreDate extends RecyclerView.Adapter <RecyclerAdapterFiltreDate.mViewHolder> {
        private static final String TAG = RecyclerAdapterBoiteSnack.class.getSimpleName();
        private List<ElementFiltreDate> mData;
        private LayoutInflater mInflater;
        private ClickListener clickListener;
        Handler mHandler = new Handler();
        Context mContext;
        DisplayCustomNotification displayCustomNotification;
        DisplayNotification displayNotification;

        public RecyclerAdapterFiltreDate(Context context, List<ElementFiltreDate> data){
            this.mData=data;
            this.mInflater=LayoutInflater.from(context);
            displayCustomNotification = new DisplayCustomNotification(context, "NCDev", " ", " ", " ", " ");
            //displayNotification = new DisplayNotification(context, "NCDev", " ", " ", " ", " ");
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
            Log.d(TAG, "onBindViewHolder" +position);
            ElementFiltreDate currentObj = mData.get(position);
            holder.setData(currentObj, position);

        }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public int getTitle (int position){
        return mData.get(position).getTitle();
    }

    public String getDate (int position) {return mData.get(position).getDate();}


        public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementFiltreDate item) {
        mData.add(item);
        notifyItemInserted(mData.size());
        displayCustomNotification.setnDescription("Evenemen : " + item.getTitle());
        displayCustomNotification.setnTickerM("Actualité : " + item.getTitle());
        displayCustomNotification.setnTime(""+(new SimpleDateFormat("HH:MM").format(Calendar.getInstance().getTime())));
        mHandler.post(displayCustomNotification);
    }

    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView  date,title;
        ElementFiltreDate current;
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

        public void setData(ElementFiltreDate current, int position) {

            this.date.setText(current.getDate());
            this.title.setText(""+current.getTitle());
            this.current=current;
            this.position=position;
        }
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
