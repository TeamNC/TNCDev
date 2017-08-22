package com.example.faustin_12.ncdev.model;

/**
 * Created by LIONEL KOUEMENI on 09/04/2017.
 */

public class ElementDetailsLive {
    private int iconID,imageID;
    private String description;
    private int nbreLove;
    private String since;

    public ElementDetailsLive( String description,String since,int iconID ,int imageID){
        this.imageID = imageID;
        this.iconID = iconID;
        this.description = description;
        this.since = since;
       // this.nbreLove = nbreLove;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbreLove() {
        return nbreLove;
    }

    public void setNbreLove(int nbreLove) {
        this.nbreLove = nbreLove;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}


