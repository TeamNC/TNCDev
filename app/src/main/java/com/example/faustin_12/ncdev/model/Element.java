package com.example.faustin_12.ncdev.model;

/**
 * Created by FAUSTIN-12 on 12/05/2016.
 */
public class Element {
    private int imageID;
    private String title;
    private String description;
    private String time;
    private int nbreCom;

    public Element (int imageID, String title, String description, String time){
        this.imageID=imageID;
        this.title=title;
        this.description=description;
        this.time = time;
        this.nbreCom = 0;
    }
    public void setImageID (int imageID){
        this.imageID=imageID;
    }
    public void setTitle (String title){
        this.title=title;
    }
    public void setDescription (String description){
        this.description=description;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setNbreCom(int nbreCom) {
        this.nbreCom = nbreCom;
    }

    public String getTime() {
        return time;
    }
    public int getNbreCom() {
        return nbreCom;
    }
    public int getImageID(){
        return imageID;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}
