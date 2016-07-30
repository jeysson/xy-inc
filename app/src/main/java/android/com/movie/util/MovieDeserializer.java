package android.com.movie.util;

import android.com.movie.model.Movie;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Jeysson Paiva on 29/07/2016.
 */

public class MovieDeserializer implements JsonDeserializer<Movie> {
    @Override
    public Movie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //
        Movie movie = new Movie();
        //
        final JsonObject jsonObject = json.getAsJsonObject();
        //
        movie.setActors(jsonObject.get("Actors").getAsString());
        movie.setAwards(jsonObject.get("Awards").getAsString());
        movie.setCountry(jsonObject.get("Country").getAsString());
        movie.setDirector(jsonObject.get("Director").getAsString());
        movie.setGenre(jsonObject.get("Genre").getAsString());
        movie.setImdbID(jsonObject.get("imdbID").getAsString());
        movie.setImdbRating(jsonObject.get("imdbRating").getAsString());
        movie.setImdbVotes(jsonObject.get("imdbVotes").getAsString());
        movie.setLanguage(jsonObject.get("Language").getAsString());
        movie.setMetascore(jsonObject.get("Metascore").getAsString());
        movie.setPlot(jsonObject.get("Plot").getAsString());
        movie.setPoster(jsonObject.get("Poster").getAsString());
        movie.setRated(jsonObject.get("Rated").getAsString());
        movie.setReleased(jsonObject.get("Released").getAsString());
        movie.setRuntime(jsonObject.get("Runtime").getAsString());
        movie.setTitle(jsonObject.get("Title").getAsString());
        movie.setType(jsonObject.get("Type").getAsString());
        movie.setWriter(jsonObject.get("Writer").getAsString());
        movie.setYear(jsonObject.get("Year").getAsString());
        //
        return movie;
    }
}
