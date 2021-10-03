package com.harshit.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<NewsListMode> list;
    Context context;

    public MyAdapter(ArrayList<NewsListMode> list, Context context) {
        this.list = list;
        this.context = context;
    }


    ;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.item_recycle,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            String author = list.get(position).getAuthor();
            String title = list.get(position).getTitle();
            String desc = list.get(position).getDescription();
            String image = list.get(position).getImage();
            String cont = list.get(position).getCont();

            holder.author.setText("Src : "+author);
            holder.title.setText(title);
            holder.desc.setText(desc);

            try {

                Glide.with(context)
                        .load(image)
                        .centerCrop()
                        .placeholder(R.drawable.logo)
                        .into(holder.imageView);

            }catch (Exception e) {
                e.printStackTrace();
            }

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(cont));
//                    it.putExtra("image", image);
//                    it.putExtra("cont", cont);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);


                }
            });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView author;
        TextView title;
        TextView desc;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
        }
    }
}
