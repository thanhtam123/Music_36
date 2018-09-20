package com.example.admin.musicplayer.screen.genres;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.admin.musicplayer.BuildConfig;
import com.example.admin.musicplayer.Constants;
import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.databinding.ActivityGenreBinding;
import com.example.admin.musicplayer.screen.HandlerClick;
import com.example.admin.musicplayer.screen.TrackClickListener;

import java.util.ArrayList;

public class GenreActivity extends AppCompatActivity implements TrackClickListener {
    private static final int PER_PAGE = 10;
    private static final String EXTRA_URN = "extra_urn";
    private static String EXTRA_LIST_TRACK = "extra_list_track";
    private static String EXTRA_NAME_GENRE = "extra_name_genre";
    private static String BUNDLE_GENRE = "bundle_genre";
    private TrackSearchAdapter mAdapter;
    private GenreViewModel mGenreViewModel;
    private ArrayList<Track> mTracks;
    private String mUrn;
    private String mType;
    private int mOffset;
    private EndlessRecyclerViewScrollListener mListener;

    public static Intent getGenreIntent(Context context, Genre genre) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_LIST_TRACK,
                (ArrayList<? extends Parcelable>) genre.getListTrackFromGenre(genre));
        bundle.putString(EXTRA_NAME_GENRE, genre.getGenre());
        bundle.putString(EXTRA_URN, genre.getQueryUrn());
        Intent intent = new Intent(context, GenreActivity.class);
        intent.putExtra(BUNDLE_GENRE, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGenreViewModel = ViewModelProviders.of(GenreActivity.this)
                .get(GenreViewModel.class);
        getDataFromBundle();
        initGenresView();
    }

    private void getDataFromBundle() {
        Bundle bundle = getIntent().getBundleExtra(BUNDLE_GENRE);
        mTracks = bundle.getParcelableArrayList(EXTRA_LIST_TRACK);
        mType = bundle.getString(EXTRA_NAME_GENRE);
        mUrn = bundle.getString(EXTRA_URN);
    }

    public void initGenresView() {
        ActivityGenreBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_genre);
        HandlerClick handlerClick = new HandlerClick(this);
        mAdapter = new TrackSearchAdapter(this, handlerClick);
        mAdapter.setData(mTracks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mListener =
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore() {
                        loadData();
                    }
                };
        binding.recyclerGenre.setLayoutManager(linearLayoutManager);
        binding.recyclerGenre.setAdapter(mAdapter);
        binding.recyclerGenre.addOnScrollListener(mListener);

        mGenreViewModel.getGenre(Constants.KIND, mType, mOffset, mUrn, BuildConfig.ApiKey)
                .observe(this, new Observer<Genre>() {
                    @Override
                    public void onChanged(@Nullable Genre genre) {
                        mAdapter.removeLoadingIndicator();
                        if (genre == null) {
                            return;
                        }
                        mAdapter.addData((ArrayList<Track>) genre.getListTrackFromGenre(genre));
                    }
                });
    }

    private void loadData() {
        mAdapter.addLoadingIndicator();
        mOffset += PER_PAGE;
        mGenreViewModel.getGenre(Constants.KIND, mType, mOffset, mUrn, BuildConfig.ApiKey);
    }

    @Override
    public void onTrackClicked(Track track) {
        Toast.makeText(GenreActivity.this, track.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
