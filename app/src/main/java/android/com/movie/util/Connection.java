package android.com.movie.util;

import android.app.ProgressDialog;
import android.com.movie.model.Movie;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Connection extends AsyncTask<String, String, Movie> {
    private List<ConnectionListener> listeners = new ArrayList<ConnectionListener>();
    private Context context;
    private Movie movie;

    public Connection(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Movie doInBackground(String... params) {
        try{
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            //URL
            String urlPath = "http://www.omdbapi.com/?t={0}&y=&plot=short&r=json";
            //params
            String param = params[0].trim().replace(' ', '+');
            //set param
            urlPath = urlPath.replace("{0}", param);
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urlPath, ServiceHandler.GET);

            if (jsonStr != null) {
                if(!jsonStr.contains("Movie not found!")){
                    final GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.registerTypeAdapter(Movie.class, new MovieDeserializer());
                    Gson gson = gsonBuilder.create();
                    //
                    JsonElement element = gson.fromJson (jsonStr, JsonElement.class);
                    movie = gson.fromJson(element, Movie.class);
                }
            }

        }catch (Exception e){

        }
        //
        return  movie;
    }

    @Override
    protected void onPostExecute(Movie result) {
        super.onPostExecute(result);

        // Notify everybody that may be interested.
        for (ConnectionListener connEvent : listeners)
            try {
                connEvent.completeExecute(result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    }

    public void addListener(ConnectionListener connectionListenerToAdd) {
        listeners.add(connectionListenerToAdd);
    }
}
