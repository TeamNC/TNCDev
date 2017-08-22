package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LIONEL KOUEMENI on 02/10/2016.
 */
public class ElementEvenement {
    @SerializedName("id_event")
    @Expose
    private int id_event;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date")
    @Expose
    private int date; //YYYMMDD
    @SerializedName("datepost")
    @Expose
    private int datepost;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("participant")
    @Expose
    private int participant;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("categorie")
    @Expose
    private int categorie;
    @SerializedName("image")
    @Expose
    private int image;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("localisation")
    @Expose
    private String localisation;
    @SerializedName("promoter")
    @Expose
    private String promoter;
    @SerializedName("sponsor")
    @Expose
    private String sponsor;

    private int nbreLove = 10;

    public ElementEvenement(int id_event, String title, int date, int datepost, int duration, String mark, int participant, String description, int categorie, int image, int price, String localisation, String promoter, String sponsor) {
        this.id_event = id_event;
        this.title = title;
        this.date = date;
        this.datepost = datepost;
        this.duration = duration;
        this.mark = mark;
        this.participant = participant;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
        this.price = price;
        this.localisation = localisation;
        this.promoter = promoter;
        this.sponsor = sponsor;
    }

    public int getNbreLove() {
        return nbreLove;
    }

    public void setNbreLove(int nbreLove) {
        this.nbreLove = nbreLove;
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

    public int getDatepost() {
        return datepost;
    }

    public void setDatepost(int datepost) {
        this.datepost = datepost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
}