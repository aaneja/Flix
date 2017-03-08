package com.codepath.aaneja.flix.models;


public class MovieItem {

    public int id;
    public String Title;
    public String Overview;

    public MovieItem(int id, String title, String overview) {
        this.id = id;
        Title = title;
        Overview = overview;
    }
}
