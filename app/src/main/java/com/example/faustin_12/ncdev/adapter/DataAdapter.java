package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.FeedItem;

import java.util.List;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    //private ArrayList<FeedItem> articles;
    private List<FeedItem> articles;
    private Context mContext;

    public DataAdapter(Context context, List<FeedItem> android) {
        this.articles = android;
        this.mContext = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.title.setText(articles.get(i).getTitle());
        viewHolder.description.setText(articles.get(i).getDescription());
        viewHolder.time.setText(articles.get(i).getPubDate());
        viewHolder.author.setText(articles.get(i).getAuthor());

        Glide.with(mContext)
                .load(articles.get(i).getEnclosure().getEnclosureLink())
                .fallback(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.icom);
    }

    public String getTitle (int position){
        return articles.get(position).getTitle();
    }
    public String getDescription (int position){
        return articles.get(position).getDescription();
    }
    public String getIconLink (int position){
        return articles.get(position).getEnclosure().getEnclosureLink();
    }
    public String getTime (int position) {return articles.get(position).getPubDate();}
    public String getNbreCom (int position) {return articles.get(position).getComments();}

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, time, author;
        private ImageView icom;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.list_item_title);
            description = (TextView) view.findViewById(R.id.list_item_description);
            time = (TextView) view.findViewById(R.id.time);
            author = (TextView) view.findViewById(R.id.author);
            icom = (ImageView) view.findViewById(R.id.list_item_icon);

        }
    }
}

