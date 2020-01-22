package id.aldiansyah.moviecataloguelocalstorage.rest;

import java.util.concurrent.TimeUnit;

import id.aldiansyah.moviecataloguelocalstorage.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static ApiService getInstanceRetrofit() {
        return getClient().create(ApiService.class);
    }
}
