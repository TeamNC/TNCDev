package com.example.faustin_12.ncdev.model;

<<<<<<< 20193fdc625a9364dcbb487a087b65022777da9f
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

=======
>>>>>>> TEMP
/**
 * Created by LIONEL KOUEMENI on 02/10/2016.
 */
public class ElementCategorie {
<<<<<<< 20193fdc625a9364dcbb487a087b65022777da9f
    @SerializedName("id_cat")
    @Expose
    private Integer idCat;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nombreevts")
    @Expose
    private Integer nombreevts;

    private int imageID;

    public ElementCategorie(int idCat, int imageID, String name, int nombreevts){
        this.idCat = idCat;
        this.imageID=imageID;
        this.name = name;
        this.nombreevts = nombreevts;
    }

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }
=======
    private int imageID;
    private String title;
    int nbreEvents;

    public ElementCategorie(int imageID, String title, int nbreEvents){
        this.imageID=imageID;
        this.title=title;
        this.nbreEvents=nbreEvents;
    }

>>>>>>> TEMP
    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

<<<<<<< 20193fdc625a9364dcbb487a087b65022777da9f
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNombreevts() {
        return nombreevts;
    }

    public void setNombreevts(int nombreevts) {
        this.nombreevts = nombreevts;
=======
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
>>>>>>> TEMP
    }
}
