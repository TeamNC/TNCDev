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
    @SerializedName("datepost")
    @Expose
    private int datepost; //YYYMMDD
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("mark")
    @Expose
    private int mark;
    @SerializedName("participant")
    @Expose
    private int participant;
    @SerializedName("sponsor")
    @Expose
    private String sponsor;
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
    private String promoter; //Confirm type int?
    @SerializedName("comment")
    @Expose
    private String comment; //Confirm type int?
    @SerializedName("like")
    @Expose
    private int like;
    //@SerializedName("share")
    //@Expose
    //private int share;
    //@SerializedName("multimedia")
    //@Expose
    //private String multimedia;
    @SerializedName("tickets")
    @Expose
    private int tickets;
    @SerializedName("guest")
    @Expose
    private String guest;

    public ElementActualite() {
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }
}


