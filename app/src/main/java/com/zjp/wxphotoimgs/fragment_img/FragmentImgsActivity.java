package com.zjp.wxphotoimgs.fragment_img;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;

import com.zjp.wxphotoimgs.R;
import com.zjp.wxphotoimgs.databinding.ActivityFragmentimgsBinding;
import com.zjp.wxphotoimgs.fragment_img.widget.ImageInfo;
import com.zjp.wxphotoimgs.fragment_img.widget.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/7/3 13:05.
 */

public class FragmentImgsActivity extends AppCompatActivity implements MyAdapter.onImgClickListener {

    ActivityFragmentimgsBinding activityFragmentimgsBinding;

    private MyAdapter myAdapter;

    private ArrayList<String> imgList = new ArrayList<>();
    private ArrayList<ImageInfo> imgImageInfos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityFragmentimgsBinding = DataBindingUtil.setContentView(this, R.layout.activity_fragmentimgs);

        activityFragmentimgsBinding.recyclerGridview.setLayoutManager(new GridLayoutManager(this, 3));


        imgList.add(0, "http://img6.cache.netease.com/3g/2015/9/30/20150930091938133ad.jpg");
        imgList.add(1, "http://img2.cache.netease.com/3g/2015/9/30/2015093000515435aff.jpg");
        imgList.add(2, "http://img5.cache.netease.com/3g/2015/9/30/20150930075225737e5.jpg");
        imgList.add(3, "http://img5.cache.netease.com/3g/2015/9/29/20150929213007cd8cd.jpg");
        imgList.add(4, "http://img3.cache.netease.com/3g/2015/9/29/20150929162747a8bfa.jpg");
        imgList.add(5, "http://img2.cache.netease.com/3g/2015/9/30/20150930091208cf03c.jpg");
        imgList.add(6, "http://img2.cache.netease.com/3g/2015/9/30/2015093000515435aff.jpg");
        imgList.add(7, "http://img5.cache.netease.com/3g/2015/9/29/20150929213007cd8cd.jpg");
        imgList.add(8, "http://img3.cache.netease.com/3g/2015/9/29/20150929162747a8bfa.jpg");

        myAdapter = new MyAdapter(this, imgList);

        activityFragmentimgsBinding.recyclerGridview.setAdapter(myAdapter);

        myAdapter.setOnImgClickListener(this);
    }


    @Override
    public void onClick(int position, View view) {

        RecyclerView recyclerGridview = activityFragmentimgsBinding.recyclerGridview;
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("imgs", imgList);
        bundle.putParcelable("info", ((PhotoView) view).getInfo());
        bundle.putInt("position", position);
        imgImageInfos.clear();
        //NOTE:if imgList.size >= the visible count in single screen,i will cause NullPointException
        //because item out of screen have been replaced/reused

        for (int i = 0; i < imgList.size(); i++) {
            imgImageInfos.add(((PhotoView) recyclerGridview.getChildAt(i)).getInfo());
        }
        recyclerGridview.getChildAt(position);

        bundle.putParcelableArrayList("infos", imgImageInfos);
        getSupportFragmentManager().beginTransaction().replace(Window.ID_ANDROID_CONTENT, ViewPagerFragment.getInstance(bundle), "ViewPagerFragment")
                .addToBackStack(null).commit();
    }
}
