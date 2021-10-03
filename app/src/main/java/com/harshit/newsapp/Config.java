package com.harshit.newsapp;

import android.util.Log;

public class Config {

    ///q = world
    public static String getApi(String q) {
//        String API = "https://gnews.io/api/v4/search?q="+q+"&lang=en&token=8ab71b7450273531c97f8e0fa728ed72";
//
//        return API;
        String API = "https://newsapi.org/v2/everything?q="+q+"&apiKey=2c3a768c1b9048c9bdd9d4e593fbe818&language=en";
        return API;
    }

    //xynmm,nm+


}
