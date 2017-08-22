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
import com.example.faustin_12.ncdev.model.ElementCategorie;
import com.example.faustin_12.ncdev.notification.DisplayCustomNotification;
import com.example.faustin_12.ncdev.notification.DisplayNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 01/10/2016.
 */
public class RecyclerAdapterEvenement extends RecyclerView.Adapter <RecyclerAdapterEvenement.mViewHolder> {
    private static final String TAG = RecyclerAdapterBoiteSnack.class.getSimpleName();
    private List<ElementCategorie> categories;
    private ClickListener clickListener;
    private LayoutInflater mInflater;
    Handler mHandler = new Handler();
    Context mContext;
    DisplayCustomNotification displayCustomNotification;
    DisplayNotification displayNotification;

    public RecyclerAdapterEvenement(Context context, List<ElementCategorie> data) {
        this.categories = data;
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
        Log.d(TAG, "onBindViewHolder" + position);
        ElementCategorie currentObj = categories.get(position);
        holder.setData(currentObj, position);

    }

    public String getTitle(int position) {
        return categories.get(position).getName();
    }

    public int getImageID(int position) {
        return categories.get(position).getImageID();
    }

    public int getNbreEvents(int position) {
        return categories.get(position).getNombreevts();
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void addInfo(ElementCategorie item) {
        categories.add(item);
        notifyItemInserted(categories.size());
    }

    public void setData (List<ElementCategorie> infos){
        categories = new ArrayList<>();
        categories.addAll(infos);
        notifyDataSetChanged();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,nbreEvents;
        ImageView imgRow;
        ElementCategorie current;
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

        public void setData(ElementCategorie current, int position) {
            this.title.setText(current.getName());
            //this.imgRow.setImageResource(current.getImageID());
            this.nbreEvents.setText("" + current.getNombreevts());
            this.current = current;
            this.position = position;
        }

    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}




