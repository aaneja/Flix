package com.codepath.aaneja.flix.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.aaneja.flix.R;
import com.codepath.aaneja.flix.models.MovieItem;

import java.util.List;

/**
 * Created by aaneja on 09/03/17.
 */

public class MovieItemAdapter extends ArrayAdapter<MovieItem> {

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView poster;
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

            // Lookup view for data population
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            // Populate the data into the template view using the data object
            tvTitle.setText(item.Title);
            tvOverview.setText(item.Overview);

            ViewHolder toCache = new ViewHolder();
            toCache.title = tvTitle;
            toCache.overview = tvOverview;
            convertView.setTag(toCache);
        }
        else {
            ViewHolder cached = (ViewHolder) convertView.getTag();
            cached.title.setText(item.Title);
            cached.overview.setText(item.Overview);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
