package com.example.faustin_12.ncdev.model;

/**
 * Created by LIONEL KOUEMENI on 15/12/2016.
 */
public class ElementOffresSpeciales { private int imageIDOffresSpeciales;
    private String titleOffresSpeciales, price;
    int delais;

    public ElementOffresSpeciales(int imageIDOffresSpeciales,String titleOffresSpeciales,int delais){
        this.imageIDOffresSpeciales=imageIDOffresSpeciales;
        this.titleOffresSpeciales=titleOffresSpeciales;
        this.price=price;
        this.delais=0;
    }

    public int getImageIDOffresSpeciales() {
        return imageIDOffresSpeciales;
    }

    public void setImageIDOffresSpeciales(int imageIDOffresSpeciales) {
        this.imageIDOffresSpeciales = imageIDOffresSpeciales;
    }

    public String getTitleOffresSpeciales() {
        return titleOffresSpeciales;
    }

    public void setTitleOffresSpeciales(String titleOffresSpeciales) {
        this.titleOffresSpeciales = titleOffresSpeciales;
    }

    public int getDelais() {
        return delais;
    }

    public void setDelais(int delais) {
        this.delais = delais;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
