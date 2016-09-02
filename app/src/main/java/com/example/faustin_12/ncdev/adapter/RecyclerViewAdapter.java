package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.Informations;

import java.util.ArrayList;


/**
 * Created by FAUSTIN-12 on 19/03/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    ArrayList<Informations> data = new ArrayList<>();
    private Context context;
    private ClickListener clickListener;

    public RecyclerViewAdapter (Context context, ArrayList<Informations> data){
        this.data=data;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view=inflater.inflate(R.layout.single_list_item, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder" + position);
        Informations current = data.get(position);
        holder.setData(current, position);
        holder.imgDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void setData (ArrayList<Informations> infos){
        data = new ArrayList<>();
        data.addAll(infos);
        notifyDataSetChanged();
    }

    public void addInfo (Informations item){
        data.add(item);
        notifyItemInserted(data.size());
    }

    public void removeItem (Informations item){
        int position = data.indexOf(item);
        if (position != -1){
            data.remove(item);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, data.size());
        }
    }

    public void removeItem (int position){
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }

    public String getTitle (int position){
        return data.get(position).getTitre();
    }
    public String getDescription (int position){
        return data.get(position).getDescription();
    }
    public int getIcon (int position){
        return data.get(position).getIconId();
    }
    public String getTime (int position) {return data.get(position).getTime();}
    public int getNbreCom (int position) {return data.get(position).getNbreCom();}
    public ArrayList<Informations> getData(){
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, description, time, nbreCom;
        ImageView imgRow, imgDelete;
        Informations current;
        int position;

        public MyViewHolder (View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.list_item_title);
            description = (TextView) itemView.findViewById(R.id.list_item_description);
            imgRow = (ImageView) itemView.findViewById(R.id.list_item_icon);
            imgDelete = (ImageButton) itemView.findViewById(R.id.button_delete);
            time = (TextView) itemView.findViewById(R.id.time);
            nbreCom = (TextView) itemView.findViewById(R.id.nbreCom);
        }

        public void setData(Informations current, int position) {
            this.title.setText(current.getTitre());
            this.description.setText(current.getDescription());
            this.imgRow.setImageResource(current.getIconId());
            this.time.setText(current.getTime());
            this.nbreCom.setText(""+current.getNbreCom());
            this.current=current;
            this.position=position;
        }

        @Override
        public void onClick(View v) {
            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }
        }
    }
    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public void setFilter (ArrayList<Informations> informations){
        data = new ArrayList<>();
        data.addAll(informations);
        notifyDataSetChanged();
    }
}
