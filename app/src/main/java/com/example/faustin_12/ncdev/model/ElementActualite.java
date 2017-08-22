package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LIONEL KOUEMENI on 18/09/2016.
 */
public class ElementActualite {
    @SerializedName("id_event")
    @Expose
    private int id_event;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date")
    @Expose
    private int date; //YYYMMDD
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("categorie")
    @Expose
    private int categorie;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("localisation")
    @Expose
    private String localisation; //To be an Object GMap
    @SerializedName("promoter")
    @Expose
    private String promoter;
    @SerializedName("comment")
    @Expose
    private int comment;
    @SerializedName("like")
    @Expose
    private int like;
    @SerializedName("share")
    @Expose
    private int share;
    @SerializedName("multimedia")
    @Expose
    private String multimedia;

    public ElementActualite(int id_event, String title, int date, String description, int categorie, String image, int price, String localisation, String promoter, int comment, int like, int share, String multimedia) {
        this.id_event = id_event;
        this.title = title;
        this.date = date;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
        this.price = price;
        this.localisation = localisation;
        this.promoter = promoter;
        this.comment = comment;
        this.like = like;
        this.share = share;
        this.multimedia = multimedia;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPromoter() {
        return promoter;
    }

    public void setPromoter(String promoter) {
        this.promoter = promoter;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }
}


