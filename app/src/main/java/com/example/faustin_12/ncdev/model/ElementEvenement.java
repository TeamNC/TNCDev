package com.example.faustin_12.ncdev.model;

/**
 * Created by LIONEL KOUEMENI on 02/10/2016.
 */
public class ElementEvenement {
    private int imageID;
    private String title;
    int nbreEvents;

    public ElementEvenement(int imageID,String title,int nbreEvents){
        this.imageID=imageID;
        this.title=title;
        this.nbreEvents=0;
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

    public int getNbreEvents() {
        return nbreEvents;
    }

    public void setNbreEvents(int nbreEvents) {
        this.nbreEvents = nbreEvents;
    }
}