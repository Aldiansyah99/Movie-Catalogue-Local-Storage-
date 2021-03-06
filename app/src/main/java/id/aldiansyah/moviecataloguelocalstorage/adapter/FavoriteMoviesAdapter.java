package id.aldiansyah.moviecataloguelocalstorage.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.util.List;

import id.aldiansyah.moviecataloguelocalstorage.BuildConfig;
import id.aldiansyah.moviecataloguelocalstorage.R;
import id.aldiansyah.moviecataloguelocalstorage.detail.MoviesDetailActivity;
import id.aldiansyah.moviecataloguelocalstorage.helper.database.MoviesDbHelper;
import id.aldiansyah.moviecataloguelocalstorage.model.ResultMovies;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.FavMoviesViewHolder> {
    private List<ResultMovies> favMovies;
    private Context context;
    private MoviesDbHelper db;

    public FavoriteMoviesAdapter(Context context, List<ResultMovies> favMovies) {
        this.favMovies = favMovies;
        this.context = context;
    }

    @NonNull
    @Override
    public FavMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new FavMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMoviesViewHolder holder, final int position) {
        holder.bind(favMovies.get(position));

    }

    @Override
    public int getItemCount() {
        return favMovies.size();
    }

    class FavMoviesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvVoteAverage;
        ImageView imgPoster;

        FavMoviesViewHolder(@NonNull View itemView) {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final int position = getAdapterPosition();
                    final PopupMenu menu = new PopupMenu(context, v);
                    menu.getMenuInflater().inflate(R.menu.popup_menu, menu.getMenu());
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()) {
                                case R.id.action_detail:
                                    showDetailMovies(position);
                                    break;

                                case R.id.action_delete:
                                    deleteMovies(position, v);
                                    break;
                            }
                            return true;
                        }
                    });
                    menu.show();
                }
            });
        }
    }

    private void showDetailMovies(int position) {
        Intent intent = new Intent(context, MoviesDetailActivity.class);
        try {
            ResultMovies resultMovies = new ResultMovies();
            resultMovies.setId(favMovies.get(position).getId());
            resultMovies.setTitle(favMovies.get(position).getTitle());
            resultMovies.setVote_average(favMovies.get(position).getVote_average());
            resultMovies.setRelease_date(favMovies.get(position).getRelease_date());
            resultMovies.setPopularity(favMovies.get(position).getPopularity());
            resultMovies.setOverview(favMovies.get(position).getOverview());
            resultMovies.setPoster(favMovies.get(position).getPoster());
            resultMovies.setBackdrop(favMovies.get(position).getBackdrop());
            intent.putExtra(MoviesDetailActivity.EXTRA_MOVIE, resultMovies);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteMovies(final int position, View v) {
        db = Room.databaseBuilder(context, MoviesDbHelper.class, "moviesdb").allowMainThreadQueries().build();

        final ResultMovies movies = favMovies.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Confirmation");
        builder.setMessage("Delete This Favorite Movies?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                db.moviesDAO().deleteMovies(movies);
                favMovies.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, favMovies.size());
                Toast.makeText(context, R.string.text_delete_succsessfully, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
