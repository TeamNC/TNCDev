package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementEvenement;
import com.example.faustin_12.ncdev.notification.DisplayCustomNotification;
import com.example.faustin_12.ncdev.notification.DisplayNotification;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<ElementEvenement> evenements;
    private Context mContext;
    private ClickListener clickListener;
    public String root = Environment.getExternalStorageDirectory().toString() + "/NCDev_images";
    Handler mHandler = new Handler();
    DisplayCustomNotification displayCustomNotification;
    DisplayNotification displayNotification;

    public DataAdapter(Context context, List<ElementEvenement> evenements) {
        this.evenements = evenements;
        this.mContext = context;
        //displayCustomNotification = new DisplayCustomNotification(context, "NCDev", " ", " ", " ", " ");
        //displayNotification = new DisplayNotification(context, "NCDev", " ", " ", " ", " ");
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder" +position);
        ElementEvenement currentObj = evenements.get(position);
        viewHolder.setData(currentObj, position);
    }

    public void setData (List<ElementEvenement> infos){
        evenements = new ArrayList<>();
        evenements.addAll(infos);
        notifyDataSetChanged();
    }

    public List<ElementEvenement> getData(){
        return this.evenements;
    }

    public void addInfo (ElementEvenement item){
        evenements.add(item);
        notifyItemInserted(evenements.size());
        //displayCustomNotification.setnDescription("Nouvelle Boite ! " + item.getTitle());
        //displayCustomNotification.setnTickerM("Nouvelle Boite ! " + item.getTitle());
        //displayCustomNotification.setnTime(""+(new SimpleDateFormat("HH:MM").format(Calendar.getInstance().getTime())));
        //mHandler.post(displayCustomNotification);
    }
    public void addInfo (int position, ElementEvenement item){
        evenements.add(position, item);
        notifyItemInserted(evenements.size());
        notifyItemInserted(position);
        //displayCustomNotification.setnDescription("Nouvelle Boite ! " + item.getTitle());
        //displayCustomNotification.setnTickerM("Nouvelle Boite ! " + item.getTitle());
        //displayCustomNotification.setnTime(""+(new SimpleDateFormat("HH:MM").format(Calendar.getInstance().getTime())));
        //mHandler.post(displayCustomNotification);
    }

    public String getTitle (int position){
        return evenements.get(position).getTitle();
    }
    public String getDescription (int position){return evenements.get(position).getTitle();}
    public int getDate (int position){return evenements.get(position).getNbreEvents();}

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }



    @Override
    public int getItemCount() {
        return evenements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, date, description;
        private ImageView icom;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.list_item_title);
            description = (TextView) view.findViewById(R.id.list_item_description);
            date = (TextView) view.findViewById(R.id.time);
            icom = (ImageView) view.findViewById(R.id.list_item_icon);

        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void setData(ElementEvenement current, int position) {
            this.title.setText(current.getTitle());
            this.description.setText(current.getTitle());
            Date date = new GregorianCalendar().getTime();
            try {
                date = (new SimpleDateFormat("yyyyMMdd")).parse(""+current.getNbreEvents());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(!(date == (new GregorianCalendar()).getTime()))
                this.date.setText(""+(new SimpleDateFormat("dd/MM/yyyy")).format(date));
        }
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void saveImage(ImageView imageView, String fileName, String filePath){
        imageView.buildDrawingCache();

        Bitmap image = imageView.getDrawingCache();
        File myDir = new File(filePath);
        myDir.mkdirs();

        fileName = fileName + ".jpg";

        File file = new File(myDir, fileName);
        //Log.i(TAG, "" + file);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

