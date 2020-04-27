package com.messed.atgtasksec.ViewModel.Pagination;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.messed.atgtasksec.Model.Photo;

public class ItemDataFactory extends DataSource.Factory {

    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> itemlivedatasource=new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource create() {
        ItemDataSource itemDataSource=new ItemDataSource();
        itemlivedatasource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> getItemlivedatasource() {
        return itemlivedatasource;
    }
}
