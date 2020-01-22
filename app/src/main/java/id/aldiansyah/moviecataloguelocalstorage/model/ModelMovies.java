package id.aldiansyah.moviecataloguelocalstorage.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelMovies {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int total_results;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("results")
    private ArrayList<ResultMovies> resultMovies;

    public int getPage() {
        return page;
    }

    public ArrayList<ResultMovies> getResultMovies() {
        return resultMovies;
    }
}
