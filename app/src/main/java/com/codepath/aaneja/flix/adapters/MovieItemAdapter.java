package com.codepath.aaneja.flix.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.aaneja.flix.R;
import com.codepath.aaneja.flix.models.MovieItem;
import com.codepath.aaneja.flix.services.MovieDbFacade;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by aaneja on 09/03/17.
 */

public class MovieItemAdapter extends ArrayAdapter<MovieItem> {

    private static final MovieDbFacade MOVIE_DB_FACADE = new MovieDbFacade();

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView image;
    }

    public MovieItemAdapter(Context context, int resource, List<MovieItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MovieItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_movie_item, parent, false);

            TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            ImageView ivMovie = (ImageView) convertView.findViewById(R.id.ivMovie);

            // Populate the data into the template view using the data object
            tvTitle.setText(item.Title);
            tvOverview.setText(item.Overview);
            setImageView(ivMovie, item, getContext() );

            ViewHolder toCache = new ViewHolder();
            toCache.title = tvTitle;
            toCache.overview = tvOverview;
            toCache.image = ivMovie;
            convertView.setTag(toCache);
        }
        else {
            ViewHolder cached = (ViewHolder) convertView.getTag();
            cached.title.setText(item.Title);
            cached.overview.setText(item.Overview);
            setImageView(cached.image,item, getContext());
        }

        // Return the completed view to render on screen
        return convertView;
    }

    private void setImageView(ImageView ivMovie, MovieItem item, Context context) {
        String widthToUse = MovieDbFacade.potraitPosterWidth;
        String imagePath = item.PosterPath;
        switch (context.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                widthToUse = MovieDbFacade.landscapeBackdropWidth;
                imagePath = item.BackDropPath;
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                widthToUse = MovieDbFacade.potraitPosterWidth;
                imagePath = item.PosterPath;
                break;
        }

        String imageUri = MOVIE_DB_FACADE.GetImageFullUrl(widthToUse,imagePath);
        Picasso.with(context).load(imageUri).fit().centerCrop().into(ivMovie);
    }
}
