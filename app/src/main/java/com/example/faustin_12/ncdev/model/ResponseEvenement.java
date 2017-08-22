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
        private List<ElementActualite> enevements = null;

        public List<ElementActualite> getEnevements() {
            return enevements;
        }

        public void setEnevements(List<ElementActualite> enevements) {
            this.enevements = enevements;
        }
}
