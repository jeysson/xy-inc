package android.com.movie.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeysson Paiva on 22/12/2014.
 */
public class BaseDAO extends SQLiteOpenHelper {
    private static final String NOME = "Movies";
    private static final int VERSAO = 1;

    public BaseDAO(Context ctx)
    {
        super(ctx, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TB_MOVIE( title text " +
                                          ", year text" +
                ", rated text, released text, runtime text" +
                ", genre text, director text, writer text" +
                ", actors text, plot text, language text" +
                ", country text, awards text, poster text" +
                ", metascore text, imdbRating text, imdbVotes text" +
                ", imdbID text, type text, arrayPoster blob); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE TB_MOVIE;");
        onCreate(db);
    }
}
