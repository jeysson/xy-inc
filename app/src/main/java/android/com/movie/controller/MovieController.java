package android.com.movie.controller;

import android.app.Activity;
import android.com.movie.MainActivity;
import android.com.movie.MovieDetail;
import android.com.movie.R;
import android.com.movie.adapter.MovieAdapter;
import android.com.movie.dao.MovieDAO;
import android.com.movie.model.Movie;
import android.com.movie.util.Connection;
import android.com.movie.util.ConnectionListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jeysson Paiva on 29/07/2016.
 */

public class MovieController implements ConnectionListener, AdapterView.OnItemClickListener {
    private View view;
    private MovieDAO movieDAO;
    private static Movie currentMovie;
    private  MovieAdapter movieAdapter;

    public MovieController(View view){
        this.view = view;
        this.movieDAO = new MovieDAO(view.getContext());
    }

    public void searchMovie(String param){
        if(param.isEmpty())
            return;;
        //
        Connection conn = new Connection(view.getContext());
        conn.addListener(this);
        conn.execute(param);
    }

    // retorn all movies in local data base
    public void getAll(){
        ListView ls = (ListView)view.findViewById(R.id.lstMovies);
        ls.setOnItemClickListener(this);
        movieAdapter = new MovieAdapter(view.getContext(), movieDAO.find());
        ls.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    // Save the movies
    public void save(final Movie movie) throws InterruptedException {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Bitmap bitmap = null;
                    bitmap = BitmapFactory.decodeStream((InputStream)new URL(movie.getPoster()).getContent());
                    //
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    movie.setArrayPoster(stream.toByteArray());
                    movieDAO.insert(movie);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void delete(){
        movieDAO.delete();
    }

    @Override
    public void completeExecute(final Movie movie) {
        if(movie == null) return;

        this.currentMovie = movie;
        TextView title = (TextView)view.findViewById(R.id.txtTitle);
        TextView directors = (TextView)view.findViewById(R.id.txtDirector);
        TextView actors = (TextView)view.findViewById(R.id.txtActors);
        TextView resume = (TextView)view.findViewById(R.id.txtResume);
        RatingBar ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);
        final ImageView img = (ImageView) view.findViewById(R.id.imgPoster);
        //
        title.setText(movie.getTitle());
        directors.setText("Director: "+movie.getDirector());
        actors.setText("Actors: "+movie.getActors());
        resume.setText("Plot: "+movie.getPlot());
        try{
        ratingBar.setRating(Float.parseFloat(movie.getImdbRating())/2.0f);
        }catch (Exception e){ratingBar.setRating(0f);}
        final Movie mm = movie;
        //
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = null;
                        bitmap = BitmapFactory.decodeStream((InputStream)new URL(mm.getPoster()).getContent());
                        img.setImageBitmap(bitmap);
                        //
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        movie.setArrayPoster(stream.toByteArray());
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();

    }

    public Movie getCurrentMovie(){
        return this.currentMovie;
    }

    public void setView(View mainView) {
        this.view = mainView;
    }

    public void showDetail() {
        TextView title = (TextView)view.findViewById(R.id.txtTitle);
        TextView directors = (TextView)view.findViewById(R.id.txtDirector);
        TextView actors = (TextView)view.findViewById(R.id.txtActors);
        TextView plot = (TextView)view.findViewById(R.id.txtPlot);
        TextView writer = (TextView)view.findViewById(R.id.txtWriter);
        TextView country = (TextView)view.findViewById(R.id.txtcountry);
        TextView language = (TextView)view.findViewById(R.id.txtLanguage);
        TextView genre = (TextView)view.findViewById(R.id.txtgenre);
        TextView awards = (TextView)view.findViewById(R.id.txtAwards);
        final ImageView imgPoster = (ImageView) view.findViewById(R.id.imgPoster);
        RatingBar ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);
        //
        title.setText(currentMovie.getTitle());
        directors.setText("Directors: "+currentMovie.getDirector());
        actors.setText("Actors:  "+currentMovie.getActors());
        plot.setText("Plot: "+currentMovie.getPlot());
        writer.setText("Writers: "+currentMovie.getWriter());
        country.setText("Country: "+currentMovie.getCountry());
        language.setText("Language: "+currentMovie.getLanguage());
        genre.setText("Genre: "+currentMovie.getGenre());
        awards.setText("Aeards: "+currentMovie.getAwards());
        //
        try{
            ratingBar.setRating(Float.parseFloat(currentMovie.getImdbRating())/2.0f);
        }catch (Exception e){ratingBar.setRating(0f);}
        //
        if(currentMovie.getArrayPoster() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(currentMovie.getArrayPoster() , 0
                    , currentMovie.getArrayPoster().length);
            imgPoster.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.currentMovie = movieAdapter.getItem(position);
        Intent intent = new Intent(view.getContext(), MovieDetail.class);
        view.getContext().startActivity(intent);
    }
}
