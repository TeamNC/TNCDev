package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LIONEL KOUEMENI on 02/10/2016.
 */
public class ElementCategorie {
    @SerializedName("id_cat")
    @Expose
    private int id_cat;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("nombreevts")
    @Expose
    private int nombreevts;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("couleur")
    @Expose
    private int couleur;

    public ElementCategorie(int id_cat, String title, int nombreevts, String image, int couleur) {
        this.id_cat = id_cat;
        this.title = title;
        this.nombreevts = nombreevts;
        this.image = image;
        this.couleur = couleur;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNombreevts() {
        return nombreevts;
    }

    public void setNombreevts(int nombreevts) {
        this.nombreevts = nombreevts;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }
}
