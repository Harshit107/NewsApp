package com.harshit.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;

    LinearLayout business;
    LinearLayout entertainment;
    LinearLayout health;
    LinearLayout science;
    LinearLayout sport;
    LinearLayout technology;
    MyAdapter myAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<NewsListMode> list;

    TextView newsTV;
    RequestQueue queue;         //volley -> lib. -> api call -> req -> res -> recyclear view


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inti();
        queue = Volley.newRequestQueue(this);
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchNews();

            }
        });


        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("Business");
            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("Entertainment");
            }
        });


        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("Health");
            }
        });


        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("Science");
            }
        });


        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("Sport");
            }
        });


        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("Technology");
            }
        });


//        String url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=2c3a768c1b9048c9bdd9d4e593fbe818";

        fetchNews();


    }

    private void fetchNews() {
        list.clear();
        swipeRefreshLayout.setRefreshing(true);//

//        {} jsonobject
//        [] jsonarray
//        "" string

        //get, post , delete, update
//        1 method
//        2 api url
//        3 body
//        4 response (success)
//        5 error


        Log.d(TAG, Config.getApi(getNewsText()));

//        A a = new A();
//        a.something();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                Config.getApi(getNewsText()),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        swipeRefreshLayout.setRefreshing(false);

                        try {
                            parse(response);
                            Log.d(TAG, response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }

//    {
//        [
//        {a,b,c}.
//        ,{a,b,c},{}
//        ]
//    }
    private void parse(JSONObject response) throws JSONException {

        JSONArray arr = response.getJSONArray("articles");

        for(int i = 0; i<arr.length(); i++ ) {

//            String s = "hira";
//            s.charAt(0);

            JSONObject obj = arr.getJSONObject(i);

//            String author = obj.getString("author");
//            String author = "";
            String title = obj.getString("title");
            String description = obj.getString("description");
            String urlToImage = obj.getString("urlToImage");
            String content = obj.getString("url");
            String author = obj.getString("author");
            list.add(new NewsListMode(urlToImage,author,title,description,content));
            myAdapter.notifyDataSetChanged();
        }








    }

    void setText(String text) {
        newsTV.setText(text);
        fetchNews();
    }
    String getNewsText() {
        return newsTV.getText().toString();
    }

    private void inti() {
        business = findViewById(R.id.business);
        entertainment = findViewById(R.id.entertainment);
        health = findViewById(R.id.health);
        science = findViewById(R.id.science);
        technology = findViewById(R.id.technology);
        sport = findViewById(R.id.sport);
        newsTV = findViewById(R.id.newsTV);
        recyclerView = findViewById(R.id.recycle);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}