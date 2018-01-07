package com.example.faustin_12.ncdev.activity.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.faustin_12.ncdev.FileApi;
import com.example.faustin_12.ncdev.InterfaceEvenements;
import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.UploadClient;
import com.example.faustin_12.ncdev.activity.MainActivity;
import com.example.faustin_12.ncdev.adapter.RecyclerAdapterActualite;
import com.example.faustin_12.ncdev.model.ElementActualite;
import com.example.faustin_12.ncdev.model.ResponseEvenement;
import com.example.faustin_12.ncdev.model.UploadResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

/**
 * Created by FAUSTIN-12 on 17/03/2016.
 */
public class ActualiteFragment extends Fragment implements RecyclerAdapterActualite.ClickListener{

    // Array of strings storing country names
    RecyclerView recyclerView;
    RecyclerAdapterActualite mAdapter;
    List<ElementActualite> mData = new ArrayList<>();
    FragmentManager mFragmentManager;
    LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout swipeContainer;
    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int pStatus = 0;
    ProgressBar progressBar;
    ImageView progressButton, uploadButton;
    Boolean is_progress = false;
    Handler mHandler = new Handler();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    String mediaPath;
    String server;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        verifyStoragePermissions(getActivity());

        View v = inflater.inflate(R.layout.recyclerview_layout, container, false);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainerC);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressButton = (ImageView) v.findViewById(R.id.progressbutton);
        uploadButton = (ImageView) v.findViewById(R.id.uploadButton);

        if(progressBar.isIndeterminate())
            progressBar.setIndeterminate(false);
        progressBar.setProgress(30);


        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPath !=null)
                    uploadFile();
                if(!is_progress && mediaPath == null)
                    doSomething();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePopup();
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerList);
        mAdapter = new RecyclerAdapterActualite(getContext(), new ArrayList<ElementActualite>());
        mAdapter.setClickListener(this);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLinearLayoutManager.getChildCount();
                    totalItemCount = mLinearLayoutManager.getItemCount();
                    pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            if(mAdapter.getItemCount()>0) {
                                loading = true;
                                loadMore(mAdapter.getData().get(mAdapter.getItemCount() - 1).getId_event());
                            }
                        }
                    }
                }
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(mAdapter.getItemCount()>0)
                    refresh(mAdapter.getData().get(0).getId_event());
                else
                    download();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mFragmentManager=getActivity().getSupportFragmentManager();

        return v;
    }

    public void uploadFile (){
        server=((MainActivity) getActivity()).getServerT();
        is_progress= true;
        Toast.makeText(getContext(), server, Toast.LENGTH_SHORT).show();
        FileApi service = new UploadClient(server).getApiService();

        File file = new File(mediaPath);

        //RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part fileToUpload =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());


        Call<UploadResponse> resultCall = service.uploadFile(fileToUpload, filename);

        resultCall.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {

                // Response Success or Fail
                if (response.isSuccessful()) {
                    if (response.body().getError()==true)

                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                    else{
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
                mediaPath = null;
                is_progress= false;

            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                is_progress= false;
                Toast.makeText(getContext(),"Fail" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showImagePopup() {
        final Intent galleryIntent = new Intent();
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Choose image");
        final CharSequence[] options = {"Images", "Videos", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Media File");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (options[i].equals("Images")) {
                    galleryIntent.setAction(Intent.ACTION_PICK);
                    galleryIntent.setType("image/*");
                    getParentFragment().startActivityForResult(chooserIntent, 100);
                }else if(options[i].equals("Videos")) {
                    galleryIntent.setAction(Intent.ACTION_PICK);
                    galleryIntent.setType("video/*");
                    getParentFragment().startActivityForResult(chooserIntent, 100);
                }else if(options[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            if (data == null) {
                Toast.makeText(getContext(),"Unable to pick media file",Toast.LENGTH_LONG).show();
                return;
            }

            Uri mediaUri = data.getData();
            mediaPath = getRealPathFromURI(mediaUri);
            Toast.makeText(getContext(),"Picked :" + mediaPath,Toast.LENGTH_SHORT).show();
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void doSomething(){
        server=((MainActivity) getActivity()).getServerT();
        new Thread(new Runnable() {
            @Override
            public void run() {
                pStatus = 0;
                is_progress = true;

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressButton.setImageResource(R.drawable.ic_action_close);
                        if(!progressBar.isIndeterminate())
                            progressBar.setIndeterminate(true);
                    }
                });

                while (pStatus <= 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
                while (pStatus <= 200) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(progressBar.isIndeterminate())
                                progressBar.setIndeterminate(false);
                            progressBar.setProgress(pStatus-100);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
                while (pStatus <= 250) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressButton.setImageResource(R.drawable.ic_action_ok);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressButton.setImageResource(R.drawable.ic_action_download);
                    }
                });
                is_progress = false;
            }
        }).start();
    }

    public void refresh (int id_event){
        server=((MainActivity) getActivity()).getServerT();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server) //http://192.168.197.1
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceEvenements apiService = retrofit.create(InterfaceEvenements.class);
        Call<ResponseEvenement> call = apiService.getJSON("/nca/db_get_all_evts_bigger_id.php?id_event="+id_event);
        call.enqueue(new Callback<ResponseEvenement>() {
            @Override
            public void onResponse(Call<ResponseEvenement> call, Response<ResponseEvenement> response) {
                ResponseEvenement responseEvenement = response.body();
                mData = responseEvenement.getEnevements();

                try{
                    if (mData.size()>0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i=0; i<mData.size(); i++){
                                            mAdapter.addInfo(0, mData.get(i));
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                ElementActualite elementActualite = new ElementActualite();
                elementActualite.setTitle("Test");
                mAdapter.addInfo(0, elementActualite);
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseEvenement> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(),"Error : "+ t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());
                ElementActualite elementActualite = new ElementActualite();
                elementActualite.setTitle("Test");
                mAdapter.addInfo(0, elementActualite);
                swipeContainer.setRefreshing(false);
            }
        });
    }

    public void loadMore (int id_event){
        Toast.makeText(getContext(),"More Loading",Toast.LENGTH_LONG).show();
        server=((MainActivity) getActivity()).getServerT();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server) //http://192.168.197.1
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceEvenements apiService = retrofit.create(InterfaceEvenements.class);
        Call<ResponseEvenement> call = apiService.getJSON("/nca/db_get_all_evts_smaller_id.php?id_event="+id_event);
        call.enqueue(new Callback<ResponseEvenement>() {
            @Override
            public void onResponse(Call<ResponseEvenement> call, Response<ResponseEvenement> response) {
                ResponseEvenement responseEvenement = response.body();
                mData = responseEvenement.getEnevements();

                try{
                    if (mData.size()>0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i=0; i<mData.size(); i++){
                                            mAdapter.addInfo(mAdapter.getItemCount(), mData.get(i));
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                loading=false;
            }

            @Override
            public void onFailure(Call<ResponseEvenement> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(),"Error : "+ t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());
                loading=false;
            }
        });
    }

    public void download (){
        // Trailing slash is needed
        ElementActualite elementActualite = new ElementActualite();
        elementActualite.setTitle("Test");
        mAdapter.addInfo(0, elementActualite);
        server=((MainActivity) getActivity()).getServerT();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server) //http://192.168.197.1
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceEvenements apiService = retrofit.create(InterfaceEvenements.class);
        Call<ResponseEvenement> call = apiService.getJSON("/nca/db_get_all_evts.php");
        call.enqueue(new Callback<ResponseEvenement>() {
            @Override
            public void onResponse(Call<ResponseEvenement> call, Response<ResponseEvenement> response) {
                ResponseEvenement responseEvenement = response.body();
                mData = responseEvenement.getEnevements();

                try{
                    if (mData.size()>0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAdapter.setData(mData);
                                        recyclerView.setAdapter(mAdapter);
                                    }
                                });
                            }
                        }).start();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseEvenement> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getContext(),"Error : "+ t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());
                swipeContainer.setRefreshing(false);
            }
        });
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {



        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.actualite_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@Override
    public void itemClicked(View view, int position) {
        DetailFragment temps = new DetailFragment();
        temps.setTitle(mAdapter.getItem(position).getTitle());
        temps.setDate(""+mAdapter.getItem(position).getDate());
        temps.setPrice("" + mAdapter.getItem(position).getDescription());
        ImageView icon = (ImageView) view.findViewById(R.id.imgRow);
        temps.setMyImageView(icon);
        Toast.makeText(getContext(),"Clicked : "+ mAdapter.getItem(position).getTitle(),Toast.LENGTH_LONG).show();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView0, temps).addToBackStack(null).commit();
    }
}
