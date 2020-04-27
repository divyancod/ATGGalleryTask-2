package com.messed.atgtasksec.ViewModel.Pagination;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.messed.atgtasksec.Model.Photo;

public class ItemViewModel extends AndroidViewModel {
    public LiveData<PagedList<Photo>> itempagedlist;
    public LiveData<PageKeyedDataSource<Integer, Photo>> liveData;


    public ItemViewModel(@NonNull Application application) {
        super(application);
        ItemDataFactory itemDataFactory=new ItemDataFactory();
        liveData=itemDataFactory.getItemlivedatasource();
        PagedList.Config config= (new PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(ItemDataSource.pagesize).build();
        itempagedlist=(new LivePagedListBuilder(itemDataFactory,config)).build();
    }
}
