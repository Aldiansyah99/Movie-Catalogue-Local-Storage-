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
import id.aldiansyah.moviecataloguelocalstorage.model.ResultTvShow;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.tvShowViewHolder> {
    private Context context;
    private ArrayList<ResultTvShow> mData = new ArrayList<>();

    public TvShowAdapter(Context context, ArrayList<ResultTvShow> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public tvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_show, parent, false);
        return new tvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tvShowViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class tvShowViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvVoteAverage;
        ImageView imgPoster;
        tvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_tvShow);
            tvVoteAverage = itemView.findViewById(R.id.tv_vote_average_tvShow);
            imgPoster = itemView.findViewById(R.id.img_poster_tvShow);
        }

        void bind(ResultTvShow tvShow) {
            String vote_average = Double.toString(tvShow.getVote_average());

            tvName.setText(tvShow.getName());
            tvVoteAverage.setText(vote_average);
            Glide.with(context)
                    .load(BuildConfig.URL_IMAGE_POSTER + tvShow.getPoster())
                    .into(imgPoster);
        }
    }
}
