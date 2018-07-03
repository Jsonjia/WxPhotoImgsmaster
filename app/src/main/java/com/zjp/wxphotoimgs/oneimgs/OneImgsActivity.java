package com.zjp.wxphotoimgs.oneimgs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zjp.wxphotoimgs.R;
import com.zjp.wxphotoimgs.databinding.ActivityOneimgsBinding;

/**
 * Created by zjp on 2018/7/3 12:44.
 */

public class OneImgsActivity extends AppCompatActivity {

    ActivityOneimgsBinding activityOneimgsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityOneimgsBinding = DataBindingUtil.setContentView(this, R.layout.activity_oneimgs);


        activityOneimgsBinding.ivDefualt.setCustomView(R.layout.popup_custom, R.id.popup_custom_iv);

    }
}
