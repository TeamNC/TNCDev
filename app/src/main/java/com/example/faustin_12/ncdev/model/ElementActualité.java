package com.example.faustin_12.ncdev.model;

/**
 * Created by LIONEL KOUEMENI on 18/09/2016.
 */
public class ElementActualité {
        private int imageID;
        private String price;
        private String date;
        private String description_actualite;
        private String localisation;
        private String categories;
        private int nbreCom;
        private int nbreLove;

        public ElementActualité (int imageID,  String price,String description_actualite,String date,String localisation ,String categories){
        this.imageID=imageID;
        this.price=price;
        this.description_actualite=description_actualite;
        this.date=date;
        this.localisation=localisation;
        this.categories=categories;
        this.nbreCom = 0 ;
        this.nbreLove = 0;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription_actualite() {
        return description_actualite;
    }

    public void setDescription_actualite(String description_actualite) {
        this.description_actualite = description_actualite;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getNbreCom() {
        return nbreCom;
    }

    public void setNbreCom(int nbreCom) {
        this.nbreCom = nbreCom;
    }

    public int getNbreLove() {
        return nbreLove;
    }

    public void setNbreLove(int nbreLove) {
        this.nbreLove = nbreLove;
    }
}

