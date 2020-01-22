package id.aldiansyah.moviecataloguelocalstorage.helper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import id.aldiansyah.moviecataloguelocalstorage.helper.dao.MoviesDAO;
import id.aldiansyah.moviecataloguelocalstorage.model.ResultMovies;

@Database(entities = {ResultMovies.class}, version = 1)
public abstract class MoviesDbHelper extends RoomDatabase {

    public abstract MoviesDAO moviesDAO();

}
