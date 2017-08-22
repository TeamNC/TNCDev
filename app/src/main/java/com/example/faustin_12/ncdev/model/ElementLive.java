package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LIONEL KOUEMENI on 30/11/2016.
 */
public class ElementLive {
    @SerializedName("id_live")
    @Expose
    private int id_live;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("promoter")
    @Expose
    private int promoter;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("categorie")
    @Expose
    private int categorie;

    private int IconIDLIVE;

    public ElementLive(){

    }

    public int getId_live() {
        return id_live;
    }

    public void setId_live(int id_live) {
        this.id_live = id_live;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPromoter() {
        return promoter;
    }

    public void setPromoter(int promoter) {
        this.promoter = promoter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getIconIDLIVE() {
        return IconIDLIVE;
    }

    public void setIconIDLIVE(int iconIDLIVE) {
        IconIDLIVE = iconIDLIVE;
    }
}
