package com.example.hoang.lesson3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class RecycelerViewAdapter extends RecyclerView.Adapter<RecycelerViewAdapter.MyViewHoler> {
    private List<File> mListGallery;
    private Context mContext;

    public RecycelerViewAdapter(List<File> mListGallery, Context mContext) {
        this.mListGallery = mListGallery;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecycelerViewAdapter.MyViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_recyceler_view, viewGroup, false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycelerViewAdapter.MyViewHoler myViewHoler, int i) {
        Picasso.with(mContext)
                .load(mListGallery.get(i))
                .into(myViewHoler.mImageAvata);
    }

    @Override
    public int getItemCount() {
        return mListGallery.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        private ImageView mImageAvata;

        public MyViewHoler(@NonNull View itemView) {
            super(itemView);
            mImageAvata = itemView.findViewById(R.id.image_avata);
        }
    }
}





