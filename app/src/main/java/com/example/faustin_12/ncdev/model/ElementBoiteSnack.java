package com.example.faustin_12.ncdev.model;

/**
 * Created by FAUSTIN-12 on 12/05/2016.
 */
public class ElementBoiteSnack {
    private int imageID;
    private String title;
    private String nameDJ;
    private int prices; // To be a list of prices
    private String place; //To be an object GMap

    public ElementBoiteSnack(int imageID, String title, String nameDJ, int prices, String place) {
        this.imageID = imageID;
        this.title = title;
        this.nameDJ = nameDJ;
        this.prices = prices;
        this.place = place;
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

    public String getNameDJ() {
        return nameDJ;
    }

    public void setNameDJ(String nameDJ) {
        this.nameDJ = nameDJ;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
