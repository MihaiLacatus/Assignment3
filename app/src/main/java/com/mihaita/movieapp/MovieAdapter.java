package com.mihaita.movieapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    List<MovieBean> movieList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieID, movieName, movieDescription;
        ImageButton btnDelete;
        RatingBar movieRatingBar;

        MyViewHolder(View view) {
            super(view);
            movieID = view.findViewById(R.id.text_movie_id);
            movieName = view.findViewById(R.id.text_movie_name);
            movieDescription = view.findViewById(R.id.text_movie_description);
            movieRatingBar = view.findViewById(R.id.ratingBar);
            btnDelete = view.findViewById(R.id.btnDelete);
        }
    }

    MovieAdapter(List<MovieBean> movieList) {

        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MyViewHolder viewHolder, int i) {

        final MovieBean movie = movieList.get(i);

        viewHolder.movieID.setText(String.valueOf(movie.getMovieID()));
        viewHolder.movieName.setText(movie.getMovieName());
        viewHolder.movieDescription.setText(String.valueOf(movie.getMovieDescription()));
        viewHolder.movieRatingBar.setRating(movie.getMovieRating());
        viewHolder.movieRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setRating(rating);
                Toast.makeText(ratingBar.getContext(), "Rating Changed", Toast.LENGTH_LONG).show();
            }
        });


        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieList.remove(viewHolder.getAdapterPosition());
                Toast.makeText(v.getContext(), "Record Deleted!"
                        , Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {

        return this.movieList.size();
    }
}
