package android.com.movie;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.com.movie.controller.MovieController;
import android.com.movie.model.Movie;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MovieDialog extends DialogFragment implements View.OnClickListener {
    private MovieController movieController;
    private Movie movie;
    private TextView txtSearch;
    private View view;
    private View mainView;

    public MovieDialog() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_moviedialog, null);
        movieController = new MovieController(view);
        //
        txtSearch = (TextView)view.findViewById(R.id.txtsearch);
        Button search = (Button)view.findViewById(R.id.btsearch);
        search.setOnClickListener(this);
        //
        builder.setTitle("Search Movie")
                .setView(view)
                .setPositiveButton(R.string.btsalve, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            salve();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                })
                //
                .setNegativeButton(R.string.btcancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MovieDialog.this.getDialog().cancel();
                    }
                });
        //
        return builder.create();
        //return
    }

    @Override
    public void onStart() {
        super.onStart();
        //
    }

    // save movie
    private void salve() throws InterruptedException {
        movie = movieController.getCurrentMovie();
        movieController.save(movie);
        Thread.sleep(1000);
        movieController.setView(mainView);
        movieController.getAll();
    }

    @Override
    public void onClick(View v) {

        movieController.searchMovie(txtSearch.getText().toString());
    }

    public void showDialog(android.support.v4.app.FragmentManager manager, View view, String info){
        this.mainView = view;
        show(manager, info);
    }
}
