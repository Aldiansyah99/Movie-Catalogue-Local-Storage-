package id.aldiansyah.moviecataloguelocalstorage.helper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.aldiansyah.moviecataloguelocalstorage.model.ResultMovies;

@Dao
public interface MoviesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMovies(ResultMovies movies);

    @Query("SELECT * FROM tbMovies")
    List<ResultMovies> showAllMovies();

    @Delete
    int deleteMovies(ResultMovies movies);

    @Query("SELECT * FROM tbMovies WHERE id = :id LIMIT 1")
    ResultMovies selectDetailMovies(String id);
}
