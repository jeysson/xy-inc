package android.com.movie.adapter;

import android.com.movie.R;
import android.com.movie.model.Movie;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;

import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Jeysson Paiva on 16/06/2016.
 */
public class MovieAdapter extends BaseAdapter {
    ArrayList<Movie> movies;
    Context context;

    public MovieAdapter(Context context, ArrayList<Movie> objects){
        this.context = context;
        this.movies = objects;

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = movies.get(position);
        //
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.movie_item, null);
        //
        TextView title = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView director = (TextView)convertView.findViewById(R.id.txtDirector);
        TextView actors = (TextView) convertView.findViewById(R.id.txtActors);
        TextView year = (TextView)convertView.findViewById(R.id.txtyear);
        RatingBar rating = (RatingBar) convertView.findViewById(R.id.ratingBar);
        ImageView img = (ImageView)  convertView.findViewById(R.id.imgPoster);
        //
        title.setText(movie.getTitle());
        director.setText("Directors: "+movie.getDirector());
        actors.setText("Actors: "+movie.getActors());
        year.setText("Year: "+movie.getYear());
        rating.setRating(Float.parseFloat(movie.getImdbRating())/2.0f);
        if(movie.getArrayPoster() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(movie.getArrayPoster() , 0, movie.getArrayPoster().length);
            img.setImageBitmap(bitmap);
        }
        //
        return convertView;
    }
}
