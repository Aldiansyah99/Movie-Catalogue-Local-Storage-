package id.aldiansyah.moviecataloguelocalstorage.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.Objects;

import id.aldiansyah.moviecataloguelocalstorage.BuildConfig;
import id.aldiansyah.moviecataloguelocalstorage.R;
import id.aldiansyah.moviecataloguelocalstorage.helper.database.MoviesDbHelper;
import id.aldiansyah.moviecataloguelocalstorage.model.ResultMovies;

public class MoviesDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private TextView tvTitle, tvVoteAverage, tvReleaseDate, tvOverview, tvPopularity;
    private ImageView imgPoster, imgBackdrop;
    private ProgressBar progressBar;
    private ShineButton btn_fav;
    private ResultMovies resultMovies;
    private MoviesDbHelper db;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        Toolbar toolbar = findViewById(R.id.toolbar_detail_movies);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Movies Detail");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        init();

        resultMovies = getIntent().getParcelableExtra(EXTRA_MOVIE);
        progressBar.setVisibility(View.VISIBLE);

        showDetail();

        db = Room.databaseBuilder(getApplicationContext(), MoviesDbHelper.class, "moviesdb").allowMainThreadQueries().build();

        if (db.moviesDAO().selectDetailMovies(String.valueOf(resultMovies.getId())) != null) {
            btn_fav.setChecked(true);
        } else {
            btn_fav.setChecked(false);
        }

        btn_fav.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (checked) {
                    insertMovies(resultMovies);
                } else {
                    db.moviesDAO().deleteMovies(resultMovies);
                    btn_fav.setEnabled(false);
                    Toast.makeText(getApplicationContext(), R.string.text_delete_succsessfully, Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            data = savedInstanceState.getString(EXTRA_MOVIE);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void init() {
        tvTitle = findViewById(R.id.tv_title_movies_detail);
        tvVoteAverage = findViewById(R.id.tv_vote_average_movies_detail);
        tvReleaseDate = findViewById(R.id.tv_release_movies_detail);
        tvPopularity = findViewById(R.id.tv_popularity_movies_detail);
        tvOverview = findViewById(R.id.tv_overview_movies_detail);
        imgPoster = findViewById(R.id.img_poster_movies_detail);
        imgBackdrop = findViewById(R.id.img_backdrop_movies_detail);
        progressBar = findViewById(R.id.progressBar_movies_detail);
        btn_fav = findViewById(R.id.btn_fav);
    }

    private void showDetail() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String vote_average = Double.toString(resultMovies.getVote_average());

                        tvTitle.setText(resultMovies.getTitle());
                        tvVoteAverage.setText(vote_average);
                        tvReleaseDate.setText(resultMovies.getRelease_date());
                        tvOverview.setText(resultMovies.getOverview());
                        tvPopularity.setText(resultMovies.getPopularity());
                        Glide.with(getApplicationContext())
                                .load(BuildConfig.URL_IMAGE_POSTER + resultMovies.getPoster())
                                .into(imgPoster);
                        Glide.with(getApplicationContext())
                                .load(BuildConfig.URL_IMAGE_BACKDROP + resultMovies.getBackdrop())
                                .into(imgBackdrop);
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        }).start();
    }

    private void insertMovies(final ResultMovies movies) {
        db.moviesDAO().insertMovies(movies);
        Toast.makeText(MoviesDetailActivity.this, R.string.text_add_success, Toast.LENGTH_SHORT).show();
    }
}
