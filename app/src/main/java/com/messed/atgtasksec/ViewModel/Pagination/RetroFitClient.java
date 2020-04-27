package com.messed.atgtasksec.ViewModel.Pagination;

import com.messed.atgtasksec.ViewModel.FCall;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {
    public static final String base_url="https://api.flickr.com/services/rest/";
    public static RetroFitClient minstance;
    Retrofit retrofit;
    public RetroFitClient()
    {
        retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static synchronized RetroFitClient getInstance()
    {
        if(minstance==null)
        {
            minstance=new RetroFitClient();
        }
        return minstance;
    }
    public FCall getApi()
    {
        return retrofit.create(FCall.class);
    }
}
