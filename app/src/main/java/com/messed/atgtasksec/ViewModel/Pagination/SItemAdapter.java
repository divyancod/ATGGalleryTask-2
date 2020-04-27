package com.messed.atgtasksec.ViewModel.Pagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.messed.atgtasksec.Model.AtgFApi;
import com.messed.atgtasksec.Model.Photo;
import com.messed.atgtasksec.R;

public class SItemAdapter extends PagedListAdapter<Photo, SItemAdapter.ItemViewHolder> {
    Context context;
    @SerializedName(value = "photo")
    private AtgFApi atgFApi;

    public SItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context=context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_gallery,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
       // Photo images=atgFApi.getPhotos().getPhoto().get(position);
        Glide.with(context).load(getItem(position).getUrlS()).into(holder.img1);


    }
    private static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.equals(newItem);

        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return true;
        }
    };




    public class ItemViewHolder extends RecyclerView.ViewHolder {
        //TextView t1;
        ImageView img1;
        // CardView cd;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //t1=(TextView)itemView.findViewById(R.id.tv1);
            img1=(ImageView)itemView.findViewById(R.id.img1);
            //  cd=(CardView)itemView.findViewById(R.id.cardlay001);
        }
    }
}
