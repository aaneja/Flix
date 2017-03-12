package com.codepath.aaneja.flix.services;

import com.codepath.aaneja.flix.models.MovieItem;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by aaneja on 10/03/17.
 */

public class MovieDbFacade {

    public static final String potraitPosterWidth = "w154";
    public static final String landscapeBackdropWidth = "w300";
    private static final TmdbApi MOVIES = new TmdbApi("a07e22bc18f5cb106bfe4cc1f83ad8ed");
    public static final String BASE_URL = MOVIES.getConfiguration().getBaseUrl();

    public List<MovieItem> GetNowPlaying() {
        List<MovieItem> movieItems = new ArrayList<>();

        MovieResultsPage pageMovies = MOVIES.getMovies().getNowPlayingMovies("en", 1);

        List<MovieDb> results = pageMovies.getResults();
        for (MovieDb movie : results) {
            MovieItem temp = new MovieItem();
            temp.id = movie.getId();
            temp.Title = movie.getTitle();
            temp.Overview = movie.getOverview();
            temp.PosterPath = movie.getPosterPath();
            temp.BackDropPath = movie.getBackdropPath();
            movieItems.add(temp);
        }

        return movieItems;
    }

    public String GetImageFullUrl(String widthPrefix, String urlPath) {
        return String.format("%s/%s%s",BASE_URL,widthPrefix,urlPath);
    }

}
