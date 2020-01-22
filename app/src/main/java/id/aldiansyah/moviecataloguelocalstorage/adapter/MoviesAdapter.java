package id.aldiansyah.moviecataloguelocalstorage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.aldiansyah.moviecataloguelocalstorage.BuildConfig;
import id.aldiansyah.moviecataloguelocalstorage.R;
import id.aldiansyah.moviecataloguelocalstorage.model.ResultMovies;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private Context context;
    private ArrayList<ResultMovies> mData = new ArrayList<>();

    public MoviesAdapter(Context context, ArrayList<ResultMovies> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvVoteAverage;
        ImageView imgPoster;
        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_movies);
            tvVoteAverage = itemView.findViewById(R.id.tv_vote_average_movies);
            imgPoster = itemView.findViewById(R.id.img_poster_movies);
        }

        void bind(ResultMovies movies) {
            String vote_average = Double.toString(movies.getVote_average());

            tvTitle.setText(movies.getTitle());
            tvVoteAverage.setText(vote_average);
            Glide.with(context)
                    .load(BuildConfig.URL_IMAGE_POSTER + movies.getPoster())
                    .into(imgPoster);
        }
    }
}
