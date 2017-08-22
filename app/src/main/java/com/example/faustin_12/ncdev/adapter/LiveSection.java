package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.fragment.DetailLiveFragment;
import com.example.faustin_12.ncdev.model.ElementLive;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by FAUSTIN-12 on 17/08/2017.
 */

public class LiveSection extends StatelessSection {
    String sectionTitle;
    List<ElementLive> sectionData;
    public Context context;
    public FragmentManager fragmentManager;
    public SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    public LiveSection(String sectionTitle, List<ElementLive> sectionData, Context context,
                       FragmentManager fragmentManager, SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter) {
        super(R.layout.section_ex2_header, R.layout.section_ex2_footer, R.layout.particular_row_live2);
        this.sectionTitle = sectionTitle;
        this.sectionData = sectionData;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.sectionedRecyclerViewAdapter = sectionedRecyclerViewAdapter;
    }

    @Override
    public int getContentItemsTotal() {
        return sectionData.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new LiveViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final LiveViewHolder holder = (LiveViewHolder) viewHolder;
        holder.setData(sectionData.get(position));
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.format("You clicked on position #%s of Section %s",
                        sectionedRecyclerViewAdapter.getPositionInSection(holder.getAdapterPosition()),
                        sectionData.get(position).getTitle()), Toast.LENGTH_SHORT).show();
                DetailLiveFragment temps = new DetailLiveFragment();
                ImageView icon = (ImageView) v.findViewById(R.id.live_item);
                temps.setMyImageView(icon);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView0, temps).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new LiveHeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        LiveHeaderHolder headerHolder = (LiveHeaderHolder) holder;

        // bind your header view here
        headerHolder.sectionTitle.setText(this.sectionTitle);
    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new LiveFooterHolder(view);
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        LiveFooterHolder footerHolder = (LiveFooterHolder) holder;

        footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.format("You clicked on footer of Section %s", sectionTitle), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void addItem(ElementLive item) {
        this.sectionData.add(item);
    }

    public List<ElementLive> getData (){ return this.sectionData;}

    class LiveHeaderHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        public TextView sectionTitle;

        public LiveHeaderHolder(View view) {
            super(view);
            rootView = view;
            sectionTitle = (TextView) view.findViewById(R.id.sectionLiveTitle);
        }
    }
    class LiveFooterHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        public TextView sectionFooter;

        public LiveFooterHolder(View view) {
            super(view);
            rootView = view;
            sectionFooter = (TextView) view.findViewById(R.id.liveSeeMore);
        }
    }

    class LiveViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private ImageView imgItem;
        private ImageView liveclockicon;
        private TextView liveTitle;
        private TextView liveTime;
        private TextView nbreVue;
        private View categorycolor;

        public LiveViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = (ImageView) view.findViewById(R.id.live_item);
            liveTitle = (TextView) view.findViewById(R.id.live_title);
            categorycolor = view.findViewById(R.id.Couleur_categorie);
        }

        public void setData(ElementLive current){
            this.liveTitle.setText(""+current.getTitle());
            this.categorycolor.setBackgroundColor(Color.RED);
            Glide.with(context).load(current.getIconIDLIVE()).placeholder(R.drawable.placeholder).into(this.imgItem);

        }
    }
}
