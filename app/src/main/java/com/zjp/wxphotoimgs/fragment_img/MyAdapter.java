package com.zjp.wxphotoimgs.fragment_img;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.zjp.wxphotoimgs.fragment_img.widget.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/7/3 13:21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private ArrayList<String> imgList;

    private onImgClickListener onImgClickListener;

    public void setOnImgClickListener(MyAdapter.onImgClickListener onImgClickListener) {
        this.onImgClickListener = onImgClickListener;
    }

    public MyAdapter(Context context, ArrayList<String> imgList) {
        this.imgList = imgList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PhotoView p = new PhotoView(mContext);

        FrameLayout.LayoutParams layoutParams = new FrameLayout .LayoutParams((int) (mContext.getResources().getDisplayMetrics().density * 100), (int) (mContext.getResources().getDisplayMetrics().density * 100));
        layoutParams.setMargins(20, 40, 20, 0);
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        p.setLayoutParams(layoutParams);
        p.setScaleType(ImageView.ScaleType.CENTER_CROP);//这里不能用FIT_XY
//        p.setEnabled(false);
        p.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(p);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String thumbnailUrl = getThumbnailImageUrl(imgList.get(position));
        Glide.with(mContext).load(thumbnailUrl).into(holder.mPhotoView);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    /**
     * get a thumbnail image url from original url
     *
     * @param imgUrl original image url
     * @return the number(85) in below url indicate the quality of original image
     */
    private String getThumbnailImageUrl(String imgUrl) {
        String url = "http://imgsize.ph.126.net/?imgurl=data1_data2xdata3x0x85.jpg&enlarge=true";
        int width = (int) (mContext.getResources().getDisplayMetrics().density * 100);
        int height = (int) (mContext.getResources().getDisplayMetrics().density * 100); //just for convenient
        url = url.replaceAll("data1", imgUrl).replaceAll("data2", width + "").replaceAll("data3", height + "");
        return url;
    }

    @Override
    public void onClick(View v) {
        if (onImgClickListener != null)
            onImgClickListener.onClick((Integer) v.getTag(), v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        PhotoView mPhotoView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPhotoView = (PhotoView) itemView;
        }
    }

    public interface onImgClickListener {
        void onClick(int position, View view);
    }
}
