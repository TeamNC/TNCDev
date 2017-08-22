package com.example.faustin_12.ncdev;

import com.example.faustin_12.ncdev.model.UploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by FAUSTIN-12 on 02/07/2017.
 */


public interface FileApi {


    @Multipart
    @POST("nca/db_upload_test.php")
    Call <UploadResponse> uploadFile(@Part MultipartBody.Part file, @Part("file") RequestBody name);
}