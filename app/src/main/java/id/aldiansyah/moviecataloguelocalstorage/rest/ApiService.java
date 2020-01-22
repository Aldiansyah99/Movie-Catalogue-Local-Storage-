package id.aldiansyah.moviecataloguelocalstorage.rest;

import id.aldiansyah.moviecataloguelocalstorage.BuildConfig;
import id.aldiansyah.moviecataloguelocalstorage.model.ModelMovies;
import id.aldiansyah.moviecataloguelocalstorage.model.ModelTvShow;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("movie?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<ModelMovies> getDataMovie();

    @GET("tv?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<ModelTvShow> getDataTvShow();

}
