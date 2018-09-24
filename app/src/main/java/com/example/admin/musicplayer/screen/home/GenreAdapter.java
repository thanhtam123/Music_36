package com.example.admin.musicplayer.screen.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.Genre;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.databinding.ItemGenreBinding;
import com.example.admin.musicplayer.screen.HandlerClick;
import com.example.admin.musicplayer.screen.TrackClickListener;
import com.example.admin.musicplayer.screen.player.MusicService;
import com.example.admin.musicplayer.screen.player.PlayerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 9/15/2018.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>{

    private List<Genre> mGenres;
    private HandlerClick mHandlerClick;
    private Context mContext;

    GenreAdapter(Context context, HandlerClick handlerClick) {
        mContext = context;
        mHandlerClick = handlerClick;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGenreBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_genre, parent, false);
        binding.setListener(mHandlerClick);
        return new GenreViewHolder(mContext, binding);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        holder.setBinding(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    void setGenreList(final List<Genre> genres) {
        if (mGenres == null) {
            mGenres = genres;
            notifyItemRangeInserted(0, mGenres.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return GenreAdapter.this.mGenres.size();
                }

                @Override
                public int getNewListSize() {
                    return mGenres.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return GenreAdapter.this.mGenres.get(oldItemPosition).getGenre() ==
                            mGenres.get(newItemPosition).getGenre();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Genre genre = mGenres.get(newItemPosition);
                    Genre old = mGenres.get(oldItemPosition);
                    return genre.getGenre() == old.getGenre();
                }
            });
            this.mGenres = genres;
            result.dispatchUpdatesTo(this);
        }
    }

    static class GenreViewHolder extends RecyclerView.ViewHolder implements TrackClickListener {
        private ItemGenreBinding mBinding;
        private TrackAdapter mAdapter;
        private Context mContext;
        private ArrayList<Track> mTracks;
        GenreViewHolder(Context context, ItemGenreBinding binding) {
            super(binding.getRoot());
            mContext = context;
            mBinding = binding;
        }

        void setBinding(Genre genre){
            mTracks = (ArrayList<Track>) genre.getListTrackFromGenre(genre);
            mBinding.setGenre(genre);
            mAdapter = new TrackAdapter(this);
            mAdapter.setTrackList(mTracks);
            mBinding.recyclerTrack.setAdapter(mAdapter);
            mBinding.executePendingBindings();
        }

        @Override
        public void onTrackClicked(Track track) {
            Intent intent = PlayerActivity.getPlayerIntent(mContext);
            mContext.startActivity(intent);
            Intent intentService = MusicService.getMusicServiceIntent(mContext,
                    mTracks.indexOf(track), mTracks);
            mContext.startService(intentService);
        }
    }
}
