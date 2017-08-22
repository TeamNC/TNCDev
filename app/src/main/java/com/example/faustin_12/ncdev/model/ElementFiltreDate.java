package com.example.faustin_12.ncdev.model;

/**
 * Created by LIONEL KOUEMENI on 11/11/2016.
 */
public class ElementFiltreDate {
    private String date;
    private int title;


    public ElementFiltreDate(String date) {

        this.title = 0;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}


