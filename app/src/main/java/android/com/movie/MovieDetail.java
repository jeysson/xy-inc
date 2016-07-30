package android.com.movie;

import android.com.movie.controller.MovieController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //
        MovieController movieController = new MovieController(getWindow().getDecorView());
        movieController.showDetail();
    }
}
