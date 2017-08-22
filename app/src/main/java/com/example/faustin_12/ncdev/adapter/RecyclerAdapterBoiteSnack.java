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
import com.example.faustin_12.ncdev.model.ElementBoiteSnack;
import com.example.faustin_12.ncdev.notification.DisplayCustomNotification;
import com.example.faustin_12.ncdev.notification.DisplayNotification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by FAUSTIN-12 on 12/05/2016.
 */
public class RecyclerAdapterBoiteSnack extends RecyclerView.Adapter <RecyclerAdapterBoiteSnack.mViewHolder> {
    private static final String TAG = RecyclerAdapterBoiteSnack.class.getSimpleName();
    private List<ElementBoiteSnack> mData;
    private LayoutInflater mInflater;
    private ClickListener clickListener;
    Handler mHandler = new Handler();
    Context mContext;
    DisplayCustomNotification displayCustomNotification;
    DisplayNotification displayNotification;

    public RecyclerAdapterBoiteSnack(Context context, List<ElementBoiteSnack> data){
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
        ElementBoiteSnack currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }

    public String getTitle (int position){
        return mData.get(position).getTitle();
    }
    public String getDescription (int position){
        return mData.get(position).getNameDJ();
    }
    public int getIcon (int position){
        return mData.get(position).getImageID();
    }
    public String getLocalisation (int position) {return mData.get(position).getPlace();}
    public int getPrices (int position) {return mData.get(position).getPrices();}


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData (List<ElementBoiteSnack> infos){
        mData = new ArrayList<>();
        mData.addAll(infos);
        notifyDataSetChanged();
    }

    public void addInfo (ElementBoiteSnack item){
        mData.add(item);
        notifyItemInserted(mData.size());
        displayCustomNotification.setnDescription("Nouvelle Boite ! " + item.getTitle());
        displayCustomNotification.setnTickerM("Nouvelle Boite ! " + item.getTitle());
        displayCustomNotification.setnTime(""+(new SimpleDateFormat("HH:MM").format(Calendar.getInstance().getTime())));
        mHandler.post(displayCustomNotification);
    }

    public void removeItem (ElementBoiteSnack item){
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
        ElementBoiteSnack current;
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

        public void setData(ElementBoiteSnack current, int position) {
            this.title.setText(current.getTitle());
            this.description.setText(current.getNameDJ());
            this.imgRow.setImageResource(current.getImageID());
            this.localisation.setText(current.getPlace());
            this.nbreLove.setText(""+current.getPrices());
            this.current=current;
            this.position=position;
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
