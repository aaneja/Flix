package com.codepath.aaneja.flix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.codepath.aaneja.flix.adapters.MovieItemAdapter;
import com.codepath.aaneja.flix.services.MovieDbFacade;

import static com.codepath.aaneja.flix.R.id.lvItems;

public class MoviesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        //Fetch the movies
        MovieDbFacade movieDbFacade = new MovieDbFacade();
        ListView lvItems = (ListView) findViewById(R.id.lvItems);
        MovieItemAdapter movieItemAdapter = new MovieItemAdapter(this, R.layout.activity_movies_list, movieDbFacade.GetNowPlaying());
        lvItems.setAdapter(movieItemAdapter);

    }

}
