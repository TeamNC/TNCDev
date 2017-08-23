package com.example.faustin_12.ncdev.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by LIONEL KOUEMENI on 22/04/2017.
 */

public class LiveFragment2 extends Fragment {

        private SectionedRecyclerViewAdapter sectionAdapter;
    android.support.v4.app.FragmentManager mFragmentManager;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.live_layout, container, false);

            sectionAdapter = new SectionedRecyclerViewAdapter();

            sectionAdapter.addSection(new NewsSection(NewsSection.WORLD));
            sectionAdapter.addSection(new NewsSection(NewsSection.BUSINESS));
            sectionAdapter.addSection(new NewsSection(NewsSection.TECHNOLOGY));
            sectionAdapter.addSection(new NewsSection(NewsSection.SPORTS));

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.LiveList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(sectionAdapter);
            mFragmentManager=getActivity().getSupportFragmentManager();
            return view;
        }



        class NewsSection extends StatelessSection {

            final static int WORLD = 0;
            final static int BUSINESS = 1;
            final static int TECHNOLOGY = 2;
            final static int SPORTS = 3;

            final int topic;

            String title;
            List<String> list;
            int imgPlaceholderResId;
            int imgPlaceholderResId2;
            int colorcategory;
            public NewsSection(int topic) {
                super(R.layout.section_ex2_header, R.layout.section_ex2_footer, R.layout.particular_row_live);

                this.topic = topic;

                switch (topic) {
                    case WORLD:
                        this.title = getString(R.string.world_topic);
                        this.list = getNews(R.array.news_world);
                        this.imgPlaceholderResId = R.drawable.images3;
                        this.imgPlaceholderResId2= R.drawable.ic_watch;
                        this.colorcategory= R.color.categorieone;
                        break;
                    case BUSINESS:
                        this.title = getString(R.string.biz_topic);
                        this.list = getNews(R.array.news_biz);
                        this.imgPlaceholderResId = R.drawable.images6;
                        this.imgPlaceholderResId2= R.drawable.ic_time_schedule;
                        this.colorcategory= R.color.categorietwo;
                        break;
                    case TECHNOLOGY:
                        this.title = getString(R.string.tech_topic);
                        this.list = getNews(R.array.news_tech);
                        this.imgPlaceholderResId = R.drawable.veste;
                        this.imgPlaceholderResId2= R.drawable.future_time;
                        this.colorcategory= R.color.categoriethree;
                        break;
                    case SPORTS:
                        this.title = getString(R.string.sports_topic);
                        this.list = getNews(R.array.news_sports);
                        this.imgPlaceholderResId = R.drawable.audrey;
                        this.imgPlaceholderResId2= R.drawable.ic_time_off;
                        this.colorcategory= R.color.categoriefour;
                        break;
                }

            }

            private List<String> getNews(int arrayResource) {
                return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
            }

            @Override
            public int getContentItemsTotal() {
                return list.size();
            }

            @Override
            public RecyclerView.ViewHolder getItemViewHolder(View view) {
                return new ItemViewHolder(view);
            }

            @Override
            public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
                final ItemViewHolder itemHolder = (ItemViewHolder) holder;

                String[] item = list.get(position).split("\\|");

               // itemHolder.tvHeader.setText(item[0]);
              //  itemHolder.tvDate.setText(item[1]);
                itemHolder.imgItem.setImageResource(imgPlaceholderResId);
                itemHolder.liveclockicon.setImageResource(imgPlaceholderResId2);
                itemHolder.categorycolor.setBackgroundColor(colorcategory);

                itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s",
                                sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
                        DetailLiveFragment temps = new DetailLiveFragment();
                       // ImageView icon = (ImageView) v.findViewById(R.id.live_item);
                       // temps.setMyImageView(icon);
                        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.containerView0, temps).addToBackStack(null).commit();
                    }
                });
            }

            @Override
            public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
                return new HeaderViewHolder(view);
            }

            @Override
            public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
                HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

                headerHolder.tvTitle.setText(title);
            }

            @Override
            public RecyclerView.ViewHolder getFooterViewHolder(View view) {
                return new FooterViewHolder(view);
            }

            @Override
            public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
                FooterViewHolder footerHolder = (FooterViewHolder) holder;

                footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder {

            private final TextView tvTitle;

            public HeaderViewHolder(View view) {
                super(view);

                tvTitle = (TextView) view.findViewById(R.id.sectionLiveTitle);
            }
        }

        class FooterViewHolder extends RecyclerView.ViewHolder {

            private final View rootView;

            public FooterViewHolder(View view) {
                super(view);

                rootView = view;
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            private final View rootView;
            private final ImageView imgItem;
            private final ImageView liveclockicon;
          //  private final TextView tvDate;
            private final View categorycolor;

            public ItemViewHolder(View view) {
                super(view);

                rootView = view;
                imgItem = (ImageView) view.findViewById(R.id.live_item);
                liveclockicon = (ImageView) view.findViewById(R.id.live_time_icon);
               // tvDate = (TextView) view.findViewById(R.id.live_date);
                categorycolor=view.findViewById(R.id.Couleur_categorie);
            }
        }
    }
