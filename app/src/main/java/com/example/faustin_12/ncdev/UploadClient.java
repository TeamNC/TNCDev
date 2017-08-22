package com.example.faustin_12.ncdev;

import com.example.faustin_12.ncdev.activity.MainActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FAUSTIN-12 on 02/07/2017.
 */

public class UploadClient {
    private static String ROOT_URL = "http://192.168.8.101";


    public UploadClient(String root_url) {
        ROOT_URL = root_url;
    }

    private static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static FileApi getApiService() {
        return getRetroClient().create(FileApi.class);
    }
}
