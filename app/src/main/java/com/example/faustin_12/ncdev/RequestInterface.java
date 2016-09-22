package com.example.faustin_12.ncdev;

import com.example.faustin_12.ncdev.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by FAUSTIN-12 on 22/09/2016.
 */

public interface RequestInterface {

    //@GET("/storage/rss/rss/karlsruhe.xml")
    @GET("/football/rss.xml")
    //@GET("/spip.php?page=backend&id_rubrique=29")
    //@GET("/news?cf=all&hl=fr&pz=1&ned=fr&topic=w&output=rss")
    //void getItems(Callback<Feed> callback);
    Call<Feed> getRSS();
}
