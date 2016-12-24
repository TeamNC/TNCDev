package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.FeedItem;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<FeedItem> articles;
    private Context mContext;
    private ClickListener clickListener;
    public String root = Environment.getExternalStorageDirectory().toString() + "/NCDev_images";

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
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.title.setText(articles.get(i).getTitle());
        viewHolder.description.setText(articles.get(i).getDescription());
        viewHolder.time.setText(articles.get(i).getPubDate());
        viewHolder.author.setText(articles.get(i).getAuthor());

        String url = articles.get(i).getInternalImageUrl();
        if (url != null){
            File test = new File (url);
            if (!test.exists())
                url = articles.get(i).getEnclosure().getEnclosureLink();
        }
        else{
            url = articles.get(i).getEnclosure().getEnclosureLink();
        }
        Toast.makeText(mContext, url, Toast.LENGTH_SHORT).show();

        Glide.with(mContext)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        //saveImage(icon, "Image" + articles.get(i).getId(), root);
                        return false;
                    }
                })
                .fallback(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.icom);

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public FeedItem getItem(int position) {
        return articles.get(position);
    }

    public void addItem(FeedItem article) {
        articles.add(article);
        notifyItemInserted(articles.size());
    }
    public void addItem(List<FeedItem> article) {
        for(int i=0; i< article.size(); i++) {
            articles.add(article.get(i));
        }
        notifyItemInserted(articles.size());
    }

    public void removeItem (FeedItem article){
        int position = articles.indexOf(article);
        if (position != -1){
            articles.remove(article);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, articles.size());
        }
    }
    public void removeItem (int position){
        articles.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, articles.size());
    }

    public void setData (List<FeedItem> data){
        this.articles = data;
        notifyDataSetChanged();
    }

    public void replaceItem(final FeedItem newItem, final int position) {
        articles.remove(position);
        articles.add(position, newItem);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, description, time, author;
        private ImageView icom;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.list_item_title);
            description = (TextView) view.findViewById(R.id.list_item_description);
            time = (TextView) view.findViewById(R.id.time);
            author = (TextView) view.findViewById(R.id.author);
            icom = (ImageView) view.findViewById(R.id.list_item_icon);

        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
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

