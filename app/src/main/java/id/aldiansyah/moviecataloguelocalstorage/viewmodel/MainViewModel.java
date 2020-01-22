package id.aldiansyah.moviecataloguelocalstorage.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.aldiansyah.moviecataloguelocalstorage.model.ModelMovies;
import id.aldiansyah.moviecataloguelocalstorage.model.ModelTvShow;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ModelMovies> responseMovies;
    private MutableLiveData<ModelTvShow> responseTvShow;

    public void initializeMovies() {
        if (responseMovies != null) {
            return;
        }
        Movies movies = Movies.getInstance();
        responseMovies = movies.getMovies();
    }

    public LiveData<ModelMovies> getMoviesModel() {
        return responseMovies;
    }

    public void initializeTvShow() {
        if (responseTvShow != null) {
            return;
        }
        TvShow tvShow = TvShow.getInstance();
        responseTvShow = tvShow.getTvShow();
    }

    public LiveData<ModelTvShow> getTvShowModel() {
        return responseTvShow;
    }
}
