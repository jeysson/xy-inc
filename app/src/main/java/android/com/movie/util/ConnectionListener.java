package android.com.movie.util;

import android.com.movie.model.Movie;

import java.net.MalformedURLException;

/**
 * Created by Jeysson Paiva on 29/07/2016.
 */
public interface ConnectionListener {
    void completeExecute(Movie movie) throws MalformedURLException;
}
