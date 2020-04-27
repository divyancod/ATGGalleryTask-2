package com.messed.atgtasksec.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.messed.atgtasksec.Model.AtgFApi;
import com.messed.atgtasksec.R;
import com.messed.atgtasksec.ViewModel.ImgModel;

import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

public class SearchFragment extends Fragment {
    SearchView searchview;
    RecyclerView recyclerView;
    private long timeSinceLastRequest;
    MyRecyclerAdapter recyclerAdapter;
    private CompositeDisposable cDisposable = new CompositeDisposable();
    static final String TAG="GalleryFragment";
    public String parameter;
    LinearLayoutManager manager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchview=root.findViewById(R.id.search01);
        recyclerView=root.findViewById(R.id.rv02);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        manager=new LinearLayoutManager(getActivity());


        //The Obervable texts are here
       Observable<String> observableQueryText = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(final ObservableEmitter<String> emitter) throws Exception {

                        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(final String newText) {
                                if(!emitter.isDisposed()){
                                    emitter.onNext(newText);
                                }
                                return false;
                            }
                        });
                    }
                })
                .debounce(450, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());
        observableQueryText.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                cDisposable.add(d);
            }
            @Override
            public void onNext(final String s) {

                timeSinceLastRequest = System.currentTimeMillis();
                //checking if string is null
             getActivity().runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     if(!s.isEmpty()) {
                         ImgModel imgModel = new ImgModel(s);
                         imgModel.getImages().observe(getActivity(), new androidx.lifecycle.Observer<AtgFApi>() {
                             @Override
                             public void onChanged(AtgFApi atgFApi) {
                                 recyclerAdapter = new MyRecyclerAdapter(getActivity(), atgFApi);
                                 recyclerView.setAdapter(recyclerAdapter);
                             }
                         });
                     }
                 }
             });


            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });

//Ends here ---- Divyanshu

        return root;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        cDisposable.clear();
    }
}
