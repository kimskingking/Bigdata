package com.example.ex12_06;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;


    public MovieAdapter(List<Movie> movieList){
        this.movieList = movieList;

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView txId,txTitle,txUrl;
        ImageView imageView;
        View itemView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.txId = itemView.findViewById(R.id.txId);
            this.txTitle = itemView.findViewById(R.id.txTitle);
            this.txUrl = itemView.findViewById(R.id.txUrl);
            this.imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,parent,false);

        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        Glide.with(holder.itemView)
                .load("https://cdn.pixabay.com/photo/2017/08/02/23/58/people-2574170_960_720.jpg")
//                .load(movie.getThumbnailUrl())
                .into(holder.imageView);

        holder.txId.setText(Integer.toString(movie.getId()));
        holder.txTitle.setText(movie.getTitle());
        holder.txUrl.setText(movie.getThumbnailUrl());

    }

    @Override
    public int getItemCount() {
        return movieList!=null ? movieList.size() : 0;
    }
}
