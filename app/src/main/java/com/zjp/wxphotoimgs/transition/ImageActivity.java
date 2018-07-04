package com.zjp.wxphotoimgs.transition;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zjp on 2018/7/4 10:04.
 */

public class ImageActivity extends AppCompatActivity {

    public static void startActivity(Context context, Pair<View, String> imageView, int position, String[] list) {

        Intent intent = new Intent(context, ImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArray("STRLIST", list);
        intent.putExtra("IMG", position);
        intent.putExtras(bundle);
        if (Build.VERSION.SDK_INT > 21) {

//            ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(context,)
//            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, imageView).toBundle())
            ;
        } else {
            context.startActivity(intent);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
