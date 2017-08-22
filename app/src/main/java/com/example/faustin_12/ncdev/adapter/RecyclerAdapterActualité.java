package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.MainActivity;
import com.example.faustin_12.ncdev.model.ElementActualite;
import com.example.faustin_12.ncdev.notification.DisplayCustomNotification;
import com.example.faustin_12.ncdev.notification.DisplayNotification;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LIONEL KOUEMENI on 18/09/2016.
 */
public class RecyclerAdapterActualité extends RecyclerView.Adapter <RecyclerAdapterActualité.mViewHolder> {
    private static final String TAG = RecyclerAdapterBoiteSnack.class.getSimpleName();
    private List<ElementActualite> mData;
    private LayoutInflater mInflater;
    private ClickListener clickListener;
    Handler mHandler = new Handler();
    Context mContext;
    DisplayCustomNotification displayCustomNotification;
    DisplayNotification displayNotification;

    public RecyclerAdapterActualité (Context context, List<ElementActualite> data){
        this.mData=data;
        this.mContext=context;
        this.mInflater=LayoutInflater.from(context);
        //displayCustomNotification = new DisplayCustomNotification(context, "NCDev", " ", " ", " ", " ");
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
        Log.d(TAG, "onBindViewHolder" +position);
        ElementActualite currentObj = mData.get(position);
        holder.setData(currentObj, position);

    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public int getPrice (int position){
        return mData.get(position).getPrice();
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
    public String getDate (int position) {return mData.get(position).getDate().toString();}
    public int getNbreCom (int position) {return mData.get(position).getComment();}


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementActualite item) {
        mData.add(item);
        notifyItemInserted(mData.size());
        /*displayCustomNotification.setnDescription("Actualité : " + item.getDescription());
        displayCustomNotification.setnTickerM("Actualité : " + item.getDescription());
        displayCustomNotification.setnTime(""+(new SimpleDateFormat("HH:MM").format(Calendar.getInstance().getTime())));
        mHandler.post(displayCustomNotification);*/
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
            date = (TextView)itemView.findViewById(R.id.days);
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
            this.price.setText("" + current.getPrice());
            this.date.setText("" + current.getDate());
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
