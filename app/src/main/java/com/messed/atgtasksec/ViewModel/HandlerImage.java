package com.messed.atgtasksec.ViewModel;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.messed.atgtasksec.Model.AtgFApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HandlerImage {
    String method="flickr.photos.getRecent";
    //ethod=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s
    int per_page=20;
    int page=1;
    String key="6f102c62f41998d151e5a1b48713cf13";
    String format="json";
    int jsoncall=1;
    String extra="url_s";
    public MutableLiveData<AtgFApi> requestImages(){
        final MutableLiveData<AtgFApi> liveData =new MutableLiveData<>();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        FCall fcall=retrofit.create(FCall.class);
        Call<AtgFApi> call=fcall.callApi(method,per_page,page,key,format,jsoncall,extra);
        call.enqueue(new Callback<AtgFApi>() {
            @Override
            public void onResponse(Call<AtgFApi> call, Response<AtgFApi> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AtgFApi> call, Throwable t) {

            }
        });
        return liveData;
    }
    //--------------------------------------------------------------------------------------------------------------------------------------
    //Testing new image calling----------------------------------------
    public MutableLiveData<AtgFApi> requestNewImages(String s)
    {
        final MutableLiveData<AtgFApi> liveData =new MutableLiveData<>();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        String method2="flickr.photos.search";
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        FCall fcall=retrofit.create(FCall.class);
        Call<AtgFApi> call=fcall.callApi2(method2,key,format,jsoncall,extra,s);
        call.enqueue(new Callback<AtgFApi>() {
            @Override
            public void onResponse(Call<AtgFApi> call, Response<AtgFApi> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AtgFApi> call, Throwable t) {

            }
        });
        return liveData;
    }
}
