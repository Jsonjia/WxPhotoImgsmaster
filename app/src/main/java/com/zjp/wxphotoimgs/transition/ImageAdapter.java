package com.zjp.wxphotoimgs.transition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjp.wxphotoimgs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/7/4 09:17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private String[] array;
    Context mContext;

    public ImageAdapter(String[] array, Context context) {
        this.array = array;
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final List<Pair<ImageView, String>> pairs = new ArrayList<>();
        Pair<ImageView, String> pair = Pair.create(holder.mItemImg, "img" + position);

        pairs.add(pair);

        Glide.with(mContext).load(array[position]).thumbnail(0.4f).into(holder.mItemImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImageActivity.startActivity(mContext, pairs, position, array);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            mItemImg = itemView.findViewById(R.id.item_imgview);
        }
    }
}
