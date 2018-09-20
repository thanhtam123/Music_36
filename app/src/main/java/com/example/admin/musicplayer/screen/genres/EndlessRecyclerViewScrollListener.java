package com.example.admin.musicplayer.screen.genres;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by TamTT on 9/16/2018.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private boolean mLoading;
    private LinearLayoutManager mLinearLayoutManager;

    protected EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        assert mLinearLayoutManager != null;
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        int firstVisiableItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = mLinearLayoutManager.getChildCount();
        int visibleThreshold = 5;
        if (mLoading && totalItemCount > (lastVisibleItem + visibleThreshold)) {
            mLoading = false;
        }
        if (!mLoading
                && (visibleItemCount + firstVisiableItem) >= totalItemCount
                && firstVisiableItem >= 0
                && totalItemCount >= 10) {
            if (mLinearLayoutManager != null) {
                onLoadMore();
            }
            mLoading = true;
        }
    }

    public abstract void onLoadMore();
}
