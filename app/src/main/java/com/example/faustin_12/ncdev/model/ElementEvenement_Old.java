package com.example.faustin_12.ncdev.model;

/**
 * Created by FAUSTIN-12 on 15/01/2017.
 */
public class ElementEvenement_Old {
    private int imageID;
    private String title;
    private String description;
    private int nbreCom;
    private String date; //To be a date in a particular date format
    private int price;
    private String place; //To be an Object GMap
    private String categories;
    private int nbreLove;

    public ElementEvenement_Old(int imageID, String title, String description, int nbreCom, String date, int price, String place, String categories, int nbreLove) {
        this.imageID = imageID;
        this.title = title;
        this.description = description;
        this.nbreCom = nbreCom;
        this.date = date;
        this.price = price;
        this.place = place;
        this.categories = categories;
        this.nbreLove = nbreLove;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbreCom() {
        return nbreCom;
    }

    public void setNbreCom(int nbreCom) {
        this.nbreCom = nbreCom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getNbreLove() {
        return nbreLove;
    }

    public void setNbreLove(int nbreLove) {
        this.nbreLove = nbreLove;
    }
}
