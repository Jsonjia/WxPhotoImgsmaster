package com.zjp.wxphotoimgs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zjp.wxphotoimgs.databinding.ActivityMainBinding;
import com.zjp.wxphotoimgs.fragment_img.FragmentImgsActivity;
import com.zjp.wxphotoimgs.oneimgs.OneImgsActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setClick(this);

    }

    public void oneclick(View v) {
        startActivity(new Intent(this, OneImgsActivity.class));
    }

    public void fragmentClick(View view) {
        startActivity(new Intent(this, FragmentImgsActivity.class));
    }

    public void transitionClick(View view) {
        Log.d("zjp", "11111111111");
    }
}
