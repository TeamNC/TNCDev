package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FAUSTIN-12 on 23/03/2017.
 */

public class ResponseCategorie {

    @SerializedName("category")
    @Expose
    private List<ElementCategorie> categories = null;

    public List<ElementCategorie> getCategories () {
            return categories;
        }

    public void setCategories(List<ElementCategorie> categories) {
        this.categories = categories;
    }
}
