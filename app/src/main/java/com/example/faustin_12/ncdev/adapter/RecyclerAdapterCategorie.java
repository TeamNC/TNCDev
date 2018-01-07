package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.BlankActivity;
import com.example.faustin_12.ncdev.activity.fragment.BlankFragment;
import com.example.faustin_12.ncdev.activity.fragment.DialogImage;
import com.example.faustin_12.ncdev.model.ElementCategorie;

import java.util.List;


/**
 * Created by LIONEL KOUEMENI on 01/10/2016.
 */
public class RecyclerAdapterCategorie extends RecyclerView.Adapter <RecyclerAdapterCategorie.mViewHolder> {
    private List<ElementCategorie> mData;
    private ClickListener clickListener;
    private LayoutInflater mInflater;
    private FragmentManager fragmentManager;
    private Context context;

    public RecyclerAdapterCategorie(Context context, List<ElementCategorie> data, FragmentManager fragmentManager) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        if (position == 0) viewType = 1;
        return viewType;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = mInflater.inflate(R.layout.row_categorie_thales, parent, false);
                return new mViewHolder(view);
            case 1:
                View pview = mInflater.inflate(R.layout.row_categorie_thales, parent, false);
                return new mViewHolder(pview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, final int position) {
        ElementCategorie currentObj = mData.get(position);
        holder.setData(currentObj, position);
        //String transitionName = "";
        //transitionName = holder.name.getText().toString();
        //ViewCompat.setTransitionName(holder.imgRow, currentObj.getTitle());

        holder.imgRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat anim = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((android.app.Activity) context, holder.imgRow,
                                ViewCompat.getTransitionName(holder.imgRow));

                Intent intent = new Intent(context, BlankActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent, anim.toBundle());

                /*DialogFragment dialogImage = new DialogImage();
                String transitionName = "";
                //transitionName = holder.name.getText().toString();
                transitionName = ViewCompat.getTransitionName(holder.imgRow);
                BlankFragment blankFragment = new BlankFragment();
                blankFragment.setTransitionName(transitionName);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                /*Fragment prev = fragmentManager.findFragmentByTag("dialogimage");
                if(prev != null){
                    fragmentTransaction.remove(prev);
                }

                Toast.makeText(context, "Start " + transitionName, Toast.LENGTH_SHORT).show();

                fragmentTransaction
                        .addSharedElement(holder.imgRow, transitionName)
                        .replace(R.id.containerView0, blankFragment).addToBackStack(null).commit();
                //dialogImage.show(fragmentTransaction, "dialogimage");*/

            }
        });

    }

    public ElementCategorie getItem(int position) {
        return mData.get(position);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addInfo(ElementCategorie item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    public void addInfo (int position, ElementCategorie item){
        mData.add(position, item);
        notifyItemInserted(mData.size());
        notifyItemInserted(position);
    }

    public void setData (List<ElementCategorie> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    public List<ElementCategorie> getData(){
        return this.mData;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,nbreEvents;
        ImageView imgRow;
        ElementCategorie current;
        int position;

        public mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.title_pop);
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
            this.name.setText(current.getTitle());
            Glide.with(context).load(current.getImage()).placeholder(R.drawable.placeholder).centerCrop().into(this.imgRow);
            this.nbreEvents.setText("" + current.getNombreevts());
            this.current = current;
            this.position = position;
        }

    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}




