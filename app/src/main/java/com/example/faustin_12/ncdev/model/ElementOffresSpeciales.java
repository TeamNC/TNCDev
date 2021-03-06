package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LIONEL KOUEMENI on 15/12/2016.
 */
public class ElementOffresSpeciales {
    @SerializedName("id_OS")
    @Expose
    private int id_OS;
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

    private int imageIDOffresSpeciales;
    private String titleOffresSpeciales, price;
    int delais;

    public ElementOffresSpeciales(){
    }

    public int getId_OS() {
        return id_OS;
    }

    public void setId_OS(int id_OS) {
        this.id_OS = id_OS;
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

    public int getImageIDOffresSpeciales() {
        return imageIDOffresSpeciales;
    }

    public void setImageIDOffresSpeciales(int imageIDOffresSpeciales) {
        this.imageIDOffresSpeciales = imageIDOffresSpeciales;
    }

    public String getTitleOffresSpeciales() {
        return titleOffresSpeciales;
    }

    public void setTitleOffresSpeciales(String titleOffresSpeciales) {
        this.titleOffresSpeciales = titleOffresSpeciales;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDelais() {
        return delais;
    }

    public void setDelais(int delais) {
        this.delais = delais;
    }
}
