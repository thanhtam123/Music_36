package com.example.admin.musicplayer.screen.home;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.databinding.ItemTrackBinding;
import com.example.admin.musicplayer.screen.TrackClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 9/14/2018.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackItemViewHolder> {

    private List<Track> mTracks = new ArrayList<>();
    private TrackClickListener mListener;

    TrackAdapter(TrackClickListener listener) {
        mListener = listener;
    }

    @Override
    public TrackItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTrackBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item_track, parent, false);
        binding.setListener(mListener);
        return new TrackItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TrackItemViewHolder holder, int position) {
        holder.setBinding(mTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    void setTrackList(final List<Track> tracks) {
        if (this.mTracks == null) {
            this.mTracks = tracks;
            notifyItemRangeInserted(0, mTracks.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return TrackAdapter.this.mTracks.size();
                }

                @Override
                public int getNewListSize() {
                    return mTracks.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return TrackAdapter.this.mTracks.get(oldItemPosition).getId() ==
                            mTracks.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Track track = mTracks.get(newItemPosition);
                    Track old = mTracks.get(oldItemPosition);
                    return track.getId() == old.getId();
                }
            });
            this.mTracks = tracks;
            result.dispatchUpdatesTo(this);
        }
    }

    static class TrackItemViewHolder extends RecyclerView.ViewHolder {
        private ItemTrackBinding mBinding;

        TrackItemViewHolder(ItemTrackBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void setBinding(Track track) {
            mBinding.setTrack(track);
            mBinding.executePendingBindings();
        }
    }
}
