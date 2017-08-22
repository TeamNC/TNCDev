package com.example.faustin_12.ncdev.activity.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public  class DetailFragment extends Fragment implements View.OnClickListener {
    private LinearLayout mRevealView;
    private boolean hidden = true;
    private ImageButton gallery_btn, photo_btn, video_btn, audio_btn, location_btn, contact_btn;


    public String title= "Detail";
    public ImageView myImageView;
    public String date = "H.M";
    public String price =" 50000";
    public FragmentManager mFragmentManager ;
    DrawerLayout mDrawerLayout;

    private Context context;
    private CollapsingToolbarLayout collapsingToolbar = null;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mFragmentManager = getFragmentManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /**
         *Inflate fragment_fixe and setup Views.
         */


        View x = inflater.inflate(R.layout.fragment_detail, null);
        //TextView tv= (TextView)x.findViewById(R.id.title1);
        TextView prix= (TextView)x.findViewById(R.id.prix_details);
        ImageView imageView = (ImageView) x.findViewById(R.id.imgRow);

        if (myImageView == null)
            myImageView=imageView;
        imageView.setImageDrawable(myImageView.getDrawable());

        collapsingToolbar =
                (CollapsingToolbarLayout) x.findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(title);

        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
        dynamicToolbarColor();
        toolbarTextAppearance();
        Toolbar toolbarD = (Toolbar) x.findViewById(R.id.toolbarD);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarD);

        mRevealView = (LinearLayout)x.findViewById(R.id.reveal_items);
        mRevealView.setVisibility(View.GONE);

        gallery_btn = (ImageButton) x.findViewById(R.id.gallery_img_btn);
        photo_btn = (ImageButton)x.findViewById(R.id.photo_img_btn);
        video_btn = (ImageButton)x.findViewById(R.id.video_img_btn);
       // audio_btn = (ImageButton) x.findViewById(R.id.audio_img_btn);
        location_btn = (ImageButton)x.findViewById(R.id.location_img_btn);
        //contact_btn = (ImageButton)x.findViewById(R.id.contact_img_btn);

        gallery_btn.setOnClickListener(this);
        photo_btn.setOnClickListener(this);
        video_btn.setOnClickListener(this);
        //audio_btn.setOnClickListener(this);
        location_btn.setOnClickListener(this);
        //contact_btn.setOnClickListener(this);


        toolbarD.setNavigationIcon(R.drawable.ic_action_important);
        toolbarD.setNavigationIcon(R.drawable.ic_action_back);
        toolbarD.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Retour", Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) getActivity()).onBackPressed();
            }
        });

        /*Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);*/

        prix.setText(price);

        ;

        mFragmentManager = getActivity().getSupportFragmentManager();

        return x;
    }

    public void setTitle (String title){
        this.title= title ;
    }
    public void setDate  (String date)  {this.date=date;}
    public void setPrice(String price) {this.price=price;}

    public void setMyImageView(ImageView imageView){
        this.myImageView=imageView;
    }
    //We’ll pass our image view’s bitmap to the Palette API,
    // which will generate colors based on the image in an PaletteAsyncListener.
    // Upon completion,
    // we can fetch the color we want and set it to our CollapsingToolbarLayout,
    //  which in turn will color our Toolbar when we scroll up.
   private void dynamicToolbarColor() {
        myImageView.buildDrawingCache();
        final Bitmap bitmap = ((BitmapDrawable)myImageView.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //int primaryDark = getResources().getColor(R.color.primary_dark);
               // int primary = getResources().getColor(R.color.primary);
               collapsingToolbar.setContentScrimColor(palette.getVibrantColor(0));
              collapsingToolbar.setStatusBarScrimColor(palette.getVibrantColor(0));
              //  collapsingToolbar.setStatusBarScrimColor(palette.getLightVibrantColor(primary));
                //collapsingToolbar.setCollapsedTitleTextColor(palette.getDarkMutedColor(primary));
               // collapsingToolbar.setExpandedTitleColor(palette.getDarkMutedColor(primary));

            }
        });
    }
    /*target = new Target() {
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    image.setImageBitmap(bitmap);
                    int defaultColor = getResources().getColor(R.color.black);
                    toolbar.setBackgroundColor(palette.getLightVibrantColor(defaultColor));
                    toolbar.setTitleTextColor(palette.getDarkMutedColor(defaultColor));
                    toolbar.setTitle(getString(R.string.app_name));
                }
            });
        }
    };
    Picasso.with(this)
            .load(getIntent().getStringExtra("url"))
            .into(target);*/

    //Setting The Title Large when the layout is expanded(layout is fully visible)
    // and Smaller when the layout is collapsed(layout is scrolled off screen)
    private void toolbarTextAppearance() {
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }

//here we set up the attach aniimation with multiple icon like whatsapp
    @Override
    public void onClick(View v) {
        hideRevealView();
        switch (v.getId()) {

            case R.id.gallery_img_btn:

                break;
            case R.id.photo_img_btn:

                break;
            case R.id.video_img_btn:

                break;
          //  case R.id.audio_img_btn:

              //  break;
            case R.id.location_img_btn:

                break;
           // case R.id.contact_img_btn:

               // break;
        }

    }
    private void hideRevealView() {
        if (mRevealView.getVisibility() == View.VISIBLE) {
            mRevealView.setVisibility(View.GONE);
            hidden = true;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detail_actualite_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
       //     return true;
       // }
        switch (id) {
            case R.id.action_settings: {
                     return true;
                }
            case R.id.action_clip:

                int cx = (mRevealView.getLeft() + mRevealView.getRight());
                int cy = mRevealView.getTop();
                int radius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());

                //Below Android LOLIPOP Version
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    SupportAnimator animator =
                            ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(700);

                    SupportAnimator animator_reverse = animator.reverse();

                    if (hidden) {
                        mRevealView.setVisibility(View.VISIBLE);
                        animator.start();
                        hidden = false;
                    } else {
                        animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                            @Override
                            public void onAnimationStart() {

                            }

                            @Override
                            public void onAnimationEnd() {
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;

                            }

                            @Override
                            public void onAnimationCancel() {

                            }

                            @Override
                            public void onAnimationRepeat() {

                            }
                        });
                        animator_reverse.start();
                    }
                }
                // Android LOLIPOP And ABOVE Version
                else {
                    if (hidden) {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                        mRevealView.setVisibility(View.VISIBLE);
                        anim.start();
                        hidden = false;
                    } else {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, radius, 0);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;
                            }
                        });
                        anim.start();
                    }
                }
                return true;

            case android.R.id.home:
              //  supportFinishAfterTransition();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
