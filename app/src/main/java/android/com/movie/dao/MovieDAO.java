package android.com.movie.dao;

import android.com.movie.model.Movie;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/**
 * Created by Jeysson Paiva on 23/12/2014.
 */
public class MovieDAO {
    private SQLiteDatabase db;

    public MovieDAO(Context ctx)
    {
        BaseDAO conn = new BaseDAO(ctx);
        db = conn.getWritableDatabase();
    }

    public void insert(Movie movie){
        ContentValues value = new ContentValues();
        value.put("title", movie.getTitle());
        value.put("year", movie.getYear());
        value.put("rated", movie.getRated());
        value.put("released", movie.getReleased());
        value.put("runtime", movie.getRuntime());
        value.put("genre", movie.getGenre());
        value.put("director", movie.getDirector());
        value.put("writer", movie.getWriter());
        value.put("actors", movie.getActors());
        value.put("plot", movie.getPlot());
        value.put("language", movie.getLanguage());
        value.put("country", movie.getCountry());
        value.put("awards", movie.getAwards());
        value.put("poster", movie.getPoster());
        value.put("metascore", movie.getMetascore());
        value.put("imdbRating", movie.getImdbRating());
        value.put("imdbVotes", movie.getImdbVotes());
        value.put("imdbID", movie.getImdbID());
        value.put("type", movie.getType());
        value.put("arrayPoster", movie.getArrayPoster());
        //
        db.insert("TB_MOVIE", null, value);
    }

    public ArrayList<Movie> find(){
        ArrayList<Movie> moviesList = new ArrayList<Movie>();
        String[] colunas = new String[]{"title",     "year",  "rated", "released",   "runtime",
                                        "genre", "director", "writer",   "actors",      "plot",
                                     "language",  "country", "awards",   "poster", "metascore",
                                   "imdbRating", "imdbVotes", "imdbID",    "type", "arrayPoster" };
        //
        Cursor cursor = db.query("TB_MOVIE", colunas, null, null, null, null, "year DESC,title ASC");
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Movie movie = new Movie();
                movie.setTitle(cursor.getString(0));
                movie.setYear(cursor.getString(1));
                movie.setRated(cursor.getString(2));
                movie.setReleased(cursor.getString(3));
                movie.setRuntime(cursor.getString(4));
                movie.setGenre(cursor.getString(5));
                movie.setDirector(cursor.getString(6));
                movie.setWriter(cursor.getString(7));
                movie.setActors(cursor.getString(8));
                movie.setPlot(cursor.getString(9));
                movie.setLanguage(cursor.getString(10));
                movie.setCountry(cursor.getString(11));
                movie.setAwards(cursor.getString(12));
                movie.setPoster(cursor.getString(13));
                movie.setMetascore(cursor.getString(14));
                movie.setImdbRating(cursor.getString(15));
                movie.setImdbVotes(cursor.getString(16));
                movie.setImdbID(cursor.getString(17));
                movie.setType(cursor.getString(18));
                movie.setArrayPoster(cursor.getBlob(19));
                moviesList.add(movie);
            }while (cursor.moveToNext());
        }
        //
        return moviesList;
    }

    public void delete(){
            db.delete("TB_MOVIE", null, null);
    }

}
