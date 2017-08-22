package com.example.faustin_12.ncdev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FAUSTIN-12 on 23/03/2017.
 */

public class ResponseEvenement {

        @SerializedName("evenements")
        @Expose
        private List<ElementEvenement> enevements = null;

        public List<ElementEvenement> getEnevements() {
            return enevements;
        }

        public void setEnevements(List<ElementEvenement> enevements) {
            this.enevements = enevements;
        }
}
