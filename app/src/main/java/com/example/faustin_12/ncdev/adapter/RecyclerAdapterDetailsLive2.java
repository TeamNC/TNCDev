package com.example.faustin_12.ncdev.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.model.ElementDetailsLive;
import com.example.faustin_12.ncdev.view.LoadingFeedItemView;
import com.example.faustin_12.ncdev.view.SquaredFrameLayout;
import com.example.faustin_12.ncdev.view.SquaredImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by LIONEL KOUEMENI on 09/04/2017.
 */
public class RecyclerAdapterDetailsLive2 extends RecyclerView.Adapter <RecyclerAdapterDetailsLive2.mViewHolder> {
    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";
    private final List<RecyclerAdapterDetailsLive2.FeedItem> feedItems = new ArrayList<>();

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;
    private boolean showLoadingView = false;
    private Context context;
    private static final String TAG = RecyclerAdapterDetailsLive2.class.getSimpleName();
    private List<ElementDetailsLive> mLData;
    private LayoutInflater mInflater;
    private final Map<Integer, Integer> likesCount = new HashMap<>();


    public RecyclerAdapterDetailsLive2(Context context, List<ElementDetailsLive> data){
        this.context = context;
        this.mLData=data;
        this.mInflater=LayoutInflater.from(context);
    }

  /*  @Override
    public int getItemViewType(int position) {
        int viewType=0;
        if(position==0) viewType=1;
        return viewType;
    }*/

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.particular_row_live_details5, parent, false);
            mViewHolder MViewHolder = new mViewHolder(view);
            setupClickableViews(view, MViewHolder);
            return MViewHolder;
        } else if (viewType == VIEW_TYPE_LOADER) {
            LoadingFeedItemView view = new LoadingFeedItemView(context);
            view.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            );return new LoadingmViewHolder(view);
        }
        return null;
    }

    private void setupClickableViews(final View view, final mViewHolder MViewHolder) {
        /*cellFeedViewHolder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onCommentsClick(view, cellFeedViewHolder.getAdapterPosition());
            }
        });
        cellFeedViewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onMoreClick(v, cellFeedViewHolder.getAdapterPosition());
            }
        });*/
        MViewHolder.ivFeedCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = MViewHolder.getAdapterPosition();
                feedItems.get(adapterPosition).likesCount++;
                notifyItemChanged(adapterPosition, ACTION_LIKE_IMAGE_CLICKED);
               // if (context instanceof MainActivity) {
                 //   ((MainActivity) context).showLikedSnackbar();
                //}
            }
        });
        MViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = MViewHolder.getAdapterPosition();
                feedItems.get(adapterPosition).likesCount++;
                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);
            //    if (get(Activity)) {
                //    ((MainActivity) context).showLikedSnackbar();
              //  }
            }
        });
      /*  cellFeedViewHolder.ivUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onProfileClick(view);
            }
        });*/
    }

    @Override
    public void onBindViewHolder(mViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder" +position);
        ElementDetailsLive currentObj = mLData.get(position);
        viewHolder.setData(currentObj, position);
         viewHolder.bindView(feedItems.get(position));

        if (getItemViewType(position) == VIEW_TYPE_LOADER) {
            bindLoadingFeedItem((LoadingmViewHolder) viewHolder);
        }

    }
    private void bindLoadingFeedItem(final  LoadingmViewHolder holder) {
        holder.loadingFeedItemView.setOnLoadingFinishedListener(new LoadingFeedItemView.OnLoadingFinishedListener() {
            @Override
            public void onLoadingFinished() {
                showLoadingView = false;
                notifyItemChanged(0);
            }
        });
        holder.loadingFeedItemView.startLoading();
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadingView && position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }




    public String getDescription_Live_Details (int position){
        return mLData.get(position).getDescription();
    }
    public int getDLIcon (int position){
        return mLData.get(position).getIconID();
    }
    public String getSince (int position) {return mLData.get(position).getSince();}
    public int getNbreLove (int position) {return mLData.get(position).getNbreLove();}
    public int getImageDL (int position){return mLData.get(position).getImageID();}
    @Override
    public int getItemCount() {
        return mLData.size();
    }

    public void addInfo(ElementDetailsLive item) {
        mLData.add(item);
        feedItems.add(new FeedItem(item.getNbreLove(), true));
        notifyItemInserted(mLData.size());
    }

    public void showLoadingView() {
        showLoadingView = true;
        notifyItemChanged(0);
    }

  public static class mViewHolder extends RecyclerView.ViewHolder {
        TextView  since, description_live_details,nbreLove;
        ImageView imgDL,DLicon;
        ElementDetailsLive current;
        int position;
        @BindView(R.id.picture_live)
        SquaredImageView ivFeedCenter;
        @BindView(R.id.description_live_details)
        TextView ivFeedBottom;
        @BindView(R.id.btnComments)
        ImageButton btnComments;
        @BindView(R.id.btnLike)
        ImageButton btnLike;
        @BindView(R.id.btnMore)
        ImageButton btnMore;
        @BindView(R.id.vBgLike)
        View vBgLike;
        @BindView(R.id.ivLike)
        ImageView ivLike;
        @BindView(R.id.tsLikesCounter)
        TextSwitcher tsLikesCounter;
        @BindView(R.id.ivUserProfile)
        ImageView ivUserProfile;
        @BindView(R.id.vImageRoot)
        SquaredFrameLayout vImageRoot;
        FeedItem feedItem;

        public mViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        public void setData(ElementDetailsLive current, int position) {
           // this.since.setText(current.getSince());
            this.ivFeedBottom.setText(current.getDescription());
         //   this.DLicon.setImageResource(current.getIconID());
           this.ivFeedCenter.setImageResource(current.getImageID());
           // this.nbreLove.setText(""+current.getNbreLove());
            this.current=current;
            this.position=position;
        }
        public void bindView(RecyclerAdapterDetailsLive2.FeedItem feedItem) {
            this.feedItem = feedItem;
             int adapterPosition = getAdapterPosition();

            btnLike.setImageResource(feedItem.isLiked ? R.drawable.ic_heart_red : R.drawable.ic_empty_love);
            tsLikesCounter.setCurrentText(vImageRoot.getResources().getQuantityString(
                    R.plurals.likes_count, feedItem.likesCount, feedItem.likesCount
            ));
        }
        public FeedItem getFeedItem() {
            return feedItem;
        }
    }
   /* public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }*/

 public static class LoadingmViewHolder extends RecyclerAdapterDetailsLive2.mViewHolder {

    LoadingFeedItemView loadingFeedItemView;

    public LoadingmViewHolder(LoadingFeedItemView view) {
        super(view);
        this.loadingFeedItemView = view;
    }

    @Override
    public void bindView(RecyclerAdapterDetailsLive2.FeedItem feedItem) {
        super.bindView(feedItem);
    }
}


    public static class FeedItem {
       public int likesCount;
       public boolean isLiked;

       public FeedItem(int likesCount, boolean isLiked) {
           this.likesCount = likesCount;
           this.isLiked = isLiked;
       }
   }
    public interface OnFeedItemClickListener {
        void onCommentsClick(View v, int position);

        void onMoreClick(View v, int position);

        void onProfileClick(View v);
    }

}
