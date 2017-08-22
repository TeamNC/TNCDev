package com.example.faustin_12.ncdev.model;

/**
 * Created by LIONEL KOUEMENI on 30/11/2016.
 */
public class ElementLive {
    private int IconIDLIVE;
    private String titlelive;
    public ElementLive(int IconIDLIVE,String titlelive){
        this.IconIDLIVE=IconIDLIVE;
        this.titlelive=titlelive;
    }

    public int getIconIDLIVE() {
        return IconIDLIVE;
    }

    public void setIconIDLIVE(int iconIDLIVE) {
        IconIDLIVE = iconIDLIVE;
    }

    public String getTitlelive() {
        return titlelive;
    }

    public void setTitlelive(String titlelive) {
        this.titlelive = titlelive;
    }
}
