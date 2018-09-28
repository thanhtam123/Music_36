package com.example.admin.musicplayer.screen.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.source.local.config.sqlite.TrackDatabase;
import com.example.admin.musicplayer.data.source.local.TrackLocalDataSource;
import com.example.admin.musicplayer.data.source.remote.GenreRemoteDataSource;
import com.example.admin.musicplayer.data.source.repository.GenreRepository;
import com.example.admin.musicplayer.databinding.FragmentHomeBinding;
import com.example.admin.musicplayer.screen.HandlerClick;

import java.util.List;


/**
 * Created by TamTT on 9/15/2018.
 */

public class HomeFragment extends Fragment{

    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mBinding;
    private GenreAdapter mAdapter;
    private HandlerClick mHandlerClick;
    private GenreRepository mRepository;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(getActivity()).
                get(HomeViewModel.class);
        mRepository = GenreRepository.getInstance(
                GenreRemoteDataSource.getGenreRemoteDataSource(),
                TrackLocalDataSource.getInstance(
                        TrackDatabase.getInstance(getActivity()).trackDao()));
        mHomeViewModel.setGenreRepository(mRepository);
        mHandlerClick = new HandlerClick(getActivity());
        mAdapter = new GenreAdapter(getContext(), mHandlerClick);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
        mBinding.recyclerGenres.setAdapter(mAdapter);
        View view = mBinding.getRoot();
        mHomeViewModel.getAllGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(@Nullable List<Genre> genres) {
                mAdapter.setGenreList(genres);
            }
        });
        return view;
    }
}
