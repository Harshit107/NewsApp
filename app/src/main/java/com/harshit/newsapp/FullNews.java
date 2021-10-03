package com.harshit.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class FullNews extends AppCompatActivity {

    ImageView imageView;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);

        Intent get = getIntent();
        String image =get.getStringExtra("image");
        String cont = get.getStringExtra("cont");
        imageView = findViewById(R.id.zoomImage);
        tv = findViewById(R.id.text);


        try {
            Glide.with(getApplicationContext())
                    .load(image)
                    .placeholder(R.drawable.logo)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tv.setText(cont);


    }
}