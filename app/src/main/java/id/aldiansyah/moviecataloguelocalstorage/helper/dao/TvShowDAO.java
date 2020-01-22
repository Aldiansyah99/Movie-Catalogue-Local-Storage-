package id.aldiansyah.moviecataloguelocalstorage.helper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.aldiansyah.moviecataloguelocalstorage.model.ResultTvShow;

@Dao
public interface TvShowDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTvShow(ResultTvShow tvShow);

    @Query("SELECT * FROM tbTvShow")
    List<ResultTvShow> showAllTvShow();

    @Delete
    int deleteTvShow(ResultTvShow tvShow);

    @Query("SELECT * FROM tbTvShow WHERE id = :id LIMIT 1")
    ResultTvShow selectDetailTvShow(String id);
}
