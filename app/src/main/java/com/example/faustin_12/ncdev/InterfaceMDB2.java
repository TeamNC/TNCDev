package com.example.faustin_12.ncdev;

import com.example.faustin_12.ncdev.model.ResponseEvenement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by FAUSTIN-12 on 24/03/2017.
 */

public interface InterfaceMDB2 {
    @GET("/ncdev/db_get_document_cdp.php?categorie={categorie}&date={date}")
    Call<ResponseEvenement> getJSON(@Path(value = "categorie", encoded = true) String categorie,
                                    @Path(value = "date", encoded = true) String date);
}
