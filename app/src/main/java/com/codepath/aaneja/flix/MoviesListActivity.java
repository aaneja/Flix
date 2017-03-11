package com.codepath.aaneja.flix;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.codepath.aaneja.flix.adapters.MovieItemAdapter;
import com.codepath.aaneja.flix.models.MovieItem;
import com.codepath.aaneja.flix.services.MovieDbFacade;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.codepath.aaneja.flix.R.id.lvItems;

public class MoviesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        ListView lvItems = (ListView) findViewById(R.id.lvItems);

        AsyncTask<Context,Integer,MovieItemAdapter> asyncTask = new AsyncTask<Context,Integer,MovieItemAdapter>() {
            @Override
            protected MovieItemAdapter doInBackground(Context... params) {
                Context caller = params[0];
                MovieDbFacade movieDbFacade = new MovieDbFacade();
                List<MovieItem> nowPlayingMovies = movieDbFacade.GetNowPlaying();
                return new MovieItemAdapter(caller, R.layout.activity_movies_list,nowPlayingMovies);
            }
        };


        try {
            AsyncTask<Context, Integer, MovieItemAdapter> task = asyncTask.execute(this);
            lvItems.setAdapter(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
