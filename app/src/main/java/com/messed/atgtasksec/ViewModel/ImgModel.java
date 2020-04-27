package com.messed.atgtasksec.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.messed.atgtasksec.Model.AtgFApi;

public class ImgModel extends ViewModel {
    private HandlerImage handlerImage;
    private MutableLiveData<AtgFApi> liveData;
    public String s;
    //private MutableLiveData<AtgFApi> liveData2;
    static final String TAG="ImgModel";
    public ImgModel(String s) {
        this.s=s;
        handlerImage = new HandlerImage();
    }

    public LiveData<AtgFApi> getImages() {
        if (liveData == null) {
            liveData = handlerImage.requestNewImages(s);
            Log.e(TAG, "getImages: "+liveData);
        }
        return liveData;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    /*public LiveData<AtgFApi> getMore()
    {
        liveData2=handlerImage.requestNewImages();
        return liveData2;
    }*/

}
