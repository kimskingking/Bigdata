package com.example.ex12_06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    Button btn;

    LinearLayoutManager linearLayoutManager;
    MovieAdapter movieAdapter;
    List<Movie> list;

    private MovieInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        recyclerview = findViewById(R.id.recyclerview);

        list = new ArrayList<>();
        movieAdapter = new MovieAdapter(list);

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(linearLayoutManager);

        movieAdapter = new MovieAdapter(list);
        recyclerview.setAdapter(movieAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = MovieClient.getClient().create(MovieInterface.class);
                Call<List<Movie>> call = apiInterface.doGetMovie();

                call.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        Log.d("code",response.code()+"");
                        Log.d("data",response.toString()+"");
                        List<Movie> resource = response.body();
                        Log.d("count",resource.size()+"");

                        for(Movie movie:resource){
                            list.add(movie);
                        }

                        movieAdapter.notifyDataSetChanged();
                        Log.d("count22",movieAdapter.getItemCount()+"");
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });

            }
        });

    }
}