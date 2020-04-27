package com.messed.atgtasksec.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.messed.atgtasksec.Model.AtgFApi;
import com.messed.atgtasksec.Model.Photo;
import com.messed.atgtasksec.R;

public class MyRecyclerAdapter extends  RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>{
    Context context;
    @SerializedName(value = "photo")
    private AtgFApi atgFApi;

    public static final String TAG="MyRecyclerAdapter";
    public MyRecyclerAdapter(Context context, AtgFApi atgFApi)
    {

        this.context=context;
        this.atgFApi=atgFApi;
    }
    @NonNull
    @Override
    public MyRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_gallery,parent,false);
        return new MyRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolder holder, int position) {
        Photo images=atgFApi.getPhotos().getPhoto().get(position);
        Glide.with(context).load(images.getUrlS()).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return atgFApi.getPhotos().getPhoto().size();
    }
    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    public class MyRecyclerHolder extends RecyclerView.ViewHolder {
        //TextView t1;
        ImageView img1;
       // CardView cd;
        public MyRecyclerHolder(@NonNull View itemView) {
            super(itemView);
           // t1=(TextView)itemView.findViewById(R.id.tv1);
            img1=(ImageView)itemView.findViewById(R.id.img1);
          //  cd=(CardView)itemView.findViewById(R.id.cardlay001);
        }
    }
}
