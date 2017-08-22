package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FAUSTIN-12 on 02/07/2017.
 */

public class UploadResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private Boolean error;

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
