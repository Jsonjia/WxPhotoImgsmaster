package com.zjp.wxphotoimgs.transition;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.zjp.wxphotoimgs.R;
import com.zjp.wxphotoimgs.databinding.ActivityTransitionBinding;

/**
 * Created by zjp on 2018/7/4 09:13.
 */

public class TransitionActivity extends AppCompatActivity {

    private ActivityTransitionBinding mViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_transition);

        mViewBinding.actRecyclerview.setLayoutManager(new GridLayoutManager(this,3));



    }
}
