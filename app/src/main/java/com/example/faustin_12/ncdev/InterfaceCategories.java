package com.example.faustin_12.ncdev;

import com.example.faustin_12.ncdev.model.ResponseCategorie;
import com.example.faustin_12.ncdev.model.ResponseEvenement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by FAUSTIN-12 on 23/03/2017.
 */

public interface InterfaceCategories {


    @GET
    Call<ResponseCategorie> getJSON(@Url String url);
}
