package com.example.faustin_12.ncdev.model;

/**
 * Created by FAUSTIN-12 on 23/03/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementEvenement {

    @SerializedName("id_event")
    @Expose
    private Integer idEvent;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("description")
    @Expose
    private String description;

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}