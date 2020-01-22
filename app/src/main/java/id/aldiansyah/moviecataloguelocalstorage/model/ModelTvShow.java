package id.aldiansyah.moviecataloguelocalstorage.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelTvShow {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int total_results;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("results")
    private ArrayList<ResultTvShow> resultTvShow;

    public int getPage() {
        return page;
    }

    public ArrayList<ResultTvShow> getResultTvShow() {
        return resultTvShow;
    }
}
