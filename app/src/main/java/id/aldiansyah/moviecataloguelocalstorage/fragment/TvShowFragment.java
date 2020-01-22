package id.aldiansyah.moviecataloguelocalstorage.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import id.aldiansyah.moviecataloguelocalstorage.ItemClickSupport;
import id.aldiansyah.moviecataloguelocalstorage.R;
import id.aldiansyah.moviecataloguelocalstorage.adapter.TvShowAdapter;
import id.aldiansyah.moviecataloguelocalstorage.detail.TvShowDetailActivity;
import id.aldiansyah.moviecataloguelocalstorage.model.ModelTvShow;
import id.aldiansyah.moviecataloguelocalstorage.model.ResultTvShow;
import id.aldiansyah.moviecataloguelocalstorage.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TvShowAdapter adapter;
    private ArrayList<ResultTvShow> listTvShow = new ArrayList<>();

    public TvShowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_tvShow);
        progressBar = view.findViewById(R.id.progress_bar_tvShow);
        progressBar.showContextMenu();

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.initializeTvShow();
        viewModel.getTvShowModel().observe(getViewLifecycleOwner(), new Observer<ModelTvShow>() {
            @Override
            public void onChanged(ModelTvShow modelTvShow) {
                showLoading(false);
                ArrayList<ResultTvShow> resultTvShow = modelTvShow.getResultTvShow();
                listTvShow.addAll(resultTvShow);
                adapter.notifyDataSetChanged();
            }
        });

        showRecyclerTvShow();
        clickMovies();
    }

    private void showRecyclerTvShow() {
        if (adapter == null) {
            adapter = new TvShowAdapter(getActivity(), listTvShow);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void clickMovies() {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnitemClickListener() {
            @Override
            public void onitemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
                try {
                    ResultTvShow resultTvShow = new ResultTvShow();
                    resultTvShow.setId(listTvShow.get(position).getId());
                    resultTvShow.setName(listTvShow.get(position).getName());
                    resultTvShow.setVote_average(listTvShow.get(position).getVote_average());
                    resultTvShow.setVote_count(listTvShow.get(position).getVote_count());
                    resultTvShow.setFirst_air_date(listTvShow.get(position).getFirst_air_date());
                    resultTvShow.setPopularity(listTvShow.get(position).getPopularity());
                    resultTvShow.setOverview(listTvShow.get(position).getOverview());
                    resultTvShow.setPoster(listTvShow.get(position).getPoster());
                    resultTvShow.setBackdrop(listTvShow.get(position).getBackdrop());
                    intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, resultTvShow);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
