package com.messed.atgtasksec.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.messed.atgtasksec.Model.Photo;
import com.messed.atgtasksec.ViewModel.Pagination.ItemViewModel;
import com.messed.atgtasksec.ViewModel.Pagination.SItemAdapter;
import com.messed.atgtasksec.R;
import com.messed.atgtasksec.ViewModel.ImgModel;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager manager;
    SearchView searchView;
    MyRecyclerAdapter myRecyclerAdapter;
    boolean isscrolling=false;
    int current,total,sout;
    static int cnt=0;
    ImgModel imgModel;
    int test=0;
    static final String TAG="HomeFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.rv01);
        manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        //recyclerView.setHasFixedSize(true);
        Log.e(TAG, "onCreateView: " );
        ItemViewModel itemViewModel= ViewModelProviders.of(this).get(ItemViewModel.class);
        final SItemAdapter adapter=new SItemAdapter(getActivity());
        itemViewModel.itempagedlist.observe(getActivity(), new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(PagedList<Photo> photos) {
                adapter.submitList(photos);
            }
        });
        recyclerView.setAdapter(adapter);
        //recyclerView.requestLayout();
        recyclerView.invalidate();
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
