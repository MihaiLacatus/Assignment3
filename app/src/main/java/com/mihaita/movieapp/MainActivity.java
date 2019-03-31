package com.mihaita.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private List<MovieBean> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loading records from the data base on application boot
        RecyclerView recycle = findViewById(R.id.recycle1);
        movieAdapter = new MovieAdapter(movieList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycle.setLayoutManager(layoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        DBClass db = new DBClass(this);
        movieList.addAll(db.getMoviesActive());
        recycle.setAdapter(movieAdapter);


        Button btnAdd = findViewById(R.id.btn_addMovie);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), AddMovieActivity.class);
                startActivityForResult(i, 1);
            }
        });

        //setting a listener for the toggle button
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //clearing the list when toggling
                movieList.clear();

                RecyclerView recycle = findViewById(R.id.recycle1);
                DBClass db = new DBClass(getApplicationContext());


                if (isChecked) {
                    movieList.addAll(db.getMoviesInactive());
                    recycle.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();
                } else {
                    movieList.addAll(db.getMoviesActive());
                    recycle.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //loading all records with the recent added one
            RecyclerView recycle = findViewById(R.id.recycle1);
            movieAdapter = new MovieAdapter(movieList);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recycle.setLayoutManager(layoutManager);
            recycle.setItemAnimator(new DefaultItemAnimator());
            recycle.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            DBClass db = new DBClass(this);
            movieList.addAll(db.getMoviesActive());
            recycle.setAdapter(movieAdapter);


            Toast.makeText(getApplicationContext(), "Record Added!"
                    , Toast.LENGTH_LONG).show();
            movieAdapter.notifyDataSetChanged();
        }

        if (requestCode == 1 && resultCode == RESULT_CANCELED) {

            Toast.makeText(getApplicationContext(), "Cancelled!"
                    , Toast.LENGTH_LONG).show();

            movieAdapter.notifyDataSetChanged();
        }
    }
}
