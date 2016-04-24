package com.ll.horsebean.module.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ll.horsebean.R;

public class GlideActivity extends Activity
{
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        ImageView img = (ImageView) findViewById(R.id.activity_glide_img1);
        String url = "http://img1.ph.126.net/gGRsUgEni_P9xFrirRs2Ww==/6630801683887325749.jpg";
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(img);
    }
}
