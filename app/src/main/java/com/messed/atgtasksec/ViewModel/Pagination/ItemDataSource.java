package com.messed.atgtasksec.ViewModel.Pagination;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.messed.atgtasksec.Model.AtgFApi;
import com.messed.atgtasksec.Model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer,Photo> {
    public static int pagesize=20;
    public static int firstpage=1;
    String method="flickr.photos.getRecent";
    //ethod=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s
    int per_page=20;
    int page=1;
    String keys="6f102c62f41998d151e5a1b48713cf13";
    String format="json";
    int jsoncall=1;
    String extra="url_s";
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Photo> callback) {
        //HandlerImage.getInstance
        RetroFitClient.getInstance().getApi().callApi(method,per_page,(++page),keys,format,jsoncall,extra)
        .enqueue(new Callback<AtgFApi>() {
            @Override
            public void onResponse(Call<AtgFApi> call, Response<AtgFApi> response) {
                if(response.body()!=null)
                {
                    List<Photo> photoList=response.body().getPhotos().getPhoto();
                    callback.onResult(photoList,null,page+1);
                }
            }

            @Override
            public void onFailure(Call<AtgFApi> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
        RetroFitClient.getInstance().getApi().callApi(method,per_page,(++page),keys,format,jsoncall,extra)
                .enqueue(new Callback<AtgFApi>() {
                    @Override
                    public void onResponse(Call<AtgFApi> call, Response<AtgFApi> response) {
                        Integer key=(params.key > 1) ? params.key-1 : null;
                        if(response.body()!=null)
                        {
                            List<Photo> photoList=response.body().getPhotos().getPhoto();
                            callback.onResult(photoList,key);
                        }
                    }

                    @Override
                    public void onFailure(Call<AtgFApi> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
       RetroFitClient.getInstance().getApi().callApi(method,per_page,(++page),keys,format,jsoncall,extra)
                .enqueue(new Callback<AtgFApi>() {
                    @Override
                    public void onResponse(Call<AtgFApi> call, Response<AtgFApi> response) {
                        //callback.onResult(response.body(),key);
                        List<Photo> photoList=response.body().getPhotos().getPhoto();
                        callback.onResult(photoList,page+1);
                    }

                    @Override
                    public void onFailure(Call<AtgFApi> call, Throwable t) {

                    }
                });

    }
}
