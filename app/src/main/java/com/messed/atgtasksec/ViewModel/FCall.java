package com.messed.atgtasksec.ViewModel;

import com.messed.atgtasksec.Model.AtgFApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FCall {
    String key="6f102c62f41998d151e5a1b48713cf13";
    ///?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s"
    @GET("/services/rest/")
    Call<AtgFApi> callApi(@Query("method") String method, @Query("per_page") int per_page,
                          @Query("page") int page, @Query("api_key") String key, @Query("format") String json,
                          @Query("nojsoncallback") int jsoncall, @Query("extras") String url_s);


    //?method=flickr.photos.search&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s&text=cat
    @GET("/services/rest/")
    Call<AtgFApi> callApi2(@Query("method") String method, @Query("api_key") String key, @Query("format") String json,
                          @Query("nojsoncallback") int jsoncall, @Query("extras") String url_s,@Query("text")String text);

}
