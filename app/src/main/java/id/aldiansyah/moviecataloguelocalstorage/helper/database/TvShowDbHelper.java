package id.aldiansyah.moviecataloguelocalstorage.helper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import id.aldiansyah.moviecataloguelocalstorage.helper.dao.TvShowDAO;
import id.aldiansyah.moviecataloguelocalstorage.model.ResultTvShow;

@Database(entities = {ResultTvShow.class}, version = 1)
public abstract class TvShowDbHelper extends RoomDatabase {

    public abstract TvShowDAO tvShowDAO();

}
