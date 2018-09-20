package com.example.admin.musicplayer.screen.genres;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.admin.musicplayer.R;
import com.example.admin.musicplayer.data.model.Track;
import com.example.admin.musicplayer.databinding.ItemLoadingBinding;
import com.example.admin.musicplayer.databinding.ItemTrackSearchBinding;
import com.example.admin.musicplayer.screen.HandlerClick;
import com.example.admin.musicplayer.screen.TrackClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 9/16/2018.
 */

public class TrackSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List mTracks;
    private TrackClickListener mListener;
    private HandlerClick mHandlerClick;

    public TrackSearchAdapter(TrackClickListener listener,
                              HandlerClick handlerClick) {
        mListener = listener;
        mHandlerClick = handlerClick;
        mTracks = new ArrayList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            ItemTrackSearchBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_track_search, parent, false);
            binding.setListener(mListener);
            binding.setHandler(mHandlerClick);
            return new TrackViewHolder(binding);
        }
        ItemLoadingBinding itemLoadingBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_loading, parent, false);
        return new ProgressViewHolder(itemLoadingBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_ITEM) {
            ((TrackViewHolder) holder).setTrackBinding((Track) mTracks.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mTracks.get(position) instanceof Track ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public void setData(ArrayList<Track> tracks) {
        if (tracks == null) return;
        mTracks.clear();
        addData(tracks);
    }

    void addData(ArrayList<Track> tracks) {
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    void addLoadingIndicator() {
        mTracks.add(VIEW_PROG);
        notifyItemInserted(mTracks.size() - 1);
    }

    void removeLoadingIndicator() {
        int index = mTracks.indexOf(VIEW_PROG);
        if (index == -1) {
            return;
        }
        mTracks.remove(index);
        notifyItemRemoved(index);
    }

    static class TrackViewHolder extends RecyclerView.ViewHolder {
        private ItemTrackSearchBinding mTrackBinding;

        TrackViewHolder(ItemTrackSearchBinding binding) {
            super(binding.getRoot());
            mTrackBinding = binding;
        }

        void setTrackBinding(Track track) {
            mTrackBinding.setTrack(track);
            mTrackBinding.executePendingBindings();
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        ProgressViewHolder(ItemLoadingBinding itemLoadingBinding) {
            super(itemLoadingBinding.getRoot());
        }
    }
}
