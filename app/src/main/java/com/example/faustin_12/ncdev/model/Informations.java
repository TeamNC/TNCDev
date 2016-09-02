package com.example.faustin_12.ncdev.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by FAUSTIN-12 on 19/03/2016.
 */
public class Informations{
    int iconId;
    String titre;
    String description;
    private String time;
    private int nbreCom;

    public Informations (int iconId, String titre, String description, String time){
        this.iconId=iconId;
        this.titre=titre;
        this.description=description;
        this.time = time;
        this.nbreCom = 0;
    }

    public void setIconId (int iconId){
        this.iconId=iconId;
    }
    public void setTitre (String titre){
        this.titre=titre;
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
    public int getIconId(){
        return iconId;
    }
    public String getTitre(){
        return titre;
    }
    public String getDescription(){
        return description;
    }

}
