package id.aldiansyah.moviecataloguelocalstorage.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
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
import id.aldiansyah.moviecataloguelocalstorage.helper.database.TvShowDbHelper;
import id.aldiansyah.moviecataloguelocalstorage.model.ResultTvShow;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private TextView tvName, tvVoteAverage, tvFirstAirDate, tvOverview, tvVoteCount, tvPopularity;
    private ImageView imgPoster, imgBackdrop;
    private ProgressBar progressBar;
    private ShineButton btn_fav;
    private ResultTvShow resultTvShow;
    private TvShowDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);
        Toolbar toolbar = findViewById(R.id.toolbar_tvShow_detail);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Tv Show Detail");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        init();

        resultTvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        progressBar.setVisibility(View.VISIBLE);

        showDetail();

        db = Room.databaseBuilder(this, TvShowDbHelper.class, "tvshowdb").allowMainThreadQueries().build();

        if (db.tvShowDAO().selectDetailTvShow(String.valueOf(resultTvShow.getId())) != null) {
            btn_fav.setChecked(true);
        } else {
            btn_fav.setChecked(false);
        }

        btn_fav.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                if (checked) {
                    insertTvShow(resultTvShow);
                } else {
                    db.tvShowDAO().deleteTvShow(resultTvShow);
                    btn_fav.setEnabled(false);
                    Toast.makeText(getApplicationContext(), R.string.text_delete_succsessfully, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void init() {
        tvName = findViewById(R.id.tv_name_tvShow_detail);
        tvVoteAverage = findViewById(R.id.tv_vote_average_tvShow_detail);
        tvVoteCount = findViewById(R.id.tv_vote_count_tvShow_detail);
        tvFirstAirDate = findViewById(R.id.tv_first_air_date_tvShow_detail);
        tvPopularity = findViewById(R.id.tv_popularity_tvShow_detail);
        tvOverview = findViewById(R.id.tv_overview_tvShow_detail);
        imgPoster = findViewById(R.id.img_poster_tvShow_detail);
        imgBackdrop = findViewById(R.id.img_backdrop_tvShow_detail);
        progressBar = findViewById(R.id.progressBar_tvShow_detail);
        btn_fav = findViewById(R.id.btn_fav_tvShow);
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
                        String vote_average = Double.toString(resultTvShow.getVote_average());
                        String vote_count = Integer.toString(resultTvShow.getVote_count());

                        tvName.setText(resultTvShow.getName());
                        tvVoteAverage.setText(vote_average);
                        tvVoteCount.setText(vote_count);
                        tvFirstAirDate.setText(resultTvShow.getFirst_air_date());
                        tvOverview.setText(resultTvShow.getOverview());
                        tvPopularity.setText(resultTvShow.getPopularity());
                        Glide.with(getApplicationContext())
                                .load(BuildConfig.URL_IMAGE_POSTER + resultTvShow.getPoster())
                                .into(imgPoster);
                        Glide.with(getApplicationContext())
                                .load(BuildConfig.URL_IMAGE_BACKDROP + resultTvShow.getBackdrop())
                                .into(imgBackdrop);
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        }).start();
    }

    private void insertTvShow(final ResultTvShow tvShow) {
        db.tvShowDAO().insertTvShow(tvShow);
        Toast.makeText(TvShowDetailActivity.this, R.string.text_add_success, Toast.LENGTH_SHORT).show();
    }
}
