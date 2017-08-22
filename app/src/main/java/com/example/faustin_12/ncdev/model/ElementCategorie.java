package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LIONEL KOUEMENI on 02/10/2016.
 */
public class ElementCategorie {
    @SerializedName("id_cat")
    @Expose
    private Integer idCat;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nombreevts")
    @Expose
    private Integer nombreevts;
    private int imageID;

    public ElementCategorie(int idCat, int imageID, String name, int nombreevts){
        this.idCat = idCat;
        this.imageID=imageID;
        this.name = name;
        this.nombreevts = nombreevts;
    }

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNombreevts() {
        return nombreevts;
    }

    public void setNombreevts(Integer nombreevts) {
        this.nombreevts = nombreevts;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
