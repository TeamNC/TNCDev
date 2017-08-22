package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FAUSTIN-12 on 12/05/2016.
 */
public class ElementSnack {
    @SerializedName("id_S")
    @Expose
    private int id_S;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("promoter")
    @Expose
    private int promoter;
    @SerializedName("specificity")
    @Expose
    private String specificity;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("DJname")
    @Expose
    private String DJname;

    private int imageID;
    private int prices; // To be a list of prices

    public ElementSnack(int id_S, String name, String mark, int promoter, String specificity, String location, String DJname, int imageID, int prices) {
        this.id_S = id_S;
        this.name = name;
        this.mark = mark;
        this.promoter = promoter;
        this.specificity = specificity;
        this.location = location;
        this.DJname = DJname;
        this.imageID = imageID;
        this.prices = prices;
    }

    public int getId_S() {
        return id_S;
    }

    public void setId_S(int id_S) {
        this.id_S = id_S;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getPromoter() {
        return promoter;
    }

    public void setPromoter(int promoter) {
        this.promoter = promoter;
    }

    public String getSpecificity() {
        return specificity;
    }

    public void setSpecificity(String specificity) {
        this.specificity = specificity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDJname() {
        return DJname;
    }

    public void setDJname(String DJname) {
        this.DJname = DJname;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }
}
