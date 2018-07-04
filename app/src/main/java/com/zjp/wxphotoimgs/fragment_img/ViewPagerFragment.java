package com.zjp.wxphotoimgs.fragment_img;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zjp.wxphotoimgs.R;
import com.zjp.wxphotoimgs.databinding.FragmentViewpagerBinding;
import com.zjp.wxphotoimgs.fragment_img.widget.ImageInfo;
import com.zjp.wxphotoimgs.fragment_img.widget.MaterialProgressBar;
import com.zjp.wxphotoimgs.fragment_img.widget.PhotoView;
import com.zjp.wxphotoimgs.fragment_img.widget.ReboundViewPager;

import java.util.ArrayList;

/**
 * Created by zjp on 2018/7/3 15:08.
 */

public class ViewPagerFragment extends Fragment {


    private FragmentViewpagerBinding fragmentBinding;

    private ArrayList<String> imgs;
    private ImageInfo imageInfo;
    private ArrayList<ImageInfo> imageInfos;
    private int position;

    public static ViewPagerFragment getInstance(Bundle imgs) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(imgs);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewpager, container, false);
        return fragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        runEnterAnimation();

        Bundle bundle = getArguments();

        imgs = bundle.getStringArrayList("imgs");
        imageInfo = bundle.getParcelable("info");
        imageInfos = bundle.getParcelableArrayList("infos");

        position = bundle.getInt("position", 0);
        fragmentBinding.text.setText((position + 1) + "/" + imgs.size());

        fragmentBinding.viewpager.getOverscrollView().setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int pos) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_view_detail, null, false);
                final PhotoView photoView = view.findViewById(R.id.image_detail);
                final MaterialProgressBar progressBar = view.findViewById(R.id.progress);

                if (position == pos) {//only animate when position equals u click in pre layout
                    photoView.animateFrom(imageInfo);
                }

                Glide.with(getActivity()).load(imgs.get(pos)).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        progressBar.setVisibility(View.GONE);
                        photoView.setImageDrawable(resource);
                    }
                });

                //force to get focal point,to listen key listener
                photoView.setFocusableInTouchMode(true);
                photoView.requestFocus();
                photoView.setOnKeyListener(pressKeyListener);//add key listener to listen back press
                photoView.setOnClickListener(onClickListener);
                photoView.setTag(pos);
                photoView.touchEnable(true);

                container.addView(view);

                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        fragmentBinding.viewpager.getOverscrollView().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragmentBinding.text.setText((position + 1) + "/" + imgs.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //set current position
        fragmentBinding.viewpager.getOverscrollView().setCurrentItem(position);
    }

    private View.OnKeyListener pressKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {//只监听返回键
                if (event.getAction() != KeyEvent.ACTION_UP) {
                    return true;
                }
                exitFragment(v);
                return true;
            }
            return false;
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            exitFragment(v);
        }
    };

    private void runEnterAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        fragmentBinding.mask.startAnimation(alphaAnimation);
    }

    public void runExitAnimation(final View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fragmentBinding.mask.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fragmentBinding.mask.startAnimation(alphaAnimation);
    }

    private void popFragment() {
        if (!ViewPagerFragment.this.isResumed()) {
            return;
        }
        final FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

    private void exitFragment(View v) {
        //退出时点击的位置
        int position = (int) v.getTag();
        //回到上个界面该view的位置
        if (((ConstraintLayout) v.getParent()).getChildAt(1).getVisibility() == View.VISIBLE) {
            popFragment();
        } else {
            runExitAnimation(v);
            ImageInfo imageInfo = imageInfos.get(position);
            ((PhotoView) v).animateTo(imageInfo, new Runnable() {
                @Override
                public void run() {
                    popFragment();
                }
            });
        }
    }
}
