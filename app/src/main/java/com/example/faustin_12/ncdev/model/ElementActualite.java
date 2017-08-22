package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LIONEL KOUEMENI on 18/09/2016.
 */
public class ElementActualite {
    @SerializedName("id_event")
    @Expose
    private Integer id_event;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date")
    @Expose
    private Integer date; //To be a date in a particular date format
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private int image;
    @SerializedName("comment")
    @Expose
    private int comment;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("localisation")
    @Expose
    private String localisation; //To be an Object GMap
    @SerializedName("categorie")
    @Expose
    private String categorie;
    @SerializedName("like")
    @Expose
    private int like;

    public ElementActualite(int imageID, String title, String description, int nbreCom, String date, int price, String place, String categories, int nbreLove) {
        /*this.imageID = imageID;
        this.title = title;
        this.description = description;
        this.nbreCom = nbreCom;
        this.date = date;
        this.price = price;
        this.place = place;
        this.categories = categories;
        this.nbreLove = nbreLove;*/
    }

    public Integer getId_event() {
        return id_event;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}


