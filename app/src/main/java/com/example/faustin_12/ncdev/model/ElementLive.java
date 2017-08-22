package com.example.faustin_12.ncdev.model;

import android.graphics.Color;

/**
 * Created by LIONEL KOUEMENI on 30/11/2016.
 */
public class ElementLive {
    private int imageID;
    private String title;
    private int nbreLive;
    private String date;
    private int categorieColor;

    public ElementLive(int imageID, String title, int nbreLive, String date, int categorieColor) {
        this.imageID = imageID;
        this.title = title;
        this.date = date;
        this.nbreLive = nbreLive;
        this.categorieColor = categorieColor;
    }

    public int getCategorieColor() {
        return categorieColor;
    }

    public void setCategorieColor(int categorieColor) {
        this.categorieColor = categorieColor;
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

    public int getNbreLive() {
        return nbreLive;
    }

    public void setNbreLive(int nbreLive) {
        this.nbreLive = nbreLive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


